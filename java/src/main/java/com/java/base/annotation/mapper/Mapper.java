package com.java.base.annotation.mapper;

import com.java.base.annotation.auto.MyLocalMethod;
import com.java.base.annotation.auto.MyLocalMethodReinforce;
import com.java.base.annotation.auto.MyMapper;
import com.java.base.annotation.auto.MySelect;
import com.java.base.annotation.model.Blog;
import com.java.mybatis.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.mybatis.model.User")
    User getUser(Map<String, Object> mapper);

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.mybatis.model.User")
    User getUserA(User user);

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.mybatis.model.User")
    User getUserB(Integer integer, String address);

    @MySelect(value = "select * from user", nameSpace = "com.java.mybatis.model.User")
    List<User> getUserC();
}
