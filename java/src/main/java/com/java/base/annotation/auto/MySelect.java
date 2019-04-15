package com.java.base.annotation.auto;

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
     * #{id} 入参属性名，其实可以做成别名，但是时间有限，暂停
     */
    String value();

    /**
     * 增删改查实体的全类名
     */
    String nameSpace();
}
