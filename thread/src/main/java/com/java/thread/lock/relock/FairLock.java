package com.java.thread.lock.relock;

import com.java.thread.until.PoolUntil;
import com.java.thread.until.ThreadPools;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁lock分为公平锁和非公平锁
 * <p>
 * 公平锁的顺序按照线程启动的顺序来获取的 可以看到，获取锁的线程顺序正是线程启动的顺序。 可以看出非公平锁对锁的获取是乱序的，即有一个抢占锁的过程。
 * <p>
 * 在公平的锁中，如果有另一个线程持有锁或者有其他线程在等待队列中等待这个锁，那么新发出的请求的线程将被放入到队列中。而非公平锁上，只有当锁被
 * 某个线程持有时，新发出请求的线程才会被放入队列中（此时和公平锁是一样的）。所以，它们的差别在于非公平锁会有更多的机会去抢占锁。
 * <p>
 * 非公平锁性能高于公平锁性能。首先，在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。而且，非公平锁能更充分的利用cpu的时间片，
 * 尽量的减少cpu空闲的状态时间。
 * <p>
 * 使用场景的话呢，其实还是和他们的属性一一相关，举个栗子：如果业务中线程占用(处理)时间要远长于线程等待，那用非公平锁其实效率并不明显，
 * 但是用公平锁会给业务增强很多的可控制性。
 *
 * @author xuweizhi
 * @date 2019/01/02 18:04
 */
@Slf4j
public class FairLock implements Runnable {

    /**
     * 公平锁
     */
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } finally {
                fairLock.unlock();
            }
        }

    }

    /**
     * 当ReentranLock 设置为 false 时，为非公平锁。
     * <p>
     * 可以看到，根据系统的调度，一个线程会倾向于再次获取已经持有的锁，这种分配方式是高效的，
     * 但是无公平性可言。
     * <p>
     * 对上面的ReentrancLock 的几个重要方法整理如下：
     * <p>
     * lock(): 获得锁，如果锁已经被占用，则等待。
     * lockInterruptibly(): 获得锁，但优先响应中断。
     * tryLock(): 尝试获得锁，如果成功，则返回true,失败返回false.该方法不等待，立即返回。
     * tryLock(long time,TimeUnit unit): 在给定时间内尝试获得锁。
     * unlock()： 释放锁。
     * <p>
     * 就重入锁的实现来看，它主要集中在Java层面。在重入锁的实现中，主要包含三个要素。
     * <p>
     * 第一，原子状态。原子状态使用CAS操作来存储当前锁的状态，判断锁是否已经被别的线程持有了。
     * 第二，等待队列。所有没有请求到锁的线程，会进入等待队列进行等待。带有线程释放锁后，系统
     * 就能从等待队列中唤醒一个线程，继续工作。
     * 第三，阻塞原语 park() 和 unpark(),用来挂起和恢复线程。没有得到锁的线程将会被挂起。有、
     * 关park()和unpark()的详细介绍，参考{@link LockSupport}
     */
    @Test
    public void test() {
        FairLock r1 = new FairLock();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor(new ThreadPools());
        executor.execute(r1);
        executor.execute(r1);
        while (Thread.activeCount() > 0) {
            Thread.activeCount();
        }
    }

    public static void main(String[] args) {
        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("线程运行");
                service.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}

@Slf4j
class Service {

    private ReentrantLock lock;

    public Service(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            log.info("获得锁");
        } finally {
            lock.unlock();
        }
    }

}