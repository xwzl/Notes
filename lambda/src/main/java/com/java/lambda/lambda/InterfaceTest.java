package com.java.lambda.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xuweizhi
 * @date 2018/11/19 13:02
 */
public class InterfaceTest {

    /**
     * lambda可以认为是接口实现
     */
    public static void main(String[] args) {

        InterfaceTest interfaceTest = new InterfaceTest();
        interfaceTest.fix(() -> {
            System.out.println("你好啊");
        });

        interfaceTest.fix(new MyInterfaces() {
            @Override
            public void myTests() {
                System.out.println("匿名内部类的实现");
            }
        });

        //遍历集合的前后今生
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        //JDK1.5之前
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("---------------------");

        //JDK1.5 增强for循环遍历
        for (Integer i : list){
            System.out.println(i);
        }
        System.out.println("---------------------");

        //JDK1.8 匿名内部类遍历
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("---------------------");

        //JDK1.8 lambda表达式

        list.forEach(i -> System.out.println(i));
        System.out.println("---------------------");

        list.forEach(System.out::println);
        System.out.println("---------------------");

    }

    //利用lambda表达式简化接口的实现过程
    void fix(MyInterfaces myInterface) {
        System.out.println("11111111111");
        myInterface.myTests(); //调用传入接口的方法 即实现类的方法
        System.out.println("22222222222");
    }
}

/**
 * 函数式接口
 * 1.如果一个接口仅有一个抽象方法，称之为函数式接口
 * 2.如果某一个接口被注解FunctionInterface声明，我们称之为函数式接口
 * 3.如果某一个接口只有一个抽象方法，但我们并没有给他声明FunctionInterface注解，那么编译已经会认为他是函数式接口
 * 4.如果接口中的方法是Object类的方法，那么编译器会会忽略它，并不会增加一个方法。
 */
@FunctionalInterface
interface MyInterfaces {

    void myTests();

    String toString();

}
