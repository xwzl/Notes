package com.java.jvm.bytecode;

/**
 * @author xuweizhi
 * @date 2019/03/07 23:54
 */
public class RealSubject implements SubJect {
    @Override
    public void request() {
        System.out.println("From real subject !");
    }
}
