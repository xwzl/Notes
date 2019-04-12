package com.java.mvc.component.other;

import com.java.mvc.resource.*;
import org.springframework.web.servlet.ViewResolver;

/**
 * {@link ThemeResolverComponent}
 * <h2>1. 概述</h2>
 * {@link ViewResolver}，实体解析器接口，根据视图名和国际化，获得最终的视图 View 对象。
 * <h2>2. 类图</h2>
 * 虽然实现类比较多，ViewResolver 分成五类实现类，就是 ViewResolver 的五个直接实现类。
 * {@link #image()}
 * <h3>3. 初始化</h3>
 * 我们以默认配置的 Spring Boot 场景下为例，来一起看看 {@link DispatcherServlet#initViewResolvers}方法，初始化 viewResolvers 变量。
 * <p>
 * 一共有三种情况，初始化 viewResolvers 属性。
 * 默认情况下，detectAllViewResolvers 为 true ，所以走情况一的逻辑，自动扫描 ViewResolver 类型的 Bean 们。在默认配置的 Spring Boot
 * 场景下，viewResolvers 的结果是：
 * <ul>
 * <li>ContentNegotiatingViewResolver</li>
 * <li>BeanNameViewResolver</li>
 * <li>ThymeleafViewResolver</li>
 * <li>ViewResolverComposite</li>
 * <li>InternalResourceViewResolver</li>
 * </ul>
 * 从实现上来说，ContentNegotiatingViewResolver 是最最最重要的 ViewResolver 实现类，所以我们先开始瞅瞅它。
 * <h2>4. ContentNegotiatingViewResolver</h2>
 * {@link ContentViewResolver}
 * <h2>5. BeanNameViewResolver</h2>
 * {@link BeanNameViewResolver} ，实现 ViewResolver、Ordered 接口，继承 WebApplicationObjectSupport 抽象类，基于 Bean 的名字获得 View
 * 对象的 ViewResolver 实现类。
 * <p>
 * 实现 {@link BeanNameViewResolver#resolveViewName} 方法，获得 Bean 的名字获得 View 对象。
 * <h2>6. ViewResolverComposite</h2>
 * <p>
 * {@link ViewResolverComposite} ，实现 ViewResolver、Ordered、InitializingBean、ApplicationContextAware、ServletContextAware 接口，
 * 复合的 ViewResolver 实现类。
 *
 * <h3>6.2 afterPropertiesSet</h3>
 * 实现 #afterPropertiesSet() 方法，进一步初始化。
 * <h3>6.3 resolveViewName</h3>
 * {@link ViewResolverComposite#resolveViewName}
 * <h2>7. AbstractCachingViewResolver</h2>
 * {@link AbstractCachingViewResolver} ，实现 ViewResolver 接口，继承 WebApplicationObjectSupport 抽象类，提供通用的缓存的 ViewResolver
 * 抽象类。对于相同的视图名，返回的是相同的 View 对象，所以通过缓存，可以进一步提供性能。
 * <h3>7.1 构造方法</h3>
 * {@link AbstractCachingViewResolver}
 * 大多数变量比较易懂。比较有趣的是 viewAccessCache 和 viewCreationCache 属性的存在。
 * <ul>
 * <li>通过 viewAccessCache 属性，提供更快的访问 View 缓存。</li>
 * <li>通过 viewCreationCache 属性，提供缓存的上限的功能。可能有胖友不太了解为什么 LinkedHashMap 能实现 LRU 缓存过期的功能，
 * 可以看看 《LRU 缓存实现(Java)》 </li>
 * <li>KEY 是通过 #getCacheKey(String viewName, Locale locale) 方法，获得缓存 KEY 。代</li>
 * </ul>
 * <h3>7.2 loadView</h3>
 * {@link AbstractCachingViewResolver#loadView} 抽象方法，加载 viewName 对应的 View 对象。
 * <h3>7.3 createView</h3>
 * {@link AbstractCachingViewResolver#createView}方法，创建 viewName 对应的 View 对象。
 * 在方法内部，就会调用 「7.2 loadView」 方法。
 * <h3>7.4 resolveViewName</h3>
 * {@link AbstractCachingViewResolver#resolveViewName}
 * <h3>7.5 子类</h3>
 * 关于 AbstractCachingViewResolver 抽象类，有三个子类：
 * <ul>
 * <li>UrlBasedViewResolver</li>
 * <li>XmlViewResolver</li>
 * <li>ResourceBundleViewResolver</li>
 * </ul>
 * 其中，UrlBasedViewResolver 是相比更关键的子类，所以在 「8. UrlBasedViewResolver」 中，我们一起来瞅瞅。
 * <h2>8. UrlBasedViewResolver</h2>
 * {@link UrlBasedViewResolver} ，实现 Ordered 接口，继承 AbstractCachingViewResolver 抽象类，基于 Url 的 ViewResolver 实现类。
 * <h3>8.1 构造方法</h3>
 * {@link UrlBasedViewResolver}
 * <h3>8.2 initApplicationContext</h3>
 * {@link UrlBasedViewResolver#initApplicationContext()},子类中，我们会看到，viewClass 属性一般会在构造中法中设置。
 * <h3>8.3 getCacheKey</h3>
 * 重写{@link UrlBasedViewResolver#getCacheKey}方法，忽略 locale 参数，仅仅使用 viewName 作为缓存 KEY 。也就是说，不支持 Locale 特性。
 * <h3>8.4 canHandle</h3>
 * {@link UrlBasedViewResolver#canHandle}方法，判断传入的视图名是否可以被处理。
 * <p>
 * 一般情况下，viewNames 为空，所以会满足 viewNames == null 代码块。也就说，所有视图名都可以被处理。
 * <h3>8.5 applyLifecycleMethods</h3>
 * {@link UrlBasedViewResolver#applyLifecycleMethods}
 * <h3>8.6 createView</h3>
 * 重写{@link UrlBasedViewResolver#createView}方法，增加了对 REDIRECT、FORWARD 的情况的处理。
 * <h3>8.7 loadView</h3>
 * {@link UrlBasedViewResolver#loadView}加载 viewName 对应的 View 对象。
 * <h3>8.8 requiredViewClass</h3>
 * {@link UrlBasedViewResolver#requiredViewClass}方法，定义了产生的视图.
 * <h3>8.9 子类</h3>
 * 关于 UrlBasedViewResolver 抽象类，有三个子类：
 * <ul>
 * <li>AbstractTemplateViewResolver</li>
 * <li>InternalResourceViewResolver</li>
 * <li>TilesViewResolver</li>
 * <li>ScriptTemplateViewResolver</li>
 * <li>XsltViewResolver</li>
 * </ul>
 * 其中，InternalResourceViewResolver 和 AbstractTemplateViewResolver 是相比更关键的子类，所以在 「9. InternalResourceViewResolver」
 * 和 「10. AbstractTemplateViewResolver」 中，我们一起来瞅瞅。
 * <h2>9. InternalResourceViewResolver</h2>
 * {@link InternalResourceViewResolver} ，继承 UrlBasedViewResolver 类，解析出 JSP 的 ViewResolver 实现类。
 * <h3>9.1 构造方法</h3>
 * 从构造方法中，可以看出，视图名会是 InternalResourceView 或 JstlView 类。😈 实际上，JstlView 是 InternalResourceView 的子类。
 * <h3>9.2 buildView</h3>
 * 重写{@link InternalResourceViewResolver#buildView}
 * <h2>10. AbstractTemplateViewResolver</h2>
 * {@link AbstractTemplateViewResolver} ，继承 UrlBasedViewResolver 类，解析出 AbstractTemplateView 的 ViewResolver 抽象类。
 * <h3>10.1 构造方法</h3>
 * {@link AbstractTemplateViewResolver}
 * <h3>10.2 requiredViewClass</h3>
 * 重写{@link AbstractTemplateViewResolver#requiredViewClass}方法，返回 AbstractTemplateView 类。
 * <h3>10.3 buildView</h3>
 * 重写{@link AbstractTemplateViewResolver#buildView}方法。
 * <h3>10.4 子类</h3>
 * 关于 AbstractTemplateViewResolver 抽象类，有二个子类：
 * <ul>
 * <li>GroovyMarkupViewResolver</li>
 * <li>FreeMarkerViewResolver</li>
 * </ul>
 * 完结篇{@link MultipartComponent}
 *
 * @author xuweizhi
 * @date 2019/03/31 19:35
 */
