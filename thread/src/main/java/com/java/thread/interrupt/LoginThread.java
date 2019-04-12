package com.java.thread.interrupt;

/**
 * @author xuweizhi
 * @date 2018/12/23 22:26
 */
public class LoginThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> LoginServlet.post("a", "1111"), "a");
        Thread thread1 = new Thread(() -> LoginServlet.post("b", "2222"), "b");
        thread.start();
        thread1.start() ;
    }
}
