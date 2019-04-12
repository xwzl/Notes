package com.java.spring.bean;

import com.java.spring.xml.dependcy.RootBeanDefinition;

/**
 * ﻿一个 bean 经历了 #createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args)
 * 方法，被创建出来，然后又经过一番属性注入，依赖处理，历经千辛万苦，千锤百炼，终于有点儿 bean 实例的样子，
 * 能堪大任了，只需要经历最后一步就破茧成蝶了。
 * <p>
 * 这最后一步就是初始化，也就是 initializeBean(final String beanName, final Object bean, RootBeanDefinition mbd) 方法。
 * 所以，这篇文章我们分析 #doCreateBean(...) 方法的中最后一步：初始化 bean 对象。
 * {@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)}
 * <h2> 激活 Aware 方法</h2>
 * Aware ，英文翻译是意识到的，感知的。Spring 提供了诸多 Aware 接口，用于辅助 Spring Bean 以编程的方式调用
 * Spring 容器，通过实现这些接口，可以增强 Spring Bean 的功能。
 * <ul>
 * <li>LoadTimeWeaverAware：加载Spring Bean时织入第三方模块，如AspectJ</li>
 * <li>BeanClassLoaderAware：加载Spring Bean的类加载器</li>
 * <li>BootstrapContextAware：资源适配器BootstrapContext，如JCA,CCI</li>
 * <li>ResourceLoaderAware：底层访问资源的加载器</li>
 * <li>BeanFactoryAware：声明BeanFactory</li>
 * <li>PortletConfigAware：PortletConfig</li>
 * <li>PortletContextAware：PortletContext</li>
 * <li>ServletConfigAware：ServletConfig</li>
 * <li>ServletContextAware：ServletContext</li>
 * <li>MessageSourceAware：国际化</li>
 * <li>ApplicationEventPublisherAware：应用事件</li>
 * <li>NotificationPublisherAware：JMX通知</li>
 * <li>BeanNameAware：声明Spring Bean的名字</li>
 * </ul>
 * <h2>1.2 后置处理器的应用</h2>
 * BeanPostProcessor 在前面介绍 bean 加载的过程曾多次遇到，相信各位不陌生，这是 Spring 中开放式框架中必不可少的一个亮点。
 * <p>
 * BeanPostProcessor 的作用是：如果我们想要在 Spring 容器完成 Bean 的实例化，配置和其他的初始化后添加一些自己的逻辑处理，那
 * 么请使用该接口，这个接口给与了用户充足的权限去更改或者扩展 Spring，是我们对 Spring 进行扩展和增强处理一个必不可少的接口。
 * <p>
 * <h3></h3>
 * {@link DependedInjectBean}
 *
 * @author xuweizhi
 * @date 2019/03/25 22:14
 */
public class InitBean {
}
