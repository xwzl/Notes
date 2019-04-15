package com.java.base.annotation;

import com.java.base.annotation.auto.MyApplication;
import com.java.base.annotation.controller.MyControllers;
import com.java.base.annotation.factory.MyBeanFactory;

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
    }

}
