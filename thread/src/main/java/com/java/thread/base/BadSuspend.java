package com.java.thread.base;

/**
 * 挂起（suspend）和 继续执行（resume）线程
 *
 * @author xuweizhi
 * @date 2019/04/06 11:33
 */
public class BadSuspend {

    public static Object u = new Object();

    static ChangeObjectThread t1 =new ChangeObjectThread("t1");
    static ChangeObjectThread t2 =new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in"+getName());
                Thread.currentThread().suspend();
            }
        }
    }

    /**
     * 这是我们需要注意，在当前系统中，线程t2其实是被挂起的，但是它的线程状态确实是RUNNABLE,
     * 这很有可能使我们误判当前系统的状态。同时，虽然主函数中已经调用了resume()方法，但由于时
     * 间先后的缘故，那个resume()方法没有生效！这就导致了线程t2被永远挂起，并且永远占用了对象
     * u的锁。
     */

    public static void main(String[] args) throws InterruptedException {
      t1.start();
      Thread.sleep(100);
      t2.start();
      t1.resume();
      t2.resume();
      t1.join();
      t2.join();

    }
}
