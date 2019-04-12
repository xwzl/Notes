package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/20 16:33
 */
public class ClassActiveLoading10 {

    static {
        System.out.println("This is C");
    }

    public static void main(String[] args) {
        A10 a10;
        System.out.println("------------");
        a10 = new A10();
        System.out.println("------------");
        System.out.println(A10.a);
        System.out.println("------------");
        System.out.println(B10.b);
        System.out.println("------------");
    }

}

class A10 {

    static int a = 1;

    static {
        System.out.println("This is A !");
    }

}

class B10 extends A10 {

    static int b = 2;

    static {
        System.out.println("This is B !");
    }

}
