package com.java.pattern.factorymethod;


/**
 * @author xuweizhi
 */
public class FactoryB implements Factory {
    @Override
    public Product factoryMethod() {
        return new ProductB();
    }
}