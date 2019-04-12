package com.java.thread.base;

import org.junit.Test;

/**
 * @author xuweizhi
 * @date 2018/12/24 9:12
 */
public class ThreadTest {

    @Test
    public void test1() {
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test2() {
        CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        thread.setName("Nice");
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        thread.setName("Nice");
        System.out.println("判断当前线程是否处于活动状态："+thread.isAlive());
        thread.start();
        System.out.println("判断当前线程是否处于活动状态："+thread.isAlive());
        System.out.println(thread.getId());
        Thread.sleep(5000);
        //活动状态是指线程已经启动但是未终止，或者线程处于正在运行或者准备开始运行的状态
        System.out.println("判断当前线程是否处于活动状态："+thread.isAlive());
    }
}
