package com.java.thread.wait;

/**
 *
 * wait(lont times) 等待一段时间唤醒线程
 * 0表示永不唤醒，除非有其他情况发生
 * @author xuweizhi
 * @date 2018/12/27 17:29
 */
public class WaitLongTime {

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized ("a") {
                System.out.println("AAA");
                try {
                    "a".wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("BBB");
            }
        }).start();
    }

}
