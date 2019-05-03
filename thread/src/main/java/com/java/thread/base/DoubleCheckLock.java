package com.java.thread.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 * @since 2019/05/01 18:22
 */
public class DoubleCheckLock {

    Object lock = new Object();

    void code1() {
        System.out.println("code1");
    }

    void otherCode() {
        System.out.println("Other Code !");
    }

    void waitCode() {
        synchronized (lock) {
            System.out.println("即将等待！");
            try {
                lock.wait();
                System.out.println("被唤醒了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void notifyCode() {
        synchronized (lock) {
            System.out.println("Notify Code !");
            lock.notifyAll();
            try {
                Thread.sleep(3000);
                System.out.println("唤醒后并不会立即释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DoubleCheckLock doubleCheckLock = new DoubleCheckLock();
        Thread t1 = new Thread(() -> {
            doubleCheckLock.code1();
            doubleCheckLock.waitCode();
            doubleCheckLock.otherCode();
        });
        Thread t2 = new Thread(() -> {
            doubleCheckLock.notifyCode();
        });

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
