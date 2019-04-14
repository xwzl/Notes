package com.java.base.annotation;

import com.java.base.annotation.auto.MyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuweizhi
 * @date 2019/04/14 13:56
 */
@MyService
public class MyServices {

    @Autowired
    private Controller controller;

}
