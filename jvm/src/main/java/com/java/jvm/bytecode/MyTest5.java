package com.java.jvm.bytecode;

/**
 * 方法的动态分派涉及到一个很重要的概念：方法接收者，方法到底是由哪一个对象调用的。
 *
 * invokevirtual字节码指令的多态查找流程
 *
 * 先到操作栈数的栈顶（即第一个元素），寻找栈顶元素指向对象的实际类型。
 * 如果在这个实际类型当中，寻找到了与常量池中的描述符和名称都相同的方法，并且权限也是允许的，
 * 直接返回目标方法的引用。如果找不到，按照集成关系，从子类到父类重复查找的流程。
 */
public class MyTest5 {

    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();

        apple.test();
        orange.test();

        apple = new Orange();
        apple.test();
    }

}

class Fruit {
    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Orange");
    }
}
