终止线程池主要有两个方法：shutdown() 和 shutdownNow()。

转 http://www.cnblogs.com/trust-freedom/p/6693601.html#autoid-0-0-0

    shutdown()后线程池将变成shutdown状态，此时不接收新任务，但会处理完正在运行的 和 在阻塞队列中等待处理的任务。

    shutdownNow()后线程池将变成stop状态，此时不接收新任务，不再处理在阻塞队列中等待的任务，还会尝试中断正在处理中的工作线程。
# 一、shutdown()  --  温柔的终止线程池
```$xslt
/**
 * Initiates an orderly shutdown in which previously submitted
 * tasks are executed, but no new tasks will be accepted.
 * Invocation has no additional effect if already shut down.
 * 开始一个有序的关闭，在关闭中，之前提交的任务会被执行（包含正在执行的，在阻塞队列中的），但新任务会被拒绝
 * 如果线程池已经shutdown，调用此方法不会有附加效应
 *
 * <p>This method does not wait for previously submitted tasks to
 * complete execution.  Use {@link #awaitTermination awaitTermination}
 * to do that.
 * 当前方法不会等待之前提交的任务执行结束，可以使用awaitTermination()
 *
 * @throws SecurityException {@inheritDoc}
 */
public void shutdown() {
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock(); //上锁
     
    try {
        //判断调用者是否有权限shutdown线程池
        checkShutdownAccess();
         
        //CAS+循环设置线程池状态为shutdown
        advanceRunState(SHUTDOWN);
         
        //中断所有空闲线程
        interruptIdleWorkers();
         
        onShutdown(); // hook for ScheduledThreadPoolExecutor
    } 
    finally {
        mainLock.unlock(); //解锁
    }
     
    //尝试终止线程池
    tryTerminate();
}
```

shutdown()执行流程：

1、上锁，mainLock是线程池的主锁，是可重入锁，当要操作workers set这个保持线程的HashSet时，需要先获取mainLock，还有当要处理largestPoolSize、completedTaskCount这类统计数据时需要先获取mainLock

2、判断调用者是否有权限shutdown线程池

3、使用CAS操作将线程池状态设置为shutdown，shutdown之后将不再接收新任务

4、中断所有空闲线程  interruptIdleWorkers()

5、onShutdown()，ScheduledThreadPoolExecutor中实现了这个方法，可以在shutdown()时做一些处理

6、解锁

7、尝试终止线程池  tryTerminate()

 

可以看到shutdown()方法最重要的几个步骤是：更新线程池状态为shutdown、中断所有空闲线程、tryTerminated()尝试终止线程池

那么，什么是空闲线程？interruptIdleWorkers() 是怎么中断空闲线程的？

```$xslt
/**
 * Interrupts threads that might be waiting for tasks (as
 * indicated by not being locked) so they can check for
 * termination or configuration changes. Ignores
 * SecurityExceptions (in which case some threads may remain
 * uninterrupted).
 * 中断在等待任务的线程(没有上锁的)，中断唤醒后，可以判断线程池状态是否变化来决定是否继续
 *
 * @param onlyOne If true, interrupt at most one worker. This is
 * called only from tryTerminate when termination is otherwise
 * enabled but there are still other workers.  In this case, at
 * most one waiting worker is interrupted to propagate shutdown
 * signals in case(以免) all threads are currently waiting.
 * Interrupting any arbitrary thread ensures that newly arriving
 * workers since shutdown began will also eventually exit.
 * To guarantee eventual termination, it suffices to always
 * interrupt only one idle worker, but shutdown() interrupts all
 * idle workers so that redundant workers exit promptly, not
 * waiting for a straggler task to finish.
 * 
 * onlyOne如果为true，最多interrupt一个worker
 * 只有当终止流程已经开始，但线程池还有worker线程时,tryTerminate()方法会做调用onlyOne为true的调用
 * （终止流程已经开始指的是：shutdown状态 且 workQueue为空，或者 stop状态）
 * 在这种情况下，最多有一个worker被中断，为了传播shutdown信号，以免所有的线程都在等待
 * 为保证线程池最终能终止，这个操作总是中断一个空闲worker
 * 而shutdown()中断所有空闲worker，来保证空闲线程及时退出
 */
private void interruptIdleWorkers(boolean onlyOne) {
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock(); //上锁
    try {
        for (Worker w : workers) { 
            Thread t = w.thread;
             
            if (!t.isInterrupted() && w.tryLock()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                } finally {
                    w.unlock();
                }
            }
            if (onlyOne)
                break;
        }
    } finally {
        mainLock.unlock(); //解锁
    }
}
```

interruptIdleWorkers() 首先会获取mainLock锁，因为要迭代workers set，在中断每个worker前，需要做两个判断：

1、线程是否已经被中断，是就什么都不做

2、worker.tryLock() 是否成功

第二个判断比较重要，因为Worker类除了实现了可执行的Runnable，也继承了AQS，本身也是一把锁，具体可见 ThreadPoolExecutor内部类Worker解析

tryLock()调用了Worker自身实现的tryAcquire()方法，这也是AQS规定子类需要实现的尝试获取锁的方法

```$xslt
protected boolean tryAcquire(int unused) {
    if (compareAndSetState(0, 1)) { 
        setExclusiveOwnerThread(Thread.currentThread()); 
        return true;
    }
    return false;
}
```
tryAcquire()先尝试将AQS的state从0-->1，返回true代表上锁成功，并设置当前线程为锁的拥有者

可以看到compareAndSetState(0, 1)只尝试了一次获取锁，且不是每次state+1，而是0-->1，说明锁不是可重入的

 

但是为什么要worker.tryLock()获取worker的锁呢？

这就是Woker类存在的价值之一，控制线程中断

在runWorker()方法中每次获取到task，task.run()之前都需要worker.lock()上锁，运行结束后解锁，即正在运行任务的工作线程都是上了worker锁的
