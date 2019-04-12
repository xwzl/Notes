package com.java.thread.synchronize;

/**
 * @author xuweizhi
 * @date 2018/12/26 17:33
 */
public class OutsClass {

    static class InnerClass {

        public void method1(InnerClass2 innerClass2) {
            String threadName = Thread.currentThread().getName();
            synchronized (innerClass2) {
                System.out.println(threadName + "进入InnerClass method1内部方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println("A=" + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName+"离开InnerClass method1内部方法");
            }
        }

        public synchronized void method2() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass method内部方法");
            for (int i = 0; i < 10; i++) {
                System.out.println("B=" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName+"离开InnerClass method内部方法");
        }

    }

    static class InnerClass2 {
        public synchronized void method() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass2 method2内部方法");
            for (int i = 0; i < 10; i++) {
                System.out.println("C=" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName+"离开InnerClass2 method2内部方法");
        }
    }

    public static void main(String[] args) {

        final InnerClass innerClass = new InnerClass();
        final InnerClass2 innerClass2 = new InnerClass2();
        new Thread(() -> innerClass.method1(innerClass2), "T1").start();
        new Thread(innerClass::method2, "T2").start();
        new Thread(innerClass2::method, "T3").start();

    }
}
