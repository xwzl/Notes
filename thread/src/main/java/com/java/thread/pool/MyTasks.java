package com.java.thread.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务类，实现Runnable接口 重写run方法
 * @author Administrator
 */
public class MyTasks implements Runnable{


    private int taskNum;

    public MyTasks(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task"+taskNum);
        try {
            //sleep 4秒模拟执行代码过程
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task"+taskNum+"执行完毕");
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数量："+executor.getPoolSize()+",线程池中等待执行的任务数量："+executor.getQueue().size()+",已执行完的任务数量："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

}