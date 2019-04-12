package com.java.jvm.bytecode;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author xuweizhi
 * @date 2019/03/07 23:58
 * <p>
 * CGLB:是采用继承代理类，重写代理类的方法，既可以调用代理类的方法又可以调用重写的方法。
 */
public class Client {

    public static void main(String[] args) {
        //打印动态代理生成的文件，修改系统的属性,JDK1.8版本
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //JDK1.11
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        RealSubject sb = new RealSubject();
        InvocationHandler ih = new DynamicSubject(sb);
        Class<?> cls = sb.getClass();
        SubJect subject1 = (SubJect) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ih);
        subject1.request();
        System.out.println(subject1.getClass());
        System.out.println(subject1.getClass().getSuperclass());
    }

}

class StringEquals {


    public static void main(String[] args) {
        //== 引用或者变量比较地址值 equals比较引用变量的内容（基本类型直接比较值）
        //字符串常量池不存在b,JVM字符串常量池中创建一个String b,并把引用指向a
        String a = "b";
        //字符串常量池存在b,JVM不创建b,直接把常量池中b的索引指向b
        String b = "b";
        //new 方式创建的字符串，直接在堆中创建一个新对象,不去常量池中检索
        String c = new String("b");
        String d = new String("b");
        //itern方法会检索常量池中是否存在b,存在直接返回常量池中"b"的引用，负责新创建变量
        String e = new String("b").intern();
        System.out.println(a == b);//true
        System.out.println(a == c);//false
        System.out.println(a.equals(c));//true 内容相同

        //以下讨论String a = "ab";的问题
        String x = "x";
        String y = "y";
        String xy = "xy";

        //从字节码角度探讨,无引用变量参与的字符串拼接，若常量池中存在拼接的结果，不会新的字符串常量
        x = x + y;
        System.out.println(xy == x);
        x = "x" + "y";
        System.out.println(x == xy);
        x = "x" + y;
        System.out.println(x == xy);
    }
}

@Data
class RefereceType {

    int a = 1;

    String b = "2";

    public static void main(String[] args) {

        RefereceType refereceType1 = new RefereceType();
        RefereceType refereceType2 = new RefereceType();

        System.out.println(refereceType1 == refereceType2);
        System.out.println(refereceType1.equals(refereceType2));

        refereceType1.setA(12);

        System.out.println(refereceType1 == refereceType2);
        System.out.println(refereceType1.equals(refereceType2));
    }

}

