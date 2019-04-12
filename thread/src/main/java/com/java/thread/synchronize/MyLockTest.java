package com.java.thread.synchronize;

/**
 * synchronize 测试
 *
 * @author xuweizhi
 * @date 2019/04/07 12:00
 */
public class MyLockTest {

    public Object lock = new Object();

    private static int count = 0;

    public static synchronized void numberAutoIncrease() {
        count++;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    numberAutoIncrease();
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }


}


