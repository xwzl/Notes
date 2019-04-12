package com.java.mvc.component;

import com.java.mvc.resource.AbstractDispatcherServletInitializer;
import com.java.mvc.resource.SpringServletContainerInitializer;
import com.java.mvc.resource.WebApplicationInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.Set;

/**
 * {@link WebApplicationContext}
 *
 * <h1>容器的初始化 Servlet 3.0 集成</h1>
 * 起来看看 Spring MVC 的初始化过程，是如何集成到 Servlet 3.0 和 Spring Boot 之中的。
 * <ul>
 * <li>Spring MVC 是如何集成到 Servlet 3.0 中</li>
 * <li>Spring MVC 是如何集成到 Spring Boot 中</li>
 * </ul>
 * 今天我们来放松下心情，不聊分布式，云原生，来聊一聊初学者接触的最多的 java web 基础。几乎所有人都是从 servlet，
 * jsp，filter 开始编写自己的第一个 hello world 工程。那时，还离不开 web.xml 的配置，在 xml 文件中编写繁琐的
 * servlet 和 filter 的配置。随着 spring 的普及，配置逐渐演变成了两种方式 —— java configuration 和 xml 配
 * 置共存。现如今，springboot 的普及，java configuration 成了主流，xml 配置似乎已经“灭绝”了。不知道你有没有好
 * 奇过，这中间都发生了哪些改变，web.xml 中的配置项又是被什么替代项取代了？
 * <h2>1. Servlet 3.0 以前的时代</h2>
 * 为了体现出整个演进过程，还是来回顾下 n 年前我们是怎么写 servlet 和 filter 代码的。
 * {@link #servlet3Before}
 * <h2>2. Servlet 3.0 新特性</h2>
 * {@link #servlet3After()}
 * <h2>3. Spring 是如何支持 Servlet 3.0 的？</h2>
 * {@link #servlet3Spring}
 * <p>
 * {@link SpringBootServletInit}
 *
 * @author xuweizhi
 * @date 2019/03/30 21:28
 */
public class Servlet3 {

    /**
     * 目结构（本文都采用 maven 项目结构）
     * <p>
     * .
     * ├── pom.xml
     * ├── src
     * ├── main
     * │   ├── java
     * │   │   └── moe
     * │   │       └── cnkirito
     * │   │           ├── filter
     * │   │           │   └── HelloWorldFilter.java
     * │   │           └── servlet
     * │   │               └── HelloWorldServlet.java
     * │   └── resources
     * │       └── WEB-INF
     * │           └── web.xml
     * └── test
     * └── java
     * <p>
     * {@link HelloWorldFilter} {@link HelloWorldServlet}
     * 别忘了在 web.xml 中配置 servlet 和 filter 。
     * <p>
     * 这样，一个 java web hello world 就完成了。当然，本文不是 servlet 的入门教程，只是为了对比。
     */
    public void servlet3Before() {

        //<?xml version="1.0" encoding="UTF-8"?>
        //<web-app xmlns="http://java.sun.com/xml/ns/javaee"
        //           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        //           xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        //        http://java.sun.com/xml/ns/javaee/web-app_4_0.xsd"
        //           version="4.0">
        //
        //    <servlet>
        //        <servlet-name>HelloWorldServlet</servlet-name>
        //        <servlet-class>moe.cnkirito.servlet.HelloWorldServlet</servlet-class>
        //    </servlet>
        //
        //    <servlet-mapping>
        //        <servlet-name>HelloWorldServlet</servlet-name>
        //        <url-pattern>/hello</url-pattern>
        //    </servlet-mapping>
        //
        //    <filter>
        //        <filter-name>HelloWorldFilter</filter-name>
        //        <filter-class>moe.cnkirito.filter.HelloWorldFilter</filter-class>
        //    </filter>
        //    <filter-mapping>
        //        <filter-name>HelloWorldFilter</filter-name>
        //        <url-pattern>/hello</url-pattern>
        //    </filter-mapping>
        //
        //</web-app>

    }

