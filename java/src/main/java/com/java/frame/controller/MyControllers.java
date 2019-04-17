package com.java.frame.controller;

import com.java.frame.auto.*;
import com.java.frame.model.User;
import com.java.frame.service.MyServices;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/14 16:01
 */
@MyController
@MyRequestMapping("user")
public class MyControllers {

    @MyResource("com.java.frame.service.impl.MyServiceImpl")
    public MyServices myServices;

    @MyAutowired
    @MyQualifier("myServiceImpl2")
    public MyServices getMyServices;

    @MyRequestMapping("getUser")
    public User getUserById(Integer id, String name, Long age, User user, List<User> lists) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        return myServices.getUserById(id);
    }

    @MyRequestMapping("getUsers")
    public List<User> getUsers() {
        return myServices.getUsers();
    }


    @MyRequestMapping("test")
    public String getUserById(Date date, LocalDateTime localDateTime) {
        return "测试空参数";
    }
}
