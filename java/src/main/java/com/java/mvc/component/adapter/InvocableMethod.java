package com.java.mvc.component.adapter;

import com.java.mvc.handler.AbstractHandlerMapping;
import com.java.mvc.handler.HandlerMethod;
import com.java.mvc.resource.InvocableHandlerMethod;
import com.java.mvc.resource.ServletInvocableHandlerMethod;

/**
 * HandlerAdapter 组件 {@link HandlerAdapterComponent}
 * <h2>2. 类图</h2>
 * ServletInvocableHandlerMethod 的整体类图如下：{@link #image()}
 * <h2>3. HandlerMethod</h2>
 * {@link HandlerMethod} ，处理器的方法的封装对象。
 * 在{@link AbstractHandlerMapping}「3.3.1 HandlerMethod」 中，我们已经做过详细解析。
 * <h2>4. InvocableHandlerMethod</h2>
 * {@link InvocableHandlerMethod}，继承 HandlerMethod 类，可 invoke 调用的 HandlerMethod 实现类。
 * <p>
 * 也就是说，HandlerMethod 只提供了处理器的方法的基本信息，不提供调用逻辑。
 * <h3>4.1 构造方法</h3>
 * dataBinderFactory、argumentResolvers、parameterNameDiscoverer 参数，是通过 setting 方法，进行设置。
 * <h3>4.2 invokeForRequest</h3>
 * {@link InvocableHandlerMethod#invokeForRequest}方法，执行请求。
 * <h2>5. ServletInvocableHandlerMethod</h2>
 * {@link ServletInvocableHandlerMethod} ，继承 InvocableHandlerMethod 类，考虑 Servlet 的 InvocableHandlerMethod 实现类。
 * <h3>5.1 构造方法</h3>
 * <h3>5.2 invokeAndHandle</h3>
 * {@link ServletInvocableHandlerMethod#invokeAndHandle} 方法，请求调用，并处理返回结果。
 * <h3>5.2 invokeAndHandle</h3>
 * {@link ServletInvocableHandlerMethod}
 *
 * 下一回详解{@link ArgumentResolver}
 * @author xuweizhi
 * @date 2019/04/01 15:53
 */
public class InvocableMethod {
    /**
     * <image src="../../image/14.png"></image>
     */
    public void image() {

    }
}
