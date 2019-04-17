package com.java.frame.service.impl;

import com.java.frame.auto.MyAutowired;
import com.java.frame.auto.MyService;
import com.java.frame.mapper.Mapper;
import com.java.frame.model.User;
import com.java.frame.service.MyServices;

/**
 * @author xuweizhi
 * @date 2019/04/14 18:58
 */
@MyService(alias = "myServiceImpl1")
public class MyServiceImpl implements MyServices {

    @MyAutowired
    public Mapper mapper;


    @Override
    public User getUserById(Integer id) {
        return mapper.getUserById(id);
    }
}
