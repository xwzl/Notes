package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * 排除不要扫描包
 *
 * @author xuweizhi
 * @date 2019/04/14 9:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyFilter {

    /**
     * 自定义需要排除的包,注解这个包必须被排除
     */
    Class<?> classTypePath();
}
