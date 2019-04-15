package com.java.base.annotation.controller;

import com.java.base.annotation.auto.*;
import com.java.base.annotation.service.MyServices;

/**
 * @author xuweizhi
 * @date 2019/04/14 16:01
 */
@MyController
@MyRequestMapping("we")
public class MyControllers {

    @MyResource("com.java.base.annotation.service.impl.MyServiceImpl")
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
