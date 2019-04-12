package com.java.mvc.component.mapping;

import com.java.mvc.component.adapter.HandlerAdapterComponent;
import com.java.mvc.handler.*;

/**
 * 注解映射派系分析：{@link AbstractHandlerMethodMappingComponent}
 * <p>
 * 因为 AbstractUrlHandlerMapping ，我们实际开发基本不会涉及。
 * <h2>1. 概述</h2>
 * 一共有五个子类，分成两条线。
 * <ul>
 * <li>AbstractUrlHandlerMapping <= SimpleUrlHandlerMapping <= WebSocketHandlerMapping 。</li>
 * <li>AbstractUrlHandlerMapping <= AbstractDetectingUrlHandlerMapping <= BeanNameUrlHandlerMapping 。</li>
 * </ul>
 * 其中，左下角的 WebSocketHandlerMapping 是 spring-websocket 项目中的类，本文会无视它。
 * <p>
 * 所以，本文我们实际会是按照 AbstractUrlHandlerMapping、SimpleUrlHandlerMapping、AbstractDetectingUrlHandlerMapping、
 * BeanNameUrlHandlerMapping 进行顺序分享。
 * <h2>2. AbstractUrlHandlerMapping</h2>
 * {@link AbstractUrlHandlerMapping} ，实现 MatchableHandlerMapping 接口，继承 AbstractHandlerMapping 抽象类，以 URL
 * 作为 Handler 的 HandlerMapping 抽象类，提供 Handler 的获取、注册等等通用的骨架方法。
 * <h3>2.2 registerHandler</h3>
 * {@link AbstractUrlHandlerMapping#registerHandler(String[], String)} 方法，注册多个 URL 的处理器。
 * <p>
 * {@link AbstractUrlHandlerMapping#registerHandler(String, Object)}  方法，注册单个 URL 的处理器。
 * <h3>2.3 getHandlerInternal</h3>
 * {@link AbstractUrlHandlerMapping#getHandlerInternal}方法，获得处理器。
 * <h3>2.4 lookupHandler</h3>
 * {@link AbstractUrlHandlerMapping#lookupHandler}方法，获得处理器。
 * <h3>2.5 buildPathExposingHandler</h3>
 * {@link AbstractUrlHandlerMapping#buildPathExposingHandler}方法，构建暴露路径的 Handler 。
 * <h3>2.6 match</h3>
 * {@link AbstractUrlHandlerMapping#match}方法，执行匹配
 * <h2>3. SimpleUrlHandlerMapping</h2>
 * {@link SimpleUrlHandlerMapping#registerHandlers}
 * <h2>4. AbstractDetectingUrlHandlerMapping</h2>
 * {@link AbstractDetectingUrlHandlerMapping}，继承 AbstractUrlHandlerMapping 抽象类，自动探测的 UrlHandlerMapping 抽象实现类。
 * <p>
 * {@link AbstractDetectingUrlHandlerMapping#detectHandlers}
 * <h2>5. BeanNameUrlHandlerMapping</h2>
 * 不讲解
 * <p>
 * 处理器适配器把处理 URL 逻辑的Handler 和 Interceptors 分发给 HandlerAdapter 组件
 * <p>
 * {@link HandlerAdapterComponent} 组件
 *
 * @author xuweizhi
 * @date 2019/04/01 13:04
 */
public class AbstractUrlHandlerMappingComponent {
}
