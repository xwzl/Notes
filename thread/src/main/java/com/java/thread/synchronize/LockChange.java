package com.java.thread.synchronize;

/**
 * @author xuweizhi
 * @date 2018/12/26 18:51
 */
public class LockChange {

    private String lock = "123";


    public void setLock() {
        synchronized (lock) {
            try {
                System.out.println(lock);
                lock = "234";
                Thread.sleep(5000);
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        LockChange change = new LockChange();
        new Thread(change::setLock, "A").start();
        try {
            //lock值刷新
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(change::setLock, "B").start();
    }

}
