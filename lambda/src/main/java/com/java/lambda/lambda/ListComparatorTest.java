package com.java.lambda.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author xuweizhi
 * @date 2018/11/21 9:16
 */
public class ListComparatorTest {

    /**
     * List排序
     */
    @Test
    public void testSort() {
        List<String> lists = Arrays.asList("Nice", "Jack", "Mike", "Lucy");
        Collections.sort(lists, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(lists);

        Collections.sort(lists, (o1, o2) -> o1.compareTo(o2));
        System.out.println(lists);
    }

    /**
     * 测试functional函数接口
     * 根据源码可知，它接受一个参数，返回一个参数
     * lambda传递的是行为,JDK1.8之前传递的是值
     * lambda表达式相当于预定义的方法，行为完全由用户自定义
     */
    @Test
    public void testFunctional() {
        ListComparatorTest listComparatorTest = new ListComparatorTest();

        //express 表达式写法
        System.out.println(listComparatorTest.toDoNumber(2, integer -> integer * integer));

        //statement 写法
        System.out.println(listComparatorTest.toDoNumber(3, integer -> {
            return integer * integer;
        }));

        System.out.println(listComparatorTest.toDoString("My Function", s -> {
             return "这个真的很好用啊！";
        }));
    }

    public int toDoNumber(Integer i, Function<Integer, Integer> function) {
        return function.apply(i);
    }

    public String toDoString(String str, Function<String, String> function) {
        return function.apply(str);
    }
}
