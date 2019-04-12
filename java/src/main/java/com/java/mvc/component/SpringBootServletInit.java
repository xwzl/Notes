package com.java.mvc.component;

import com.java.mvc.servlet.HelloServlet;
import com.java.mvc.config.MyServletConfigure;
import com.java.mvc.resource.ServletContextInitializerBeans;
import com.java.mvc.resource.ServletWebServerApplicationContext;
import com.java.mvc.resource.TomcatServletWebServerFactory;
import com.java.mvc.resource.TomcatStarter;
import com.java.mvc.servlet.CustomServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContainerInitializer;

/**
 * {@link Servlet3} 容器的初始化，Servlet 3 集成
 * <h2> Spring MVC 是如何集成到 Spring Boot 中的</h2>
 *
 * <h2>1. Spring Boot 如何加载 Servlet？</h2>
 * <p>
 * Spring Boot 对于 servlet 的处理才是重头戏：
 * <ul>
 * <li>其一，是因为 Spring Boot 使用范围很广，很少有人用 spring 而不用 Spring Boot 了。</li>
 * <li>其二，是因为它没有完全遵守 Servlet3.0 的规范！</li>
 * </ul>
 * <p>
 * 是的，前面所讲述的 servlet 的规范，无论是 web.xml 中的配置，还是 servlet3.0 中的 ServletContainerInitializer
 * 和 Spring Boot 的加载流程都没有太大的关联。按照惯例，先卖个关子，先看看如何在 Spring Boot 中注册 servlet 和 filter，
 * 再来解释下 Spring Boot 的独特之处。
 *
 * <h3>1.1 注册方式一：Servlet 3.0 注解 + @ServletComponentScan</h3>
 * <p>
 * Spring Boot 依旧兼容 Servlet 3.0 一系列以 @Web* 开头的注解：@WebServlet，@WebFilter，@WebListener 。
 * 如{@link #example()}Servlet 3.0 注解在SpringBoot中的支持。
 * <h3>1.2 注册方式二：RegistrationBean</h3>
 * {@link MyServletConfigure}
 * <p>
 * ServletRegistrationBean 和 FilterRegistrationBean 都集成自 RegistrationBean ，RegistrationBean
 * 是 Spring Boot 中广泛应用的一个注册类，负责把 servlet，filter，listener 给容器化，使他们被 Spring 托管，
 * 并且完成自身对 Web 容器的注册。这种注册方式也值得推崇。
 * <P></P>
 * <image src="../image/09.png"></image>
 * <p></p>
 * 从图中可以看出 RegistrationBean 的地位，它的几个实现类作用分别是：
 * <ul>
 * <li>前三个，帮助容器注册 filter，servlet，listener 。</li>
 * <li>最后的 DelegatingFilterProxyRegistrationBean 使用的不多，但熟悉 Spring Security 的朋友不会感到陌生，
 * SpringSecurityFilterChain 就是通过这个代理类来调用的。</li>
 * <li>另外 RegistrationBean 实现了 ServletContextInitializer 接口，这个接口将会是下面分析的核心接口，大家先
 * 混个眼熟，了解下它有一个抽象实现 RegistrationBean 即可。</li>
 * </ul>
 * <h2>2. SpringBoot 中 Servlet 加载流程的源码分析</h2>
 * 暂时只介绍这两种方式，下面解释下之前卖的关子，为什么说 Spring Boot 没有完全遵守 servlet3.0 规范。讨论的前提是
 * Spring Boot 环境下使用内嵌的容器，比如最典型的 Tomcat 。
 * <p>
 * 高能预警，以下内容比较烧脑，觉得看起来吃力的朋友可以跳过本节直接看下一节的总结！
 * <h3>2.1 Initializer 被替换为 TomcatStarter</h3>
 * 当使用内嵌的 Tomcat 时，你会发现 Spring Boot 完全走了另一套初始化流程，完全没有使用前面提到的 SpringServletContainerInitializer ，
 * 实际上一开始我在各种 ServletContainerInitializer 的实现类中打了断点，最终定位到，根本没有运行到 SpringServletContainerInitializer
 * 内部，而是进入了 {@link TomcatStarter} 这个类中。
 * <p></p>
 * <image src="../image/10.png"></image>
 * <p></p>
 * 并且，仔细扫了一眼源码的包，并没有发现有 SPI 文件对应到 TomcatStarter。于是我猜想，内嵌 Tomcat 的加载可能不依赖于
 * Servlet3.0 规范和 SPI ！它完全走了一套独立的逻辑。为了验证这一点，我翻阅了 Spring Github 中的 issue，得到了 Spring
 * 作者肯定的答复：https://github.com/spring-projects/spring-boot/issues/321
 * <p>
 * This was actually an intentional design decision. The search algorithm used by the containers was
 * problematic. It also causes problems when you want to develop an executable WAR as you often want
 * a javax.servlet.ServletContainerInitializer for the WAR that is not executed when you run java -jar.
 * See the org.springframework.boot.context.embedded.ServletContextInitializer for an option that works
 * with Spring Beans.
 * <p>
 * Spring Boot 这么做是有意而为之。Spring Boot 考虑到了如下的问题，我们在使用 Spring Boot 时，开发阶段一般都是使用内嵌
 * Tomcat 容器，但部署时却存在两种选择：一种是打成 jar 包，使用 java -jar 的方式运行；另一种是打成 war 包，交给外置容器去
 * 运行。
 * <p>
 * <b>前者就会导致容器搜索算法出现问题，因为这是 jar 包的运行策略，不会按照 Servlet 3.0 的策略去加载 ServletContainerInitializer！</b>
 * <p>
 * 最后作者还提供了一个替代选项：{@link ServletContextInitializer}，注意是 ServletContextInitializer！它和
 * {@link ServletContainerInitializer} 长得特别像，别搞混淆了！
 * <ul>
 * <li>前者 ServletContextInitializer 是 org.springframework.boot.web.servlet.ServletContextInitializer 。</li>
 * <li>后者 ServletContainerInitializer 是 javax.servlet.ServletContainerInitializer 。前文还提到 RegistrationBean
 * 实现了 ServletContextInitializer 接口。</li>
 * </ul>
 * <h3>2.2 TomcatStarter 中的 ServletContextInitializer 是关键</h3>
 * TomcatStarter 中<b>org.springframework.boot.context.embedded.ServletContextInitializer[] initializers 属性</b>，
 * 是 Spring Boot 初始化 servlet，filter，listener 的关键。
 * <p>
 * 进入类{@link org.springframework.boot.web.embedded.tomcat.TomcatStarter}打断点测试，看加载哪些
 * ServletContextInitializer类。
 * <p></p>
 * 经过删减源码后，可以看出 TomcatStarter 的主要逻辑，它其实就是负责调用一系列 ServletContextInitializer 的 #onStartup
 * (ServletContext servletContext) 方法，那么在 debug 中，ServletContextInitializer[] initializers 到底包含了哪些
 * 类呢？会不会有我们前面介绍的 RegistrationBean 呢？
 * <p>
 * 太天真了，RegistrationBean 并没有出现在 TomcatStarter 的 debug 信息中，initializers 只包含了三个类，其中只有第一个类看上去
 * 比较核心，注意第一个类不是 EmbeddedWebApplicationContext ！而是这个类中的 $1 匿名类，为了搞清楚 Spring Boot 如何加载 filter、
 * servlet、listener ，看来还得研究下 EmbeddedWebApplicationContext 的结构。
 * <p>
 * <b>PS:EmbeddedWebApplicationContext疑似转移到{@link ServletWebServerApplicationContext},EmbeddedWebApplicationContext
 * 的逻辑，应该是修改到了 ServletWebServerApplicationContext 中。</b>
 * <h3>2.3 ServletWebServerApplicationContext 中的 6 层迭代加载</h3>
 * {@link #app()}
 *
 * <h2>3. ServletWebServerApplicationContext加载流程总结</h2>
 * 如果你对具体的代码流程不感兴趣，可以跳过上述的 6 层分析，直接看本节的结论。总结如下：
 * <ul>
 * <li>ServletWebServerApplicationContext的 #onRefresh() 方法，触发配置了一个匿名的 ServletContextInitializer。</li>
 * <li>这个匿名的 ServletContextInitializer 的 onStartup(ServletContext servletContext) 方法，会去容器中搜索到了
 * 所有的 RegistrationBean ，并按照顺序加载到 ServletContext 中。</li>
 * <li>这个匿名的 ServletContextInitializer 最终传递给 TomcatStarter，由 TomcatStarter 的 onStartup 方法去触发
 * ServletContextInitializer 的 #onStartup(ServletContext servletContext) 方法，最终完成装配！</li>
 * </ul>
 * <h2>4. 第三种注册 Servlet 的方式</h2>
 * 研究完了上述 Spring Boot 启动的内部原理，可以发现 ServletContextInitializer 其实是 Spring 中 ServletContainerInitializer
 * 的代理，虽然 Spring Boot 中 Servlet3.0 不起作用了，但它的代理还是会被加载的，于是我们有了第三种方式注册 Servlet。
 * {@link CustomServletContextInitializer}
 * <p>
 * 虽然 ServletContainerInitializer 不能被内嵌容器加载，ServletContextInitializer 却能被 Spring Boot 的
 * ServletWebServerApplicationContext 加载到，从而装配其中的 servlet 和 filter。
 * <p>
 * 实际开发中，还是以一，二两种方法来注册为主，这里只是提供一个可能性，来让我们理解 Spring Boot 的加载流程。
 * <h2>5. 加载流程拾遗</h2>
 * <h3>5.1 TomcatStarter 既然不是通过 SPI 机制装配的，那是怎么被 Spring 使用的？</h3>
 * <b>调试请进入在 {@link org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory#configureContext}打断点，启动IO</b>
 * 自然是被 new 出来的。在 {@link TomcatServletWebServerFactory#configureContext} ，TomcatStarter 是被主动实例化出来的，
 * 并且还传入了 ServletContextInitializer 的数组，和上面分析的一样，一共有三个 ServletContextInitializer，包含了
 * ServletWebServerApplicationContext 中的匿名实现。
 * <h3>5.2 TomcatServletWebServerFactory 又是如何被声明的？</h3>
 * 只要类路径下存在 Tomcat 类，以及在 Web 环境下，就会触发 Spring Boot 的自动配置。
 * {@link ServletContainerAutoConfiguration}
 *
 * SpringMVC 组件{@link MvcComponent}
 * @author xuweizhi
 * @date 2019/03/30 23:39
 */
