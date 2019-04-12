package com.java.spring.bean;

import com.java.spring.xml.AbstractApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractMessageSource;

/**
 * {@link EnvironmentProperty}
 * 前面都是基于 BeanFactory 这个容器来进行分析的，BeanFactory 容器有点儿简单，它并不适用于我们生产环境，
 * 在生产环境我们通常会选择 ApplicationContext ，相对于大多数人而言，它才是正规军，相比于 BeanFactory
 * 这个杂牌军而言，它由如下几个区别：
 * <ul>
 * <li>继承 MessageSource，提供国际化的标准访问策略。</li>
 * <li>继承 ApplicationEventPublisher ，提供强大的事件机制。</li>
 * <li>扩展 ResourceLoader，可以用来加载多个 Resource，可以灵活访问不同的资源。</li>
 * <li>对 Web 应用的支持。</li>
 * </ul>
 * <h2>1. ApplicationContext</h2>
 * {@link org.springframework.context.ApplicationContext}
 * <ul>
 * <li>BeanFactory：Spring 管理 Bean 的顶层接口，我们可以认为他是一个简易版的 Spring 容器。ApplicationContext 继承 BeanFactory 的两个子类：HierarchicalBeanFactory 和 ListableBeanFactory。HierarchicalBeanFactory 是一个具有层级关系的 BeanFactory，拥有属性 parentBeanFactory 。ListableBeanFactory 实现了枚举方法可以列举出当前 BeanFactory 中所有的 bean 对象而不必根据 name 一个一个的获取。</li>
 * <li>ApplicationEventPublisher：用于封装事件发布功能的接口，向事件监听器（Listener）发送事件消息。</li>
 * <li>ResourceLoader：Spring 加载资源的顶层接口，用于从一个源加载资源文件。ApplicationContext 继承 ResourceLoader 的子类 ResourcePatternResolver，该接口是将 location 解析为 Resource 对象的策略接口。</li>
 * <li>MessageSource：解析 message 的策略接口，用不支撑国际化等功能。</li>
 * <li>EnvironmentCapable：用于获取 Environment 的接口。</li>
 * </ul>
 * <h2>2. ApplicationContext 的子接口</h2>
 * ApplicationContext 有两个直接子类：WebApplicationContext.MD 和 ConfigurableApplicationContext 。
 * <h3>2.1 WebApplicationContext.MD</h3>
 * 该接口只有一个 #getServletContext() 方法，用于给 Servlet 提供上下文信息。
 * <h3>2.2 ConfigurableApplicationContext</h3>
 * {@link ConfigurableApplicationContext}
 * <ul>
 * <li>Lifecycle：对 context 生命周期的管理，它提供 #start() 和 #stop() 方法启动和暂停组件。</li>
 * <li>Closeable：标准 JDK 所提供的一个接口，用于最后关闭组件释放资源等。</li>
 * </ul>
 * <h3>2.3 ConfigurableWebApplicationContext</h3>
 * <p>
 * WebApplicationContext.MD 接口和 ConfigurableApplicationContext 接口有一个共同的子类接口 ConfigurableWebApplicationContext，该接口将这两个接口进行合并，提供了一个可配置、可管理、可关闭的 WebApplicationContext.MD ，同时该接口还增加了 #setServletContext(ServletContext servletContext)，setServletConfig(ServletConfig servletConfig) 等方法，用于装配 WebApplicationContext.MD 。
 * <p>
 * 上面三个接口就可以构成一个比较完整的 Spring 容器，整个 Spring 容器体系涉及的接口较多，所以下面小编就一个具体的实现类来看看
 * ApplicationContext 的实现（其实在前面一系列的文章中，小编对涉及的大部分接口都已经分析了其原理），当然不可能每个方法都涉及
 * 到，但小编会把其中最为重要的实现方法贴出来分析。ApplicationContext 的实现类较多，就以 ClassPathXmlApplicationContext
 * 来分析 ApplicationContext。
 * <h3>3. ClassPathXmlApplicationContext</h3>
 * ClassPathXmlApplicationContext 是我们在学习 Spring 过程中用的非常多的一个类，很多人第一个接触的 Spring 容器就是它，
 * 包括小编自己，下面代码我想很多人依然还记得吧。
 * <p>
 * 下图是 ClassPathXmlApplicationContext 的结构类图：{@link #image()}
 * <p>
 * 主要的的类层级关系如下：
 * org.springframework.context.support.AbstractApplicationContext
 * org.springframework.context.support.AbstractRefreshableApplicationContext
 * org.springframework.context.support.AbstractRefreshableConfigApplicationContext
 * org.springframework.context.support.AbstractXmlApplicationContext
 * org.springframework.context.support.ClassPathXmlApplicationContext
 * <p>
 * 这种设计是模板方法模式典型的应用，AbstractApplicationContext 实现了 ConfigurableApplicationContext 这个全家桶接
 * 口，其子类 AbstractRefreshableConfigApplicationContext 又实现了 BeanNameAware 和 InitializingBean 接口。所以
 * ClassPathXmlApplicationContext 设计的顶级接口有：
 * <p>
 * BeanFactory：Spring 容器 Bean 的管理
 * MessageSource：管理 message ，实现国际化等功能
 * ApplicationEventPublisher：事件发布
 * ResourcePatternResolver：资源加载
 * EnvironmentCapable：系统 Environment（profile + Properties） 相关
 * Lifecycle：管理生命周期
 * Closable：关闭，释放资源
 * InitializingBean：自定义初始化
 * BeanNameAware：设置 beanName 的 Aware 接口
 * <h3>3.1 MessageSource</h3>
 * MessageSource 定义了获取 message 的策略方法 #getMessage(...) 。
 * <p>
 * 在 ApplicationContext 体系中，该方法由 AbstractApplicationContext 实现。
 * <p>
 * 在 AbstractApplicationContext 中，它持有一个 MessageSource 实例，将 #getMessage(...) 方法委托给该实例来实现，
 * 代码如{@link #message()}
 * <h3>3.2 ApplicationEventPublisher</h3>
 * ApplicationEventPublisher ，用于封装事件发布功能的接口，向事件监听器（Listener）发送事件消息。
 * <p>
 * 该接口提供了一个 #publishEvent(Object event, ...) 方法，用于通知在此应用程序中注册的所有的监听器。
 * 该方法在 AbstractApplicationContext 中实现。代码如{@link #applicationEventPublisher()}
 * <h3>3.3 ResourcePatternResolver</h3>
 * ResourcePatternResolver 接口继承 ResourceLoader 接口，为将 location 解析为 Resource 对象的策略
 * 接口。他提供的 #getResources(String locationPattern) 方法，在 AbstractApplicationContext 中实
 * 现，在 AbstractApplicationContext 中他持有一个 ResourcePatternResolver 的实例对象。
 * <p>
 * 如果小伙伴对 Spring 的 ResourceLoader 比较熟悉的话，你会发现最终是在 PathMatchingResourcePatternResolver 中实现，
 * 该类是 ResourcePatternResolver 接口的实现者。
 * <h3>3.4 EnvironmentCapable</h3>
 * 提供当前系统环境 Environment 组件。提供了一个 #getEnvironment() 方法，用于返回 Environment 实例对象。
 * 该方法在 AbstractApplicationContext 实现。{@link #environment)}
 * <h3>3.5 Lifecycle</h3>
 * Lifecycle ，一个用于管理声明周期的接口。
 * <p>
 * 在 AbstractApplicationContext 中存在一个 LifecycleProcessor 类型的实例对象 lifecycleProcessor ，
 * AbstractApplicationContext 中关于 Lifecycle 接口的实现都是委托给 lifecycleProcessor 实现的。
 * {@link AbstractApplicationContext}在启动、停止的时候会分别发布 ContextStartedEvent 和 ContextStoppedEvent 事件。
 * <h3>3.6 Closable</h3>
 * Closable 接口用于关闭和释放资源，提供了 #close() 方法，以释放对象所持有的资源。在 ApplicationContext 体系中由
 * AbstractApplicationContext 实现，用于关闭 ApplicationContext 销毁所有 Bean ，此外如果注册有 JVM shutdown
 * hook ，同样要将其移除。{@link AbstractApplicationContext#close}
 * <h3>3.7 InitializingBean</h3>
 * InitializingBean 为 Bean 提供了初始化方法的方式，它提供的 #afterPropertiesSet() 方法，用于执行初始化动作。在
 * ApplicationContext 体系中，该方法由 AbstractRefreshableConfigApplicationContext 实现，
 * <h3>3.8 BeanNameAware</h3>
 * BeanNameAware ，设置 Bean Name 的接口。接口在 AbstractRefreshableConfigApplicationContext 中实现。
 * <h2>4. 小结</h2>
 * 由于篇幅问题，再加上大部分接口小编都已经在前面文章进行了详细的阐述，所以本文主要是以 Spring Framework 的 ApplicationContext 为中心，对其结构和功能的实现进行了简要的说明。
 *
 * 这里不得不说 Spring 真的是一个非常优秀的框架，具有良好的结构设计和接口抽象，它的每一个接口职能单一，且都是具体功能到各个模块的高度抽象，且几乎每套接口都提供了一个默认的实现（defaultXXX）。
 *
 * 对于 ApplicationContext 体系而言，他继承 Spring 中众多的核心接口，能够为客户端提供一个相对完整的 Spring 容器，接口 ConfigurableApplicationContext 对 ApplicationContext 接口再次进行扩展，提供了生命周期的管理功能。
 * 抽象类 ApplicationContext 对整套接口提供了大部分的默认实现，将其中“不易变动”的部分进行了封装，通过“组合”的方式将“容易变动”的功能委托给其他类来实现，同时利用模板方法模式将一些方法的实现开放出去由子类实现，从而实现“对扩展开放，对修改封闭”的设计原则。
 *
 * 最后我们再来领略下图的风采：{@link #classPath()}
 *
 * {@link ContainFresh}
 *
 * @author xuweizhi
 * @date 2019/03/27 16:09
 */
