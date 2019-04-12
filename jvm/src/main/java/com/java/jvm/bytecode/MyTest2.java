package com.java.jvm.bytecode;

public class MyTest2 {

    String str = "welcome";

    private int x = 5;

    public static Integer in = 10;

    public static void main(String[] args) {

        MyTest2 mytest2 = new MyTest2();

        mytest2.setX(8);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test(String str) {
        synchronized (str) {
            System.out.println("hello world");
        }
    }

    private synchronized static void test2() {

    }

    static {
        System.out.println("yyy");
    }

}

