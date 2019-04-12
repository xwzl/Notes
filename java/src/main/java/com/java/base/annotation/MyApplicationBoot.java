package com.java.base.annotation;

import com.java.base.annotation.auto.MyApplication;
import com.java.base.annotation.factory.MyBeanFactory;

/**
 * @author xuweizhi
 * @date 2019/04/12 12:08
 */
@MyApplication
public class MyApplicationBoot {

    public static void main(String[] args) {
        MyBeanFactory factory = MyBeanFactory.run(MyApplicationBoot.class, args);
        Dog dog= (Dog)factory.getBean(Dog.class);
    }
}
