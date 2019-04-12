package com.java.lambda.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Stream 流对象初认知
 *
 * @author xuweizhi
 * @date 2018/11/20 9:25
 */
public class StreamListWithLambda {


    /**
     * Java与众多把函数作为一等公民的语言中不一样的是，lambda表达式依赖于对象，编译器需要根据
     * 上下文推断类型 LambdaInterface LambdaInterface2 就是其上下文
     */
    @Test
    public void lambdaObject() {

        LambdaInterface lambdaInterface = () -> {
        };

        LambdaInterface2 lambdaInterface2 = () -> {
        };

    }

    @Test
    public void arrayaboutstreamtest() {
        List<String> list = Arrays.asList("Hello", "Nice", "Jack");
        list.forEach((item) -> {
            System.out.println(item.toUpperCase());
        });

        /**
         * list.stream() 串行流 单线程
         * list.parallelStream()并行流 多线程
         * 中间流 返回Stream对象 和节点流
         */
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    @FunctionalInterface
    interface LambdaInterface {

        void say();

    }

    @FunctionalInterface
    interface LambdaInterface2 {

        void say();

    }

}
