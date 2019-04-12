package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/23 21:44
 */
public class MyThreadTest {

    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.setName("线程1");
        myThread.start();
        System.out.println("多线情况下，代码执行顺序与代码顺序不一致；");
    }
}
