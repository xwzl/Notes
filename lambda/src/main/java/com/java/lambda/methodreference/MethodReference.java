package com.java.lambda.methodreference;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author xuweizhi
 * @date 2018/11/26 16:01
 */
public class MethodReference {

    /**
     * 方法引用是lambda表达式的特殊情况或者语法糖 obj.method 方法引用
     * 方法引用有四类：
     * 1. 类名::静态方法名
     * 2. 引用::实例方法名
     * 3. 类名::实名方法名
     * 4. 构造方法引用
     */
    @Test
    public void testMethodReference() {

        List<String> lists = Arrays.asList("a", "b", "c", "d", "f");
        List<String> collect = lists.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect.toString());

        //方法引用可以看作是一个指针 function pointer 指向另外一个函数的指针
        lists.forEach(System.out::println);
    }

    @Test
    public void testStudent() {
        Student student1 = new Student("张三", 12);
        Student student2 = new Student("发啊", 8);
        Student student3 = new Student("都是", 113);
        Student student4 = new Student("大叔", 24);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        list.sort(Student::compareStudentByName);
        list.forEach(System.out::println);
        list.sort(Student::compareStudentByScore);
        list.forEach(System.out::println);
        System.out.println(get(Student::new));
    }


    public Student get(Supplier<Student> supplier) {
        return supplier.get();
    }
}
