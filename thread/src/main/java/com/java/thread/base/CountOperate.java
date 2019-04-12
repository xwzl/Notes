package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/24 9:14
 */
public class CountOperate extends Thread {

    public CountOperate(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println("this.getName()="+this.getName());
        System.out.println("this.isAlive="+this.isAlive());
        System.out.println("Operate---end");
    }

    @Override
    public void run(){
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println(Thread.currentThread().getClass());
        System.out.println("this.getName()="+this.getName());
        System.out.println(this.getClass());
        System.out.println("this.isAlive()="+this.isAlive());
        System.out.println("run---end");
    }
}
