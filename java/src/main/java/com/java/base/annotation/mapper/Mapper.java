package com.java.base.annotation.mapper;

import com.java.base.annotation.auto.MySelect;
import com.java.base.annotation.model.Blog;
import com.java.base.annotation.auto.MyLocalMethod;
import com.java.base.annotation.auto.MyLocalMethodReinforce;
import com.java.base.annotation.auto.MyMapper;
import com.java.mybatis.model.User;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/11 19:21
 */
@MyMapper
public interface Mapper {

    /**
     * 打印博客信息
     */
    @MyLocalMethod(value = "我们都是好孩子", className = "com.java.base.annotation.model.Blog",
            methodName = "fineBlog", methodParamClass = {"java.lang.String", "java.lang.String"},
            methodParamValues = {"欢迎来到 Java 技术栈博客专栏", "评论数：1211"})
    void getBlog(Blog blog, String title, String records);

    @MyLocalMethodReinforce(className = "com.java.base.annotation.model.Blog")
    void fineBlog(String title, String records);

    @MyLocalMethodReinforce(className = "com.java.base.annotation.model.Blog")
    void badBlog(Date date);

    @MySelect("select * from user where u_id = #{id}")
    User getUser(String id);
}
