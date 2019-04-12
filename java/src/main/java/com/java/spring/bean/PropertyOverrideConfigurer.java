package com.java.spring.bean;

/**
 * {@link BeanFactoryPostProcessors }作用与 BeanDefinition 完成加载之后与 Bean 实例化之前，是 Spring 提供的一种强大的扩展机制。它有两个重要的
 * 子类，一个是 PropertyPlaceholderConfigurer，另一个是 PropertyOverrideConfigurer ，其中 PropertyPlaceholderConfigurer 允许
 * 我们通过配置 Properties 的方式来取代 Bean 中定义的占位符，而 PropertyOverrideConfigurer 呢？
 * <p>
 * PropertyOverrideConfigurer 允许我们对 Spring 容器中配置的任何我们想处理的 bean 定义的 property 信息进行覆盖替换。
 * <p>
 * 通俗点说，就是我们可以通过 PropertyOverrideConfigurer 来覆盖任何 bean 中的任何属性，只要我们想。
 * <h2>使用</h2>
 * PropertyOverrideConfigurer 的使用规则是 beanName.propertyName=value，这里需要注意的是 beanName.propertyName 则是该 bean 中存在的属性
 * <h2>1.1 示例一</h2>
 * 依然使用以前的例子{@link com.java.spring.ioc.StudentService}，我们只需要修改下配置文件，声明下 PropertyOverrideConfigurer 以及其加载的配置文件。
 * 如下：{@link #configFile()}
 * <h2>1.2 示例二</h2>
 * 下面我们看一个有趣的例子，如果我们一个 bean 中 PropertyPlaceholderConfigurer 和 PropertyOverrideConfigurer 都使用呢？那是显示谁定义的值呢？
 * 这里先简单分析下：如果PropertyOverrideConfigurer 先作用，那么 PropertyPlaceholderConfigurer 在匹配占位符的时候就找不到了，如果 PropertyOverrideConfigurer
 * 后作用，也会直接取代 PropertyPlaceholderConfigurer 定义的值，所以无论如何都会显示 PropertyOverrideConfigurer 定义的值。是不是这样呢？
 * {@link #configFile2()}
 * <h2>2. 实现原理</h2>
 * UML 结构图如下：<p></p>
 * <image src="../../../../../resources/static/spring/POC.png"></image>
 * 与 PropertyPlaceholderConfigurer 一样，也是继承 PropertyResourceConfigurer，我们知道 PropertyResourceConfigurer 对
 * BeanFactoryPostProcessor 的 #postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 方法提供了实现，
 * 在该实现中它会去读取指定配置文件中的内容，然后调用 #processProperties 方法。该方法是一个抽象方法，具体的实现由子类来实现，所以这里
 * 我们只需要看 {@link com.java.spring.util.PropertyOverrideConfigurer#processProperties}方法的具体实现。
 * <h2>小结</h2>
 * 至此，PropertyOverrideConfigurer 到这里也就分析完毕了。最后看下 PropertyPlaceholderConfigurer 和
 * PropertyOverrideConfigurer 整体的结构图：{@link #image()}
 * 接下来分析类型转换{@link BeanTypeConvert}
 * @author xuweizhi
 * @date 2019/03/27 10:14
 */
public class PropertyOverrideConfigurer {


    /**
     * 指定 student 的 name 属性值为 "chenssy" 。
     * 声明 PropertyOverrideConfigurer 加载的文件为 application.properties，内容如下：
     * <p>
     * student.name = chenssy-PropertyOverrideConfigurer
     * <p>
     * 运行结果：student name:chenssy-PropertyOverrideConfigurer
     * <p>
     * 从中可以看出 PropertyOverrideConfigurer 定义的文件取代了 bean 中默认的值。
     */
    public void configFile() {
        //配置文件
        //<bean class="org.springframework.beans.factory.config.PropertyOverrideConfigurer">
        //    <property name="locations">
        //        <list>
        //            <value>classpath:application.properties</value>
        //        </list>
        //    </property>
        //</bean>
        //
        //<bean id="student" class="org.springframework.core.service.StudentService">
        //    <property name="name" value="chenssy"/>
        //</bean>
    }

    /**
     * 指定 .PropertyOverrideConfigurer 加载文件为 application1.properties 。配置文件如下：
     * <p>
     * student.name = chenssy-PropertyOverrideConfigurer
     * <p>
     * PropertyPlaceholderConfigurer 加载文件为 application2.properties 。配置文件如下：
     * <p>
     * studentService.name = chenssy-PropertyPlaceholderConfigurer
     * <p>
     * student 的 name 属性使用占位符 ${studentService.name}。
     * <p>
     * 测试程序依然是打印 name 属性值，运行结果如下：
     * <p>
     * student name: chenssy-PropertyOverrideConfigurer
     * <p>
     * 所以，上面的分析没有错。下面我们来分析 PropertyOverrideConfigurer 实现原理。
     * <p>
     * 其实如果了解 PropertyPlaceholderConfigurer 的实现机制的话，那么 PropertyOverrideConfigurer
     * 也不难猜测：加载指定 Properties，迭代其中的属性值，依据 “.” 来得到 beanName（split(".")[0]），从
     * 容器中获取指定的 BeanDefinition，然后得到 name 属性，进行替换即可。
     */
    public void configFile2() {
        //<bean class="org.springframework.beans.factory.config.PropertyOverrideConfigurer">
        //    <property name="locations">
        //        <list>
        //            <value>classpath:application1.properties</value>
        //        </list>
        //    </property>
        //</bean>
        //
        //<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        //    <property name="locations">
        //        <list>
        //            <value>classpath:application2.properties</value>
        //        </list>
        //    </property>
        //</bean>
        //
        //<bean id="student" class="org.springframework.core.service.StudentService">
        //    <property name="name" value="${studentService.name}"/>
        //</bean>
    }

    /**
     * <image src="../../../../../resources/static/spring/POCf.png"></image>
     */
    void image() {

    }
}
