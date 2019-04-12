package com.java.jvm.order;

/**
 * @author xuweizhi
 * @date 2019/02/22 14:26
 */
public class ClassLoadersA {

    public static void main(String[] args) throws Exception {

        MyClassLoader loader = new MyClassLoader("One");

        Class<?> clazz = loader.loadClass("com.java.jvm.order.Sample");
        System.out.println("class:" + clazz.hashCode());

        Object object = clazz.getDeclaredConstructor().newInstance();

    }

}
