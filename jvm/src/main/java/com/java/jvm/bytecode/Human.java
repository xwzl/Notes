package com.java.jvm.bytecode;

public class Human {
    public void sleep() {
        System.out.println("Human sleep..");
    }

    public static void main(String[] args) {
        // 向上转型
        Human h = new Male();
        Human h1 = new Human();
        //h.speak();此时需要向下转型，否则不能调用speak方法。
        Male m = (Male) h;
        m.speak();
        /**Male m1 = (Male)h1;
         m1.speak();    此时会出现运行时错误，所以可以用instanceOF判断*/
        if (h1 instanceof Male) {
            Male m1 = (Male) h1;
            m1.speak();

        }
    }
}

class Male extends Human {
    @Override
    public void sleep() {
        System.out.println("Male sleep..");
    }

    public void speak() {
        System.out.println("I am Male");
    }
}