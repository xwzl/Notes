package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 按别名注入
 *
 * @author xuweizhi
 * @date 2019/04/14 9:36
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyQualifier {

    /**
     * 其值为目标类的别名或者名称
     */
    String value();
}