    /**
     * Servlet 3.0 作为 Java EE 6 规范体系中一员，随着 Java EE 6 规范一起发布。该版本在前一版本（Servlet 2.5）的
     * 基础上提供了若干新特性用于简化 Web 应用的开发和部署。其中一项新特性便是提供了无 xml 配置的特性。
     * <p>
     * servlet3.0 首先提供了 @WebServlet ，@WebFilter 等注解，这样便有了抛弃 web.xml 的第一个途径，凭借注解声明
     * servlet 和 filter 来做到这一点。
     * <p>
     * 除了这种方式，servlet3.0 规范还提供了更强大的功能，可以在运行时动态注册 servlet ，filter，listener。以 servlet
     * 为例，过滤器与监听器与之类似。ServletContext 为动态配置 Servlet 增加了如下方法：
     * <ul>
     * <li>ServletRegistration.Dynamic addServlet(String servletName,Class<? extends Servlet> servletClass)</li>
     * <li>ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet)</li>
     * <li>ServletRegistration.Dynamic addServlet(String servletName, String className)</li>
     * <li>T createServlet(Class clazz)</li>
     * <li>ServletRegistration getServletRegistration(String servletName)</li>
     * <li>Map<String,? extends ServletRegistration> getServletRegistrations()</li>
     * </ul>
     * 其中前三个方法的作用是相同的，只是参数类型不同而已；
     * <p>
     * 通过 #createServlet(Class clazz) 方法创建的 Servlet，通常需要做一些自定义的配置，然后使用 #addServlet(...)
     * 方法来将其动态注册为一个可以用于服务的 Servlet。
     * <p>
     * 两个 #getServletRegistration() 方法主要用于动态为 Servlet 增加映射信息，这等价于在 web.xml 中使用 标签为存在
     * 的 Servlet 增加映射信息。
     * <p>
     * 以上 ServletContext 新增的方法：
     *
     * <ul>
     * <li>要么是在 javax.servlet.ServletContextListener 的 #contextInitialized(ServletContextEvent sce) 方法中调用。</li>
     * <li>要么是在 javax.servlet.ServletContainerInitializer 的 #onStartup(Set<Class<?>> c, ServletContext ctx) 方法中调用。</li>
     * </ul>
     * <p>
     * ServletContainerInitializer 也是 Servlet 3.0 新增的一个接口，容器在启动时使用 JAR 服务 API(JAR Service API) 来
     * 发现 ServletContainerInitializer 的实现类，并且容器将 WEB-INF/lib 目录下 JAR 包中的类都交给该类的 #onStartup(...)
     * 方法处理，我们通常需要在该实现类上使用 @HandlesTypes 注解来指定希望被处理的类，过滤掉不希望给 #onStartup(...) 处理的类。
     * <p>
     * 一个典型的 servlet3.0+ 的 web 项目结构如下：
     * .
     * ├── pom.xml
     * └── src
     * ├── main
     * │   ├── java
     * │   │   └── moe
     * │   │       └── cnkirito
     * │   │           ├── CustomServletContainerInitializer.java
     * │   │           ├── filter
     * │   │           │   └── HelloWorldFilter.java
     * │   │           └── servlet
     * │   │               └── HelloWorldServlet.java
     * │   └── resources
     * │       └── META-INF
     * │           └── services
     * │               └── javax.servlet.ServletContainerInitializer
     * └── test
     * └── java
     * <p>
     * 我并未对 HelloWorldServlet 和 HelloWorldFilter 做任何改动，而是新增了一个{@link CustomServletContainerInitializer},它实现了
     * javax.servlet.ServletContainerInitializer 接口，用来在 web 容器启动时加载指定的 servlet 和 filter。
     * <p>
     * 而方法入参中 Set<Class<?>> c 和 @HandlesTypes 注解在 demo 中我并未使用，感兴趣的朋友可以 debug 看看
     * 到底获取了哪些 class ，一般正常的流程是使用 @HandlesTypes 指定需要处理的 class，而后对 Set<Class<?>>
     * 进行判断是否属于该 class，正如前文所言，#onStartup(...) 会加载不需要被处理的一些 class。
     * <p>
     * 这么声明一个 ServletContainerInitializer 的实现类，web 容器并不会识别它，所以，需要借助 SPI 机制来指定
     * 该初始化类，这一步骤是通过在项目路径下创建 META-INF/services/javax.servlet.ServletContainerInitializer
     * 来做到的，它只包含一行内容：
     * <p>
     * moe.cnkirito.CustomServletContainerInitializer
     * <p>
     * 使用 ServletContainerInitializer 和 SPI 机制，我们的 web 应用便可以彻底摆脱 web.xml 了。
     */
    public void servlet3After() {


    }

