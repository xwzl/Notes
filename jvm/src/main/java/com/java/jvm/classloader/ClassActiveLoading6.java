package com.java.jvm.classloader;

/**
 * @author xuweizhi
 * @date 2019/02/19 17:15
 */
public class ClassActiveLoading6 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println("main" + Singleton.count1);
        System.out.println("main" + Singleton.count2);
    }

}

/**
 * 静态变量初始化时按照代码顺序进行初始化，JVM为静态变量赋值分为两个阶段：
 * JVM找对Java类对应的二进制文件，把数据加载进内存，验证二进制文件的正确
 * 性，准备阶段如果类中存在静态变量，在内存中把默认值初始化原始类型为0，引
 * 用类型为null,在把类中的符号引用换位直接引用。
 * 第二次静态成员变量赋值是这个类首次被主动调用的时候在赋值，因此才会有下面
 * 现象存在。
 */
class Singleton {

    public static int count1=1;

    //public static int count2 = 0;

    private static Singleton singleton = new Singleton();

    public Singleton() {
        count1++;
        count2++;
        System.out.println(count1);
        System.out.println(count2);
    }

    public static int count2 = 3;

    public static Singleton getSingleton() {
        return singleton;
    }
}
