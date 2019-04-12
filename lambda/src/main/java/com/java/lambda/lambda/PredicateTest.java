package com.java.lambda.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author xuweizhi
 * @date 2018/11/23 13:54
 */
public class PredicateTest {

    @Test
    public void text() {
        Predicate<String> p = p1 -> p1.length() > 5;
        System.out.println(p.test("Will Test"));
        Predicate<String> p2 = p3 -> p3.length() < 10;
        System.out.println(p.and(p2).test("Hell"));
        System.out.println(p.and(p2).test("Hello Map"));
        System.out.println(p.and(p2).test("Hello Maps A V"));
        char b = "ss".equals("x") ? 'a' : 'c';
    }

    /**
     * 测试预定义函数接口
     */
    @Test
    public void test2() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        conditionFilter(lists, integer -> integer > 5);
        System.out.println(Predicate.isEqual("people").test("People"));
    }

    public void conditionFilter(List<Integer> lists, Predicate<Integer> predicate) {
        lists.forEach(integer -> {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        });
    }

}
