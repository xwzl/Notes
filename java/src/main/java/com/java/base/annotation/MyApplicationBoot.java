package com.java.base.annotation;

import com.java.base.annotation.auto.MyApplication;
import com.java.base.annotation.auto.MyComponent;
import com.java.base.annotation.auto.MyComponentScan;
import com.java.base.annotation.auto.MyFilter;
import com.java.base.annotation.factory.MyBeanFactory;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/12 12:08
 */
@MyApplication
@MyComponentScan(packageName = "我们", excludeFilters = {@MyFilter(classTypePath = MyComponent.class)})
public class MyApplicationBoot {

    public static void main(String[] args) {
        MyBeanFactory factory = MyBeanFactory.run(MyApplicationBoot.class, args);
        Mapper mapper = factory.getBean(Mapper.class);
        mapper.getBlog(new Blog(), "11", "22");
        mapper.fineBlog("121", "232");
        mapper.badBlog(new Date());
    }

}
