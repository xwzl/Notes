package com.java.jvm.base;

/**
 * @author xuweizhi
 * @date 2019/04/06 12:14
 */
public class Aoo {

    int a = 1;

    public Aoo() {
        this.test();
    }

    public void test() {
        System.out.println("Aoo" + a);
    }
}

class Boo extends Aoo {

    int b = 2;

    public Boo() {
    }

    public void test() {
        System.out.println("Boo" + a + ";" + b);
    }

}

class Coo extends Boo {

    public static void main(String[] args) {
        Boo boo =new Boo();
    }
}