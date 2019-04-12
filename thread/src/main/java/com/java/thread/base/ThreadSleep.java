package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/24 17:03
 */
public class ThreadSleep extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 100000; i++) {
                System.out.println("i="+i);
            }
            System.out.println("run begin");
            Thread.sleep(2000);
            System.out.println("run end");
        } catch (Exception e) {
            System.out.println("在沉睡中被停止" + this.isInterrupted());
            e.printStackTrace();
        }
    }

    // 调用interrupt方法，当run方法中有sleep方法被调用会抛出异常
    // 在沉睡状态下终止线程，会抛出异常
    public static void main(String[] args) {
        try {
            ThreadSleep myThreadSleep = new ThreadSleep();
            myThreadSleep.start();
            //Thread.sleep(200);
            myThreadSleep.interrupt();
        } catch (Exception e) {
            System.out.println("Main catch!");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
