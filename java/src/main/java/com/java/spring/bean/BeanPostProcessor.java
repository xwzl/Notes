package com.java.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * <h2>1. BeanPostProcessor 接口</h2>
 * Spring 作为优秀的开源框架，它为我们提供了丰富的可扩展点，除了前面提到的 Aware 接口，还包括其他部分，其中一个很重要的就是 BeanPostProcessor。
 * 这篇文章主要介绍 BeanPostProcessor 的使用以及其实现原理。我们先看 BeanPostProcessor 的定位：
 * <p>
 * <b>BeanPostProcessor 的作用：在 Bean 完成实例化后，如果我们需要对其进行一些配置、增加一些自己的处理逻辑，那么请使用 BeanPostProcessor。</b>
 * <h2>2. BeanPostProcessor 示例</h2>
 * 首先定义一个类，该类实现 BeanPostProcessor 接口，详情见{@link BeanPostProcessorTest}
 * <h2>3. BeanPostProcessor 基本原理</h2>
 * BeanPostProcessor 可以理解为是 Spring 的一个工厂钩子（其实 Spring 提供一系列的钩子，如 Aware 、InitializingBean、DisposableBean），它是
 * Spring 提供的对象实例化阶段强有力的扩展点，允许 Spring 在实例化 bean 阶段对其进行定制化修改，比较常见的使用场景是处理标记接口实现类或者为当前对
 * 象提供代理实现（例如 AOP）。
 * <p>
 * 一般普通的 BeanFactory 是不支持自动注册 BeanPostProcessor 的，需要我们手动调用 #addBeanPostProcessor(BeanPostProcessor beanPostProcessor)
 * 方法进行注册。注册后的 BeanPostProcessor 适用于所有该 BeanFactory 创建的 bean，但是<b>ApplicationContext 可以在其 bean 定义中自动检测所有的
 * BeanPostProcessor 并自动完成注册，同时将他们应用到随后创建的任何 Bean 中。</b>
 * <p>
 * #postProcessBeforeInitialization(Object bean, String beanName) 和 #postProcessAfterInitialization(Object bean, String beanName) 两个
 * 方法，都接收一个 Object 类型的 bean ，一个 String 类型的 beanName ，其中 bean 是已经实例化了的 instanceBean ，能拿到这个你是不是可以对它为所欲
 * 为了？ 这两个方法是初始化 bean 的前后置处理器，他们应用 #invokeInitMethods(String beanName, final Object bean, RootBeanDefinition mbd) 方
 * 法的前后。如图:<p></p>
 * <image src="../../../../../resources/static/spring/BeanPostProcessor.jpg"></image>
 * <p></p>
 * 初始化方法{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#initializeBean(Object, String)}
 * <p>
 * 前置方法{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 * <p>
 * 后置方法{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 * <p>
 * <h3>3.1 自动检测并注册</h3>
 * #getBeanPostProcessors() 方法，返回的是 beanPostProcessors 集合，该集合里面存放就是我们自定义的 BeanPostProcessor ，如果该集合中存在元素则调用相应
 * 的方法，否则就直接返回 bean 了。这也是为什么使用 BeanFactory 容器是无法输出自定义 BeanPostProcessor 里面的内容，因为在 BeanFactory#getBean(...) 方
 * 法的过程中根本就没有将我们自定义的 BeanPostProcessor 注入进来，所以要想 BeanFactory 容器 的 BeanPostProcessor 生效我们必须手动调用 #addBeanPostProcessor
 * (BeanPostProcessor beanPostProcessor) 方法，将定义的 BeanPostProcessor 注册到相应的 BeanFactory 中。但是 ApplicationContext 不需要手动，因为
 * ApplicationContext 会自动检测并完成注册。
 * <p>
 * ApplicationContext 实现自动注册的原因，在于我们构造一个 ApplicationContext 实例对象的时候会调用 #registerBeanPostProcessors
 * 方法，将检测到的 BeanPostProcessor 注入到 ApplicationContext 容器中，同时应用到该容器创建的 bean 中。
 * <p>
 * {@link com.java.spring.xml.AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)}
 * <h2>4. 小结</h2>
 * 至此，BeanPostProcessor 已经分析完毕了，这里简单总结下：
 * <ul>
 * <li>BeanPostProcessor 的作用域是容器级别的，它只和所在的容器相关 ，当 BeanPostProcessor 完成注册后，它会应用于所有跟它在同一个容器内的 bean 。</li>
 * <li>BeanFactory 和 ApplicationContext 对 BeanPostProcessor 的处理不同，ApplicationContext 会自动检测所有实现了 BeanPostProcessor 接口的 bean，并完成注册，但是使用 BeanFactory 容器时则需要手动调用 AbstractBeanFactory#addBeanPostProcessor(BeanPostProcessor beanPostProcessor) 方法来完成注册</li>
 * <li>ApplicationContext 的 BeanPostProcessor 支持 Ordered，而 BeanFactory 的 BeanPostProcessor 是不支持的，原因在于ApplicationContext 会对 BeanPostProcessor 进行 Ordered 检测并完成排序，而 BeanFactory 中的 BeanPostProcessor 只跟注册的顺序有关。</li>
 * </ul>
 *
 * @author xuweizhi
 * @date 2019/03/25 23:26
 */
public class BeanPostProcessor {

    /**
     * <b>测试方法，运行结果为</b>
     * hello BeanPostProcessor!!!
     * <p>
     * 运行结果比较奇怪，为什么没有执行 #postProcessBeforeInitialization(...) 和 #postProcessAfterInitialization(...) 方法呢？
     */
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("spring.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        BeanPostProcessorTest beanPostProcessorTest = new BeanPostProcessorTest();
        //在工厂显示的加入处理类
        factory.addBeanPostProcessor(beanPostProcessorTest);
        reader.loadBeanDefinitions(resource);

        BeanPostProcessorTest test = (BeanPostProcessorTest) factory.getBean("beanPostProcessorTest");
        test.display();
    }

}

class BeanPostProcessorTest implements org.springframework.beans.factory.config.BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean [" + beanName + "] 开始初始化");
        // 这里一定要返回 bean，不能返回 null
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean [" + beanName + "] 完成初始化");
        return bean;
    }

    public void display() {
        System.out.println("hello BeanPostProcessor!!!");
    }
}