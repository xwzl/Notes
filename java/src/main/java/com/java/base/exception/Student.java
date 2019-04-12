package com.java.base.exception;

import com.java.base.common.Human;

/**
 * @author xuweizhi
 * @date 2018/12/15 20:18
 */
public class Student extends Human {

    {
        System.out.println("子类构造代码块" + index++);
    }

    static {
        System.out.println("子类静态代码块：" + index++);
    }

    public Student() {
        System.out.println("子类无参构造函数：" + index++);
    }

    public Student(String name) {
        super(name);
        System.out.println("子类有参构造函数：" + index++);
    }

    public static void main(String[] args) {
        Student student = new Student("sss");
    }
}
