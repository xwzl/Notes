package com.java.thread.synchronize;

/**
 * @author xuweizhi
 * @date 2018/12/24 17:34
 */
public class LockStop {

    private String username = "a";
    private String password = "aaa";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    synchronized public void printString(String username, String password) {
        try {
            this.username = username;
            this.password = password;
        } catch (Exception e) {

        }
    }

    synchronized public void print() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("公共资源已经被suspend占用了，无法被其他线程访问");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        final LockStop lockStop = new LockStop();
        Thread thread = new Thread(() -> {
            lockStop.print();
        });
        thread.setName("a");
        thread.start();
        Thread.sleep(1000);
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread2线程已经启动，但是无法访问公共的方法");
            System.out.println("因为print()方法被a线程锁定并且永远被suspend了");
            lockStop.print();
        });
        thread1.start();
        Thread.sleep(1000);
        //thread.resume();
    }
}
