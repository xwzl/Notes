package com.java.jvm.classloader;

/**
 *  一个类初始化分为三个阶段，加载，连接，初始化、
 *  对于静态字段来说，只有直接定义了该字段的类才会被初始化，但是有没有被加载呢？
 *  -XX:+TraceClassLoading,用于追踪类的加载信息并打印出来
 *
 *  -XX:+<option> 表示开启option选项
 *  -XX:-<option> 表示关闭option选项
 *  -XX:<option>=<value> 表示设置option的值为value
 * @author xuweizhi
 * @date 2019/02/19 10:19
 */
public class ClassActiveLoading1 {

    public static void main(String[] args) {

        //Child调用父类静态成员变量，未被首次使用，静态代码块未被执行
        System.out.println(Child1.str);
        //Child调用自身静态成员变量（主动使用），本身的静态代码块被调用;父类第二次调用不会别初始化，父类静态代码块不会被执行。
        System.out.println(Child1.str2);
    }

}

class Parent1 {

    public static String str = "This is a static String!";

    static {
        System.out.println("This is a parent static method !");
    }


}

class Child1 extends Parent1 {

    public static String str2 = "two";

    static {
        System.out.println("This is a child static method !");
    }

}