package com.java.spring.bean;

import com.java.spring.xml.AbstractAutowireCapableBeanFactory;
import com.java.spring.xml.dependcy.RootBeanDefinition;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;

/**
 * 完成Bean实例创建后进行属性注入
 * {@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)} 方法，进行属性填充
 * <h2>getResolvedAutowireMode</h2>
 * 其中根据注入类型( AbstractBeanDefinition#getResolvedAutowireMode() 方法的返回值 )的不同来判断：
 * <ul>
 *     <li>是根据名称来自动注入{@link AbstractAutowireCapableBeanFactory#autowireByName(String, RootBeanDefinition, BeanWrapper, MutablePropertyValues)} </li>
 *     <li>还是根据类型来自动注入{@link AbstractAutowireCapableBeanFactory#autowireByType(String, RootBeanDefinition, BeanWrapper, MutablePropertyValues)} </li>
 * </ul>
 *
 * @author xuweizhi
 * @date 2019/03/25 15:20
 */
public class FillBeanAttribute {
}
