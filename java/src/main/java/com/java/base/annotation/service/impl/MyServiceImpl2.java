package com.java.base.annotation.service.impl;

import com.java.base.annotation.auto.MyAutowired;
import com.java.base.annotation.auto.MyService;
import com.java.base.annotation.mapper.Mapper;
import com.java.base.annotation.service.MyServices;

/**
 * @author xuweizhi
 * @date 2019/04/14 18:58
 */
@MyService(alias = "myServiceImpl2")
public class MyServiceImpl2 implements MyServices {

    @MyAutowired
    public Mapper mapper;

    @Override
    public void run() {
        mapper.fineBlog("111", "2222");
    }
}
