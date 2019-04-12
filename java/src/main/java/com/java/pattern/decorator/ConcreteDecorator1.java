package com.java.pattern.decorator;

/**
 * @author xuweizhi
 * @date 2018/12/14 16:07
 */
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doOtherSomething();
    }

    private void doOtherSomething(){
        System.out.println("我们是一个好孩子！");
    }
}
