package com.java.jvm.order;


import java.lang.reflect.Method;

/**
 * @author xuweizhi
 * @date 2019/02/25 11:31
 */
public class ClassLoaderReflect {

    /**
     *
     * 同一个命名空间内的类是相互可见的
     *
     * 子加载器的命名空间包含所有父加载器的命名空间，因此由子加载器加载的类能看见父加载器加载的类，例如系统类加载器加载的类看见根类加载器加载的类
     *
     * 由父加载器加载的类不能看见自加载加载的类
     *
     * 如果两个加载器之间没有直接或间接的父子关系，那么它们各自加载的类相互不可见。
     *
     * 删除classpath路径下的Person.class对象
     * com.java.jvm.order.Person
     * loaderNameloader1
     * com.java.jvm.order.Person
     * loaderNameloader2
     * false
     * Exception in thread "main" java.lang.reflect.InvocationTargetException
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at com.java.jvm.order.ClassReflect.main(ClassReflect.java:29)
     * Caused by: java.lang.ClassCastException: com.java.jvm.order.Person cannot be cast to com.java.jvm.order.Person
     * 	at com.java.jvm.order.Person.setPerson(Person.java:13)
     * 	... 5 more
     */
    public static void main(String[] args) throws Exception {

        MyClassLoader loader1 = new MyClassLoader("loader1");
        System.out.println(loader1.hashCode());
        MyClassLoader loader2 = new MyClassLoader("loader2");

        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");
        loader2.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class<?> loadClass1 = loader1.loadClass("com.java.jvm.order.Person");
        System.out.println(loadClass1.hashCode());
        Class<?> loadClass2 = loader2.loadClass("com.java.jvm.order.Person");

        System.out.println(loadClass1 == loadClass2);

        Object o1 = loadClass1.getDeclaredConstructor().newInstance();
        Object o2 = loadClass2.getDeclaredConstructor().newInstance();

        Method method = loadClass1.getMethod("setPerson", Object.class);
        method.invoke(o1, o2);
    }
}
