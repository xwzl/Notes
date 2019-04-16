package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * 注解测试
 *
 * @author xuweizhi
 * @date 2019/04/14 0:42
 */
@Documented
// 注解可被子类继承
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Repeatable 括号内的就相当于用来保存该注解内容的容器
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {

    String value() default "";

    MyService[] component() default {};
}
