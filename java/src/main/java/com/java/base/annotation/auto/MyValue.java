package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/11 20:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MyValue {

    /**
     * 用于注入 application.properties 属性值，假如你有指定的话
     */
    String value() default "";
}
