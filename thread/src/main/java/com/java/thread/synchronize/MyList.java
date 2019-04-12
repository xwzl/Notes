package com.java.thread.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/12/26 13:09
 */
public class MyList {

    private List<String> list = new ArrayList<>();

    synchronized private void add(String msg) {
        System.out.println("Thread Name:" + Thread.currentThread().getName() + "执行了add方法");
        list.add(msg);
        System.out.println("Thread Name:" + Thread.currentThread().getName() + "退出了add方法");
    }

    synchronized private int getSeize() {
        System.out.println("Thread Name:" + Thread.currentThread().getName() + "执行了getSize方法");
        int size = list.size();
        System.out.println("Thread Name:" + Thread.currentThread().getName() + "退出了size方法");
        return size;
    }

    synchronized private void add1(String msg) {
        list.add(msg);
    }

    synchronized private int getSeize1() {
        return list.size();
    }


    static class Service {

        /**
         * 未加同步代码块出现脏读现象
         * Thread Name:main执行了getSize方法
         * Thread Name:main退出了size方法
         * MyList length:2
         *
         * 对list加锁 相当于对list实例枷锁，当有this指向list对象时，表示同一个锁对象
         * synchronized(非this对象) 格式的写法是将x对象本身作为"对象监视器"，这样获取可以得出以下结论
         * 1. 当多个线程同时执行synchronized(x){}同步代码块时呈同步现象
         * 2. 当其他线程执行x对象中synchronized同步方法是呈同步效果。
         * 3. 当其他线程执行x对象方法里面的synchronized(this)代码块时也呈现同步效果。
         * 但是其他线程调用不加synchronized关键字的方法是，还是异步调用
         * 为了验证上面的3个结论：创建{@link BlockLock#main(String[])}对象
         *
         */
        public MyList addServiceMethod(MyList list, String data) {
            try {

                synchronized (list) {
                    if (list.getSeize1() < 1) {
                        //模拟远程调用
                        Thread.sleep(2000);
                        list.add1(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public static void main(String[] args) {
        //模拟远程调用
        //tests();

        MyList myList = new MyList();

        Thread thread = new Thread(() -> {
            Service service = new Service();
            service.addServiceMethod(myList, "A");
            //为什么在线程方法里面是对应线程调用相应方法，在其他对象方法里面是main调用了方法
            //myList.add("a");
        }, "A");

        thread.start();

        Thread thread1 = new Thread(() -> {
            Service service = new Service();
            service.addServiceMethod(myList, "B");
        }, "B");
        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyList length:" + myList.getSeize());

    }

    private static void tests() {
        MyList list = new MyList();
        MyList list1 = new MyList();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add("A" + (i + 1));
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list1.add("B" + (i + 1));
            }
        }, "B").start();
    }
}
