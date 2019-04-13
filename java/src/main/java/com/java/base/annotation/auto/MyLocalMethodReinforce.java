package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/13 21:11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyLocalMethodReinforce {

    /**
     * 反射调用的类，必须赋值
     */
    String className();

    /**
     * 如果接口的方法名与被调用者的方法名不同，必须设置
     */
    String methodName() default "";
}
