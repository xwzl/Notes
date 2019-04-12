package com.java.base.common;

/**
 * @author xuweizhi
 * @date 2019/03/13 22:36
 */
public class ParamsTransport {

    /**
     * 答：是值传递。Java编程语言只有值传递参数。
     * <p>
     * 当一个对象实例作为一个参数被传递到方法中时，参数的值就是该对象的引用一个副本。指向同一个对象，对象的内容可以
     * 在被调用的方法中改变，但对象的引用(不是引用的副本)是永远不会改变的。
     * <p>
     * 1.Java中没有指针，所以也没有引用传递了，仅仅有值传递。不过，可以通过对象的方式来实现引用传递。类似java没有
     * 多继承，但可以用多次implements接口实现多继承的功能。
     * <p>
     * 2.在Java应用程序中永远不会传递对象，而只传递对象的引用。因此是按引用传递对象。但重要的是要区分参数是如何传
     * 递的。
     * <p>
     * 3.Java应用程序按引用传递对象这一事实并不意味着 Java 应用程序按引用传递参数。参数可以是对象引用，而 Java应
     * 用程序是按值传递对象引用的。
     * <p>
     * 4.Java应用程序中的变量可以为以下两种类型之一：引用类型或基本类型。当作为参数传递给一个方法时，处理这两种类
     * 型的方式是相同的。两种类型都是按值传递的，没有一种按引用传递。
     * <p>
     * 按值传递意味着当将一个参数传递给一个函数时，函数接收的是原始值的一个副本。因此，如果函数修改了该参数，仅
     * 改变副本，而原始值保持不变。
     * <p>
     * 按引用传递意味着当将一个参数传递给一个函数时，函数接收的是原始值的内存地址，而不是值的副本。因此，如果函
     * 数修改了该参数的值，调用代码中的原始值也随之改变。如果函数修改了该参数的地址，调用代码中的原始值不会改变。
     * <p>
     * 值传递：方法调用时，实际参数把它的值传递给对应的形式参数，方法执行中，对形式参数值的改变不影响实际参数的值。
     * <p>
     * 引用传递：也称为传地址。方法调用时，实际参数的引用(地址，而不是参数的值)被传递给方法中相对应的形式参数，在
     * 方法执行中，对形式参数的操作实际上就是对实际参数的操作，方法执行中形式参数值的改变将会影响实际参数的值。
     */
    // 在函数中传递的是基本数据类型
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        change(a, b);

        System.out.println("a=" + a); // 3
        System.out.println("b=" + b); // 4

        int[] count = {1, 2, 3, 4, 5};
        change(count);
        System.out.println(count[0]); // 6
    }

    /**
     * 结果为:
     * a=3
     * b=4
     * <p>
     * 原因：参数中传递的是基本数据类型a和b的拷贝，在函数中交换的也是那份拷贝的值，而不是数据本身。
     */
    public static void change(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    /**
     * 原因：在方法中，传递引用数据类型int数组，实际上传递的是该数组的地址值，他们都指向数组对象，在方法中可以改变数组对象的内容。
     */
    public static void change(int[] counts) {
        counts[0] = 6;
    }

    // 传递的是对象的引用
    class A {
        int i = 0;
    }

    //public class Test {
    // 结果为：
    //   1
    //   0
    //
    //原因：在该程序中，对象的引用指向的是A，而在add方法中，传递的引用的一份副本则指向了一个新的对象，
    //并对新的对象进行操作。而原来的A对象并没有发生任何变化。引用指向的是还是原来的A对象。
    //    public static void main(String args[]) {
    //        A a = new A();
    //        add(a); // 1
    //        System.out.println(a.i);  // 0
    //    }
    //
    //    public static void add(A a) {
    //        a = new A();
    //        a.i++;
    //        System.out.println(a.i);
    //    }
    //}


    // String 不改变，数组改变
    //原因：String类比较特别，看过String类代码的都知道，String类是final修饰的。所以值是不变的。
    //函数中String对象引用的副本指向了另外一个新String对象，而数组对象引用的副本没有改变，而是改
    // 变对象中数据的内容。
    //对于对象类型，也就是Object的子类，如果你在方法中修改了它的成员的值，那个修改是生效的，方法
    // 调用结束后，它的成员是新的值；但是如果你把它指向一个其它的对象，方法调用结束后，原来对它的
    //  引用并没用指向新的对象。
    //public class Example {
    //    public static void main(String args[]) {
    //        Example ex = new Example();
    //        ex.change(ex.str, ex.ch);
    //        System.out.print(ex.str + " and "); // good and
    //        System.out.println(ex.ch); // gbc
    //    }
    //
    //    String str = new String("good"); // good 是常量，不能改
    //
    //    char[] ch = { 'a', 'b', 'c' };
    //
    //    public void change(String str, char ch[]) {
    //        str = "test ok"; // test ok 又是一个常量，也不能改 　　等价于 String str1 = new String("test ok");
    //        ch[0] = 'g';
    //    }
    //}



}
