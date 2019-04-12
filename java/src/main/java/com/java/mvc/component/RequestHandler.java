package com.java.mvc.component;

import com.java.mvc.component.mapping.AbstractHandlerMappingComponent;
import com.java.mvc.resource.DispatcherServlet;
import com.java.mvc.resource.FrameworkServlet;

/**
 * {@link MvcComponent}
 * <h1>1. 概述</h1>
 * 本文，我们来一览一个用户的请求，是如何被 DispatcherServlet 处理的。
 * <p>
 * 整体流程实际不复杂，但是涉及的全部代码会非常多，所以本文重点在于解析整体的流程。特别具体和细节的代码实现，我们会放到
 * 后续的文章，一篇一篇细细咀嚼。
 * <h2>2. FrameworkServlet</h2>
 * 虽然在 「1. 概述」 的整体流程图，我们看到请求首先是被 DispatcherServlet 所处理，但是实际上，FrameworkServlet
 * 才是真正的入门。FrameworkServlet 会实现:
 * <ul>
 * <li>doGet</li>
 * <li>doPost</li>
 * <li>doPut</li>
 * <li>doDelete</li>
 * <li>doOptions</li>
 * <li>doTrace</li>
 * <li>service</li>
 * </ul>
 * 等方法。而这些实现，最终会调用 #processRequest(HttpServletRequest request, HttpServletResponse response)
 * 方法，处理请求。
 * <h3>2.1 不同 HttpMethod 的请求处理</h3>
 * <h3>2.1.1 service</h3>
 * {@link FrameworkServlet#service}，调试进{@link org.springframework.web.servlet.FrameworkServlet#service}
 * 打断点
 * <h3>2.1.2 doGet & doPost & doPut & doDelete</h3>
 * FrameworkServlet中，这四个方法，都是直接调用 #processRequest方法，处理请求。
 * <h3>2.1.3 doOptions</h3>
 * {@link FrameworkServlet#doOptions}
 * <h3>2.1.4 doTrace</h3>
 * {@link FrameworkServlet#doTrace}
 * <h2>2.2 processRequest</h2>
 * {@link FrameworkServlet#processRequest}方法，处理请求。其中执行逻辑的方法doService方法在
 * {@link DispatcherServlet#doService}实现
 * <h2>3. DispatcherServlet</h2>
 * <h3>3.1 doService</h3>
 * {@link DispatcherServlet#doService}方法，DispatcherServlet 的处理请求的入口方法。
 * <h3>3.2 doDispatch</h3>
 * {@link DispatcherServlet#doService}方法中{@link DispatcherServlet#doDispatch}用于请求分发，
 * 是下面这张图是讲的这个方法的核心流程。{@link #image()}
 * <p>
 * 调试请进方法{@link org.springframework.web.servlet.DispatcherServlet#doService}
 * <p>
 * 处理器匹配接口，根据请求( handler )获得其的处理器( handler )和拦截器们( HandlerInterceptor 数组 )。
 * {@link AbstractHandlerMappingComponent}
 *
 * @author xuweizhi
 * @date 2019/03/31 17:46
 */
public class RequestHandler {

    /**
     * <image src="../image/1.jpg"></image>
     */
    public void image() {

    }

}
