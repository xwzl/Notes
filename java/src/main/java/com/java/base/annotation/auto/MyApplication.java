package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 自定义启动类
 *
 * @author xuweizhi
 * @date 2019/04/11 19:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MyApplication {

    /**
     * 自定义包扫描
     */
    String packageName() default "";

    /**
     * 设置配置文件加载路径，默认为application.properties
     */
    String loadResources() default "application.properties";

}
