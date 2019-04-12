package com.java.base.reflect;

/**
 * @author Administrator
 */
public class Child extends Parent<Dog> {

    public Child(Class<?> clazz) {
        super(clazz);
    }
}