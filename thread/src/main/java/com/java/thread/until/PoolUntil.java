package com.java.thread.until;

import java.util.concurrent.*;

/**
 * @author xuweizhi
 * @date 2019/01/02 17:20
 */
public class PoolUntil {

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(10, 200, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
    }

    public static ThreadPoolExecutor getThreadPoolExecutor(ThreadPools threadPool) {
        if (threadPool.getCorePoolSize() == 0) {
            threadPool.setCorePoolSize(10);
        }
        if (threadPool.getMaximumPoolSize() == 0) {
            threadPool.setMaximumPoolSize((int) (threadPool.getCorePoolSize() * 1.5));
        }
        if (threadPool.getKeepAliveTime() == 0L) {
            threadPool.setKeepAliveTime(200L);
        }
        if (threadPool.getUnit() == null) {
            threadPool.setUnit(TimeUnit.MILLISECONDS);
        }
        if (threadPool.getWorkQueue() == null) {
            threadPool.setWorkQueue(new ArrayBlockingQueue<>(10));
        }
        return new ThreadPoolExecutor(threadPool.getCorePoolSize(), threadPool.getMaximumPoolSize(), threadPool.getKeepAliveTime(),
                threadPool.getUnit(), threadPool.getWorkQueue(), (ThreadFactory) Thread::new);
    }


    //public static void main(String[] args){
    //    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
    //    for (int i = 0; i < 25; i++) {
    //        MyTask myTask = new MyTask(i);
    //        executor.execute(myTask);
    //        System.out.println("线程池中线程数量："+executor.getPoolSize()+",线程池中等待执行的任务数量："+executor.getQueue().size()+",已执行完的任务数量："+executor.getCompletedTaskCount());
    //    }
    //    //executor.shutdown();
    //}
}

class MyTask implements Runnable {

    private int taskNum;

    public MyTask(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + taskNum);
        try {
            Thread.currentThread().sleep(4000);//sleep 4秒模拟执行代码过程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + taskNum + "执行完毕");
    }
}