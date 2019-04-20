package com.java.thread.base;


/**
 * @author xuweizhi
 * @date 2019/01/03 16:35
 */
public class ThreadGroups {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        Thread b = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ThreadGroup group = new ThreadGroup("线程组对象");
        Thread thread1 = new Thread(group, a);
        Thread thread2 = new Thread(group, b);
        thread1.start();
        thread2.start();
        System.out.println("活动中的线程数为:"+group.activeCount());
        System.out.println("name"+group.getName());
    }

}
