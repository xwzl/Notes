package com.java.jvm.classloader;

/**
 * 对数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.java.jvm.classloader.Parent4这种形式。
 * 动态生成的类型，其父类就是Object
 * <p>
 * 对于数组来说，JavaDoc经常将构成数组的元素为Component,实际上就是将数组降低一个维度后的类型。
 * 对于数组来说，它的类加载器与元素类型的类加载器一样，如果是原生类型则为空
 * <p>
 * anewarry：表示创建一个引用类型（如类、接口、数据）的数组，并将其引用值压入栈顶
 * newarry: 表示创建一个原始类型（如int、float、char等）的数组，并将其引用压入栈顶
 *
 * @author xuweizhi
 * @date 2019/02/19 15:27
 */
public class ClassActiveLoading4 {
    public static void main(String[] args) {
        Parent4 parent4 = new Parent4();

        Parent4[] parent4s = new Parent4[1];
        System.out.println("Java虚拟机运行期动态生成" + parent4s.getClass());

        Parent4[][] parent4s1 = new Parent4[1][1];
        System.out.println(parent4.getClass());

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getClassLoader());

        ClassActiveLoading1[] classActiveLoading1 = new ClassActiveLoading1[1];
        System.out.println(classActiveLoading1.getClass().getClassLoader());
    }
}

class Parent4 {
    static {
        System.out.println("YY");
    }
}
