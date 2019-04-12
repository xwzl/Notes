package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/20 16:33
 */
public class ClassActiveLoading9 {

    static {
        System.out.println("This is C");
    }

    public static void main(String[] args){
      System.out.println(B.b);
    }

}

class A {

    public static int a = 1;

    static {
        System.out.println("This is A !");
    }

}

class B extends A {

    public static int b = 2;

    static {
        System.out.println("This is B !");
    }

}
