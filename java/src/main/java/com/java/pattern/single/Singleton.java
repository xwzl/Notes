package com.java.pattern.single;

/**
 * 懒汉式
 *
 * @author xuweizhi
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}