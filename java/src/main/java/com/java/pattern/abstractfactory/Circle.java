package com.java.pattern.abstractfactory;

/**
 * @author xuweizhi
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}