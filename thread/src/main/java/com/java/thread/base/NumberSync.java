package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/23 21:56
 */
public class NumberSync extends Thread {

    private int i = 0;

    @Override
    public synchronized  void run() {
        super.run();
        i++;
        System.out.println("当前线程" + currentThread().getName() + "打印" + i);
    }
}
