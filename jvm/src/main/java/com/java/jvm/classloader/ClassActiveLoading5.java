package com.java.jvm.classloader;

import java.util.Random;

/**
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化，只有在真正使用父接口的时候（如直接引用接口所定义的常量时），才会初始化
 * 1. 在初始化一个类时，并不会先初始化它实现的接口
 * 2. 在初始化一个接口时，并不会先初始化它的父接口
 *
 * @author xuweizhi
 * @date 2019/02/19 16:33
 */
public class ClassActiveLoading5 {

    public static void main(String[] args) {

        //System.out.println(Child5.b);

        System.out.println(Why.str);
    }

}


/**
 * 准备阶段: thread -> null
 * 初始化阶段: thread -> new Thread(){
 * {
 * System.out.println("我被初始化了");
 * }
 * };
 */
interface Parent5 {

    public static final int a = 5;

    public static final Thread thread = new Thread() {
        {
            System.out.println("我被初始化了");
        }
    };
}

/**
 * 如果常量（final修饰）的值编译期可以知道，程序执行不会加载class文件，否则，需要重新加载文件。
 */
interface Child5 extends Parent5 {

    public static final int b = 6;

    public static final Thread thread = new Thread() {
        {
            System.out.println("我被初始化了");
        }
    };

}

class Why implements Child5 {

    public static String str = "Why";
}

interface Child55 extends Parent5 {
    int c = new Random().nextInt(3);
}

class C {

    /**
     * 构造代码块，每次创建一个实例都会被执行一次
     */ {
        System.out.println("Hello");
    }

    public C() {
        System.out.println("c");
    }

}