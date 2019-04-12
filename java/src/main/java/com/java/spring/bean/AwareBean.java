package com.java.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * <h3>AbstractAutowireCapableBeanFactory 的 #doCreateBean方法，主要干三件事情：</h3>
 * <ol>
 * <li>实例化 bean 对象：#createBeanInstance 方法。</li>
 * <li>属性注入：#populateBean 方法。</li>
 * <li>初始化 bean 对象：#initializeBean 方法。</li>
 * </ol>
 * <h3>而初始化 bean 对象时，也是干了三件事情：</h3>
 * <ul>
 * <li>激活 Aware 方法</li>
 * <li>后置处理器的应用</li>
 * <li>激活自定义的 init 方法</li>
 * </ul>
 * <h2>1. Aware 接口</h2>
 * {@link org.springframework.beans.factory.Aware}接口为 Spring 容器的核心接口，是一个具
 * 有标识作用的超级接口，实现了该接口的 bean 是具有被 Spring 容器通知的能力，通知的方式是采用回调的方式。
 * <p>
 * Aware 接口是一个空接口，实际的方法签名由各个子接口来确定，且该接口通常只会有一个接收单参数的 set 方法，
 * 该 set 方法的命名方式为 set + 去掉接口名中的 Aware 后缀，即 XxxAware 接口，则方法定义为 setXxx()，
 * 例如 BeanNameAware（setBeanName），ApplicationContextAware（setApplicationContext）。
 * <p>
 * Aware 的子接口需要提供一个 setXxx 方法，我们知道 set 是设置属性值的方法，即 Aware 类接口的 setXxx
 * 方法其实就是设置 xxx 属性值的。 Aware 的含义是感知的、感应的，那么在 Spring 容器中是如何实现感知并设
 * 置属性值得呢？我们可以从初始化 bean 中的激活 Aware 的方法 #invokeAwareMethods(final String beanName, final Object bean)
 * 中看到一点点，代码如下：
 * <blockquote><pre>
 * // AbstractAutowireCapableBeanFactory.java
 *
 * private void invokeAwareMethods(final String beanName, final Object bean) {
 * 	if (bean instanceof Aware) {
 * 	    // BeanNameAware
 * 		if (bean instanceof BeanNameAware) {
 * 			((BeanNameAware) bean).setBeanName(beanName);
 *                }
 * 		// BeanClassLoaderAware
 * 		if (bean instanceof BeanClassLoaderAware) {
 * 			ClassLoader bcl = getBeanClassLoader();
 * 			if (bcl != null) {
 * 				((BeanClassLoaderAware) bean).setBeanClassLoader(bcl);
 *            }
 *        }
 * 		// BeanFactoryAware
 * 		if (bean instanceof BeanFactoryAware) {
 * 			((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
 *        }* 	}
 * }
 * </pre></blockquote>
 * 首先，判断 bean 实例是否属于 Aware 接口的范畴，如果是的话，则调用实例的 setXxx() 方法给实例设置 xxx 属性值，
 * 在 #invokeAwareMethods(...) 方法，主要是设置 beanName，beanClassLoader、BeanFactory 中三个属性值。
 *
 * <h2>2. Aware 子类</h2>
 * Spring 提供了一系列的 Aware 接口，如下图（部分）：<p></p>
 * <image src="../../../../../resources/static/spring/aware.jpg"></image>
 * 上面只是一部分子类，从这里我们可以看到 Spring 提供的 Aware 接口是是何其多。同时从上图我们也看到了几个比较熟悉
 * 的接口，如 BeanClassLoaderAware、BeanFactoryAware、BeanNameAware，下面就这三个接口来做一个简单的演示，
 * 先看各自的定义：
 * {@link BeanClassLoaderAware}
 * {@link BeanFactoryAware}
 * {@link BeanNameAware}
 * {@link ApplicationContextAware}
 * <h2>2.1 示例</h2>
 * {@link MyApplicationAware}
 * 从这了我们基本上就可以 Aware 真正的含义是什么了？感知，其实是 Spring 容器在初始化主动检测当前 bean 是否实现了
 * Aware 接口，如果实现了则回调其 set 方法将相应的参数设置给该 bean ，这个时候该 bean 就从 Spring 容器中取得
 * 相应的资源。
 * <p>
 * 最后列出部分常用的 Aware 子接口，便于日后查询：
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
 *
 * @author xuweizhi
 * @date 2019/03/25 23:26
 */
public class AwareBean {

    /**
     * <h2>运行结果</h2>
     * <image src="../../../../../resources/static/spring/AwareTest.jpg"></image>
     * 从该运行结果可以看出，这里只执行了三个 Aware 接口的 set 方法，原因就是通过 #getBean(...) 方法调用时，
     * 在激活 Aware 接口时只检测了 BeanNameAware、BeanClassLoaderAware、BeanFactoryAware 三个 Aware
     * 接口。如果将测试方法调整为下面：
     * <blockquote><pre>
     *         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
     *         MyApplicationAware applicationAware = (MyApplicationAware) applicationContext.getBean("myApplicationAware");
     *         applicationAware.display();
     * </pre></blockquote>
     * 则运行结果如下：<p></p>
     * <image src="../../../../../resources/static/spring/AwareTest1.jpg"></image>
     */
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("spring.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        MyApplicationAware applicationAware = (MyApplicationAware) factory.getBean("myApplicationAware");
        applicationAware.display();


    }

    class MyApplicationAware implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

        private String beanName;

        private BeanFactory beanFactory;

        private ClassLoader classLoader;

        private ApplicationContext applicationContext;

        @Override
        public void setBeanClassLoader(ClassLoader classLoader) {
            System.out.println("调用了 BeanClassLoaderAware 的 setBeanClassLoader 方法");
            this.classLoader = classLoader;
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println("调用了 BeanFactoryAware 的 setBeanFactory 方法");
            this.beanFactory = beanFactory;
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("调用了 BeanNameAware 的 setBeanName 方法");
            this.beanName = name;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("调用了 ApplicationContextAware 的 setApplicationContext 方法");
            this.applicationContext = applicationContext;
        }

        public void display() {
            System.out.println("beanName:" + beanName);
            System.out.println("是否为单例：" + beanFactory.isSingleton(beanName));
            System.out.println("系统环境为：" + applicationContext.getEnvironment());
        }
    }
}


interface BeanClassLoaderAware extends Aware {

    /**
     * 将 BeanClassLoader 提供给 bean 实例回调
     * 在 bean 属性填充之后、初始化回调之前回调，
     * 例如InitializingBean的InitializingBean.afterPropertiesSet（）方法或自定义init方法
     */
    void setBeanClassLoader(ClassLoader classLoader);

}


interface BeanFactoryAware extends Aware {
    /**
     * 将 BeanFactory 提供给 bean 实例回调
     * 调用时机和 setBeanClassLoader 一样
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}


interface BeanNameAware extends Aware {

    /**
     * 在创建此 bean 的 bean工厂中设置 beanName
     */
    void setBeanName(String name);

}


interface ApplicationContextAware extends Aware {

    /**
     * 设置此 bean 对象的 ApplicationContext，通常，该方法用于初始化对象
     */
    void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException;

}