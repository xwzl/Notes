package com.java.spring.bean;

import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h2>关于Property文件加载与解析,占位符#{}的替换</h2>
 * 介绍了{@link BeanFactoryPostProcessors}，知道 BeanFactoryPostProcessor 作用域容器启动阶段，可以对解析好的 BeanDefinition
 * 进行定制化处理，而其中 PropertyPlaceholderConfigurer 是其一个非常重要的应用，也是其子类，介绍如下：
 * <p>
 * PropertyPlaceholderConfigurer 允许我们用 Properties 文件中的属性，来定义应用上下文（配置文件或者注解）。
 * <p>
 * 什么意思，就是说我们在 XML 配置文件（或者其他方式，如注解方式）中使用占位符的方式来定义一些资源，并将这些占位符所代表的资源配置到
 * Properties 中，这样只需要对 Properties 文件进行修改即可，这个特性非常，在后面来介绍一种我们在项目中经常用到场景。
 * <h2>1. PropertyResourceConfigurer</h2>
 * <image src="../../../../../resources/static/spring/Property.png"></image>
 * <p>
 * 从 PropertyPlaceholderConfigurer 的结构图可以看出，它间接实现了 Aware 和 BeanFactoryPostProcessor 两大扩展接口，这里只需
 * 要关注 BeanFactoryPostProcessor 即可。我们知道 BeanFactoryPostProcessor 提供了#postProcessBeanFactory接口方法，在这个体
 * 系中该方法的是在 PropertyResourceConfigurer 中实现，该类为属性资源的配置类，它实现了BeanFactoryPostProcessor 接口，代码如下
 * {@link com.java.spring.xml.PropertyResourceConfigurer#postProcessBeanFactory}
 * <h2>2.  PropertyPlaceholderConfigurer</h2>
 * 在 PropertyPlaceholderConfigurer 中，重写 #processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) 方法，
 * 代码如下：{@link com.java.spring.xml.PropertyPlaceholderConfigurer#processProperties}
 * <blockquote><pre>
 * // PropertyPlaceholderConfigurer.java
 * protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
 *         throws BeansException {
 *     // <1> 创建 StringValueResolver 对象
 *     StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(props);
 *     // <2> 处理
 *     doProcessProperties(beanFactoryToProcess, valueResolver);
 * }
 * </pre></blockquote>
 * <h2>2.1 PlaceholderResolvingStringValueResolver</h2>
 * 对应 #processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) 方法的 <1> 处。
 * <p>
 * 首先，构造一个 PlaceholderResolvingStringValueResolver 类型的 StringValueResolver 实例。StringValueResolver 为一个解析 String 类型值
 * 的策略接口，该接口提供了 #resolveStringValue(String strVal) 方法，用于解析 String 值。PlaceholderResolvingStringValueResolver 为其一个
 * 解析策略，构造方法如下
 * <h2>2.2 doProcessProperties</h2>
 * 对应 #processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) 方法的 <2> 处。
 * <p>
 * 然后，得到 String 解析器的实例 valueResolver 后，则会调用 #doProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
 * StringValueResolver valueResolver) 方法，来进行真值的替换操作。该方法在父类 PlaceholderConfigurerSupport 中实现，代码如下：
 * <p>
 * {@link com.java.spring.xml.PlaceholderConfigurerSupport#doProcessProperties}
 * <h2>3. 小结</h2>
 * 到这里占位符的解析就结束了，我们将利用 PropertyPlaceholderConfigurer 来实现动态加载配置文件，这个场景也是非常常见的。
 * <h2>应用</h2>
 * {@link #use}
 * BeanFactoryPostProcessor 另外一个重要的子类{@link PropertyOverrideConfigurer}
 *
 * @author xuweizhi
 * @date 2019/03/26 23:46
 */
public class PropertyPlaceholderConfigurer {

    /**
     * 它允许我们在 XML 配置文件中使用占位符并将这些占位符所代表的资源单独配置到简单的 properties 文件中来加载。这个特性非常重要，
     * 因为它我们对 Bean 实例属性的配置变得非常容易控制了，主要使用场景有：
     * <ul>
     * <li>动态加载配置文件，多环境切换</li>
     * <li>属性加解密</li>
     * </ul>
     * <h2>1. 多环境切换</h2>
     * 在我们项目开发过程中，都会存在多个环境，如 dev 、test 、prod 等等，各个环境的配置都会不一样，在传统的开发过程中我们都是在进行打
     * 包的时候进行人工干预，或者将配置文件放在系统外部，加载的时候指定加载目录，这种方式容易出错，那么有没有一种比较好的方式来解决这种情
     * 况呢？有，利用 PropertyPlaceholderConfigurer 的特性来动态加载配置文件，实现多环境切换。
     * <p>
     * 首先我们定义四个 Properties 文件，如下：<p></p>
     * <image src="../../../../../resources/static/spring/user.jpg"></image>
     * <ul>
     * <li>配置内容如下：student.name=chenssy-dev</li>
     * <li>application-test.properties 文件，如下：student.name=chenssy-test</li>
     * <li>application-prod.properties 文件，如下：student.name=chenssy-prod</li>
     * </ul>
     * <p>
     * 然后实现一个类，该类继承 PropertyPlaceholderConfigurer，实现 #loadProperties(Properties props) 方法，
     * 根据环境的不同加载不同的配置文件，实现代码见{@link CustomPropertyConfig}
     * <p>
     */
    public void use() {
        //在 idea 的 VM options 里面增加 -Dspring.profiles.active=dev，标志当前环境为 dev 环境。测试代码如下：
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //
        //StudentService studentService = (StudentService) context.getBean("studentService");
        //System.out.println("student name:" + studentService.getName());
        //运行结果：student name:chenssy-dev
        //当将 -Dspring.profiles.active 调整为 test，则打印结果则是 chenssy-test，这样就完全实现了根据不同的环境加载不同的配置。
    }

    //配置文件
    //<bean id="PropertyPlaceholderConfigurer" class="org.springframework.core.custom.CustomPropertyConfig">
    //    <property name="locations">
    //        <list>
    //            <value>classpath:config/application-dev.properties</value>
    //            <value>classpath:config/application-test.properties</value>
    //            <value>classpath:config/application-prod.properties</value>
    //        </list>
    //    </property>
    //</bean>
    //
    //<bean id="studentService" class="org.springframework.core.service.StudentService">
    //    <property name="name" value="${student.name}"/> //从配置文件加载属性值
    //</bean>

}

class CustomPropertyConfig extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

    private Resource[] locations;

    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    @Override
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    @Override
    public void setLocalOverride(boolean localOverride) {
        this.localOverride = localOverride;
    }

    /**
     * 覆盖这个方法，根据启动参数，动态读取配置文件
     *
     * @param props
     * @throws IOException
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
        if (locations != null) {
            // locations 里面就已经包含了那三个定义的文件
            for (Resource location : this.locations) {
                InputStream is = null;
                try {
                    String filename = location.getFilename();
                    String env = "application-" + System.getProperty("spring.profiles.active", "dev") + ".properties";

                    // 找到我们需要的文件，加载
                    if (filename.contains(env)) {
                        logger.info("Loading properties file from " + location);
                        is = location.getInputStream();
                        this.propertiesPersister.load(props, is);

                    }
                } catch (IOException ex) {
                    logger.info("读取配置文件失败.....");
                    throw ex;
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }
    }
}
