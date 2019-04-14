package com.java.base.annotation.model;

import com.java.base.annotation.auto.MyAutowired;
import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.controller.MyControllers;

import java.util.Date;

/**
 * 自定义注解测试类
 *
 * @author xuweizhi
 * @date 2019/04/11 19:17
 */
@MyComponent
public class Blog {

    private String title;

    private String records;

    @MyAutowired
    MyControllers myControllers;

    public Blog() {
    }

    public Blog(String title, String records) {
        this.title = title;
        this.records = records;
    }

    public void fineBlog(String title, String records) {
        System.out.println("博客的标题是：" + title + " 评论数是:" + records);
    }

    public void badBlog(Date date) {
        System.out.println(date);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", records='" + records + '\'' +
                '}';
    }
}
