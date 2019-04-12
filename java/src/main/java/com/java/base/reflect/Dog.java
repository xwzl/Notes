package com.java.base.reflect;

import com.java.mybatis.model.User;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xuweizhi
 * @date 2019/04/09 16:43
 */
public class Dog extends Animal<User> {


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Dog dog = new Dog();
        dog.getT();
        System.out.println(dog.getT());
    }


}
