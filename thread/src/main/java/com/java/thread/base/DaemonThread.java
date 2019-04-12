package com.java.thread.base;

/**
 * 守护线程，随着用户线程的结束一起结束
 *
 * @author xuweizhi
 * @date 2018/12/24 18:34
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                System.out.println(i);
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(50);
        System.out.println("主线程结束，守护线程跟着结束！");
    }

}
