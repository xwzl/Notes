package com.java.pattern.simplefactory.sample;

/**
 * @author xuweizhi
 */
public class Client {

    public static void main(String[] args) {
        Chart chart;
        //通过静态工厂方法创建产品
        chart = ChartFactory.getChart("histogram");
        chart.display();
    }

}