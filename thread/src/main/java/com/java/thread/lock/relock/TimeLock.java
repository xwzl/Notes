package com.java.thread.lock.relock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2. 锁申请等待限时
 * <p>
 * 除了等待外部通知外，要避免死锁还有另外一种方法，那就是限时等待。
 * <p>
 * 通常，我们无法判断为什么一个线程迟迟拿不到锁。也许是因为死锁了，也许是因为产生了饥饿。
 * 如果给定一个等待时间，让线程自动放弃，那么对系统来说是有意义的。
 * <p>
 * 我们可以使用tryLock()方法进行一次限时的等待。
 *
 * @author xuweizhi
 * @date 2019/04/07 14:56
 */
public class TimeLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            // 五秒内获取锁，获取成功则返回true
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock fair ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 在本例中，由于占用锁的线程会持有锁长达 6 秒，故另外一个线程无法在 5 秒的等待时间内获得锁，
     * 因此会请求失败。
     */
    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();

        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }

}

/**
 * {@link ReentrantLock#tryLock()}方法也可以不带参数直接运行。在这用情况下，当前线程会尝试获得
 * 锁，如果锁并未被其他线程占用，则会申请锁成功，并立即返回true。若锁被其他线程占用，则当前线程不会
 * 进行等待，而是立即返回false。这种模式不会引起线程等待，因此不会产生死锁。
 */
class TryLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();

    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job Done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job Done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    /**
     * 采用了非常容易死锁的加锁顺序，也就是先让t1获得lock1，再让t2获得lock2,接着做反向请求，
     * 让t2申请lock2,t2申请lock1.在一般情况下，这会导致t1和t2相互等待，从而引起死锁。
     *
     * 但是使用tryLock()方法后，这种情况就大大改善了。由于线程不会傻傻地等待，而是不停的尝试。
     * 因此，只要执行足够长的时间，线程总是会得到所有需要的资源，从而正常执行。
     */
    public static void main(String[] args) {

        Thread t1 = new Thread(new TryLock(1));
        Thread t2 = new Thread(new TryLock(2));

        t1.start();
        t2.start();

    }

}
