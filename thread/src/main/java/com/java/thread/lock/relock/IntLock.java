package com.java.thread.lock.relock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 中断响应
 * <p>
 * 对于关键字synchronized来说，如果一个线程在等待锁，那么结果只有两种情况，要么
 * 它获得这把锁继续执行，要么它就保持等待。而使用可重入锁，则提供另外一种可能性，那
 * 就是线程可以被中断，也就是在等待锁的过程中，程序可以根据需要取消对锁的请求；
 *
 * @author xuweizhi
 * @date 2019/04/07 14:24
 */
public class IntLock extends Thread {

    private static ReentrantLock lock1 = new ReentrantLock();

    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    /**
     * 控制加锁的顺序，方便造成死锁
     */
    private IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                // lockInterruptibly方法是一个可以对中断进行响应的锁申请动作
                // 即在等待锁的过程中，可以响应中断。
                System.out.println("lock1:1");
                lock1.lockInterruptibly();
                System.out.println("lock1:2");
                Thread.sleep(500);
                System.out.println("lock1:3");
                lock2.lockInterruptibly();
                System.out.println("lock1:4");
            } else {
                System.out.println("lock2:1");
                lock2.lockInterruptibly();
                System.out.println("lock2:2");
                Thread.sleep(500);
                System.out.println("lock2:3");
                lock1.lockInterruptibly();
                System.out.println("lock2:4");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                System.out.println("lock1:5");
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                System.out.println("lock2:5");
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new IntLock(1));
        Thread t2 = new Thread(new IntLock(2));
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //t2线程放弃其任务直接退出，释放资源；实则只有t1完成了线程的执行
        t2.interrupt();
    }
}
