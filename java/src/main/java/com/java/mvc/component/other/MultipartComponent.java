package com.java.mvc.component.other;

import com.java.mvc.resource.*;

/**
 * {@link ViewResolverComponent}
 * MultipartResolver文件解析对象
 * <h2>1. 概述</h2>
 * {@link MultipartResolver} ，内容类型( Content-Type )为 multipart/* 的请求的解析器接口。
 * <p>
 * 例如，文件上传请求，MultipartResolver 会将 HttpServletRequest 封装成 MultipartHttpServletRequest ，这样从
 * MultipartHttpServletRequest 中获得上传的文件。具体的使用示例，参见 《spring-boot 上传文件 MultiPartFile 获取
 * 不到文件问题解决》
 * https://blog.csdn.net/happy_cheng/article/details/54178392
 * <p>
 * 关于内容类型( Content-Type )为 multipart/* ，胖友可以看看 《HTTP 协议之 multipart/form-data 请求分析》 文章。
 * https://blog.csdn.net/five3/article/details/7181521
 * <p>
 * {@link MultipartResolver}  接口，
 * <h2>2. 类图</h2>
 * MultipartResolver 的体系结构如下：{@link #image()}
 * 一共有两块：
 * <ul>
 * <li>上半部分，MultipartRequest 接口及其实现类</li>
 * <li>下半部分，MultipartResolver 接口以及其实现类</li>
 * </ul>
 * <h2>3. 初始化</h2>
 * 我们以默认配置的 Spring Boot 场景下为例，来一起看看{@link DispatcherServlet#initMultipartResolver}方法，初始化
 * multipartResolver 变量。
 * <h2>4. StandardServletMultipartResolver</h2>
 * {@link StandardServletMultipartResolver}，实现 MultipartResolver 接口，使用 Servlet 3.0 标准的上传 API 的
 * ultipartResolver 实现类。
 * <h3>4.1 构造方法</h3>
 * resolveLazily 属性，是否延迟解析。什么意思呢？就是是否在需要获得对应文件时，在进行文件的解析。
 * <h3>4.2 isMultipart</h3>
 * 实现 {@link StandardServletMultipartResolver#isMultipart}方法，
 * <h3>4.3 resolveMultipart</h3>
 * 实现 {@link StandardServletMultipartResolver#resolveMultipart}方法，创建 StandardMultipartHttpServletRequest 对象。
 * <h3>4.4 cleanupMultipart </h3>
 * 实现 {@link StandardServletMultipartResolver#cleanupMultipart} 方法，删除临时的 javax.servlet.http.Part 们.
 * <h3>4.5 StandardMultipartHttpServletRequest</h3>
 * {@link  StandardMultipartHttpServletRequest} ，继承 AbstractMultipartHttpServletRequest 抽象类，基于 Servlet 3.0
 * 的 Multipart HttpServletRequest 实现类。
 * <h3>4.5.1 构造方法</h3>
 * {@link StandardMultipartHttpServletRequest#multipartParameterNames} 属性，普通参数名的集合。普通参数名，指的是非上传文件的参数名。
 *
 * <1> 处，如果不延迟加载，调用 #parseRequest(HttpServletRequest request) 方法，则解析请求。详细解析，见 「4.5.2 parseRequest」 。
 * <h3>4.5.2 parseRequest</h3>
 * {@link StandardMultipartHttpServletRequest#parseRequest}方法，解析请求。
 * <h3>4.5.3 initializeMultipart</h3>
 * 如果我们开启 lazyParsing 延迟解析的功能，则可通过 multipartParameterNames 属性是否为 null 来判断，是否已经初始化。
 * <p>
 * 其中，实现 #initializeMultipart() 方法，内部调用 #parseRequest(HttpServletRequest request) 方法，解析请求。
 * <h3>4.5.4 getFile</h3>
 * {@link StandardMultipartHttpServletRequest#getFiles(String)}
 * <p>
 * 完结，真TM类
 * @author xuweizhi
 * @date 2019/03/31 18:51
 */
public class MultipartComponent {

    /**
     * <image src="../../image/24.png"></image>
     */
    public void image() {

    }

}
