package com.java.base.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xuweizhi
 * @date 2019/03/15 17:31
 * @description 我们都是好孩子
 */
public class ThreeDay {


    /**
     *
     * 1. 调用Class类的getDeclaredMethod可以获取指定方法名和参数的方法对象Method。
     * 2. 其中privateGetDeclaredMethods方法从缓存或JVM中获取该Class中申明的方法列表，
     * searchMethods方法将从返回的方法列表里找到一个匹配名称和参数的方法对象。
     * 3. 如果找到一个匹配的Method，则重新copy一份返回，即Method.copy()方法
     * 4. 所次每次调用getDeclaredMethod方法返回的Method对象其实都是一个新的对象，且新对
     * 象的root属性都指向原来的Method对象，如果需要频繁调用，最好把Method对象缓存起来。
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ReflectMethodUnderlying method = new ReflectMethodUnderlying();
        Method my = method.getClass().getDeclaredMethod("my", null);
        my.invoke(method, null);

    }

}

/**
 * 1. privateGetDeclaredMethods 从缓存或JVM中获取该Class中申明的方法列表，实现如下：
 * 2. 其中reflectionData()方法实现如下：
 * 3. 这里有个比较重要的数据结构ReflectionData，用来缓存从JVM中读取类的如下属性数
 * 从reflectionData()方法实现可以看出：reflectionData对象是SoftReference类型的，说明在内存紧张时可能会被回收，
 * 不过也可以通过-XX:SoftRefLRUPolicyMSPerMB参数控制回收的时机，只要发生GC就会将其回收，如果reflectionData被
 * 回收之后，又执行了反射方法，那只能通过newReflectionData方法重新创建一个这样的对象了，
 * 4. 在privateGetDeclaredMethods方法中，如果通过reflectionData()获得的ReflectionData对象不为空，则尝试从
 * ReflectionData对象中获取declaredMethods属性，如果是第一次，或则被GC回收之后，重新初始化后的类属性为空，则需要
 * 重新到JVM中获取一次，并赋值给ReflectionData，下次调用就可以使用缓存数据了

 *
 */
class ReflectMethodUnderlying {

    void my() {
        System.out.println("HaHa");
    }

}
