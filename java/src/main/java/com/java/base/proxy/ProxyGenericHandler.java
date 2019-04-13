package com.java.base.proxy;


import java.lang.reflect.*;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 * @date 2019/03/16 14:24
 * @description
 */
public class ProxyGenericHandler<T, R> implements InvocationHandler {


    private Method[] methods;

    private R r;

    private T t;

    public ProxyGenericHandler(T t, R r) {

        /**
         * 获取需要增强方法列表
         */
        this.methods = r.getClass().getMethods();

        this.r = r;

        this.t = t;

        /**
         * 获取传入对象带有泛型参数的接口对象
         */
        Type[] genericInterfaces = t.getClass().getGenericInterfaces();

        for (Type type : genericInterfaces) {
            ParameterizedType param = (ParameterizedType) type;
            Type value = param.getActualTypeArguments()[0];
            System.out.println("实现接口的泛型参数类型 : " + value);
        }

    }

    public static void main(String[] args) {
        //ProxyGenericHandler proxyHandler = new ProxyGenericHandler(new Humans());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Stream.of(methods).forEach(method1 -> {
            if (method1.getName().equals(method.getName())) {
                try {
                    method1.setAccessible(true);
                    method.setAccessible(true);
                    method1.invoke(r, args);
                    method.invoke(t, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }
}
