package com.java.frame.mapper;

import com.java.frame.auto.*;
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

    @MyInsert(value = " insert into user (u_id,address,role) values (null,#{address},#{role})",
            nameSpace = "com.java.frame.model.User")
    boolean addUser(User uer);


    @MyUpdate(value = " update user set address =#{address},apartment=#{apartment},create_time=#{createTime}" +
            ",password=#{password},phone_number=#{phoneNumber},username=#{username},role=#{role} where u_id = #{uId}",
            nameSpace = "com.java.frame.model.User")
    void updateUser(User user);

    @MyDelete(value = "delete from user where u_id = #{uId} and role = #{role}", nameSpace = "com.java.frame.model.User")
    void delectUser(User user);

    @MyDelete(value = "delete from user where u_id = #{uId}", nameSpace = "com.java.frame.model.User")
    void delectUser(Integer id);
}
