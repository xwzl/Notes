package com.java.pattern.simplefactory;

/**
 * @author xuweizhi
 */
public class Factory {
    /**
     *    静态工厂方法
     */
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ProductA();
            //初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ProductB();
            //初始化设置product
        }
        return product;
    }
}