    /**
     * 回到我们的 spring 全家桶，可能已经忘了具体是什么时候开始不写 web.xml 了，我只知道现在的项目已经再也看不到它了，
     * spring 又是如何支持 servlet3.0 规范的呢？
     * <p>
     * 寻找 spring 中 ServletContainerInitializer 的实现类并不困难，可以迅速定位到 {@link SpringServletContainerInitializer#onStartup}
     * 该实现类。
     * <p>
     * #onStartup中有一个重要的类WebApplicationInitializer，WebApplicationInitializer 类便是 spring 用来初始
     * 化 web 环境的委托者类，它通常有三个实现类：<p></p>
     * <image src="../image/07.png"></image>
     * <p>
     * 你一定不会对 DispatcherServlet 感到陌生，AbstractDispatcherServletInitializer#registerDispatcherServlet
     * 便是<b>无 web.xml 前提下创建 DispatcherServlet 的关键代码。</b>
     * <p>
     * 其实现逻辑是在{@link SpringServletContainerInitializer#onStartup}调用{@link WebApplicationInitializer#onStartup}
     * 方法,在子类{@link AbstractDispatcherServletInitializer#onStartup}，
     * 你一定不会对 DispatcherServlet 感到陌生，AbstractDispatcherServletInitializer#registerDispatcherServlet 便是
     * 无 web.xml 前提下创建 DispatcherServlet 的关键代码。
     * <p>
     * 然后，我们可以去项目中寻找一下 org.springframework:spring-web:version 的依赖，它下面就存在一个 ServletContainerInitializer
     * 的扩展，指向了 SpringServletContainerInitializer，这样只要在 servlet 3.0 环境下部署，spring 便可以自动加载进
     * 行初始化：<p></p>
     * <image src="../image/08.png"></image>
     * <p></p>
     * 注意，上述这一切特性从 spring 3 就已经存在了，而如今 spring 5 已经伴随 springboot 2.0 一起发行了。
     */
    public void servlet3Spring() {

    }
}

/**
 * 自定义servlet
 */
class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("hello world");
    }

}

/**
 * 自定义拦截器
 */
class HelloWorldFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("触发 hello world 过滤器...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

/**
 * 自定义初始化器
 */
class CustomServletContainerInitializer implements ServletContainerInitializer {

    private final static String JAR_HELLO_URL = "/hello";

    /**
     * ServletContext 我们称之为 servlet 上下文，它维护了整个 web 容器中注册的 servlet，filter，listener，
     * 以 servlet 为例，可以使用 servletContext.addServlet 等方法来添加 servlet。
     * <p>
     * 而方法入参中 Set<Class<?>> c 和 @HandlesTypes 注解在 demo 中我并未使用，感兴趣的朋友可以 debug 看看
     * 到底获取了哪些 class ，一般正常的流程是使用 @HandlesTypes 指定需要处理的 class，而后对 Set<Class<?>>
     * 进行判断是否属于该 class，正如前文所言，#onStartup(...) 会加载不需要被处理的一些 class。
     * <p>
     * 这么声明一个 ServletContainerInitializer 的实现类，web 容器并不会识别它，所以，需要借助 SPI 机制来指定
     * 该初始化类，这一步骤是通过在项目路径下创建 META-INF/services/javax.servlet.ServletContainerInitializer
     * 来做到的，它只包含一行内容：
     * <p>
     * moe.cnkirito.CustomServletContainerInitializer
     * <p>
     * 使用 ServletContainerInitializer 和 SPI 机制，我们的 web 应用便可以彻底摆脱 web.xml 了。
     *
     * @param servletContext ServletContext 我们称之为 servlet 上下文
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext servletContext) {

        System.out.println("创建 helloWorldServlet...");

        //注册
        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                HelloWorldServlet.class.getSimpleName(),
                HelloWorldServlet.class);
        servlet.addMapping(JAR_HELLO_URL);

        System.out.println("创建 helloWorldFilter...");

        FilterRegistration.Dynamic filter = servletContext.addFilter(
                HelloWorldFilter.class.getSimpleName(), HelloWorldFilter.class);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        filter.addMappingForUrlPatterns(dispatcherTypes, true, JAR_HELLO_URL);
    }

}