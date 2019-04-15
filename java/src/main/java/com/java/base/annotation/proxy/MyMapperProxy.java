package com.java.base.annotation.proxy;

import com.java.base.annotation.ioc.MyLocalMethodMapping;
import com.java.base.annotation.util.StringUntils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 自定义MethodInterceptor
 * 这个接口只有一个intercept()方法，这个方法有4个参数：
 * 1. obj表示增强的对象，即实现这个接口类的一个对象；
 * 2. method表示要被拦截的方法；
 * 3. args表示要被拦截方法的参数；
 * 4. proxy表示要触发父类的方法对象；
 *
 * @author xuweizhi
 * @date 2019/04/13 20:04
 */
public class MyMapperProxy implements MethodInterceptor {

    private Map<String, Object> mapper;

    private Class<?> clazz;

    public MyMapperProxy(Map<String, Object> mapper, Class<?> clazz) {
        this.mapper = mapper;
        this.clazz = clazz;
    }

    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        String methodKey = this.clazz.getName() + "#" + method.getName();
        Object invoke = null;
        Class<?> clazz = null;
        Method proxyMethod = null;
        Object newInstance = null;

        // 解析 MyLocalMethod 注解
        Object obj = mapper.get(methodKey);
        if(obj instanceof MyLocalMethodMapping){
            MyLocalMethodMapping mapping = (MyLocalMethodMapping) obj;
            if (mapping != null) {
                clazz = Class.forName(mapping.getClassName());
                newInstance = clazz.getDeclaredConstructor().newInstance();
                proxyMethod = clazz.getDeclaredMethod(mapping.getMethodName(), mapping.getMethodParamClass());
                proxyMethod.setAccessible(true);
                invoke = proxyMethod.invoke(newInstance, (Object[]) mapping.getMethodParamValues());
            }
            return invoke;
        }


        String reinforceKey = this.clazz.getName() + "&" + method.getName();
        // 解析 MyLocalMethodReinforce 注解
        obj = (MyLocalMethodMapping) mapper.get(reinforceKey);
        if(obj instanceof MyLocalMethodMapping){
            MyLocalMethodMapping mapping = (MyLocalMethodMapping) obj;
            if (mapping != null) {
                mapping = (MyLocalMethodMapping) mapper.get(reinforceKey);
                clazz = Class.forName(mapping.getClassName());
                if (StringUntils.isNotEmpty(mapping.getMethodName())) {
                    proxyMethod = clazz.getDeclaredMethod(mapping.getMethodName(), getClasses(objects));
                } else {
                    proxyMethod = clazz.getDeclaredMethod(method.getName(), getClasses(objects));
                }
                proxyMethod.setAccessible(true);
                newInstance = clazz.getDeclaredConstructor().newInstance();
                invoke = proxyMethod.invoke(newInstance, objects);
            }
        }
        return invoke;
    }

    private Class<?>[] getClasses(Object[] objects) {
        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }

}
