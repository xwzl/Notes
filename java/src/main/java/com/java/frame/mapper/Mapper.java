package com.java.frame.mapper;

import com.java.frame.auto.MyLocalMethod;
import com.java.frame.auto.MyLocalMethodReinforce;
import com.java.frame.auto.MyMapper;
import com.java.frame.auto.MySelect;
import com.java.frame.model.Blog;
import com.java.frame.model.User;

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
    @MyLocalMethod(value = "我们都是好孩子", className = "com.java.frame.model.Blog",
            methodName = "fineBlog", methodParamClass = {"java.lang.String", "java.lang.String"},
            methodParamValues = {"欢迎来到 Java 技术栈博客专栏", "评论数：1211"})
    void getBlog(Blog blog, String title, String records);

    @MyLocalMethodReinforce(className = "com.java.frame.model.Blog")
    void fineBlog(String title, String records);

    @MyLocalMethodReinforce(className = "com.java.frame.model.Blog")
    void badBlog(Date date);

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.frame.model.User")
    User getUser(Map<String, Object> mapper);

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.frame.model.User")
    User getUserA(User user);

    @MySelect(value = "select * from user where u_id = #{uId} and address = #{address}", nameSpace = "com.java.frame.model.User")
    User getUserB(Integer integer, String address);

    @MySelect(value = "select * from user", nameSpace = "com.java.frame.model.User")
    List<User> getUserC();
}
