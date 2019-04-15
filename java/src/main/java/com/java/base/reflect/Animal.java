package com.java.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取泛型参数的真实值
 *
 * @author xuweizhi
 * @date 2019/04/09 16:38
 */
public abstract class Animal<T> {

    Class<T> clazz;

    private Type rawType;

    public Animal() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.rawType = parameterizedType.getActualTypeArguments()[0];
            if (rawType instanceof Class) {
                this.clazz = (Class<T>) rawType;
            }
        }
    }

    public T getT() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return clazz.getDeclaredConstructor().newInstance();
    }

}
