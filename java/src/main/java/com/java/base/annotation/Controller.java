package com.java.base.annotation;

import com.java.base.annotation.auto.MyController;
import com.java.base.annotation.auto.MyRequestMapping;

/**
 * @author xuweizhi
 * @date 2019/04/14 16:01
 */
@MyController
@MyRequestMapping("we")
public class Controller {

    @MyRequestMapping("we/we")
    public void say() {
        System.out.println("哈哈");
    }

}
