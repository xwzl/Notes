package com.java.base.annotation;

import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.auto.MyLocalMethod;
import com.java.base.annotation.auto.MyLocalMethodReinforce;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/11 19:21
 */
@MyComponent
public interface Mapper {

    /**
     * 打印博客信息
     */
    @MyLocalMethod(value = "我们都是好孩子", className = "com.java.base.annotation.Blog",
            methodName = "fineBlog", methodParamClass = {"java.lang.String", "java.lang.String"},
            methodParamValues = {"欢迎来到 Java 技术栈博客专栏", "111"})
    void getBlog(Blog blog, String title, String records);

    @MyLocalMethodReinforce(className = "com.java.base.annotation.Blog")
    void fineBlog(String title, String records);

    @MyLocalMethodReinforce(className = "com.java.base.annotation.Blog")
    void badBlog(Date date);
}
