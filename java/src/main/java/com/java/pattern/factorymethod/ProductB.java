package com.java.pattern.factorymethod;

/**
 * @author xuweizhi
 */
public class ProductB implements Product {
    @Override
    public void method() {
        System.out.println("生成B产品");
    }
}