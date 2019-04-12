package com.java.lambda.base;


import com.java.lambda.methodreference.Student;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.System.out;

/**
 * @author xuweizhi
 * @date 2018/11/29 9:25
 */
public class ScannerTest<H> {

    private H md;

    public static void main(String[] args) {

        //scannerMethod();
        //bigDecimal();
        //arrayBase();
        //say();
        //asserts();
        Student student = null;
        out.println('a');
        int[] arrays = {1, 2, 3, 4};
            ScannerTest<String> test = new ScannerTest<>();
        out.println(getMd(new Date()));
    }




    public static <H>H getMd(H h) {
        return h;
    }

    public static void asserts() {
        double d = sqrt(12) + abs(12);
        assert (d > 10);
        out.println(d);
    }

    public static void arrayBase() {
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Integer[] ints2 = Arrays.copyOf(ints, ints.length);
        Integer[] ints3 = ints;
        ints[2] = 5;
        ints2[3] = 1;
        out.println(ints);
        out.println(ints2.getClass());
        out.println(ints3.getClass());
        for (int i = 0; i < ints.length; i++) {
            out.println(ints[i] + " " + ints2[i] + ints3[i]);

        }
    }

    public static void bigDecimal() {
        BigDecimal bigDecimal1 = new BigDecimal(10);
        BigDecimal bigDecimal2 = new BigDecimal(1);
        BigDecimal bigDecimal3 = new BigDecimal(2.00);
        BigDecimal bigDecimal4 = new BigDecimal(12);
        //不同精度的值，值相等，就相等 -1 <; 0 =; 1 >
        int i = bigDecimal3.compareTo(bigDecimal2);
        BigDecimal bigDecimal = BigDecimal.valueOf(12);
        out.println(i);
    }

    public static void scannerMethod() {
        Scanner scanner = new Scanner(System.in);
        out.println("请输入你的年龄！");
        String age = scanner.nextLine();
        out.println("你的年龄是" + age + "岁吗？Yes or No ！");
        String flag = scanner.nextLine();
        if ("yes".equalsIgnoreCase(flag)) {
            out.println("注册成功");
        } else {
            out.println("注册失，请重新注册！");
        }
        out.println("This is a bad message ！");
    }
}
