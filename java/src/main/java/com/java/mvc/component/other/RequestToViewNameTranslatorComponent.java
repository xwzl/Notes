package com.java.mvc.component.other;

import com.java.mvc.resource.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.RequestToViewNameTranslator;

/**
 * {@link ExceptionResolver}
 * <h2>1. 概述</h2>
 * {@link RequestToViewNameTranslator},请求到视图名的转换器接口。
 * <p>
 * 在 DispatcherServlet 中，我们已经看到，在 ModelAndView 不存在对应的视图时，会通过 RequestToViewNameTranslator 来获取默认的视图名，作为其视图。
 *
 * <h2>2. DefaultRequestToViewNameTranslator</h2>
 * {@link DefaultRequestToViewNameTranslator}，实现 RequestToViewNameTranslator 接口，默认且是唯一的 RequestToViewNameTranslator 实现类。
 *
 * 主要包含{@link DefaultRequestToViewNameTranslator#getViewName}
 *
 * 好咧，这就是这个类的核心。开心么？？？哈哈哈哈？？？？
 *
 * {@link LocaleResolverComponent}
 * @author xuweizhi
 * @date 2019/03/31 19:20
 */
public class RequestToViewNameTranslatorComponent {
}
