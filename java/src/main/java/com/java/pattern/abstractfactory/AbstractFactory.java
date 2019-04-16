package com.java.pattern.abstractfactory;

/**
 * @author xuweizhi
 */
public abstract class AbstractFactory {
    /**
     * 1
     *
     * @param color 颜色
     * @return 1
     */
    abstract Color getColor(String color);

    /**
     * 1
     *
     * @param shape 形状
     * @return 1
     */
    abstract Shape getShape(String shape);
}