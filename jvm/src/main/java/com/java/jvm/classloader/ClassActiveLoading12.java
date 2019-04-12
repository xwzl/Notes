package com.java.jvm.classloader;

/**
 *  调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
 * @author xuweizhi
 * @date 2019/02/20 17:14
 */
public class ClassActiveLoading12 {

    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        //加载类的文件
        Class<?> clazz = loader.loadClass("com.java.jvm.classloader.CL");
        System.out.println(clazz);

        clazz = Class.forName("com.java.jvm.classloader.CL");
        System.out.println(clazz);

    }
}

class CL {

    static {
        System.out.println("ClassLoader");
    }

}