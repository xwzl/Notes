package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 按别名注入，是{@link MyAutowired} 和 {@link MyQualifier}
 *
 * @author xuweizhi
 * @date 2019/04/14 9:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyResource {

    /**
     *  其值为目标类的别名或者名称
     */
    String value();
}
