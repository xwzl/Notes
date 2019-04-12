package com.java.mvc.component.other;

import com.java.mvc.component.MvcComponent;
import com.java.mvc.component.adapter.MessageConverter;
import com.java.mvc.handler.AbstractHandlerExceptionResolver;
import com.java.mvc.handler.AbstractHandlerMethodExceptionResolver;
import com.java.mvc.handler.HandlerExceptionResolverComposite;
import com.java.mvc.handler.SimpleMappingExceptionResolver;
import com.java.mvc.resource.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * {@link MessageConverter }
 * <h2>1. 概述</h2>
 * 本文，我们来分享 HandlerExceptionResolver 组件。在{@link MvcComponent}中，我们对它已经做了介绍：{@link HandlerExceptionResolver}，
 * 处理器异常解析器接口，将处理器( handler )执行时发生的异常，解析( 转换 )成对应的 ModelAndView 结果。
 * <p>
 * {@link HandlerExceptionResolver#resolveException}, 解析异常，转换成对应的 ModelAndView 结果.
 * <p>
 * 也就是说，如果异常被解析成功，则会返回 ModelAndView 对象。
 * <h2>2. 类图</h2>
 * HandlerExceptionResolver 的类图如下：{@link #image()}
 * <h2>3. 初始化</h2>
 * 仔细一瞅，类还是不少的哈。我们以默认配置的 Spring Boot 场景下为例，来一起看看 {@link DispatcherServlet#initHandlerExceptionResolvers}
 * 方法，初始化 handlerExceptionResolvers 变量。
 * <h2>4. HandlerExceptionResolverComposite</h2>
 * {@link HandlerExceptionResolverComposite}，实现 HandlerExceptionResolver、Ordered 接口，复合的 HandlerExceptionResolver 实现类。
 * <h3>4.1 构造方法</h3>
 * {@link HandlerExceptionResolverComposite#resolvers} resolvers 数组;
 * <p>
 * {@link HandlerExceptionResolverComposite#order } 优先级，最低;
 * <p>
 * 那么，还是让我们来看看，在默认配置的 Spring Boot 场景下，是通过 {@link WebMvcConfigurationSupport#handlerExceptionResolver} 方法，
 * 进行初始化。
 * <h3>4.2 resolveException</h3>
 * // 实现{@link WebMvcConfigurationSupport#handlerExceptionResolver()}方法，遍历 HandlerExceptionResolver 数组，逐个处理异常 ex ，如果成功，
 * // 则返回 ModelAndView 对象。
 * <h3>5. AbstractHandlerExceptionResolver</h3>
 * {@link AbstractHandlerExceptionResolver}，实现 HandlerExceptionResolver、Ordered 接口，HandlerExceptionResolver 抽象类，作为所有
 * HandlerExceptionResolver 实现类的基类。
 * <h3>5.1 构造方法</h3>
 * {@link AbstractHandlerExceptionResolver}
 * <h3>5.2 shouldApplyTo</h3>
 * {@link AbstractHandlerExceptionResolver#shouldApplyTo}方法，判断当前 HandlerExceptionResolver 是否能应用到传入的 handler 处理器。
 * <h3>5.3 prepareResponse</h3>
 * {@link AbstractHandlerExceptionResolver#prepareResponse}方法，阻止响应缓存。
 * <h3>5.4 resolveException</h3>
 * {@link AbstractHandlerExceptionResolver#resolveException}
 * <h2>6. AbstractHandlerMethodExceptionResolver</h2>
 * {@link AbstractHandlerMethodExceptionResolver}，继承 AbstractHandlerExceptionResolver 抽象类，基于 handler 类型为 HandlerMethod 的
 * HandlerExceptionResolver 抽象类。
 * <p>
 * 可能胖友会有疑惑，为什么 AbstractHandlerMethodExceptionResolver 只有一个 ExceptionHandlerExceptionResolver 子类，为什么还要做抽象呢？因为
 * ExceptionHandlerExceptionResolver 是基于 @ExceptionHandler 注解来配置对应的异常处理器，而<b>如果未来我们想自定义其它的方式来配置对应的异常处
 * 理器，就可以来继承 AbstractHandlerMethodExceptionResolver 这个抽象类。</b>
 * <p>
 * todo 有没发现 Spring MVC 中，存在大量的逻辑与配置分离的分层实现？
 * <h3>6.1 shouldApplyTo</h3>
 * 重写{@link AbstractHandlerMethodExceptionResolver#shouldApplyTo}
 * <h3>6.2 doResolveException</h3>
 * 重写 {@link AbstractHandlerMethodExceptionResolver#doResolveException}
 * <h2>7. ExceptionHandlerExceptionResolver</h2>
 * {@link ExceptionHandlerResolver}
 * <h2>8. ResponseStatusExceptionResolver</h2>
 * {@link ResponseStatusExceptionResolver}，实现 MessageSourceAware 接口，<b>继承AbstractHandlerExceptionResolver抽象类，基于@ResponseStatus
 * 提供错误响应的 HandlerExceptionResolver 实现类。</b>
 * <h3>8.1 applyStatusAndReason</h3>
 * {@link ResponseStatusExceptionResolver#applyStatusAndReason}方法，设置错误响应。
 * <h3>8.2 doResolveException</h3>
 * {@link ResponseStatusExceptionResolver#doResolveException}方法
 * <h2>9. DefaultHandlerExceptionResolver</h2>
 * {@link DefaultHandlerExceptionResolver}，继承 AbstractHandlerExceptionResolver 抽象类，默认 HandlerExceptionResolver 实现类，
 * 针对各种异常，设置错误响应。
 * <p>
 * 主要实现了{@link DefaultHandlerExceptionResolver#doResolveException}方法
 * <h2>10. SimpleMappingExceptionResolver</h2>
 * {@link SimpleMappingExceptionResolver#doResolveException}
 *
 * 虽然很长，但是实际上，灰常简单。{@link RequestToViewNameTranslatorComponent}
 * @author xuweizhi
 * @date 2019/04/02 10:11
 */
public class ExceptionResolver {

    /**
     * <image src="../../image/21.png"></image>
     */
    public void image() {

    }

}

/**
 * <h2>7. 概述</h2>
 * {@link ExceptionHandlerExceptionResolver}，实现 ApplicationContextAware、InitializingBean 接口，继承 AbstractHandlerMethodExceptionResolver
 * 抽象类，todo <b>基于 @ExceptionHandler 配置 HandlerMethod 的 HandlerExceptionResolver 实现类。</b>
 * <p>
 * 可能有的胖友并没有使用 @ExceptionHandler 注解来实现过异常的处理，可以先看看{@link HelloAdviceController} 或者 https://www.jianshu.com/p/12e1a752974d
 *
 * <h2>7.1 构造方法</h2>
 * {@link ExceptionHandlerExceptionResolver#ExceptionHandlerExceptionResolver}与{@link  HandlerAdapterComponent} 7. RequestMappingHandlerAdapter
 * 类似，有大量的相同变量，也是最终调用 ServletInvocableHandlerMethod 的方法。
 * <p>
 * <h2>7.2 afterPropertiesSet</h2>
 * {@link ExceptionHandlerExceptionResolver#afterPropertiesSet} 方法，进一步初始化 ExceptionHandlerExceptionResolver 。
 * <h2>7.3 ExceptionHandlerMethodResolver</h3>
 * 关于 ExceptionHandlerMethodResolver 类，因为只有 ExceptionHandlerExceptionResolver 类在用，所以放在此处。
 * <p>
 * {@link ExceptionHandlerMethodResolver}，<b>注解了 @ExceptionHandler 的方法的解析器。</b>
 * <p>
 * <h3>7.3.1 构造方法</h3>
 * 类属性：
 * <ul>
 * <li>{@link ExceptionHandlerMethodResolver#mappedMethods }</li>
 * <li>{@link ExceptionHandlerMethodResolver#exceptionLookupCache }</li>
 * </ul>
 * mappedMethods 和 exceptionLookupCache 差别在于，后者是经过查找，比较优先级后所产生的。
 * <p>
 * {@link ExceptionHandlerMethodResolver#ExceptionHandlerMethodResolver}
 * <h3>7.3.2 hasExceptionMappings</h3>
 * {@link ExceptionHandlerMethodResolver#hasExceptionMappings} 方法，判断 mappedMethods 非空。
 * <h3>7.3.3 resolveMethod</h3>
 * {@link ExceptionHandlerMethodResolver#resolveMethod}方法，解析异常对应的方法。
 * <h2>7.4 getExceptionHandlerMethod</h2>
 * {@link ExceptionHandlerExceptionResolver#getExceptionHandlerMethod} 方法，获得异常对应的 ServletInvocableHandlerMethod 对象。
 * <h2>7.5 doResolveHandlerMethodException</h2>
 * 实现 {@link ExceptionHandlerExceptionResolver#doResolveHandlerMethodException} 方法.
 */
class ExceptionHandlerResolver {

}
