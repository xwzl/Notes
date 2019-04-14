package com.java.base.annotation.auto;

import java.lang.annotation.*;

/**
 * 按类型注入，如果一个接口有多个是实现类，必须配合 {@link MyQualifier} 使用
 * {@link MyQualifier#value()} 值为实现类的名称 或者 别名
 *
 * @author xuweizhi
 * @date 2019/04/11 20:03
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAutowired {

}
