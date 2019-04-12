package com.java.base.enums;

import org.junit.Test;

/**
 * @author xuweizhi
 * @date 2019/04/09 16:01
 */
public class MyEnumTest {

    @Test
    public void test(){
        Class<MyEnum> enumClass = MyEnum.class;
        MyEnum one = Enum.valueOf(enumClass, "TWO");
        System.out.println(one);
        System.out.println(one.getClass());
    }

    @Test
    public void test2(){
        Class<CodeStateEnum> enumClass = CodeStateEnum.class;
        CodeStateEnum codeStateEnum = Enum.valueOf(enumClass, "SUMMER");
        System.out.println(codeStateEnum.getCode());
        System.out.println(codeStateEnum.getClass());

    }
}
