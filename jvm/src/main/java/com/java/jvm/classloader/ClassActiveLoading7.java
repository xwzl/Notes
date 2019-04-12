package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/20 15:39
 */
public class ClassActiveLoading7 {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        clazz = Class.forName("com.java.jvm.classloader.D36");
        System.out.println(clazz.getClassLoader());

    }

}

class D36 {

}