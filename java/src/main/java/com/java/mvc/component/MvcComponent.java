package com.java.mvc.component;

import com.java.mvc.resource.DispatcherServlet;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.*;

/**
 * {@link SpringBootServletInit}
 * 组件一览
 * <h1>1. 概述</h1>
 * 在{@link WebApplicationContext}中会调用 {@link DispatcherServlet#initStrategies}方法，
 * 初始化 Spring MVC 的各种组件。
 * <p>
 * 掐指一算，一共有 9 个组件。本文，我们对这 9 个组件，做一个简单的介绍。
 * <p>
 * 当然，在具体介绍组件之前，我们还是通过一张图，来看看这些组件，在一次用户的请求中，扮演了什么样的角色。
 * <p>
 * {@link #image()}
 * <p>
 * 当然，这个图并没有包括所有的组件，主要涉及最核心的组件
 * <h2>2. MultipartResolver</h2>
 * {@link #multipartResolver()}
 * <h2>3. LocaleResolver</h2>
 * {@link LocaleResolver} ，本地化( 国际化 )解析器接口，详情见 https://www.cnblogs.com/liukemng/p/3750117.html
 * <h2>4. ThemeResolver</h2>
 * {@link ThemeResolver}，主题解析器接口，详情见 https://blog.csdn.net/neweastsun/article/details/79213867
 * <p>
 * 当然，因为现在的前端，基本和后端做了分离，所以这个功能已经越来越少用了。
 * <h2>5. HandlerMapping</h2>
 * {@link  HandlerMapping}，处理器匹配接口，根据请求( handler )获得其的处理器( handler )和拦截器们( HandlerInterceptor 数组 )。
 * <h2>6. HandlerAdapter</h2>
 * {@link  HandlerAdapter} ，处理器适配器接口。
 * <p>
 * 因为，处理器 handler 的类型是 Object 类型，需要有一个调用者来实现 handler 是怎么被使用，怎么被执行。而 HandlerAdapter
 * 的用途就在于此。可能如果接口名改成 HandlerInvoker ，笔者觉得会更好理解。
 * <h2>7. HandlerExceptionResolver</h2>
 * {@link  HandlerExceptionResolver} ，处理器异常解析器接口，将处理器( handler )执行时发生的异常，
 * 解析( 转换 )成对应的 ModelAndView 结果。
 * <h2>8. RequestToViewNameTranslator</h2>
 * {@link RequestToViewNameTranslator}，请求到视图名的转换器接口。
 * <h2>9. ViewResolver</h2>
 * {@link ViewResolver}，实体解析器接口，根据视图名和国际化，获得最终的视图 View 对象。
 * <p>
 * ViewResolver 的实现类比较多，例如说，InternalResourceViewResolver 负责解析 JSP 视图，FreeMarkerViewResolver
 * 负责解析 Freemarker 视图。当然，详细的，我们后续文章解析。
 * <h2>10. FlashMapManager</h2>
 * {@link FlashMapManager}，FlashMap 管理器接口，负责重定向时，保存参数到临时存储中。
 * <p>
 * 默认情况下，这个临时存储会是 Session 。也就是说：
 * <p>
 * <ul>
 * <li>重定向前，保存参数到 Seesion 中。</li>
 * <li>重定向后，从 Session 中获得参数，并移除。</li>
 * </ul>
 * 酱紫，我们下一篇好整体的了解一个用户的请求，DispatcherServlet 是如何使用上述的组件，对其进行处理的。
 * {@link RequestHandler}
 * @author xuweizhi
 * @date 2019/03/31 17:24
 */
public class MvcComponent {

    /**
     * <image src="../image/1.jpg"></image>
     */
    public void image() {

    }

    /**
     * {@link MultipartResolver} ，内容类型( Content-Type )为 multipart/* 的请求的解析器接口。
     * <p>
     * 例如，文件上传请求，MultipartResolver 会将 HttpServletRequest 封装成 MultipartHttpServletRequest ，这样从
     * MultipartHttpServletRequest 中获得上传的文件。具体的使用示例，参见 https://blog.csdn.net/happy_cheng/article/details/54178392
     * <p>
     * 关于内容类型( Content-Type )为 multipart/* ，胖友可以看看 https://blog.csdn.net/five3/article/details/7181521;
     */
    public void multipartResolver() {
        //public interface MultipartResolver {
        //
        //    /**
        //     * 是否为 multipart 请求
        //     */
        //    boolean isMultipart(HttpServletRequest request);
        //
        //    /**
        //     * 将 HttpServletRequest 请求封装成 MultipartHttpServletRequest 对象
        //     */
        //    MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException;
        //
        //    /**
        //     * 清理处理 multipart 产生的资源，例如临时文件
        //     *
        //     */
        //    void cleanupMultipart(MultipartHttpServletRequest request);
        //
        //}
    }

}
