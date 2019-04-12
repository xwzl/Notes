package com.java.pattern.decorator;

/**
 * @author xuweizhi 装饰方法
 * @date 2018/12/14 16:05
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }

}
