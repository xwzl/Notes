package com.java.thread.base.security;

/**
 * @author xuweizhi
 * @date 2018/12/26 20:36
 */
public class LockChangeEvent {

    private String lock = "123";

    public void printLog() {
        synchronized (lock) {
            System.out.println(lock);
            try {
                lock = "12";
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }

    public static void main(String[] args) {
        LockChangeEvent event = new LockChangeEvent();
        new Thread(event::printLog).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(event::printLog).start();
    }

}
