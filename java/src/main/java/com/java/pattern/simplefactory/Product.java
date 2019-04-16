package com.java.pattern.simplefactory;

/**
 * @author xuweizhi
 */
public abstract class Product {
    /**
     * 所有产品类的公共业务方法
     */
    public void methodSame() {
        //公共方法的实现
        System.out.println("采购原材料");
    }

    /**
     * 声明抽象业务方法
     */
    public abstract void methodDiff();
}