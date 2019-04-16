package com.java.frame.factory;

import com.java.frame.auto.MyApplication;
import org.springframework.beans.BeansException;

/**
 * <h2>基于里氏替换原则</h2>
 * 里氏替换原则通俗的来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。它包含以下4层含义：
 * <ul>
 * <li>子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法。</li>
 * <li>子类中可以增加自己特有的方法。</li>
 * <li>当子类的方法重载父类的方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松。</li>
 * <li>当子类的方法实现父类的抽象方法时，方法的后置条件（即方法的返回值）要比父类更严格。</li>
 * </ul>
 * BeanFactory 工厂
 *
 * @author xuweizhi
 * @date 2019/04/16 10:44
 */
public interface BeanFactory {

    /**
     * 初始化配置
     *
     * @param application 启动类注解
     * @param packageName 扫描包路径
     */
    void initConfigure(MyApplication application, String packageName);

    /**
     * 初始化 BeanFactory 工厂
     *
     * @param clazz 启动类 Class 对象
     * @return Bean 工厂
     */
    BeanFactory init(Class<?> clazz);

    /**
     * 根据别名获取 bean 对象
     *
     * @param name 别名
     * @param <T>  返回的bean 对象
     * @return 返回的bean 对象
     * @throws BeansException 异常
     */
    <T> T getBean(String name) throws BeansException;

}
