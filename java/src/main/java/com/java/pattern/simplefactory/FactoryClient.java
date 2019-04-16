package com.java.pattern.simplefactory;

/**
 * @author xuweizhi
 */
public class FactoryClient {
    public static void main(String args[]) {
        Product product;
        //通过工厂类创建产品对象
        product = Factory.getProduct("A");
        product.methodSame();
        product.methodDiff();
        //通过工厂类创建产品对象
        product = Factory.getProduct("B");
        product.methodSame();
        product.methodDiff();
    }
}