public class ViewResolverComponent {

    /**
     * <image src="../../image/23.png"></image>
     */
    public void image() {

    }
}

/**
 * <h2>1. 概述</h2>
 * {@link ContentNegotiatingViewResolver}，实现 ViewResolver、Ordered、InitializingBean 接口，继承
 * WebApplicationObjectSupport 抽象类，基于内容类型来获取对应 View 的 ViewResolver 实现类。
 * <p>
 * 其中，内容类型指的是 "Content-Type" 和拓展后缀。
 *
 * <h2>4.1 构造方法</h2>
 *
 * <ul>
 * <li>{@link ContentNegotiatingViewResolver#cnmFactoryBean }</li>
 * <li>{@link ContentNegotiatingViewResolver#useNotAcceptableStatusCode }</li>
 * <li>{@link ContentNegotiatingViewResolver#defaultViews}</li>
 * <li>{@link ContentNegotiatingViewResolver#viewResolvers}</li>
 * <li>{@link ContentNegotiatingViewResolver#order }</li>
 * </ul>
 * <p>
 * viewResolvers 属性，ViewResolver 数组。对于来说，ContentNegotiatingViewResolver 会使用这些 viewResolvers 们，
 * 解析出所有的 View 们，然后基于内容类型来获取对应的 View 们。此时的 View 结果，可能是一个，可能是多个，所以需要比较获
 * 取到最优的 View 对象。
 * <p>
 * defaultViews 属性，默认 View 数组。那么此处的默认是什么意思呢？在 viewResolvers 们解析出所有的 View 们的基础上，也
 * 会添加 defaultViews 到 View 结果中。如果听起来有点绕，下面看具体的代码，会更加易懂。
 * <p>
 * order 属性，顺序，优先级最高。所以，这也是为什么在 「3. 初始化」 中排行第一。
 *
 * <h2>4.2 initServletContext</h2>
 * {@link ContentNegotiatingViewResolver#initServletContext }方法，初始化 viewResolvers 属性。
 * <h2>4.3 afterPropertiesSet</h2>
 * {@link ContentNegotiatingViewResolver#afterPropertiesSet }
 * <h2>4.4 resolveViewName</h2>
 * {@link ContentNegotiatingViewResolver#resolveViewName }
 * <h3>4.4.1 getMediaTypes</h3>
 * {@link ContentNegotiatingViewResolver#getMediaTypes }
 * <h3>4.4.2 getCandidateViews</h3>
 * {@link ContentNegotiatingViewResolver#getCandidateViews }
 * <h3>4.4.3 getBestView</h3>
 * {@link ContentNegotiatingViewResolver#getBestView }
 */
class ContentViewResolver {

}