public class ApplicationContextResolver {

    /**
     * <image src="../../../../../resources/static/spring/wuyu.png"></image>
     */
    public void image() {

    }

    /**
     * <image src="../../../../../resources/static/spring/classpaath.jpg"></image>
     */
    public void classPath() {

    }
    /**
     * 真正的实现逻辑在{@link AbstractMessageSource#getMessage}中
     */
    public void message() {
        //// AbstractApplicationContext.java
        //
        //private MessageSource messageSource;
        //
        //  // 实现 getMessage()
        //public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
        //    // 委托给 messageSource 实现
        //    return getMessageSource().getMessage(code, args, defaultMessage, locale);
        //}
        //
        //private MessageSource getMessageSource() throws IllegalStateException {
        //    if (this.messageSource == null) {
        //        throw new IllegalStateException("MessageSource not initialized - " + "call 'refresh' before accessing messages via the context: " + this);
        //    }
        //    return this.messageSource;
        //}
    }

    /**
     * 如果指定的事件不是 ApplicationEvent，则它将包装在PayloadApplicationEvent 中。
     * 如果存在父级 ApplicationContext ，则同样要将 event 发布给父级 ApplicationContext 。
     */
    public void applicationEventPublisher() {
        //// AbstractApplicationContext.java
        //
        //@Override
        //public void publishEvent(ApplicationEvent event) {
        //    publishEvent(event, null);
        //}
        //
        //@Override
        //public void publishEvent(Object event) {
        //    publishEvent(event, null);
        //}
        //
        //protected void publishEvent(Object event, @Nullable ResolvableType eventType) {
        //    Assert.notNull(event, "Event must not be null");
        //
        //    // Decorate event as an ApplicationEvent if necessary
        //    ApplicationEvent applicationEvent;
        //    if (event instanceof ApplicationEvent) {
        //        applicationEvent = (ApplicationEvent) event;
        //    } else {
        //        applicationEvent = new PayloadApplicationEvent<>(this, event);
        //        if (eventType == null) {
        //            eventType = ((PayloadApplicationEvent) applicationEvent).getResolvableType();
        //        }
        //    }
        //
        //    // Multicast right now if possible - or lazily once the multicaster is initialized
        //    if (this.earlyApplicationEvents != null) {
        //        this.earlyApplicationEvents.add(applicationEvent);
        //    } else {
        //        getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
        //    }
        //
        //    // Publish event via parent context as well...
        //    if (this.parent != null) {
        //        if (this.parent instanceof AbstractApplicationContext) {
        //            ((AbstractApplicationContext) this.parent).publishEvent(event, eventType);
        //        } else {
        //            this.parent.publishEvent(event);
        //        }
        //    }
        //}
    }

    public void environment() {
        //// AbstractApplicationContext.java
        //public ConfigurableEnvironment getEnvironment() {
        //    if (this.environment == null) {
        //        this.environment = createEnvironment();
        //    }
        //    return this.environment;
        //}
        //如果持有的 environment 实例对象为空，则调用 #createEnvironment() 方法，创建一个。代码如下：
        //
        //// AbstractApplicationContext.java
        //
        //protected ConfigurableEnvironment createEnvironment() {
        //    return new StandardEnvironment();
        //}
        //StandardEnvironment 是一个适用于非 WEB 应用的 Environment。
    }

}
