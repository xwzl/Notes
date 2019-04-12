package com.java.thread.security;

/**
 * 发生异常 会释放锁
 * @author xuweizhi
 * @date 2018/12/25 17:58
 */
public class Service {

    synchronized public void method() {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " Time=" + System.currentTimeMillis());
            int i = 1;
            while (i == 1) {
                if (("" + Math.random()).substring(0, 8).equals("0.234123")) {
                    System.out.println("ThreadName=" + Thread.currentThread().getName() + " Time=" + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("Time" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        Thread thread = new Thread(service::method);
        Thread thread1 = new Thread(service::method);
        thread.setName("a");
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.setName("b");
        thread1.start();
    }

}
