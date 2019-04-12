package com.java.jvm.bytecode;

/**
 * invokeinterface：调用接口中的方法，实际上是在运行期决定的，决定到底调用实现改接口的哪个对象的特定方法，JDK1.8之后有默认方法。
 * invokestatic：调用特定的静态方法。
 * invokespecial：调用自己的私有方法、构造方法<init>,以及父类的方法。
 * invokevirtual：调用虚方法，与多态紧密相关，运行期动态查找的过程。实现接口或者继承类的某个类的具体方法。
 * incokedynamic：动态调用方法。
 * <p>
 * 静态解析的四种情形
 * <p>
 * 1. 静态方法
 * 2. 父类方法
 * 3. 构造方法
 * 4. 私有方法
 * <p>
 * 以上4类方法称作非虚方法，他们是在类加载阶段就可以将符号引用转换为直接引用的。
 * <p>
 * 方法调用的静态分派
 *
 * GrandPa grandPa1 = new Father();
 *
 * 1.本质上是GrandPa是grandPa1声明的类型，声明的类型称之为静态类型
 * 2.new Father()实际上是grandPa1指向的实际类型
 * 3.以上代码，gi的静态类型是GrandPa，而grandPa1的实际类型（真正指派的类型）是Father
 * 我们可以得到这样一个结论：变量的静态类型是不会发生变化的，而变量的实际类型是会发生变化的（多态的一种体现），实际类型实在运行期才能确定的。
 *
 * 方法重载是一种静态的行为，唯一判断的依据是根据方法声明的类型参数类型来调用哪个方法
 */
public class MyTest4 {

    public static void test() {
        System.out.println("test invoked");
    }

    public static void main(String[] args) {
        //test();

        GrandPa grandPa1 = new Father();
        GrandPa grandPa2 = new Son();

        MyTest4 myTest4 = new MyTest4();

        myTest4.test(grandPa1);
        myTest4.test(grandPa2);
    }

    //方法重载是一种静态的行为，唯一判断的依据是根据方法声明的类型参数类型来调用哪个方法
    public void test(Son son) {
        System.out.println("son");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(GrandPa grandPa) {
        System.out.println("grandPa");
    }

}

class GrandPa {

}

class Father extends GrandPa {

}

class Son extends Father {

}
