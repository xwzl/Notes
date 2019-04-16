package com.java.pattern.abstractfactory;

/**
 * @author xuweizhi
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}