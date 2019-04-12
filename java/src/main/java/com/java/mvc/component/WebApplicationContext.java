package com.java.mvc.component;

import com.java.mvc.resource.ContextLoader;
import com.java.mvc.resource.ContextLoaderListener;
import com.java.mvc.resource.FrameworkServlet;
import com.java.mvc.resource.HttpServletBean;
import com.java.spring.bean.AwareBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * <h1>1. 概述</h1>
 * <p>
 * 随着 Spring Boot 逐步全面覆盖到我们的项目之中，我们已经基本忘却当年经典的 Servlet + Spring MVC 的组合，
 * 那让人熟悉的 web.xml 配置。而本文，我们想先抛开 Spring Boot 到一旁，回到从前，一起来看看 Servlet 是怎么
 * 和 Spring MVC 集成，怎么来初始化 Spring 容器的。
 * <p>
 * 在开始看具体的源码实现之前，我们先一起来看看现在“陌生”的 web.xml 配置。代码如下：
 * {@link #xmlConfig()}
 * <p>
 * 在 1 处，配置了{@link ContextLoaderListener }对象。这是一个 javax.servlet.ServletContextListener 对象，
 * {@link ContextLoaderListener#initWebApplicationContext}会初始化一个Root Spring WebApplicationContext 容器.
 * <p>
 * <p>
 * 在 2 处，配置了{@link DispatcherServlet} 对象，这是一个{@link HttpServlet}对象，他除了拦截我们之地指定*.do
 * 请求外，也会初始化一个属于它的 Spring WebApplicationContext 容器。并且，这个容器是以 [1] 处的 Root 容器作为
 * 父容器。
 * <p>
 * 再总结一次，[1] 和 [2] 分别会创建其对应的 Spring WebApplicationContext 容器，并且它们是父子容器的关系。
 *
 * <h2>2. Root WebApplicationContext 容器</h2>
 * <p>
 * 在概述中，我们已经看到，Root WebApplicationContext 容器的初始化，通过 <b>ContextLoaderListener </b>来实现。
 * 在 Servlet 容器启动时，例如 Tomcat、Jetty 启动，则会被 ContextLoaderListener 监听到，从而调用 {@link ContextLoaderListener#contextInitialized}
 * 方法，初始化 Root WebApplicationContext 容器。
 * <p>
 * 而ContextLoaderListener 的类图如下：{@link #image()}
 *
 * <h3>3.1 ContextLoaderListener</h3>
 * <p>
 * {@link ContextLoaderListener} ，实现 ServletContextListener 接口，继承 ContextLoader 类，实现 Servlet 容器启动和关闭时，
 * 分别初始化和销毁 WebApplicationContext 容器。
 * <p>
 * 注意，这个 ContextLoaderListener 类，是在 spring-web 项目中。
 *
 * <h3>3.1.1 构造方法</h3>
 * {@link ContextLoaderListener#ContextLoaderListener(com.java.mvc.resource.WebApplicationContext)}
 * <p>
 * 这两个构造方法，是因为父类 ContextLoader 有这两个构造方法，所以必须重新定义。比较需要注意的是，第二个构造方法，可以直接传递一个
 * WebApplicationContext 对象，那样，实际 ContextLoaderListener 就无需在创建一个新的 WebApplicationContext 对象。
 *
 * <h3>3.1.2 contextInitialized</h3>
 * <p>
 * 调用父类 ContextLoader 的 {@link ContextLoaderListener#initWebApplicationContext(ServletContext servletContext)}
 * 方法，初始化 WebApplicationContext 对象。
 *
 * <h3>3.1.3 contextDestroyed</h3>
 * <p>
 * 销毁 WebApplicationContext 容器的逻辑。本文，甚至本系列，都应该暂时不会详细解析。所以，感兴趣的胖友，需要自己研究咯。当然，在这并不着急。
 *
 * <h3>3.2 ContextLoader</h3>
 * <p>
 * 因为 {@link ContextLoader} 的属性比较多，我们逐块来看。
 * <p>
 * 第一块，defaultStrategies 静态属性，默认的配置 Properties 对象。
 * <p>
 * {@link ContextLoader#initWebApplicationContext(ServletContext)}方法，初始化 WebApplicationContext 对象。
 *
 * <h2>4. Servlet WebApplicationContext 容器的初始化，是在 DispatcherServlet 初始化的过程中执行。</h2>
 * <p>
 * DispatcherServlet 的类图如下：{@link #image1()}
 *
 * <h3>4.1 HttpServletBean ，负责将 ServletConfig 设置到当前 Servlet 对象中。类上的简单注释如下</h3>
 * <p>
 * {@link HttpServletBean} and {@link #httpServletBean()}
 *
 * <h3>4.2 FrameworkServlet ，负责初始化 Spring Servlet WebApplicationContext 容器。</h3>
 * <p>
 * {@link FrameworkServlet} and {@link #frameworkServlet()}
 * <h3>DispatcherServlet ，负责初始化 Spring MVC 的各个组件，以及处理客户端的请求。</h3>
 * <p>
 * {@link DispatcherServlet} and {@link #dispatcherServlet()}
 * <p>
 * 每一层的 Servlet 实现类，执行对应负责的逻辑。干净~下面，我们逐个类来进行解析。
 * {@link Servlet3}
 *
 * @author xuweizhi
 * @date 2019/03/29 0:22
 */
public class WebApplicationContext {

    //<!-- 省略非关键的配置 -->
    //
    //<!-- [1] Spring配置 -->
    //<listener>
    //    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    //</listener>
    //<!-- 指定Spring Bean的配置文件所在目录。默认配置在WEB-INF目录下 -->
    //<context-param>
    //    <param-name>contextConfigLocation</param-name>
    //    <param-value>classpath:config/applicationContext.xml</param-value>
    //</context-param>
    //
    //<!-- ====================================== -->
    //
    //<!-- [2] Spring MVC配置 -->
    //<servlet>
    //    <servlet-name>spring</servlet-name>
    //    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    //    <!-- 可以自定义servlet.xml配置文件的位置和名称，默认为WEB-INF目录下，名称为[<servlet-name>]-servlet.xml，如spring-servlet.xml
    //    <init-param>
    //        <param-name>contextConfigLocation</param-name>
    //        <param-value>/WEB-INF/spring-servlet.xml</param-value> // 默认
    //    </init-param>
    //    -->
    //    <load-on-startup>1</load-on-startup>
    //</servlet>
    //<servlet-mapping>
    //    <servlet-name>spring</servlet-name>
    //    <url-pattern>*.do</url-pattern>
    //</servlet-mapping>
    public void xmlConfig() {

    }

    /**
     * <image src="../image/05.png"></image>
     */
    public void image() {

    }

    /**
     * <image src="../image/06.png"></image>
     */
    public void image1() {

    }

    /**
     * <h1>HttpServletBean</h1>
     * {@link HttpServletBean}，实现 EnvironmentCapable、EnvironmentAware 接口，继承 HttpServlet 抽象类，
     * 负责将 ServletConfig 集成到 Spring 中。当然，HttpServletBean 自身也是一个抽象类。
     * <h2>3.1 构造方法</h2>
     * <p>
     * {@link HttpServletBean#requiredProperties}
     * <p>
     * environment 属性，相关的方法：
     * <ul>
     * <li>{@link HttpServletBean#setEnvironment} 实现自 EnvironmentAware 接口，自动注入</li>
     * <li>{@link HttpServletBean#getEnvironment} 实现自 EnvironmentCapable 接口</li>
     * <li>{@link HttpServletBean#createEnvironment} 实现自 EnvironmentCapable 接口</li>
     * </ul>
     * 为什么 environment 属性，能够被自动注入呢？答案是 EnvironmentAware 接口,详情见{@link AwareBean}
     * <h2>3.2 init</h2>
     * {@link HttpServletBean#init()}方法，负责将 ServletConfig 设置到当前 Servlet 对象中。
     */
    public void httpServletBean() {

    }

    /**
     * <h1>FrameworkServlet</h1>
     * {@link FrameworkServlet} ，实现 ApplicationContextAware 接口，继承 HttpServletBean 抽象类，负责
     * 初始化 Spring Servlet WebApplicationContext 容器。同时，FrameworkServlet 自身也是一个抽象类。
     * <h2>4.1 构造方法</h2>
     * FrameworkServlet 的属性还是非常多，我们还是只看部分的关键属性。
     * <ul>
     * <li>{@link FrameworkServlet#contextClass} 创建的 WebApplicationContext 类型</li>
     * <li>{@link FrameworkServlet#contextConfigLocation}  配置文件的地址</li>
     * <li>{@link FrameworkServlet#webApplicationContext}  WebApplicationContext 对象</li>
     * <li>contextConfigLocation 属性，配置文件的地址。例如：/WEB-INF/spring-servlet.xml 。</li>
     * <li>webApplicationContext 属性，WebApplicationContext 对象，即本文的关键，Servlet WebApplicationContext 容器。它有四种方式进行“创建”。</li>
     * </ul>
     *
     * <h3>webApplicationContext四种创建方式</h3>
     * <ul>
     * <li>{@link FrameworkServlet#FrameworkServlet(org.springframework.web.context.WebApplicationContext)}</li>
     * <li>{@link FrameworkServlet#setApplicationContext(ApplicationContext)}</li>
     * <li>{@link FrameworkServlet#findWebApplicationContext}</li>
     * <li>{@link FrameworkServlet#createWebApplicationContext(org.springframework.web.context.WebApplicationContext)}</li>
     * </ul>
     * <h2>4.2 initServletBean</h2>
     * {@link FrameworkServlet#initServletBean}方法，进一步初始化当前 Servlet 对象。实际上，重心在初始化 Servlet WebApplicationContext 容器。
     * <h2></h2>
     */
    public void frameworkServlet() {

    }

    /**
     * <h1>DispatcherServlet</h1>
     */
    public void dispatcherServlet() {

    }
}
