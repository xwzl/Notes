package com.java.base.annotation;

import com.java.base.annotation.auto.MyApplication;
import com.java.base.annotation.controller.MyControllers;
import com.java.base.annotation.factory.MyBeanFactory;
import com.java.base.annotation.mapper.Mapper;
import com.java.base.annotation.model.Blog;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/12 12:08
 */
@MyApplication
//@MyComponentScan(packageName = "com.java.base.annotation",
//        includeFilters = {
//                @MyFilter(classTypePath = HashMapOrder.class),
//                @MyFilter(classTypePath = User.class)},
//        excludeFilters = {
//                @MyFilter(classTypePath = MyComponent.class),
//                @MyFilter(classTypePath = MyEnum.class)}
//)
public class MyApplicationBoot {

    public static void main(String[] args) {
        MyBeanFactory factory = MyBeanFactory.run(MyApplicationBoot.class, (Object[]) args);
        MyControllers controller = factory.getBean(MyControllers.class);
        System.out.println(controller.myServices.getClass().getName());
        Mapper bean = factory.getBean(Mapper.class);
        bean.badBlog(new Date());
        bean.getBlog(new Blog(), "22", "333");
        bean.fineBlog("动态传值","评论数121");
        MyControllers bean1 = factory.getBean(MyControllers.class);
        bean1.myServices.run();
    }

}
