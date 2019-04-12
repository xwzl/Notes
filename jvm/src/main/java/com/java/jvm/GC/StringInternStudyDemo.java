package com.java.jvm.GC;

public class StringInternStudyDemo {
    public static void main(String[] args) {
        printJdkVersion();
        testAndPrintResult("计算机", "软件");
        testAndPrintResult("ja", "va");
        testAndPrintResult("ma", "in");
    }

    private static void testAndPrintResult(String prefix, String suffix) {
        String str3 = new StringBuilder(prefix).append(suffix).toString();
        System.out.println(str3.intern() == str3);
    }

    private static void printJdkVersion() {
        String javaVersion = "java.version";
        System.out.println(javaVersion + ":" + System.getProperty(javaVersion));
    }
}

class StringTest {

    public static void main(String[] args) {
        String str1 = "计算机";
        String str2 = new String("计算机").intern();
        //返回常量池中的引用复制
        String str3 = new String("计算机2").intern();
        String str4 = "计算机2";
        System.out.println(str1==str2);
        System.out.println(str3==str4);
        //直接new 出来的对象不会放入字符串常量池
        String a1 = new String("Why");
        String a2 = "Why";
        System.out.println(a1==a2);
    }
}