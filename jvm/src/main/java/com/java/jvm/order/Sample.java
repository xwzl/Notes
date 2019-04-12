package com.java.jvm.order;

/**
 * @author xuweizhi
 * @date 2019/02/22 14:20
 */
public class Sample {

    public Sample() {
        System.out.println("Sample is load by : " + this.getClass().getClassLoader());
        new Cat();
        System.out.println("from Cat:" + Cat.class);
    }

}
