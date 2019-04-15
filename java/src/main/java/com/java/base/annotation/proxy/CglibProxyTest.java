package com.java.base.annotation.proxy;

/**
 * @author xuweizhi
 * @date 2019/04/13 20:17
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        //Enhancer enhancer = new Enhancer();
        //// 设置enhancer对象的父类
        //enhancer.setSuperclass(Mapper.class);
        //// 设置enhancer的回调对象
        //enhancer.setCallback(new MyMapperProxy(new HashMap<>(),null,da));
        //// 创建代理对象
        //Mapper proxy = (Mapper) enhancer.create();
        //// 通过代理对象调用目标方法
        //proxy.getBlog(new Blog(), "22", "222");
    }

}
