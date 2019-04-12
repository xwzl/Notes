package com.java.pattern.decorator;

/**
 * @author xuweizhi
 * @date 2018/12/14 16:17
 */
public class ComponetTest {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponet()));
        component.doSomething();
    }
}
