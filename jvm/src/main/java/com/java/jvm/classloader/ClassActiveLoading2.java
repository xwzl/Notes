package com.java.jvm.classloader;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中，本质上，调用类并没有直接引用到定义常量的类，因此并不会触发
 * 定义常量的类的初始化
 * 注意：这里指的是将常量存放到了ClassActiveLoading2的常量池中，之后ClassActiveLoading与Parent2就没有任何关系了，
 * 甚至，我们可以将Parent2的class文件删除
 *
 * @author xuweizhi
 * @date 2019/02/19 13:50
 */
public class ClassActiveLoading2 {

    public static void main(String[] args) {
        System.out.println(Parent2.str);
        System.out.println(Parent2.s);
        System.out.println(Parent2.i);
        System.out.println(Parent2.m);
    }

}

class Parent2 {

    public static final String str = "加Final关键字，如果赋值对象是常量，静态代码块不执行！";

    public static final short s = 7;

    public static final int i = 555;

    public static final int m = 1;

    static {
        System.out.println("final修饰的变量是常量，加载的时进入调用方法类的常量池！因此不会调用static block!");
    }
}
