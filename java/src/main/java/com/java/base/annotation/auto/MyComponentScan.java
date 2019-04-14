package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 自定义包扫描
 *
 * @author xuweizhi
 * @date 2019/04/14 9:48
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponentScan {

    /**
     * 自定义包扫描
     */
    String packageName() default "";

    /**
     * 指定类所属的包及子包被扫描，默认包路径以外的包也会被扫描
     */
    MyFilter[] includeFilters() default {};

    /**
     * 指定类属的包及子包不被扫描
     */
    MyFilter[] excludeFilters() default {};

}
