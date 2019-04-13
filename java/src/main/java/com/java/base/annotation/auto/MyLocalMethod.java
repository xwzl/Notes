package com.java.base.annotation.auto;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义注解,根据定义的注解信息，利用反射的方式调用目标类的方法
 * 但是不会调用接口注入的参数，是其注解注入的参数
 *
 * @author xuweizhi
 * @date 2019/04/11 19:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyLocalMethod {

    /**
     * 随便定义的注解，互为别名的属性必须定义默认值
     */
    @AliasFor("methodName")
    String value() default "默认属性";

    /**
     * 方法名
     */
    @AliasFor("value")
    String methodName() default "默认属性";

    /**
     * 注解调用某个类
     */
    String className() default "";

    /**
     * 调用方法的参数
     */
    String[] methodParamClass() default {};

    /**
     * 调用方法需要的参数值
     */
    String[] methodParamValues() default {};

    /**
     * 无法忽视的力量
     */
    String description() default "";

}
