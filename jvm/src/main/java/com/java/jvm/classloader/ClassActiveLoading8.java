package com.java.jvm.classloader;

import java.util.UUID;

/**
 * @author xuweizhi
 * @date 2019/02/20 16:25
 */
public class ClassActiveLoading8 {

    public static void main(String[] args) {

        System.out.println(FinalTest.str);
       //System.out.println(FinalTest.string);
    }
}

class FinalTest {

    public static final String str = "YY";

    public static final String string = UUID.randomUUID().toString();

    static {
        System.out.println("我们都是一个好孩子！");
    }

}