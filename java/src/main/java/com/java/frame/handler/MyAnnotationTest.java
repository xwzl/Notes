package com.java.frame.handler;

import com.java.frame.model.Blog;
import com.java.frame.auto.MyAutowired;
import com.java.frame.auto.MyComponent;

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
