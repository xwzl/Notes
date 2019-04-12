package com.java.lambda.lamdasource;


import com.java.lambda.methodreference.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @author xuweizhi
 * @date 2018/12/03 9:50
 */
public class StreamResource {

    public static void accept(Consumer<Integer> consumer, Integer index) {
        consumer.accept(index);
    }


    public static void main(String[] args) {

        //consumerTest();

        List<Student> list = Arrays.asList(
                new Student("张三", 91, 56),
                new Student("张三", 92, 56),
                new Student("张三", 93, 56),
                new Student("张三", 94, 56),
                new Student("张三", 95, 56),
                new Student("张三", 96, 56),
                new Student("张三", 97, 56));

        //流的执行流程 一次性操作一条记录所有的操作 -> 管道
        Map<Integer, Student> collect = list.stream().collect(HashMap<Integer, Student>::new,
                (map, student) -> map.put(student.getScore(), student),
                HashMap::putAll);

        System.out.println(collect);

        /*
         forEach 有两个实现类  head里面的实现
         Arrays ArrayList 实现
         AbstractPipeline 两个构造方法
         一个用来初始化话管道 一个用来对管道操作以及计数
         */
        list.stream().map(student -> student).forEach(System.out::println);

    }

    public static void consumerTest() {
        Consumer<Integer> consumer = System.out::println;
        IntConsumer intConsumer = System.out::println;

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

        //面向对象，传递的是对象的引用
        StreamResource.accept(consumer, 100);
        //StreamResource.accept((Consumer)intConsumer, 100);

        //函数式编程 传递方法行为
        StreamResource.accept(consumer::accept, 100);
        StreamResource.accept(intConsumer::accept, 100);
    }

}
