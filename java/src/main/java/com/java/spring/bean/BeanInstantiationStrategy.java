package com.java.spring.bean;


import com.java.spring.util.CglibSubclassingInstantiationStrategy;
import com.java.spring.xml.InstantiationStrategy;
import com.java.spring.xml.SimpleInstantiationStrategy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Method;

/**
 * {@link BeanWrapper}
 * 在开始分析 InstantiationStrategy 之前，我们先来简单回顾下 Bean 的实例化过程：
 * <ol>
 * <li>Bean 的创建，主要是 AbstractAutowireCapableBeanFactory#doCreateBean(...) 方法。在这个方法中有 Bean 的实例化、
 * 属性注入和初始化过程，对于 Bean 的实例化过程这是根据 Bean 的类型来判断的，如果是单例模式，则直接从 factoryBeanInstanceCache
 * 缓存中获取，否则调用 #createBeanInstance(...) 方法来创建。</li>
 * <li>在 #createBeanInstance(...) 方法中，如果 Supplier 不为空，则调用 #obtainFromSupplier(...) 实例化 bean。如果 factory
 * 不为空，则调用 #instantiateUsingFactoryMethod(...) 方法来实例化 Bean 。如果都不是，则调用 #instantiateBean(...) 方法来实例化
 * Bean 。但是无论是 #instantiateUsingFactoryMethod(...) 方法，还是 #instantiateBean() 方法，最后都一定会调用到 InstantiationStrategy
 * 接口的 #instantiate(...) 方法。</li>
 * </ol>
 * <h2>1. InstantiationStrategy</h2>
 * InstantiationStrategy 接口定义了 Spring Bean 实例化的策略，根据创建对象情况的不同，提供了三种策略：无参构造方法、有参构造方法、工厂方法。
 * {@link InstantiationStrategy }
 * <h2>2. SimpleInstantiationStrategy</h2>
 * InstantiationStrategy 接口有两个实现类：SimpleInstantiationStrategy 和 CglibSubclassingInstantiationStrategy。
 * SimpleInstantiationStrategy 对以上三个方法都做了简单的实现。
 * <p>
 * ① 如果是工厂方法实例化，则直接使用反射创建对象，如{@link SimpleInstantiationStrategy#instantiate(RootBeanDefinition, String, BeanFactory, Object, Method, Object...)}
 * <p>
 * ② 如果是构造方法实例化，则是先判断是否有 MethodOverrides，如果没有则是直接使用反射，如果有则就需要 CGLIB 实例化对象。
 * 如{@link SimpleInstantiationStrategy#instantiate(RootBeanDefinition, String, BeanFactory)}
 *<h2>MethodOverrides</h2>
 * 对于 MethodOverrides，在 BeanDefinitionParserDelegate 类解析 bean 的时候是否还记得这两个方法：#parseLookupOverrideSubElements(...)
 * 和 #parseReplacedMethodSubElements(...) 这两个方法分别用于解析 lookup-method 和 replaced-method 属性。
 *
 * <h3>4. CglibSubclassingInstantiationStrategy</h3>
 * 类 CglibSubclassingInstantiationStrategy 为 Spring 实例化 Bean 的默认实例化策略，其主要功能还是对父类功能进行补充：
 * 其父类将 CGLIB 的实例化策略委托其实现。
 *
 * CglibSubclassingInstantiationStrategy 实例化 Bean 策略，是通过其内部类 CglibSubclassCreator 来实现的
 * {@link CglibSubclassingInstantiationStrategy#instantiateWithMethodInjection}
 *
 * {@link BeanDefinitionRegistries}
 * @author xuweizhi
 * @date 2019/03/27 13:35
 */
public class BeanInstantiationStrategy {
}
