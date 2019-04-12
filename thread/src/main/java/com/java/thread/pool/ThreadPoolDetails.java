package com.java.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池个参数含义
 * <p>
 * corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。在创建了线程池后，默认情况下，线程池中并没有任何线程，
 * 而是等待有任务到来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可
 * 以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，在创建了线程池后，线程池中的线程数为
 * 0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 * <p>
 * maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
 * <p>
 * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime
 * 才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，
 * 则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于
 * corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 * <p>
 * unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
 * TimeUnit.DAYS;               //天
 * TimeUnit.HOURS;             //小时
 * TimeUnit.MINUTES;           //分钟
 * TimeUnit.SECONDS;           //秒
 * TimeUnit.MILLISECONDS;      //毫秒
 * TimeUnit.MICROSECONDS;      //微妙
 * TimeUnit.NANOSECONDS;       //纳秒
 * <p>
 * workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择：
 * ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
 * ArrayBlockingQueue;
 * LinkedBlockingQueue;
 * SynchronousQueue;
 * <p>
 * threadFactory：线程工厂，主要用来创建线程；
 * <p>
 * handler：表示当拒绝处理任务时的策略，有以下四种取值：
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 * <p>
 * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);
 * <p>
 * 明白了ThreadPoolExecutor、AbstractExecutorService、ExecutorService和Executor几个之间的关系了。
 * 　　Executor是一个顶层接口，在它里面只声明了一个方法execute(Runnable)，返回值为void，参数为Runnable类型，从字面意思可以理解，就是用来执行传进去的任务的；
 * 　　然后ExecutorService接口继承了Executor接口，并声明了一些方法：submit、invokeAll、invokeAny以及shutDown等；
 * 　　抽象类AbstractExecutorService实现了ExecutorService接口，基本实现了ExecutorService中声明的所有方法；
 * 　　然后ThreadPoolExecutor继承了类AbstractExecutorService。
 * <p>
 * execute()方法实际上是Executor中声明的方法，在ThreadPoolExecutor进行了具体的实现，这个方法是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行。
 * 　　submit()方法是在ExecutorService中声明的方法，在AbstractExecutorService就已经有了具体的实现，在ThreadPoolExecutor中并没有对其进行重写，这个方法也是用来向线程池提交任务的，但是它和execute()方法不同，它能够返回任务执行的结果，去看submit()方法的实现，会发现它实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果（Future相关内容将在下一篇讲述）。
 * 　　shutdown()和shutdownNow()是用来关闭线程池的。
 * 　　还有很多其他的方法：
 * 　　比如：getQueue() 、getPoolSize() 、getActiveCount()、getCompletedTaskCount()等获取与线程池相关属性的方法，有兴趣的朋友可以自行查阅API。
 * <p>
 * 线程池的具体实现原理，将从下面几个方面讲解：
 * 　　1.线程池状态
 * 　　2.任务的执行
 * 　　3.线程池中的线程初始化
 * 　　4.任务缓存队列及排队策略
 * 　　5.任务拒绝策略
 * 　　6.线程池的关闭
 * 　　7.线程池容量的动态调整
 * <p>
 * 1.线程池状态
 * 在ThreadPoolExecutor中定义了一个volatile变量，另外定义了几个static final变量表示线程池的各个状态：
 * volatile int runState;
 * static final int RUNNING    = 0;
 * static final int SHUTDOWN   = 1;
 * static final int STOP       = 2;
 * static final int TERMINATED = 3;
 * <p>
 * runState表示当前线程池的状态，它是一个volatile变量用来保证线程之间的可见性；
 * 　　下面的几个static final变量表示runState可能的几个取值。
 * 　　当创建线程池后，初始时，线程池处于RUNNING状态；
 * 　　如果调用了shutdown()方法，则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；
 * 　　如果调用了shutdownNow()方法，则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；
 * 　　当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。
 * <p>
 * 2.任务的执行
 * //任务缓存队列，用来存放等待执行的任务
 * private final BlockingQueue<Runnable> workQueue;
 * //线程池的主要状态锁，对线程池状态（比如线程池大小、runState等）的改变都要使用这个锁
 * private final ReentrantLock mainLock = new ReentrantLock();
 * //用来存放工作集
 * private final HashSet<Worker> workers = new HashSet<Worker>();
 * //线程存货时间
 * private volatile long  keepAliveTime;
 * //是否允许为核心线程设置存活时间
 * private volatile boolean allowCoreThreadTimeOut;
 * //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
 * private volatile int   corePoolSize;
 * //线程池最大能容忍的线程数
 * private volatile int   maximumPoolSize;
 * //线程池中当前的线程数
 * private volatile int   poolSize;
 * //任务拒绝策略
 * private volatile RejectedExecutionHandler handler;
 * //线程工厂，用来创建线程
 * private volatile ThreadFactory threadFactory;
 * //用来记录线程池中曾经出现过的最大线程数
 * private int largestPoolSize;
 * //用来记录已经执行完毕的任务个数
 * private long completedTaskCount;
 * <p>
 * 每个变量的作用都已经标明出来了，这里要重点解释一下corePoolSize、maximumPoolSize、largestPoolSize三个变量。
 * 　　corePoolSize在很多地方被翻译成核心池大小，其实我的理解这个就是线程池的大小。举个简单的例子：
 * 　　假如有一个工厂，工厂里面有10个工人，每个工人同时只能做一件任务。
 * 　　因此只要当10个工人中有工人是空闲的，来了任务就分配给空闲的工人做；
 * 　　当10个工人都有任务在做时，如果还来了任务，就把任务进行排队等待；
 * 　　如果说新任务数目增长的速度远远大于工人做任务的速度，那么此时工厂主管可能会想补救措施，比如重新招4个临时工人进来；
 * 　　然后就将任务也分配给这4个临时工人做；
 * 　　如果说着14个工人做任务的速度还是不够，此时工厂主管可能就要考虑不再接收新的任务或者抛弃前面的一些任务了。
 * 　　当这14个工人当中有人空闲时，而新任务增长的速度又比较缓慢，工厂主管可能就考虑辞掉4个临时工了，只保持原来的10个工人，毕竟请额外的工人是要花钱的。
 * 　　这个例子中的corePoolSize就是10，而maximumPoolSize就是14（10+4）。
 * 　　也就是说corePoolSize就是线程池大小，maximumPoolSize在我看来是线程池的一种补救措施，即任务量突然过大时的一种补救措施。
 * 　　不过为了方便理解，在本文后面还是将corePoolSize翻译成核心池大小。
 * 　　largestPoolSize只是一个用来起记录作用的变量，用来记录线程池中曾经有过的最大线程数目，跟线程池的容量没有任何关系。
 * <p>
 * 　　
 * 下面我们进入正题，看一下任务从提交到最终执行完毕经历了哪些过程。
 * 在ThreadPoolExecutor类中，最核心的任务提交方法是execute()方法，虽然通过submit也可以提交任务，但是实际上submit方法里面最终调用的还是execute()方法，
 * 所以我们只需要研究execute()方法的实现原理即可：
 *  public void execute(Runnable command) {
 *     if (command == null)
 *         //首先，判断提交的任务command是否为null，若是null，则抛出空指针异常；
 *         throw new NullPointerException();
 *     if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
 *         if (runState == RUNNING && workQueue.offer(command)) {
 *             if (runState != RUNNING || poolSize == 0)
 *                 ensureQueuedTaskHandled(command);
 *         }
 *         else if (!addIfUnderMaximumPoolSize(command))
 *             reject(command); // is shutdown or saturated
 *     }
 *  }
 *
 * 参考md文件
 * @author xuweizhi
 * @date 2019/01/02 11:30
 */
public class ThreadPoolDetails {

    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }


}
class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
