package com.java.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/12/27 15:16
 */
public class PrintGame {

    private static List<String> list = new ArrayList<>();

    public static void add() {
        list.add("anyThing");
    }

    public static int size() {
        return list.size();
    }

}

class A extends Thread {

    private Object lock;

    public A(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                if (PrintGame.size() != 5) {
                    System.out.println("wait begin " + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait end " + System.currentTimeMillis());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class B extends Thread {

    private Object lock;

    public B(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            /**
             * 必须制定完notify同步代码块中代码，呈wait状态的线程才会被调用
             */
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    PrintGame.add();
                    if (PrintGame.size() == 5) {
                        lock.notify();
                        System.out.println("已发出通知");
                    }
                    System.out.println("添加了" + (i + 1) + "个元素");
                    Thread.sleep(1000);
                }
            }
            System.out.println("退出同步代码块后，后面的代码会立即执行吗？");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PrintGameTest {

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            A a = new A(lock);
            a.start();
            Thread.sleep(50);
            B b = new B(lock);
            b.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}