package com.java.frame.controller;

import com.java.frame.auto.*;
import com.java.frame.model.User;
import com.java.frame.service.MyServices;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/14 16:01
 */
@MyController
@MyRequestMapping("we")
public class MyControllers {

    @MyResource("com.java.frame.service.impl.MyServiceImpl")
    public MyServices myServices;

    @MyAutowired
    @MyQualifier("myServiceImpl2")
    public MyServices getMyServices;

    @MyRequestMapping("we/we")
    public String say() {
        List<User> run = myServices.run();
        return run.toString();
    }

}
