package com.java.pattern.decorator;

/**
 * @author xuweizhi
 * @date 2018/12/14 16:04
 */
public class ConcreteComponet implements Component {

    @Override
    public void doSomething() {
      System.out.println("功能A");
    }

}
