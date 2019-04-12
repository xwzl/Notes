package com.java.lambda.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * @author xuweizhi
 * @date 2018/11/26 13:56
 */
public class SupplierTest {

    @Test
    public void testGet() {
        Supplier<String> supplier = () -> "我是一个大宝贝儿！";
        Supplier<Person> supplier1 = Person::new;
        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }

    @Test
    public void binaryTest() {
        BinaryOperator<Integer> binaryOperator = (integer, integer2) -> integer + integer2 * integer;
        System.out.println(binaryOperator.apply(112, 2));
        String a = minBy("amy", "my1", (o1, o2) -> o1.codePointAt(0) - o2.codePointAt(0));
        System.out.println("a".codePointAt(0));
    }

    @Test
    public void testOptional() {
        String str = "Hello World !";
        Optional<String> optional = Optional.of(str);
        optional.ifPresent(System.out::println);

        List<String> list = new ArrayList<String>();
        Optional<List<String>> optional2 = Optional.of(list);
        System.out.println(optional2.map(strings -> "x").orElse("boy"));
    }

    public static String minBy(String obj1, String obj2, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(obj1, obj2);
    }
}
