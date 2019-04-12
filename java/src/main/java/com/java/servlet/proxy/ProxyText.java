package com.java.servlet.proxy;


import java.lang.reflect.Proxy;

/**
 * @author xuweizhi
 * @date 2019/03/16 11:45
 */
public class ProxyText {

    public static void main(String[] args) {
        //Humans humans = new Humans();
        //Class<?> clazz = humans.getClass();
        //ProxyHandler proxyHandler = new ProxyHandler(humans);
        //Behavior Behavior = ProxyHandler.getProxyBehavior(clazz, proxyHandler);
        //Interest interest = ProxyHandler.getProxyInterest(clazz, proxyHandler);
        //interest.hobby("篮球");
        Humans humans = new Humans();

        ProxyGenericHandler<Humans, Interest> handler = new ProxyGenericHandler<>(humans, hobby -> {
            System.out.println("我们");
        });

        Interest interest=(Interest)Proxy.newProxyInstance(humans.getClass().getClassLoader(), humans.getClass().getInterfaces(), handler);
        interest.hobby("my");
    }

}
