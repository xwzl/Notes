package com.java.lambda.base;

import java.io.IOException;

/**
 * @author xuweizhi
 * @date 2018/11/29 16:00
 */
public class ThreadTest {
    public static void main(String[] args){
      thred1();
    }
    public static void demo() {
        Thread thread = new Thread(()->{
            for (int i = 0 ; i < 100 ; i ++) {
                try {
                    Thread.sleep(100) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });

        thread.setDaemon(true);
        thred1();
        thread.start();
        System.out.println(thread.isDaemon());

        try {
            System.in.read() ;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void thred1() {
        System.out.println("主线程开始执行");

        //新建线程
        Thread thread1 = new Thread(() -> System.out.println("线程一等待执行"));
        Thread thread2 = new Thread(() -> System.out.println("线程二等待执行"));
        Thread thread3 = new Thread(() -> System.out.println("线程三等待执行"));

        /*
            设置为守护线程，在线程启动前被调用所谓守护线程是指在程序运行的时候在后台提供一种通用服务的线程，比如垃圾回收线程就是一个很称职的守护者，
            并且这种线程并不属于程序中不可或缺的部分。因 此，当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。反过来说，
            只要任何非守护线程还在运行，程序就不会终止。
            (1) thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运
            行的常规线程设置为守护线程。
            (2) 在Daemon线程中产生的新线程也是Daemon的。
            (3) 守护线程应该永远不去访问固有资源，如文件、数据库，因为它会在任何时候甚至在一个操作的中间发生中断。
        */
        thread1.setDaemon(true);
        //线程处于可执行状态
        thread1.start();
        thread2.start();
        thread3.start();


        //等待终止指定的线程
        System.out.println("主线程执行完毕");
    }
}
