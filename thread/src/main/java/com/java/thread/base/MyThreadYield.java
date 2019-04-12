package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/24 18:22
 */
public class MyThreadYield extends Thread {

    @Override
    public void run() {
        super.run();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            //把cpu的调度权让出
            Thread.yield();
            System.out.println(i);
        }
        System.out.println("程序运行时间:" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        MyThreadYield yield = new MyThreadYield();
        yield.start();
    }

}
