package com.java.mvc.component.adapter;

import com.java.mvc.component.mapping.AbstractHandlerMethodMappingComponent;
import com.java.mvc.component.mapping.AbstractUrlHandlerMappingComponent;
import com.java.mvc.handler.SimpleServletHandlerAdapter;
import com.java.mvc.resource.*;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.mvc.Controller;

/**
 * {@link AbstractUrlHandlerMappingComponent} 组件
 *
 * <h2>1. 概述</h2>
 * {@link HandlerAdapter}，处理器适配器接口。
 * <p>
 * 因为，处理器 handler 的类型是 Object 类型，需要有一个调用者来实现 handler 是怎么被使用，怎么被执行。
 * 而 HandlerAdapter 的用途就在于此。可能如果接口名改成 HandlerInvoker ，笔者觉得会更好理解。
 * <h2>2. HandlerAdapter</h2>
 * HandlerAdapter 的子类比较多，整体类图如下：{@link #image()}
 * <ul>
 * <li>左边的 AbstractHandlerMethodAdapter 和 RequestMappingHandlerAdapter 相对复杂</li>
 * <li>右边的 SimpleServletHandlerAdapter、HttpRequestHandlerAdapter、SimpleControllerHandlerAdapter 相对简单</li>
 * </ul>
 * <h2>3. SimpleControllerHandlerAdapter</h2>
 * {@link SimpleControllerHandlerAdapter}，实现 HandlerAdapter 接口，基于 {@link Controller} 的 HandlerAdapter 实现类。
 * <h2>4. HttpRequestHandlerAdapter</h2>
 * {@link HttpRequestHandlerAdapter}，实现 HandlerAdapter 接口，基于 {@link HttpRequestHandler} 的 HandlerAdapter 实现类。
 * <h2>5. SimpleServletHandlerAdapter</h2>
 * {@link SimpleServletHandlerAdapter}实现 HandlerAdapter 接口，基于 javax.servlet.Servlet 的 HandlerAdapter 实现类。
 * <h2>6. AbstractHandlerMethodAdapter</h2>
 * {@link AbstractHandlerMethodAdapter}实现 HandlerAdapter、Ordered 接口，继承 WebContentGenerator 抽象类，基于
 * org.springframework.web.method.HandlerMethod 的 HandlerMethodAdapter 抽象类。
 * <h3>为什么要有这层抽象？</h3>
 * <p>
 * 让我们回过头看看 {@link AbstractHandlerMethodMappingComponent}就会明白：
 * <ul>
 * <li>AbstractHandlerMethodMapping 对应 「6. AbstractHandlerMethodAdapter」 。</li>
 * <li>RequestMappingInfoHandlerMapping 对应 「7. RequestMappingHandlerAdapter」 。</li>
 * </ul>
 * <h3>6.1 构造方法</h3>
 * {@link AbstractHandlerMethodAdapter#AbstractHandlerMethodAdapter}
 * <h3>6.2 supports</h3>
 * {@link AbstractHandlerMethodAdapter#supports}方法，支持 HandlerMethod 类型的处理器。
 * <h3>6.3 handle</h3>
 * {@link AbstractHandlerMethodAdapter#handle}方法，处理器请求。
 * <h3>6.4 getLastModified</h3>
 * {@link AbstractHandlerMethodAdapter#getLastModified} 方法，获得最后更新时间。
 * <h2>7. RequestMappingHandlerAdapter</h2>
 * {@link RequestMappingHandlerAdapter}，实现 BeanFactoryAware、InitializingBean 接口，继承 AbstractHandlerMethodAdapter
 * 抽象类，基于 @RequestMapping 注解的 HandlerMethod 的 HandlerMethodAdapter 实现类。
 * <h3>7.1 构造方法</h3>
 * {@link RequestMappingHandlerAdapter}
 * <h3>7.2 afterPropertiesSet</h3>
 * 这里我们会看到大量的 RequestMappingHandlerAdapter 的属性初始化。
 * {@link RequestMappingHandlerAdapter#afterPropertiesSet}
 * <p>
 * 调试进{@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#afterPropertiesSet}
 * <h3>7.2.1 initControllerAdviceCache</h3>
 * {@link RequestMappingHandlerAdapter#initControllerAdviceCache}方法，初始化 ControllerAdvice 相关。
 * <h3>7.2.2 getDefaultArgumentResolvers</h3>
 * {@link RequestMappingHandlerAdapter#getDefaultArgumentResolvers()} 方法，获得默认的 HandlerMethodArgumentResolver 数组。
 * <h3>7.2.3 getDefaultInitBinderArgumentResolvers</h3>
 * {@link RequestMappingHandlerAdapter#getDefaultInitBinderArgumentResolvers()} 方法，获得默认的 HandlerMethodArgumentResolver  数组。
 * <h3>7.2.4 getDefaultReturnValueHandlers</h3>
 * {@link RequestMappingHandlerAdapter#getDefaultReturnValueHandlers()} 方法，获得默认的 HandlerMethodReturnValueHandler 数组。
 * <h3>7.3 supportsInternal</h3>
 * 实现 #supportsInternal() 接口，默认返回 true 。
 * <h3>7.4 getLastModifiedInternal</h3>
 * 实现 #getLastModifiedInternal() 方法，默认返回 -1 。
 * <h3>7.5 handleInternal</h3>
 * 实现{@link RequestMappingHandlerAdapter#handleInternal}方法，处理请求。
 *
 * 我们“再次”来分享 ServletInvocableHandlerMethod 组件。{@link InvocableMethod}
 * @author xuweizhi
 * @date 2019/03/31 19:12
 */
public class HandlerAdapterComponent {

    /**
     * <image src="../../image/13.png"></image>
     */
    public void image() {

    }
}