@Component
public class SpringBootServletInit {

    /**
     * Servlet 3.0 注解在SpringBoot中的支持。
     * <p>
     * 不要忘记让启动类去扫描到这些注解，如{@link com.java.Io} and {@link HelloServlet}
     */
    public void example() {
        //@WebServlet("/hello")
        //public class HelloWorldServlet extends HttpServlet {}
        //@WebFilter("/hello/*")
        //public class HelloWorldFilter implements Filter {}
    }

    //@Bean
    //public ServletRegistrationBean helloWorldServlet() {
    //    ServletRegistrationBean helloWorldServlet = new ServletRegistrationBean();
    //    myServlet.addUrlMappings("/hello");
    //    myServlet.setServlet(new HelloWorldServlet());
    //    return helloWorldServlet;
    //}
    //
    //@Bean
    //public FilterRegistrationBean helloWorldFilter() {
    //    FilterRegistrationBean helloWorldFilter = new FilterRegistrationBean();
    //    myFilter.addUrlPatterns("/hello/*");
    //    myFilter.setFilter(new HelloWorldFilter());
    //    return helloWorldFilter;
    //}

    /**
     * 大体原理，@ServletComponentScan 注解上的 @Import(ServletComponentScanRegistrar.class) ，
     * 它会将扫描到的 @WebServlet、@WebFilter、@WebListener 的注解对应的类，最终封装成 FilterRegistrationBean、
     * ServletRegistrationBean、ServletListenerRegistrationBean 对象，注册到 Spring 容器中。也就
     * 是说，和注册方式二：RegistrationBean 统一了。
     */
    public void springBootWithServlet() {
        //@SpringBootApplication
        //@ServletComponentScan
        //public class SpringBootServletApplication {
        //
        //   public static void main(String[] args) {
        //      SpringApplication.run(SpringBootServletApplication.class, args);
        //   }
        //}
    }

