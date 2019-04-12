package com.java.jvm.classloader;

import java.util.UUID;

/**
 * @author xuweizhi
 * @date 2019/02/19 15:14
 */
public class ClassActiveLoading3 {

    public static void main(String[] args){
      System.out.println(Parent3.uuid);
    }

}

class Parent3 {

    public static final String uuid = UUID.randomUUID().toString();

    /**
     * 当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量池中，这时在程序运行时，
     * 会导致主动使用这个常量所在的类，显然会导致这个类被初始化。
     */
    static {
        System.out.println("My Parent3 static code !");
    }
}
