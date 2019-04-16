package com.java.pattern.factorymethod;


/**
 * @author xuweizhi
 */
public class FactoryA implements Factory {
    @Override
    public Product factoryMethod() {
        return new ProductA();
    }
}