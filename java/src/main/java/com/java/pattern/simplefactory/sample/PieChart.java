package com.java.pattern.simplefactory.sample;

/**
 * 饼状图类：具体产品类
 *
 * @author xuweizhi
 */
public class PieChart implements Chart {
    public PieChart() {
        System.out.println("创建饼状图！");
    }

    @Override
    public void display() {
        System.out.println("显示饼状图！");
    }
}
