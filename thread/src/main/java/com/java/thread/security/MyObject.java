package com.java.thread.security;

/**
 * @author xuweizhi
 * @date 2018/12/25 14:48
 */
public class MyObject {

    public synchronized void methodA() {
        System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public synchronized void methodB() {
        System.out.println("begin methodB threadName=" + Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end time = " + System.currentTimeMillis());
    }

    /**
     *  A线程先持有object的锁，B线程可以以异步的方式调用object对象的非同步方法
     *  A线程先持有object的锁，B线程不可以以异步的方式调用object对象的同步方法
     */
    public static void main(String[] args) {
        MyObject object = new MyObject();
        Thread thread = new Thread(() -> {
            System.out.println("线程A");
            object.methodA();
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("线程B");
            object.methodB();
        });
        thread.setName("A");
        thread1.setName("B");
        thread.start();
        thread1.start();
    }

}
