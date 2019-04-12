package com.java.spring.bean;

import com.java.spring.xml.BeanDefinitionRegistry;
import com.java.spring.xml.DefaultListableBeanFactory;
import org.springframework.core.AliasRegistry;

/**
 * {@link BeanInstantiationStrategy}
 * 将定义 Bean 的资源文件解析成 BeanDefinition 后需要将其注入容器中，这个过程由 BeanDefinitionRegistries 来完成。
 * <p>
 * BeanDefinitionRegistries：向注册表中注册 BeanDefinition 实例，完成注册的过程。
 * <p>
 * 下图是 BeanDefinitionRegistries 类结构图：{@link #image()}
 * BeanDefinitionRegistries 继承了 AliasRegistry 接口，其核心子类有三个：SimpleBeanDefinitionRegistry、
 * DefaultListableBeanFactory、GenericApplicationContext 。
 * <h2>AliasRegistry</h2>
 * 用于别名管理的通用型接口，作为 BeanDefinitionRegistries 的顶层接口。 AliasRegistry 定义了一些别名管理的方法。
 * {@link AliasRegistry}
 * <h2>BeanDefinitionRegistries</h2>
 * BeanDefinition 的注册接口，如 RootBeanDefinition 和 ChildBeanDefinition。它通常由 BeanFactories 实现，
 * 在 Spring 中已知的实现者为：DefaultListableBeanFactory 和 GenericApplicationContext。BeanDefinitionRegistries
 * 是 Spring 的 Bean 工厂包中唯一封装 BeanDefinition 注册的接口。
 * {@link BeanDefinitionRegistry}
 * <h2>SimpleBeanDefinitionRegistry</h2>
 * SimpleBeanDefinitionRegistry 是 BeanDefinitionRegistry 一个简单的实现，它还继承 SimpleAliasRegistry
 * （ AliasRegistry 的简单实现），它仅仅只提供注册表功能，无工厂功能。
 * <p>
 * SimpleBeanDefinitionRegistry 使用 ConcurrentHashMap 来存储注册的 BeanDefinition。
 * <p>
 * 他对注册其中的 BeanDefinition 都是基于 beanDefinitionMap 这个集合来实现的,实现简单、粗暴。
 * <h2>4. DefaultListableBeanFactory</h2>
 * DefaultListableBeanFactory，ConfigurableListableBeanFactory（其实就是 BeanFactory ） 和 BeanDefinitionRegistry
 * 接口的默认实现：一个基于 BeanDefinition 元数据的完整 bean 工厂。所以相对于 SimpleBeanDefinitionRegistry 而言，DefaultListableBeanFactory
 * 则是一个具有注册功能的完整 Bean 工厂。它同样是用 ConcurrentHashMap 数据结构来存储注册的 BeanDefinition 。
 *
 * <h3>4.1 registerBeanDefinition</h3>
 * <p>
 * 再看看{@link DefaultListableBeanFactory#registerBeanDefinition}
 * <h3>4.2 removeBeanDefinition</h3>
 * 在看看 #removeBeanDefinition(String beanName) 方法，其实也是调用 beanDefinitionMap.remove(beanName) 的逻辑。
 * <h2>5. GenericApplicationContext</h2>
 * 对于类 GenericApplicationContext ，查看源码你会发现他实现注册、注销功能都是委托 DefaultListableBeanFactory 实现的。
 * <h2>6. 小结</h2>
 * 所以 BeanDefinition 注册并不是非常高大上的功能，内部就是用一个 Map 实现 ，并不是多么高大上的骚操作，所以有时候我们会潜意
 * 识地认为某些技术很高大上就觉得他很深奥，如果试着去一探究竟你会发现，原来这么简单。虽然 BeanDefinitionRegistry 实现简单，
 * 但是它作为 Spring IOC 容器的核心接口，其地位还是很重的.
 * {@link EnvironmentProperty}环境属性
 *
 * @author xuweizhi
 * @date 2019/03/27 14:03
 */
public class BeanDefinitionRegistries {

    /**
     * <image src="../../../../../resources/static/spring/BeanDefinitionRegisgtry.png"></image>
     */
    public void image() {

    }
}
