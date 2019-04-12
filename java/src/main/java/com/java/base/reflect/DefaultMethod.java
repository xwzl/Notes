package com.java.base.reflect;

import java.util.List;

/**
 * Java8 通过反射执行接口的default方法
 *
 * @author Administrator
 */
public interface DefaultMethod {

    /**
     * 通过反射调用默认方法
     */
    default String getName() {
        return "name";
    }

    /**
     * 正常方法
     */
    List<Object> getAll();
}