    /**
     * ApplicationContext 大家应该是比较熟悉的，这是 spring 一个比较核心的类，一般我们可以从中获取到那些注册在容器中的托管
     * Bean，而这篇文章，主要分析的便是它在内嵌容器中的实现类：ServletWebServerApplicationContext ，重点分析它加载 filter
     * servlet listener 这部分的代码。这里是整个代码中迭代层次最深的部分，做好心理准备起航，来看看 ServletWebServerApplicationContext
     * 是怎么获取到所有的 servlet、filter、listener 的！以下方法均出自于 ServletWebServerApplicationContext 。
     * <h3>第一层：onRefresh()</h3>
     * #onRefresh() 方法，是 ApplicationContext 的生命周期方法，EmbeddedWebApplicationContext 的实现非常简单，只干了一件事：
     * {@link ServletWebServerApplicationContext#onRefresh()} 进入{@link org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext#onRefresh()}打断点
     * <p>
     * 调用 #createWebServer() 方法，连接到了第二层。
     * <h3>第二层：createWebServer()</h3>
     * <b>看名字 Spring 是想创建一个内嵌的 Servlet 容器，WebServer 其实就是 servlet、filter、listener 的总称。</b>
     * {@link ServletWebServerApplicationContext#createWebServer}
     * <p>
     * 凡是带有 servlet，initializer 字样的方法，都是我们需要留意的。其中 #getSelfInitializer() 方法，便涉及到了我们最为
     * 关心的初始化流程，所以接着连接到了第三层。
     * <h3>第三层：getSelfInitializer()</h3>
     * {@link ServletWebServerApplicationContext#selfInitialize}
     * <p>
     * 还记得前面 TomcatStarter 的 debug 信息中，第一个 ServletContextInitializer 就是出现在 ServletWebServerApplicationContext
     * 中的一个匿名类，没错了，就是这里的 #getSelfInitializer() 方法创建的！
     * <p>
     * 解释下这里的 #getSelfInitializer() 和 #selfInitialize(ServletContext servletContext) 方法，为什么要这么设计？
     * 这是典型的回调式方式，当匿名 ServletContextInitializer 类被 TomcatStarter 的 #onStartup() 方法调用，设计上是触发了 #selfInitialize(ServletContext servletContext) 方法的调用。
     * 所以这下就清晰了，为什么 TomcatStarter 中没有出现 RegistrationBean ，其实是隐式触发了 EmbeddedWebApplicationContext 中的 #selfInitialize(ServletContext servletContext) 方法。这样，#selfInitialize(ServletContext servletContext) 方法中，调用 #getServletContextInitializerBeans() 方法，获得 ServletContextInitializer 数组就成了关键。
     * <h3>第四层：getServletContextInitializerBeans()</h3>
     * {@link ServletWebServerApplicationContext#getServletContextInitializerBeans}没错了，注释都告诉我们，这个
     * ServletContextInitializerBeans 类，就是用来加载 Servlet 和 Filter 的。
     * <h3>第五层：ServletContextInitializerBeans 的构造方法</h3>
     * {@link ServletContextInitializerBeans#ServletContextInitializerBeans}
     * <h3>第六层：addServletContextInitializerBeans(beanFactory)</h3>
     * {@link ServletContextInitializerBeans#addServletContextInitializerBeans}
     * <ul>
     * <li>调用 #getOrderedBeansOfType( beanFactory, ServletContextInitializer.class) 方法，便是去容器中寻找注册过得
     * ServletContextInitializer ，这时候就可以把之前那些 RegistrationBean 全部加载出来了。并且 RegistrationBean 还实现了
     * Ordered 接口，在这儿用于排序。</li>
     * <li>后续的 #addServletContextInitializerBean(ListableBeanFactory beanFactory) 方法，胖友可以自己去瞅瞅。</li>
     * </ul>
     */
    public void app() {

    }
}
