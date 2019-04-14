package com.java.base.annotation.auto;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author xuweizhi
 * @date 2019/04/14 1:07
 */
public class MyAnnotationTest {

    @Test
    public void classExtendTest() {
        Class<Child> child = Child.class;
        Annotation[] a = child.getAnnotations();
        System.out.println("如果a有值，说明注解在子类中被继承");
        System.out.println(a);
        System.out.println("如果b.length大于0，说明其注解可被重复使用");
        MyAnnotations b = (MyAnnotations) a[0];
        System.out.println(b.value().length);
    }

    @Test
    public void myServiceTest() {
        Class<MyServiceTest> service = MyServiceTest.class;
        MyService myService = service.getAnnotation(MyService.class);
        Class<? extends Annotation> type = myService.annotationType();
        Method value = null;
        try {
            value = type.getDeclaredMethod("value");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MyComponent annotation = value.getAnnotation(MyComponent.class);
        System.out.println(annotation);
    }

    @Test
    public void test(){
        Class<MyServiceTest> service = MyServiceTest.class;
        Annotation[] annotations = service.getAnnotations();
        for (Annotation annotation : annotations) {
            boolean annotationPresent = annotation.annotationType().isAnnotationPresent(MyComponent.class);
            System.out.println(annotationPresent);
        }
    }

}

@MyAnnotation(component = {@MyService, @MyService})
@MyAnnotation(component = {@MyService, @MyService})
@MyAnnotation(component = {@MyService, @MyService})
@MyAnnotation(component = {@MyService, @MyService})
@MyAnnotation(component = {@MyService, @MyService})
class Parent {

}

class Child extends Parent {

}

@MyService
class MyServiceTest {

}