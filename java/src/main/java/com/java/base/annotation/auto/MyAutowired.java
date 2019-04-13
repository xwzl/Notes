package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 自定义自动注入
 *
 * @author xuweizhi
 * @date 2019/04/11 20:03
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAutowired {
}
