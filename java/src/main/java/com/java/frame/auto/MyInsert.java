package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/15 13:29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyInsert {

    String value();

    String nameSpace();

}
