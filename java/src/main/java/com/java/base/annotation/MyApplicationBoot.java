package com.java.base.annotation;

import com.java.base.annotation.auto.*;
import com.java.base.annotation.factory.MyBeanFactory;
import com.java.base.annotation.mapper.Mapper;
import com.java.base.annotation.model.Blog;
import com.java.base.annotation.service.impl.MyServiceImpl;
import com.java.base.array.HashMapOrder;
import com.java.base.enums.MyEnum;
import com.java.mybatis.model.User;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2019/04/12 12:08
 */
@MyApplication
@MyComponentScan(packageName = "com.java.base.annotation",
        includeFilters = {
                @MyFilter(classTypePath = HashMapOrder.class),
                @MyFilter(classTypePath = User.class)},
        excludeFilters = {
                @MyFilter(classTypePath = MyComponent.class),
                @MyFilter(classTypePath = MyEnum.class)}
)
public class MyApplicationBoot {

    public static void main(String[] args) {
        MyBeanFactory factory = MyBeanFactory.run(MyApplicationBoot.class, (Object[]) args);
        Mapper mapper = factory.getBean(Mapper.class);
        mapper.getBlog(new Blog(), "11", "22");
        mapper.fineBlog("121", "232");
        mapper.badBlog(new Date());

        MyServiceImpl bean = factory.getBean(MyServiceImpl.class);
        bean.mapper.badBlog(new Date());

    }

}
