package com.java.pattern.decorator;

import java.util.Scanner;

/**
 * @author xuweizhi
 * @date 2018/12/14 16:11
 */
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doOtherThing();
        aiImpl();
    }

    private void doOtherThing() {
        System.out.println("我是一个坏孩子");
    }

    /**
     * 据说价值一个亿的AI代码
     */
    private void aiImpl() {

        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            s = scanner.nextLine();
            s = s.replaceAll("HP", "*");
            s = s.replaceAll("MP", "*");
            s = s.replaceAll("HMP", "*");
            s = s.replaceAll("GP", "*");
            s = s.replaceAll("主席", "*");
            s = s.replace("吗", "");
            s = s.replace("?", "!");
            s = s.replace("？", "!");
            System.out.println(s);
            if (s.equals("end")) {
                break;
            }
        }

    }
}
