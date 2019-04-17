package com.java.frame;

import com.java.frame.auto.MyApplication;
import com.java.frame.factory.MyBeanFactory;

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
        MyBeanFactory.run(MyApplicationBoot.class, (Object[]) args);
        //Mapper bean = factory.getBean(Mapper.class);
        //bean.badBlog(new Date());
        //bean.getBlog(new Blog(), "22", "333");
        //bean.fineBlog("动态传值", "评论数121");
        //controller.say();
        //MyControllers bean1 = factory.getBean(MyControllers.class);
        //bean1.myServices.run();
        //Map<String, Object> map = new HashMap<>();
        //map.put("uId", 1);
        //map.put("address", "addres}");
        //User user1 = bean.getUser(map);
        //User user = new User();
        //user.setUId(1);
        //user.setAddress("addres}");
        //User userA = bean.getUserA(user);
        //User userB = bean.getUserB(1, "addres}");
        //System.out.println(user1);
        //System.out.println(userA);
        //System.out.println(userB);
        //List<User> userC = bean.getUserC();
        //userC.forEach(System.out::println);
        //boolean b = bean.addUser(user);
        //System.out.println(b);
        //Mapper mapper = factory.getBean(Mapper.class);

        //User user = new User();
        //user.setUId(55);
        //System.out.println(user);
        //user.setAddress("北京");
        //user.setRole(1);
        //user.setApartment("首创国际城");
        //user.setPassword("158262751");
        //user.setCreateTime(LocalDateTime.now());
        //user.setPhoneNumber("15280975199");
        //user.setUsername("root");
        //
        //User userA = mapper.getUserA(user);
        //System.out.println(userA);
        //
        //mapper.updateUser(user);
        //
        //System.out.println(mapper.getUserA(user));

        //mapper.addUser(new User());
        //mapper.delectUser(user);
        //mapper.delectUser(56);
    }


}

