package com.java.base.annotation.controller;

import com.java.base.annotation.auto.MyAutowired;
import com.java.base.annotation.auto.MyController;
import com.java.base.annotation.auto.MyRequestMapping;
import com.java.base.annotation.service.MyServices;

/**
 * @author xuweizhi
 * @date 2019/04/14 16:01
 */
@MyController
@MyRequestMapping("we")
public class MyControllers {

    @MyAutowired
    public MyServices myServices;

    @MyRequestMapping("we/we")
    public void say() {
        System.out.println("哈哈");
    }

}
