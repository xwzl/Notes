package com.java.base.common;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author xuweizhi
 * @date 2019/03/14 10:27
 * @description
 */
public class StringTest {

    class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    @Test
    public void exception() {
        try {
            int[] a = new int[10];
            a[0]++;
            System.out.println(a[0]);
            throw new MyException("这个类型检查错误");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void api1() throws UnsupportedEncodingException {
        System.out.println(reverse(null));
        String encode = "dfasdfa";
        //String s = new String(encode.getBytes("utf-8"), "ISO-8859-1");
        //System.out.println(s);
    }


    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    @Test
    public void test() {

        String a1 = new String("qwqw");
        String a2 = "qwqw";

        //false
        System.out.println(a1 == a2);
        //true
        System.out.println(a1.intern() == a2);

        //false为什么错误呢？
        System.out.println(a1.intern() == a1);

        String value = new StringBuilder().append("ja").append("va").toString();
        //false java返回常量池中的索引
        // value 指向堆中的内存，value.intern() 指向常量池索引
        System.out.println(value.intern() == value);
        System.out.println(value.hashCode());
        System.out.println(value.intern().hashCode());
        System.out.println("java".hashCode());
        String java = new String("java");

        System.out.println(java.hashCode());
        System.out.println(java == value);

        String value1 = new StringBuilder().append("VV").append("VV").toString();
        //true "VVVV"在常量池中首次，直接返回value1的地址(intern方法返回常量中索引)
        System.out.println(value1.intern() == value1);
        System.out.println(value1.hashCode());
        System.out.println(value1.intern().hashCode());

        String main = new String("ma") + new String("in");
        System.out.println(main.intern() == main);

        String main1 = new String("ma1") + new String("in");
        System.out.println(main1.intern() == main1);
    }

}
