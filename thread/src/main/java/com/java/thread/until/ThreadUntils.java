package com.java.thread.until;

/**
 * @author xuweizhi
 * @date 2018/12/26 17:36
 */
public class ThreadUntils {

    static private void printThreaNameAndSystemTime() {
        System.out.println("Thread Name=" + Thread.currentThread().getName() + " System Time =" + System.currentTimeMillis());
    }
}
