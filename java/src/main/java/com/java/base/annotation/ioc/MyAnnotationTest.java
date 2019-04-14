package com.java.base.annotation.ioc;

import com.java.base.annotation.model.Blog;
import com.java.base.annotation.auto.MyAutowired;
import com.java.base.annotation.auto.MyComponent;

/**
 * @author xuweizhi
 * @date 2019/04/11 19:20
 */
@MyComponent
public class MyAnnotationTest {

    @MyAutowired
    Blog blog;

    public static void main(String[] args) {
    }


}
