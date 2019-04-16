package com.java.frame.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/15 13:28
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MySelect {

    /**
     * sql select * from user where id = #{id}
     * #{id} 入参属性名，其实可以做成别名，时间有限，待定 #{} 必须是 Java 对象的属性名
     * 目前没有动态 sql，sql 定义的参数必须全部传入
     */
    String value();

    /**
     * 增删改查实体的全类名
     */
    String nameSpace();
}
