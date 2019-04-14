package com.java.mvc.component.adapter;

import com.java.mvc.config.MyResponseBodyAdvice;
import com.java.mvc.controller.TemplateController;
import com.java.mvc.resource.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * {@link ArgumentResolver}
 * <h2>1. 概述</h2>
 * 本文接{@link InvocableMethod}我们来分享 HandlerMethodReturnValueHandler，
 * HandlerMethod 的返回值的处理器接口。
 * <p>
 * 两个方法，分别是是否支持解析该类型、以及处理返回值。
 * <h2>2. 类图</h2>
 * HandlerMethodReturnValueHandler 的实现类非常多，如下图所示：{@link #image()}
 * <h2>3. ModelAndViewContainer</h2>
 * {@link ModelAndViewContainer} ，主要是作为 Model 和 View 的容器，当然其中还有其它属性。
 * <h3>3.1 构造方法</h3>
 * 属性有点多，不要懵逼。我们带着一起在下面的几个方法来瞅瞅。
 * <h3>3.2 getModel</h3>
 * {@link ModelAndViewContainer#getModel()} 方法，获得 Model 对象。
 * <h3>3.3 View 相关的方法</h3>
 * 比较简单啊
 * <h3>3.4 requestHandled 标识</h3>
 * 关于 requestHandled 的修改地方，实际在 Spring MVC 地方蛮多处都可以进行修改。感兴趣的胖友，可
 * 以使用 IDEA 右键该属性，选择 “Find Usages” 进行查看。
 * <h2>4. HandlerMethodReturnValueHandlerComposite</h2>
 * {@link HandlerMethodReturnValueHandlerComposite}实现 HandlerMethodReturnValueHandler 接口，
 * 复合的 HandlerMethodReturnValueHandler 实现类。
 * <h3>4.2 getReturnValueHandler</h3>
 * {@link HandlerMethodReturnValueHandlerComposite#getReturnValueHandler}方法，获得方法
 * 返回值对应的 HandlerMethodReturnValueHandler 对象。
 * <h3>4.3 supportsParameter</h3>
 * {@link HandlerMethodReturnValueHandlerComposite#getReturnValueHandler}方法，如果能获
 * 得到对应的 HandlerMethodReturnValueHandler 处理器，则说明支持。
 * <h3>4.4 handleReturnValue</h3>
 * {@link HandlerMethodReturnValueHandlerComposite#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)}
 * <h2>5. RequestResponseBodyMethodProcessor</h2>
 * {@link ResponseBodyAnnotation}
 * <h2>6. ViewNameMethodReturnValueHandler</h2>
 * {@link ViewNameMethodReturnValueHandler} ，实现 HandlerMethodReturnValueHandler 接口，
 * 处理返回结果是视图名的 ReturnValueHandler 实现类。
 * <p>
 * ViewNameMethodReturnValueHandler 适用于前后端未分离，MyController 返回视图名的场景，例如 JSP、Freemarker 等等。
 * <h3>6.1 构造方法</h3>
 * {@link ViewNameMethodReturnValueHandler#redirectPatterns}属性，一般情况下，不进行设置。至于用途，我们来看看
 * {@link ViewNameMethodReturnValueHandler#isRedirectViewName(String viewName)} 方法，判断是否为重定向的视图名。
 * <p>
 * toso 是不是明白落？是不是也弄清楚，为什么 "redirect:" 开头，就是重定向的视图。
 * <h3>6.2 supportsReturnType</h3>
 * {@link ViewNameMethodReturnValueHandler#supportsReturnType}方法，判断返回值类型是否为 void 或者字符串。
 * <h3>6.3 handleReturnValue</h3>
 * {@link ViewNameMethodReturnValueHandler#handleReturnValue}方法，
 *
 * {@link MessageConverter}
 * @author xuweizhi
 * @date 2019/04/01 16:22
 */
public class ReturnValue {

    /**
     * <image src="../../image/17.png"></image>
     */
    public void image() {

    }

    /**
     * <image src="../../image/18.png"></image>
     */
    public void image1() {

    }
}

/**
 * {@link RequestResponseBodyMethodProcessor}，继承 AbstractMessageConverterMethodProcessor
 * 抽象类，todo 处理请求参数添加了 @RequestBody 注解，或者返回值添加了 @ResponseBody 注解的处理。
 * <p>
 * <b>因为前后端分离之后，后端基本是提供 Restful API ，所以 RequestResponseBodyMethodProcessor
 * 成为了目前最常用的 HandlerMethodReturnValueHandler 实现类。</b>
 * <p>
 * {@link ReturnValue#image1()}
 * <p>
 * 从图中，我们也会发现，RequestResponseBodyMethodProcessor 也是 HandlerMethodArgumentResolver 的实现类。
 * <p>
 * 虽然，{@link TemplateController#index1()}方法的返回值没添加 @ResponseBody 注解，但是 @RestController
 * 注解，默认有 @ResponseBody 注解 。
 * <h3>5.1 构造方法</h3>
 * 其构造方法中的converts参数，HttpMessageConverter 数组。关于 HttpMessageConverter ，可以说我们非常熟悉的朋友了。
 * 例如，我们想要将 POJO 对象，返回成 JSON 数据给前端，就会使用到 MappingJackson2HttpMessageConverter 类。
 * <p>
 * requestResponseBodyAdvice 参数，一般情况是 ResponseBodyAdvice 类型，可实现对返回结果的修改。
 * 详情见{@link MyResponseBodyAdvice}
 * <h3>5.2 supportsReturnType</h3>
 * {@link RequestResponseBodyMethodProcessor#supportsReturnType}方法，判断是否添加 todo @ResponseBody 注解。
 * <h3>5.3 handleReturnValue</h3>
 * 实现{@link RequestResponseBodyMethodProcessor#handleReturnValue} 方法。
 * <h3>5.4.1 writeWithMessageConverters</h3>
 * {@link AbstractMessageConverterMethodProcessor#writeWithMessageConverters(Object, MethodParameter, ServletServerHttpRequest, ServletServerHttpResponse)}
 * 方法，使用 HttpMessageConverter 对对象进行转换，并写入到响应。
 * <ul>
 * <li>1、获得响应使用的 MediaType 对象。</li>
 * <li>2、获得响应结果和 Media 对象获得对应的 HttpMessageConverter 对象。</li>
 * <li>3、使用 HttpMessageConverter 将响应结果进行转化，写入到响应中。</li>
 * </ul>
 * <p>
 * 至此，整个逻辑已经解析完成。在日常使用 Spring MVC 中，我们经常碰到的组件的调用，应该要有一个比较好的理解。如果没有，
 * 在多多调试。特别是：
 * <ul>
 * <li>1、HttpMessageConverter</li>
 * <li>2、RequestResponseBodyAdviceChain</li>
 * </ul>
 */
class ResponseBodyAnnotation {

}
