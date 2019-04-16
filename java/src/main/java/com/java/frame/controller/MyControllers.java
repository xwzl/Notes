package com.java.frame.controller;

import com.java.frame.auto.*;
import com.java.frame.service.MyServices;

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
    public void say() {
        System.out.println(myServices.getClass());
        System.out.println(getMyServices.getClass());
    }

}
