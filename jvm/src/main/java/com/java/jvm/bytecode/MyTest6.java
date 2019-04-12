package com.java.jvm.bytecode;

import java.util.Date;

public class MyTest6 {

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.test("hello");
        dog.test(new Date());

        animal.test(animal);
        animal.test(dog);

    }

}

class Animal {

    public void test(String str) {
        System.out.println("animal str");
    }

    public void test(Date date) {
        System.out.println("animal date");
    }

    public void test(Animal animal) {
        System.out.println("p animal");
    }

    public void test(Dog dog) {
        System.out.println("p animal");
    }
}

class Dog extends Animal {


    @Override
    public void test(String str) {
        System.out.println("dog str");
    }

    @Override
    public void test(Date date) {
        System.out.println("dog date");
    }

    @Override
    public void test(Animal animal) {
        System.out.println("c animal");
    }

    @Override
    public void test(Dog dog) {
        System.out.println("c animal");
    }

}
