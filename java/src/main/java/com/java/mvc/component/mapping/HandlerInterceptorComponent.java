package com.java.mvc.component.mapping;

import com.java.mvc.config.MVCConfiguration;
import com.java.mvc.servlet.SecurityInterceptor;
import com.java.mvc.handler.AbstractHandlerMapping;
import com.java.mvc.handler.MappedInterceptor;
import com.java.mvc.resource.HandlerExecutionChain;
import com.java.mvc.resource.HandlerInterceptor;

/**
 * {@link AbstractHandlerMappingComponent}
 * <h2>1. 概述</h2>
 * 本文，我们来分享 Spring MVC 的拦截器，可能是大家最最最熟悉的组件。如果胖友没有实现过自定义的 Spring MVC 的拦截器，
 * 那可能有丢丢遗憾。所以，在看这篇文章之余，胖友也可以自己去尝试写一个 Spring MVC 的拦截器。
 * <h2>2. HandlerInterceptor</h2>
 * {@link HandlerInterceptor}
 * <h2>3. HandlerExecutionChain</h2>
 * {@link HandlerExecutionChain}，处理器执行链。
 * <h3>3.1 构造方法</h3>
 * 查看属性和构造放啊
 * <h3>3.2 addInterceptor</h3>
 * {@link HandlerExecutionChain#addInterceptor}方法，添加拦截器到 interceptorList 中。
 * <p>
 * {@link HandlerExecutionChain#addInterceptors}方法，添加拦截器们到 interceptorList 中。
 * <h3>3.3 getInterceptors</h3>
 * {@link HandlerExecutionChain#getInterceptors}获得 interceptors 数组。
 * <h3>3.4 applyPreHandle</h3>
 * {@link HandlerExecutionChain#applyPreHandle} 方法，应用拦截器的前置处理。
 * <h3>3.5 triggerAfterCompletion</h3>
 * {@link HandlerExecutionChain#triggerAfterCompletion} 方法，触发拦截器的已完成处理。
 * <h3>3.6 applyPostHandle</h3>
 * {@link HandlerExecutionChain#applyPostHandle} 方法，应用拦截器的后置处理。
 * <h2>4. HandlerInterceptor 实现类</h2>
 * HandlerInterceptor 的实现类，如下图所示：{@link #image()}
 * <p>
 * 比较多，本文我们只看几个重要的实现类。
 * <h3>4.1 MappedInterceptor</h3>
 * {@link MappedInterceptor}，实现 HandlerInterceptor 接口，支持地址匹
 * 配的 HandlerInterceptor 实现类。
 * <b>4.1.1 构造方法</b>
 * <ul>
 * <li>{@link MappedInterceptor#includePatterns}</li>
 * <li>{@link MappedInterceptor#excludePatterns}</li>
 * <li>{@link MappedInterceptor#pathMatcher}</li>
 * <li>{@link MappedInterceptor#interceptor}</li>
 * </ul>
 * <b>4.1.2 matches</b><p></p>
 * {@link MappedInterceptor#matches} 方法，判断路径是否匹配。
 * <p></p>
 * <b>4.1.3 拦截方法实现</b><p></p>
 * 直接调用拦截器的方法
 * <h2>5. 拦截器配置</h2>
 * 在 Spring MVC 中，有多种方式，配置拦截器。那么对拦截器的配置是怎么解析和初始化的呢？下面，我们
 * 逐小节来解析。当然，本文暂时不会特别详细解析，而是交给胖友自己去研读。
 * <h3>5.1 resource:interceptors /> 标签</h3>
 * 实际上，我们已经看过 resource:interceptors /> 的配置示例。那么，再来看一次，哈哈哈哈。
 * 每一个 resource:interceptor /> 标签，会被 {@link InterceptorsBeanDefinitionParser} 解析成
 * 「4.1 MappedInterceptor」 对象，注册到 Spring IOC 容器中。
 * <p>
 * 在 {@link AbstractHandlerMapping#detectMappedInterceptors}方法中，会扫描 MappedInterceptor Bean 。
 * <p>
 * 所以，这种方式，胖友只要看看 InterceptorsBeanDefinitionParser 类即可。
 * <p>
 * 当然，基于这样的思路，我们直接配置 MappedInterceptor 的 Bean 对象也是可以的，无论是通过 XML ，还是通过 @Bean 注解。
 * <h3>5.2 Java Config</h3>
 * 在使用 Spring Boot 时，这是主流的方式。示例如下：
 * <ul>
 * <li>{@link SecurityInterceptor }</li>
 * <li>{@link MVCConfiguration  }</li>
 * </ul>
 * SecurityInterceptor 是拦截器，通过 @Component 注册到 Spring IOC 容器中。因为它是
 * HandlerInterceptorAdapter 的子类,<b>而不是 MappedInterceptor 的子类</b>，所以不
 * 会被 {@link AbstractHandlerMapping#detectMappedInterceptors} 方法扫描到.
 * <p>
 * 在 MVCConfiguration 的 #addInterceptors(InterceptorRegistry registry) 方法中，我们将
 * securityInterceptor 拦截器添加到 InterceptorRegistry 这个拦截器注册表中。
 * <p>
 * 通过 {@link com.java.mvc.resource.WebMvcConfigurationSupport#getInterceptors}方法，获得拦截器们。
 * <p>
 * 而通过这样的方式的方式配置拦截器，最终通过 {@link AbstractHandlerMapping#setInterceptors}，
 * 设置到 MappingHandler 中。
 * <p>
 * 当然，具体哪些地方调用 AbstractMappingHandler 的 #setInterceptors(Object... interceptors) 方法，
 * 请使用 IDEA 的搜索下，哪些地方调用了 WebMvcConfigurationSupport 的 #getInterceptors() 方法。
 *
 * {@link AbstractHandlerMethodMappingComponent}
 * @author xuweizhi
 * @date 2019/03/31 19:07
 */
public class HandlerInterceptorComponent {

    /**
     * <image src="../../image/12.png"></image>
     * 好像显示不完全
     */
    public void image() {

    }

    //每一个 <resource:interceptor /> 标签，将被解析成一个 MappedInterceptor Bean 对象。
    //<resource:interceptors>
    //    <resource:interceptor>
    //        <resource:mapping path="/interceptor/**" />
    //        <resource:exclude-mapping path="/interceptor/b/*" />
    //        <bean class="com.elim.learn.spring.resource.interceptor.MyInterceptor" />
    //    </resource:interceptor>
    //</resource:interceptors>
}
