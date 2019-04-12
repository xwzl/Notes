package com.java.thread.synchronize;

/**
 * 本实验说明：不在synchronized块中就是异步执行，在synchronized块中就是同步执行。
 *
 * @author xuweizhi
 * @date 2018/12/26 11:26
 */
public class Task {

    public void doLongTimeTask() {
        for (int i = 0; i < 5; i++) {
            System.out.println("no synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
        }
        System.out.println("");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("no synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
            }
            System.out.println("end");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Task task = new Task();
        new Thread(task::doLongTimeTask,"A").start();
        new Thread(task::doLongTimeTask,"F").start();
    }
}
