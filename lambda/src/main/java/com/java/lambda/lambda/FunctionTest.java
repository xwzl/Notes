package com.java.lambda.lambda;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author xuweizhi
 * @date 2018/11/22 9:07
 */
public class FunctionTest {


    /**
     * Compose方法 先处理第二个参数方法 在返回第一个方法的对象
     * 高阶函数 方法之间传递函数或者返回函数
     * 返回调用compose对象的apply方法
     */
    public String compose(String str, Function<String, String> function1, Function<String, String> function2) {
        // 1.创建function1对象
        // 2.调用compose方法，传入function2方法的实例对象
        // 3.compose放首先调用function2方法的apply方法对参数做处理，然后把处理的值作为参数传入function1方法apply方法
        // 4.compose 方法返回function1方法的实例
        return function1.compose(function2).apply(str);
    }

    /**
     * 返回传入andThen的方法
     */
    public String andThen(String str, Function<String, String> function1, Function<String, String> function2) {
        //与compose方法相反
        //compose方法 先用传入的方法处理参数，然后在把参数传入自身的方法，再调用自身的方法
        //andThen与之相反，执行自身的方法，把返回值传入传入方法并把传入方法返回，在调用的方法就是传入的方法
        return function1.andThen(function2).apply(str);
    }

    @Test
    public void test() {
        // compose 和 andThen 都是返回Function方法apply的实现
        FunctionTest functionTest = new FunctionTest();
        System.out.println(functionTest.compose("入参1", s -> {
            System.out.println("调用自身方法");
            return "自身->" + s;
        }, s -> {
            System.out.println("调用传入方法");
            return "传入->" + s;
        }));

        System.out.println(functionTest.andThen("入参1", s -> {
            System.out.println("调用自身方法");
            return "自身->" + s;
        }, s -> {
            System.out.println("调用传入方法");
            return "传入->" + s;
        }));
    }

    @Test
    public void testBiFunction() {
        System.out.println(biFunction("Obj1", "Obj2", (s1, s2) -> s1 + s2));
        System.out.println(biFunction("Obj1", "Obj2", (s, s2) -> {
            System.out.println("biFunction方法");
            return s + s2;
        }, s -> {
            System.out.println("function Apply方法");
            return s+s;
        }));
    }

    public static String biFunction(String s1, String s2, BiFunction<String, String, String> biFunction) {

        return biFunction.apply(s1, s2);
    }

    /**
     * 返回BioFunctoin对象
     */
    public static String biFunction(String s1, String s2, BiFunction<String, String, String> biFunction, Function<String, String> function) {

        //     返回一个匿名函数
        return biFunction.andThen(function).apply(s1, s2);
    }
}
