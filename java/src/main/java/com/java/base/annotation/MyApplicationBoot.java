package com.java.base.annotation;

import com.java.base.annotation.auto.MyApplication;
import com.java.base.annotation.controller.MyControllers;
import com.java.base.annotation.factory.MyBeanFactory;
import com.java.base.annotation.mapper.Mapper;
import com.java.base.annotation.model.Blog;
import com.java.mybatis.model.User;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        bean.fineBlog("动态传值", "评论数121");
        MyControllers bean1 = factory.getBean(MyControllers.class);
        bean1.myServices.run();
        Map<String, Object> map = new HashMap<>();
        map.put("uId", 1);
        map.put("address", "addres}");
        bean.getUser(map);
        User user = new User();
        user.setUId(1);
        user.setAddress("addres}");
        bean.getUserA(user);
    }

    @Test
    public void test1() {
        String a = Map.class.getName();
        Class<HashMap> hashMapClass = HashMap.class;
        get(a, hashMapClass);

    }

    private void get(String a, Class<?> hashMapClass) {

    }

    @Test
    public void test() {
        System.out.println(String.class.getName());
        System.out.println(Character.class.getName());
        System.out.println(Date.class.getName());
        System.out.println(Integer.class.getName());
        System.out.println(Float.class.getName());
        System.out.println(Double.class.getName());
        System.out.println(Long.class.getName());
        System.out.println(Byte.class.getName());
        System.out.println(Short.class.getName());
        System.out.println(Boolean.class.getName());
    }

}
