package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * 定义包扫描注解
 *
 * @author xuweizhi
 * @date 2019/04/11 19:33
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponent {

    /**
     * 注册别名，默认为当前类全类名路径
     */
    String alias() default "";
}
