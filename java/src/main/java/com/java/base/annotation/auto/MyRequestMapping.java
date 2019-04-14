package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/14 15:04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MyRequestMapping {

    String value();
}
