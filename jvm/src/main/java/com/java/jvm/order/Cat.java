package com.java.jvm.order;

/**
 * @author xuweizhi
 * @date 2019/02/22 14:20
 */
public class Cat {

    public Cat(){
        System.out.println("Cat is load by :"+this.getClass().getClassLoader());
        System.out.println("form Cat:" + Sample.class);
    }

}
