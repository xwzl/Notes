package com.java.pattern.abstractfactory;

/**
 * @author xuweizhi
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}