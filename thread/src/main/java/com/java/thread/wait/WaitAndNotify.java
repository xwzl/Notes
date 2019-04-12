package com.java.thread.wait;

/**
 *
 * 当wait()方法被执行后，锁被自动释放。
 * 当notify()放被执行,锁不自动释放。
 *   1.执行完同步代码块就会释放对象的锁。
 *   2.执行同步代码块的过程中，遇到异常而导致线程终止，锁也会被释放
 *   3.执行同步代码块的过程中，执行了锁对象锁所属的wait方法，这个线程会释放对象锁，而此线程的对象会进入线程池中，等待被唤醒。
 *
 * @author xuweizhi
 * @date 2018/12/27 16:10
 */
public class WaitAndNotify {

    private Object lock;

    private WaitAndNotify(Object lock) {
        this.lock = lock;
    }

    /**
     * 打印两行释放锁？说明wait方法会释放对象锁。
     */
    public void testWait() {
        synchronized (lock) {
            try {
                System.out.println("Wait会释放锁?");
                lock.wait();
                System.out.println("Wait会释放锁！如果不会释放锁，这行代码打印出现在wait会释放锁？之后");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("出现异常了，因为呈wait状态的线程被interrupt了");
            }
        }
    }

    /**
     * 按顺序执行说明不释放锁
     */
    public void testSleep() {
        synchronized (lock) {
            try {
                System.out.println("Sleep会释放锁?");
                Thread.sleep(3000);
                System.out.println("Sleep不会释放锁！如果释放锁，这行代码随机执行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testNotify() {
        synchronized (lock) {
            try {
                System.out.println("Notify会释放锁?");
                lock.notify();
                Thread.sleep(1000);
                System.out.println("Notify不会释放锁！如果释放锁，线程不会睡眠！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        WaitAndNotify waitAndNotify = new WaitAndNotify(lock);
        Thread thread = new Thread(waitAndNotify::testWait);
        thread.start();
        Thread thread1 = new Thread(waitAndNotify::testWait);
        thread1.start();
        //thread.interrupt();
        new Thread(waitAndNotify::testSleep).start();
        new Thread(waitAndNotify::testSleep).start();
        new Thread(waitAndNotify::testNotify).start();
        new Thread(waitAndNotify::testNotify).start();
}

}
