package com.java.base.reflect;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 通过反射调用 接口的默认方法
 *
 * @author xuweizhi
 * @date 2019/04/10 9:57
 */
public class DefaultMethodTest {

    public static void main(String[] args) throws NoSuchMethodException {
        DefaultMethod myDefault = new MyDefaultImpl();
        DefaultMethod proxy = (DefaultMethod) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), myDefault.getClass().getInterfaces(), new MyDefaultProxy());
        proxy.getName();

    }
}

class MyDefaultImpl implements DefaultMethod {

    @Override
    public List<Object> getAll() {
        return null;
    }
}

class MyDefaultProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isDefault()) {
            Class<DefaultMethod> clazz = DefaultMethod.class;

            Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                    .getDeclaredConstructor(Class.class, int.class);
            constructor.setAccessible(true);
            Class<?> declaringClass = method.getDeclaringClass();
            int allModes = MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE;
            return constructor.newInstance(declaringClass, allModes)
                    .unreflectSpecial(method, declaringClass)
                    .bindTo(proxy)
                    .invokeWithArguments(args);
        }
        return null;
    }
}