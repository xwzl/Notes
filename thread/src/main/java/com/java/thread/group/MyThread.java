package com.java.thread.group;

/**
 * @author XuWeiZhi
 */
public class MyThread extends Thread {

    public MyThread(ThreadGroup tg, String name) {
        super(tg, name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName = " + Thread.currentThread().getName() +
                "准备开始死循环了");
        while (!this.isInterrupted()) {
        }
        System.out.println("ThreadName = " + Thread.currentThread().getName() +
                "结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("我的线程组");
        MyThread mt = null;
        for (int i = 0; i < 3; i++) {
            mt = new MyThread(tg, "线程" + i);
            mt.start();
        }
        Thread.sleep(5000);
        tg.interrupt();
        System.out.println("调用了interrupt()方法");
    }

}