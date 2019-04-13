package com.java.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuweizhi
 * @date 2019/03/16 11:35
 */
public class ProxyHandler implements InvocationHandler {

    private Humans humans;

    public ProxyHandler(Humans humans) {
        this.humans = humans;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sing".equals(method.getName())) {
            System.out.println("Sing:方法已经被增强");
            method.invoke(this.humans, args);
            System.out.println("Sing:方法增强结束");
        }
        if ("dance".equals(method.getName())) {
            System.out.println("Dance:方法已经被增强");
            method.invoke(this.humans, args);
            System.out.println("Dance:方法增强结束");
        }
        if ("hobby".equals(method.getName())) {
            System.out.println("Hobby:方法已经被增强");
            method.invoke(this.humans, args);
            System.out.println("Hobby:方法增强结束");
        }
        return null;
    }

    /**
     * 获取Human代理对象
     */
    public static Behavior getProxyBehavior(Class clazz, InvocationHandler handler) {
        return (Behavior) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }

    public static Interest getProxyInterest(Class clazz, InvocationHandler handler) {
        return (Interest) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}
