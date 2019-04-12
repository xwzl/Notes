package com.java.thread.security;

/**
 * synchronized 解读
 * @author xuweizhi
 * @date 2018/12/25 14:17
 */
public class Student {

    private String username;

    private String age;

    public Student() {
        this.username = "三少";
        this.age = "12";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public synchronized void print(String msg) {

        if (msg.equalsIgnoreCase("A")) {
            this.username = "BB";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.username = "三哥";
        }
        System.out.println("Username:" + username + " Age:" + age);

    }

    public static void main(String[] args) {

        //syncPrint();

        //  异步打印 两个线程分别访问同一个类的两个不同实例的相同名称的同步方法，效果却是以异步的方式运行的。
        //  关键字synchronized取得的是对象锁，而不是方法锁或者代码锁
        //  所以在同步打印中，哪个线程先执行带synchronized关键字的方法，哪个线程就持有该方法所属对象的锁lock，那么其他线程只能处于等待状态，
        //  前提是多个线程访问的是同一个对象。
        //  有多个线程访问多个对象，则jvm会创建多个锁。
        final Student student = new Student();
        final Student student1 = new Student();
        Thread thread1 = new Thread(() -> {
            System.out.println("线程一启动");
            student.print("a");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("线程二启动");
            student1.print("c");
        });

        thread1.start();
        thread2.start();
    }

    /**
     * 同步打印
     */
    private static void syncPrint() {
        final Student student = new Student();

        Thread thread1 = new Thread(() -> {
            System.out.println("线程1启动");
            student.print("a");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("线程2启动");
            student.print("c");
        });

        thread1.start();
        thread2.start();
    }

}
