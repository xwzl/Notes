package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/15 17:12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumn {

    /**
     * Java 字段对应数据库中名称
     */
    String value();

}
