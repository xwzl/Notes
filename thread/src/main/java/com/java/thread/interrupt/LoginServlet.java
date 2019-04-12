package com.java.thread.interrupt;

/**
 * @author xuweizhi
 * @date 2018/12/23 22:22
 */
public class LoginServlet {

    private static String username;
    private static String password;

    public static void post(String user, String pass) {
        username = user;
        if (username.equals("a")) {
            try {
                System.out.println("a");
                Thread.sleep(5000);
                //不放弃线程锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        password = pass;
        System.out.println("username:" + username + " password:" + password);
    }
}
