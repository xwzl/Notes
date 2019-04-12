package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/21 10:32
 */
public class ClassActiveLoading13 {


    public static void main(String[] args) {

        ClassLoader loader = ClassLoader.getSystemClassLoader();

        System.out.println(loader);

        while (loader != null) {
            loader = loader.getParent();
            System.out.println(loader);
        }

    }

}

class AAA {

}