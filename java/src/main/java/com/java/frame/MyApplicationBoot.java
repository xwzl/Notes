package com.java.frame;

import com.java.frame.auto.MyApplication;
import com.java.frame.controller.MyControllers;
import com.java.frame.factory.MySimpleBeanFactory;
import com.java.frame.mapper.Mapper;
import com.java.frame.model.Blog;
import com.java.frame.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        MySimpleBeanFactory factory = MySimpleBeanFactory.run(MyApplicationBoot.class, (Object[]) args);
        MyControllers controller = factory.getBean(MyControllers.class);
        Mapper bean = factory.getBean(Mapper.class);
        bean.badBlog(new Date());
        bean.getBlog(new Blog(), "22", "333");
        bean.fineBlog("动态传值", "评论数121");
        controller.say();
        MyControllers bean1 = factory.getBean(MyControllers.class);
        bean1.myServices.run();
        Map<String, Object> map = new HashMap<>();
        map.put("uId", 1);
        map.put("address", "addres}");
        User user1 = bean.getUser(map);
        User user = new User();
        user.setUId(1);
        user.setAddress("addres}");
        User userA = bean.getUserA(user);
        User userB = bean.getUserB(1, "addres}");
        System.out.println(user1);
        System.out.println(userA);
        System.out.println(userB);
        List<User> userC = bean.getUserC();
        userC.forEach(System.out::println);
    }


}
