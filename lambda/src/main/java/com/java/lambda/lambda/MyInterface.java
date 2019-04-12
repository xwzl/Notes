package com.java.lambda.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

//函数式编程
public class MyInterface {

    public void testss(MyHours myhours) {
        System.out.println("这是一个函数编程，lambda表达式相当于接口的实现");
        myhours.test();
    }

    @Test
    public void test() {
        MyInterface myInterface = new MyInterface();
        myInterface.testss(() -> System.out.println("你是"));
        MyHours myHours = () -> System.out.println("HaHa");
        System.out.println(myHours.getClass());
        System.out.println(myHours.getClass().getInterfaces()[0]);
        System.out.println(myHours.getClass().getSuperclass());
    }

    /**
     * list集合遍历的前后今生
     **/
    @Test
    public void testList() {

        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        lists.forEach(System.out::println);
        //default 默认实现方法  传递的不是参数而是方法行为 this指的是调用当前方法的类
        //default void forEach(Consumer<? super T> action) {
        //        Objects.requireNonNull(action);//断言
        //        for (T t : this) {
        //            action.accept(t);
        //        }
        //    }
        //方法引用 :: == 函数式接口
        lists.forEach(System.out::println);
    }
}

/**
 * 1.一個以接口只有一个（不是Object对象的）抽象方法，它可有可无FunctionInteger注解标注
 * 2.被FunctionInterface标注的方法一定是函数式接口
 * 3.函数方法的实例可以通过lambda表达式，方法引用，和构造方法引用创建
 */
@FunctionalInterface
interface MyHours {

    void test();

    @Override
    String toString();
}