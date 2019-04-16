package com.java.pattern.factorymethod;

/**
 * @author xuweizhi
 */
public class Client {
    public static void main(String[] args) {
        //可通过配置文件实现
        Factory factory = new FactoryA();
        Product product = factory.factoryMethod();
        product.method();
    }
}