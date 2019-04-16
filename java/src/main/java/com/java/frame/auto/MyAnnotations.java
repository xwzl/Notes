package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * 用于目标注解多次使用
 *
 * @author xuweizhi
 * @date 2019/04/14 0:50
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotations {

    /**
     * 注解方法必须为 value 值，否则会报错
     */
    MyAnnotation[] value();

}
