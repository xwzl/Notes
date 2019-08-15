package com.java.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁：Re-Entrant-Lock 从名字就可以看出，这种锁是可以反复进入的。当然，这里的反复进入
 * 是仅仅限于一个线程。
 * <p>
 * ReEntrantLock 与synchronize一样持有对象锁特征
 * <p>
 * 重入锁可以完全替代关键字synchronized.在 JDK 5.0 的早期版本中，重入锁的性能远优于关键字
 * synchronized，但从JDK 6.0 开始，JDK 在关键字 synchronized 关键字上做了大量的优化，使
 * 得两者的性能差距并不大。
 *
 * @author xuweizhi
 * @date 2019/01/02 11:25
 */
@Slf4j
public class ReEntrantLockSample {

    private Lock lock = new ReentrantLock();

    public void testMethod(int threadName) {
        //获取lock获取锁，调用unlock释放锁
        lock.lock();
        Thread.currentThread().setName(threadName + "");
        for (int i = 0; i < 10; i++) {
            log.info("这是一个非常开心的事情！");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        //useSimpleThreadPool();

        final ReEntrantLockSample lock = new ReEntrantLockSample();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> lock.testMethod(finalI)).start();
        }
    }

    private static void useSimpleThreadPool() {
        final ReEntrantLockSample lock = new ReEntrantLockSample();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 15, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        //int i1 = Runtime.getRuntime().availableProcessors();
        //
        //System.out.println(i1);

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            executor.submit(() -> lock.testMethod(finalI));
        }
    }
}
