package com.java.frame.service.impl;

import com.java.frame.auto.MyAutowired;
import com.java.frame.auto.MyService;
import com.java.frame.mapper.Mapper;
import com.java.frame.model.User;
import com.java.frame.service.MyServices;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/14 18:58
 */
@MyService(alias = "myServiceImpl2")
public class MyServiceImpl2 implements MyServices {

    @MyAutowired
    public Mapper mapper;

    @Override
    public List<User> run() {
        return null;
    }
}
