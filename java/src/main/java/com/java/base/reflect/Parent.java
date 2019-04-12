package com.java.base.reflect;

import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeReference;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xuweizhi
 * @date 2019/04/09 16:26
 */
public abstract class Parent<T> {

    private final Type rawType;

    public Parent(Class<?> clazz) {
        this.rawType = getSuperclassTypeParameter(clazz);
    }

    Type getSuperclassTypeParameter(Class<?> clazz) {
        // 【1】从父类中获取 <T>
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            // 能满足这个条件的，例如 GenericTypeSupportedInHierarchiesTestCase.CustomStringTypeHandler 这个类
            // try to climb up the hierarchy until meet something useful
            // 排除 TypeReference 类
            if (TypeReference.class != genericSuperclass) {
                return getSuperclassTypeParameter(clazz.getSuperclass());
            }

            throw new TypeException("'" + getClass() + "' extends TypeReference but misses the type parameter. "
                    + "Remove the extension or add a type parameter to it.");
        }

        // 【2】获取 <T>
        Type rawType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        // TODO remove this when Reflector is fixed to return Types
        // 必须是泛型，才获取 <T>
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
            System.out.println(rawType.getTypeName());
        }
        Class<T> type = (Class<T>) rawType;
        try {
            System.out.println(type.getDeclaredConstructor().newInstance());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return rawType;
    }

    public static void main(String[] args) {
        Child user = new Child(Child.class);
    }


}
