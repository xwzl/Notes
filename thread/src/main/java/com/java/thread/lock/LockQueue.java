package com.java.thread.lock;

import com.java.thread.until.PoolUntil;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuweizhi
 * @date 2019/01/03 9:25
 */
@Slf4j
public class LockQueue {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final LockQueue lockQueue = new LockQueue();
        //ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        //executor.execute(lockQueue::waitMethod);
        Thread thread = new Thread(lockQueue::waitMethod);
        thread.start();
        Thread.sleep(1000);
        //executor.execute(lockQueue::waitMethod);
        Thread thread1 = new Thread(lockQueue::waitMethod);
        thread1.start();
        Thread.sleep(1000);
        //查询指定的线程是否在等待获取此锁定 false true true
        log.info("" + lockQueue.lock.hasQueuedThread(thread));
        log.info("" + lockQueue.lock.hasQueuedThread(thread1));
        //查询是否有线程正在等待获取此锁定
        log.info("" + lockQueue.lock.hasQueuedThreads());
    }
}

@Slf4j
class LockWaiter {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            lock.lock();
            //hasWaiters 判断有没有线程在等待 获取等待的线程数
            log.info("有没有线程正在等待condition?" + lock.hasWaiters(condition) + " 线程数是多少？" + lock.getWaitQueueLength(condition));
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final LockWaiter lockWaiter = new LockWaiter();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(lockWaiter::waitMethod);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        Thread.sleep(2000);
        lockWaiter.notifyMethod();
    }

}

@Slf4j
class IsLock {

    private ReentrantLock lock;

    public IsLock(Boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethon() {
        try {
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isLocked());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IsLock lock = new IsLock(true);
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        executor.execute(lock::serviceMethon);
    }
}

@Slf4j
class LockInterrput {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String name;

    private void setThreadName(String name) {
        this.name = name;
    }

    private void waitMethod() {
        try {
            //lock.lock();
            //如果当前线程未被中断，则获取锁；如果已经被中断则出现异常。
            lock.lockInterruptibly();
            //Thread.currentThread().setName(this.name);
            log.info("lock begin");
            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                String value = new String();
                Math.random();
            }
            log.info("lock end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }

        }
    }

    public void tryLock2() {
        try {
            //如果锁在给定的时间内，没有被另外一个线程锁持有，当前线程未中断的情况下，获取该锁
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                log.info("获取锁时间" + System.currentTimeMillis());
                Thread.sleep(6000);
            } else {
                log.info("没有获得锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void tryLock() {
        //仅在调用时锁定 未被另一个线程保持 的情况下，才获取该锁定。
        if (lock.tryLock()) {
            log.info("获取锁定");
        } else {
            log.info("未被锁定");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final LockInterrput lockInterrput = new LockInterrput();
        //lockInterrupt();
        //tryLock(lockInterrput);
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        executor.execute(() -> {
            log.info(System.currentTimeMillis() + "");
            lockInterrput.tryLock2();
        });
        Thread.sleep(20000);
        executor.execute(() -> {
            log.info(System.currentTimeMillis() + "");
            lockInterrput.tryLock2();
        });
    }

    private static void tryLock(LockInterrput lockInterrput) {
        ThreadPoolExecutor threadPoolExecutor = PoolUntil.getThreadPoolExecutor();
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
        threadPoolExecutor.execute(lockInterrput::tryLock);
    }

    private static void lockInterrupt() throws InterruptedException {
        final LockInterrput lockInterrput = new LockInterrput();
        //ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        lockInterrput.setThreadName("A");
        Thread thread = new Thread(lockInterrput::waitMethod);
        thread.start();
        //executor.execute(lockInterrput::waitMethod);
        Thread.sleep(500);
        lockInterrput.setThreadName("B");
        Thread thread1 = new Thread(lockInterrput::waitMethod);
        thread1.start();
        //executor.execute(lockInterrput::waitMethod);
        thread1.interrupt();
        System.out.println("main end");
    }

}

@Slf4j
class OrderRun {

    private volatile Integer index = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();

    public void run(String threadName, Integer tag) {
        try {
            lock.lock();
            Thread.currentThread().setName(threadName);
            while (this.index != tag) {
                if (tag == 1) {
                    a.await();
                }
                if (tag == 2) {
                    b.await();
                }
                if (tag == 3) {
                    c.await();
                }
            }
            for (int i = 0; i < 3; i++) {
                log.info((i + 1) + "");
            }
            //finally中语句，在return执行之后返回之前执行，不影响unlock()锁的释放
            if (tag == 1) {
                this.index = 2;
                b.signalAll();
                return;
            }
            if (tag == 2) {
                this.index = 3;
                c.signalAll();
                return;
            }
            if (tag == 3) {
                this.index = 1;
                a.signalAll();
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OrderRun run = new OrderRun();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> run.run("A", 1));
            executor.execute(() -> run.run("B", 2));
            executor.execute(() -> run.run("C", 3));
        }
    }

    
}
