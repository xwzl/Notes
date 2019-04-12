package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/20 17:08
 */
public class ClassActiveLoading11 {

    public static void main(String[] args) {
         System.out.println(Child11.a);
         System.out.println("-----------------");
         Child11.lol();
    }

}


class Parent11 {

    static int a = 1;

    static {
        System.out.println("This is parent11 static block!");
    }

    static void lol() {
        System.out.println("This is method block");
    }
}

class Child11 extends Parent11 {

    static {
        System.out.println("This is child11 static block");
    }
}