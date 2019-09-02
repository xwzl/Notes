package com.java.thread.base.security;

/**
 * @author xuweizhi
 * @date 2018/12/25 18:27
 */
public class SynchronizedTime {

    private String data1;

    private String data2;

    synchronized private void executeTime() {
        System.out.println("begin task");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data1 = "获取远方传递过来的数据1！" + System.currentTimeMillis();
        data2 = "获取远方传递过来的数据2！" + System.currentTimeMillis();
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
        SynchronizedTime time = new SynchronizedTime();
        Thread thread1 = new Thread(() -> {
            CommonUntil.beginTime1 = System.currentTimeMillis();
            time.executeTime();
            CommonUntil.endTime1 = System.currentTimeMillis();
        });
        Thread thread2 = new Thread(() -> {
            CommonUntil.beginTime2 = System.currentTimeMillis();
            time.executeTime();
            CommonUntil.endTime2 = System.currentTimeMillis();
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beginTime = CommonUntil.beginTime1;
        if (CommonUntil.beginTime2 < CommonUntil.beginTime1) {
            beginTime = CommonUntil.beginTime2;
        }
        long endTime = CommonUntil.endTime1;
        if (CommonUntil.endTime2 > CommonUntil.endTime1) {
            endTime = CommonUntil.endTime2;
        }
        System.out.println("耗时："+(endTime-beginTime));
    }
}
