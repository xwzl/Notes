package com.java.jvm.order;

/**
 *
 * 关于命名空间的重要说明
 *
 * 1. 子加载器所加载的类能够访问到父加载器所加载的类
 * 2. 父加载器所加载的类无法访问到子加载器所加载的类
 * @author xuweizhi
 * @date 2019/02/22 14:26
 */
public class ClassLoadersB {

    /**
     *
     * com.java.jvm.order.Sample
     * loaderNameB
     * class:1566502717
     * Sample is load by : com.java.jvm.order.MyClassLoader@215be6bb
     * Cat is load by :jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
     *
     */
    public static void main(String[] args) throws Exception {

        MyClassLoader loader = new MyClassLoader("B");
        loader.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> clazz = loader.loadClass("com.java.jvm.order.Sample");
        System.out.println("class:" + clazz.hashCode());

        Object object = clazz.getDeclaredConstructor().newInstance();

    }

}
