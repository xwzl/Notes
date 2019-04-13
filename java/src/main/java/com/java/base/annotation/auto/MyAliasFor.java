package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/14 1:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyAliasFor {

    Class<? extends Annotation> annotation() default Annotation.class;
}
