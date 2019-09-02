package com.java.thread.base.security;

/**
 * @author xuweizhi
 * @date 2018/12/25 18:27
 */
public class SynchronizedMethod {

    private String data1;

    private String data2;

    private void executeTimes() {
        System.out.println("begin task");
        //线程未同步，几乎同时进行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程同步，两个线程几乎同死打印
        System.out.println(Thread.currentThread().getName()+"外层开始时间"+System.currentTimeMillis());
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName()+"开始时间"+System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"结束时间"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data1 = "获取远方传递过来的数据1！" + System.currentTimeMillis();
            data2 = "获取远方传递过来的数据2！" + System.currentTimeMillis();
        }
        System.out.println(Thread.currentThread().getName()+"外层结束时间"+System.currentTimeMillis());
        System.out.println(data1);
        System.out.println(data2);
        System.out.println("end task");
    }

    static class CommonUntil {
        private static long beginTime1;
        private static long beginTime2;
        private static long endTime1;
        private static long endTime2;
    }

    public static void main(String[] args) {
        SynchronizedMethod times = new SynchronizedMethod();
        Thread thread1 = new Thread(() -> {
            CommonUntil.beginTime1 = System.currentTimeMillis();
            times.executeTimes();
            CommonUntil.endTime1 = System.currentTimeMillis();
        });
        Thread thread2 = new Thread(() -> {
            CommonUntil.beginTime2 = System.currentTimeMillis();
            times.executeTimes();
            CommonUntil.endTime2 = System.currentTimeMillis();
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beginTime1 = CommonUntil.beginTime1;
        if (CommonUntil.beginTime2 < CommonUntil.beginTime1) {
            beginTime1 = CommonUntil.beginTime2;
        }
        long endTime1 = CommonUntil.endTime1;
        if (CommonUntil.endTime2 > CommonUntil.endTime1) {
            endTime1 = CommonUntil.endTime2;
        }
        System.out.println("耗时1："+(endTime1-beginTime1));
    }
}
