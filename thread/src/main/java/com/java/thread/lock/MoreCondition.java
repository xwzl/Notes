package com.java.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多个condition通知部分代码的执行，如果使用单个condition进行唤醒，则是唤醒所有的等待线程
 *
 * @author xuweizhi
 * @date 2019/01/02 16:47
 */
@Slf4j
public class MoreCondition {

    static class Service {

        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();

        public void awaitA() {
            try {
                lock.lock();
                log.info("Begin with A" + System.currentTimeMillis());
                c1.await();
                log.info("End with A" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitB() {
            try {
                lock.lock();
                log.info("Begin with B" + System.currentTimeMillis());
                c2.await();
                log.info("End with B" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_A() {
            try {
                lock.lock();
                log.info("signalAll A 时间为" + System.currentTimeMillis());
                c1.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_B() {
            try {
                lock.lock();
                log.info("signalAll B 时间为" + System.currentTimeMillis());
                c2.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }

    /**
     * 唤醒单独的睡眠线程
     */
    public static void main(String[] args) {
        Service service = new Service();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        executor.execute(service::awaitA);
        executor.execute(service::awaitB);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.execute(service::signalAll_A);
    }
}
