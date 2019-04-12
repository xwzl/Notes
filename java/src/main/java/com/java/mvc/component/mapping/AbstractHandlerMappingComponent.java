package com.java.mvc.component.mapping;

import com.java.mvc.component.RequestHandler;
import com.java.mvc.handler.AbstractHandlerMapping;
import com.java.mvc.handler.MatchableHandlerMapping;
import com.java.mvc.handler.RequestMatchResult;
import com.java.mvc.resource.DispatcherServlet;
import com.java.mvc.resource.HandlerMapping;

/**
 * {@link RequestHandler}
 * <h1>HandlerMapping 组件</h1>
 * <h2>1. 概述</h2>
 * {@link HandlerMapping} ，处理器匹配接口，根据请求( handler )获得其的处理器( handler )
 * 和拦截器们( HandlerInterceptor 数组 )。
 * <h2>2. 类图</h2>
 * HandlerMapping 的子类比较多，整体类图如下：{@link #handlerMappingClassPicture}
 * <ul>
 * <li>绿框 AbstractHandlerMapping 抽象类，实现了【获得请求对应的处理器和拦截器们】的骨架逻辑，而暴露 而暴露
 * #getHandlerInternal(HttpServletRequest request) 抽象方法,，交由子类实现。这就是我们常说的“模板方法模式” 。</li>
 * <li>AbstractHandlerMapping 的子类，分成两派，分别是：
 * <ul>
 * <li>红框 AbstractUrlHandlerMapping 系，基于 URL 进行匹配。例如 《基于XML配置的Spring MVC 简单的
 * HelloWorld实例应用》 。当然，实际我们开发时，这种方式已经基本不用了，被 @RequestMapping 等注解的方式
 * 所取代。不过，Spring MVC 内置的一些路径匹配，还是使用这种方式。</li>
 * <li>黄框 AbstractHandlerMethodMapping 系，基于 Method 进行匹配。例如，我们所熟知的 @RequestMapping 等注解的方式。</li>
 * </ul>
 * </li>
 * <li>白框 MatchableHandlerMapping 接口，定义判断请求和指定 pattern 路径是否匹配的接口方法。</li>
 * </ul>
 * 考虑到文章的篇幅和更加干净，我们拆分成三篇文章：
 * <ul>
 * <li>本文，分享 AbstractHandlerMapping 抽象类、MatchableHandlerMapping 接口</li>
 * <li>下文，分享 AbstractHandlerMethodMapping 系</li>
 * <li>下下文，分享 AbstractUrlHandlerMapping 系</li>
 * </ul>
 * <h2>3. AbstractHandlerMapping</h2>
 * {@link #handlerMapping}
 * <h3>3.1 构造方法</h3>
 * 看这个类变量的定义
 * <h3>3.2 initApplicationContext</h3>
 * 该方法，是对 WebApplicationObjectSupport 的覆写，而 WebApplicationObjectSupport 的集成关系是
 * WebApplicationObjectSupport => ApplicationObjectSupport => ApplicationContextAware 。
 * <b>{@link AbstractHandlerMapping#initApplicationContext()}方法，初始化拦截器。</b>
 * <h3>3.3 getHandler</h3>
 * {@link AbstractHandlerMapping#getHandler}方法，获得请求对应的 HandlerExecutionChain 对象。
 * <h2>4. MatchableHandlerMapping</h2>
 * {@link MatchableHandlerMapping}，定义判断请求和指定 pattern 路径是否匹配的接口方法。
 * <p>
 * 返回的是 {@link RequestMatchResult} 类，请求匹配结果。
 * <p>
 * 目前实现 MatchableHandlerMapping 接口的类，有 RequestMappingHandlerMapping 类和
 * AbstractUrlHandlerMapping 抽象类
 * <h2>5. DispatcherServlet</h2>
 * 在 DispatcherServlet 中，通过调用 {@link DispatcherServlet#initHandlerMappings}方法，初始化
 * HandlerMapping 们。酱紫，HandlerMapping 就集成到 DispatcherServlet 中了。
 *
 * 下面一篇文章，我们先分享 HandlerInterceptor 拦截器，再分享 AbstractHandlerMethodMapping、AbstractUrlHandlerMapping 类。
 *
 * {@link HandlerInterceptorComponent}
 * @author xuweizhi
 * @date 2019/03/31 19:39
 */
public class AbstractHandlerMappingComponent {

    /**
     * <image src="../../image/11.png"></image>
     */
    public void handlerMappingClassPicture() {

    }

    /**
     * {@link AbstractHandlerMapping}，实现 HandlerMapping、Ordered、BeanNameAware 接口，继承
     * WebApplicationObjectSupport抽象类，HandlerMapping 抽象基类，实现了【获得请求对应的处理器和拦
     * 截器们】的骨架逻辑，<b>而暴露 {@link AbstractHandlerMapping#getHandlerInternal}抽象方法，交
     * 由子类实现</b>。
     * <p>
     * WebApplicationObjectSupport 抽象类，提供 applicationContext 属性的声明和注入。
     */
    public void handlerMapping() {

    }

}
