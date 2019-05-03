package com.java.thread.base;

/**
 * @author xuweizhi
 * @since  2018/12/24 9:07
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("自定义线程组");
    }
}
