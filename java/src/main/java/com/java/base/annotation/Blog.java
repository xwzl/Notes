package com.java.base.annotation;

import com.java.base.annotation.auto.MyAutowired;
import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.util.DateUtils;

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
    private DateUtils dateUtils;

    public Blog() {
    }

    public Blog(String title, String records) {
        this.title = title;
        this.records = records;
    }

    public void fineBlog(String title, String records) {
        System.out.println("博客的标题是：" + title + " 评论数是:" + records);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", records='" + records + '\'' +
                '}';
    }
}
