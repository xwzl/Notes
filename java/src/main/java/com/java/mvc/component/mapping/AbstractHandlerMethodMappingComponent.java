package com.java.mvc.component.mapping;

import com.java.mvc.handler.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * <h2>1. 概述</h2>
 * {@link HandlerInterceptorComponent}一共有三个子类，继承关系很简单，AbstractHandlerMethodMapping
 * <= RequestMappingInfoHandlerMapping <= RequestMappingHandlerMapping 。所以，我们在下面的每一小
 * 节，分享一个类。
 * <h2>2. 注解</h2>
 * Spring MVC 的请求匹配的注解，我们已经是熟悉的不能再熟悉，如下:
 * <ul>
 * <li>org.springframework.web.bind.annotation.@RequestMapping</li>
 * <li>org.springframework.web.bind.annotation.@GetMapping</li>
 * <li>org.springframework.web.bind.annotation.@PostMapping</li>
 * <li>org.springframework.web.bind.annotation.@PutMapping</li>
 * <li>org.springframework.web.bind.annotation.@DeleteMapping</li>
 * <li>org.springframework.web.bind.annotation.@PatchMapping</li>
 * </ul>
 * 具体每个注解有哪些属性，胖友点击链接瞅瞅即可。毕竟，太熟悉了。哈哈哈哈。
 * <h2>3. AbstractHandlerMethodMapping</h2>
 * {@link AbstractHandlerMethodMappingAnalyze}
 * <h2>4. RequestMappingInfoHandlerMapping</h2>
 * {@link RequestMappingInfoHandlerMappingAnalyze}
 * <h2>5. RequestMappingHandlerMapping</h2>
 * {@link RequestMappingHandlerMapping}，实现 MatchableHandlerMapping, EmbeddedValueResolverAware 接口，
 * 继承 RequestMappingInfoHandlerMapping 抽象类，基于@RequestMapping 注解来构建 RequestMappingInfo 对象。
 * <h3>5.1 构造方法</h3>
 * <h3>5.2 afterPropertiesSet</h3>
 * {@link RequestMappingHandlerMapping#afterPropertiesSet}
 * <h3>5.3 isHandler</h3>
 * 实现 #isHandler(Class<?> beanType) 方法，判断是否为处理器。
 * <h3>5.4 getMappingForMethod</h3>
 * {@link RequestMappingHandlerMapping#getMappingForMethod}方法，获得方法上的 RequestMappingInfo 对象，基于 @RequestMapping 构造
 * <h3>5.5 match</h3>
 * {@link RequestMappingHandlerMapping#match}
 *
 * 另外一个派系分析：{@link AbstractUrlHandlerMappingComponent}
 * @author xuweizhi
 * @date 2019/03/31 22:43
 */
public class AbstractHandlerMethodMappingComponent {

}

/**
 * <h2>1. 概述</h2>
 * {@link AbstractHandlerMethodMapping}实现 InitializingBean 接口，继承 AbstractHandlerMapping 抽
 * 象类，以 Method 作为 Handler 的 HandlerMapping 抽象类，提供 Mapping 的初始化、注册等通用的骨架方法.这
 * 就是我们常说的“模板方法模式” 。
 * <p>
 * 那么具体是什么呢？AbstractHandlerMethodMapping 定义为了 <T> 泛型，交给子类做决定。例如，子类 RequestMappingInfoHandlerMapping
 * 使用 RequestMappingInfo 类作为 <T> 泛型，也就是我们在 「2. 注解」 看到的 @RequestMapping 等注解。
 * <h2>3.1 构造方法</h2>
 * <ul>
 * <li>{@link AbstractHandlerMethodMapping#mappingRegistry }</li>
 * <li>{@link AbstractHandlerMethodMapping#namingStrategy}</li>
 * </ul>
 * T  泛型，就是我们上面提到的，Mapping 的类型。
 * <p>
 * <b>mappingRegistry 属性</b>，Mapping 注册表。详细解析，见 「3.2 MappingRegistry」 。
 * <p>
 * <b>namingStrategy 属性</b>，{@link HandlerMethodMappingNamingStrategy} 接口，名字好长，Handler 的
 * Method的 Mapping 的名字生成策略接口.可能不太好理解，获得 Mapping 的名字。这样，我们就可以根据 Mapping
 * 的名字，获得 Handler 。
 * <p>
 * {@link RequestMappingInfoHandlerMethodMappingNamingStrategy#getName}实现该接口:
 * <ul>
 * <li>情况一，如果 Mapping 已经配置名字，则直接返回。例如，@RequestMapping(name = "login", value = "user/login")
 * 注解的方法，它对应的 Mapping 的名字就是 "login" 。</li>
 * <li>情况二，如果 Mapping 未配置名字，则使用使用类名大写 + "#" + 方法名。例如，@RequestMapping(value = "user/login")
 * 注解的方法，假设它所在的类为 UserController ，对应的方法名为 login ，则它对应的 Mapping 的名字就是 USERCONTROLLER#login </li>
 * </ul>
 * <h2>3.2 MappingRegistry</h2>
 * MappingRegistry ，是 AbstractHandlerMethodMapping 的私有类，Mapping 注册表。
 * <h3>3.2.1 构造方法</h3>
 * <ul>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#registry }</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#mappingLookup  }</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#urlLookup  }</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#nameLookup  }</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#corsLookup  }</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistry#readWriteLock   }</li>
 * </ul>
 * <h3>3.2.2 register</h3>
 * {@link AbstractHandlerMethodMapping.MappingRegistry#register} 方法，注册.
 * <h3>3.2.3 unregister</h3>
 * {@link AbstractHandlerMethodMapping.MappingRegistry#unregister} 方法，取消注册.
 * <h2>3.3 createHandlerMethod</h2>
 * {@link AbstractHandlerMethodMapping#createHandlerMethod} 方法，创建 HandlerMethod 对象。
 * <h3>3.3.1 HandlerMethod</h3>
 * {@link HandlerMethod} ，处理器的方法的封装对象。详情见{@link HandlerMethodAnalyze}
 * <h3>3.4 MappingRegistration</h3>
 * MappingRegistration ，是 AbstractHandlerMethodMapping 的私有静态类，Mapping 注册登记。
 * 详情见{@link MappingRegistrationAnalyze}
 * <h3>3.5 #afterPropertiesSet() </h3>
 * 打起精神，虽然前面已经看了很多代码。此处，我们才真正进行初始化。
 * {@link AbstractHandlerMethodMapping#afterPropertiesSet()}方法，进行初始化
 * <h3>3.5.1 processCandidateBean</h3>
 * {@link AbstractHandlerMethodMapping#processCandidateBean}方法，判断 Bean 是否为处理器，如果是，则扫描处理器方法。
 * <h3>3.5.2 detectHandlerMethods</h3>
 * {@link AbstractHandlerMethodMapping#detectHandlerMethods}方法，初始化处理器的方法们。
 * <h3>3.6 getHandlerInternal</h3>
 * 这个方法，就是我们在 {@link AbstractHandlerMappingComponent} 中，看到 AbstractHandlerMapping
 * 给子类暴露的很核心的方法。
 * 实现 {@link AbstractHandlerMethodMapping#getHandlerInternal} 方法，获得请求对应的 HandlerMethod 对象
 * <h3>3.6.1 lookupHandlerMethod</h3>
 * {@link AbstractHandlerMethodMapping#lookupHandlerMethod} 方法，获得 HandlerMethod 对象。
 * <p>
 * ε=(´ο｀*)))唉，这一段终于看完了，想吐  {@link AbstractHandlerMethodMappingComponent}
 */
class AbstractHandlerMethodMappingAnalyze {

}

/**
 * <h2>属性如下：</h2>
 * <ul>
 * <li>{@link HandlerMethod#bean} </li>
 * <li>{@link HandlerMethod#beanFactory} </li>
 * <li>{@link HandlerMethod#beanType} </li>
 * <li>{@link HandlerMethod#method} </li>
 * <li>{@link HandlerMethod#bridgedMethod} </li>
 * <li>{@link HandlerMethod#parameters} </li>
 * <li>{@link HandlerMethod#responseStatus} </li>
 * <li>{@link HandlerMethod#responseStatusReason} </li>
 * <li>{@link HandlerMethod#resolvedFromHandlerMethod} </li>
 * <li>{@link HandlerMethod#interfaceParameterAnnotations} </li>
 * </ul>
 * <h2>HandlerMethod(String beanName, BeanFactory beanFactory, Method method) 构造方法</h2>
 * {@link HandlerMethod#HandlerMethod(String, BeanFactory, Method)} 对应方法的 <1> 的情况，
 *
 * <h2>HandlerMethod(Object bean, Method method) 构造方法</h2>
 * 对应 #createHandlerMethod(Object handler, Method method) 方法的 <2> 的情况，
 * {@link HandlerMethod#HandlerMethod(Object, Method)}
 * <p>
 * 返回{@link AbstractHandlerMethodMappingAnalyze}
 */
class HandlerMethodAnalyze {

}

/**
 * <h2>类属性</h2>
 * <ul>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistration#mapping}</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistration#handlerMethod}</li>
 * <li>{@link AbstractHandlerMethodMapping.MappingRegistration#mappingName}</li>
 * </ul>
 * {@link AbstractHandlerMethodMappingAnalyze}
 */
class MappingRegistrationAnalyze {

}

/**
 * <h2>概述</h2>
 * {@link com.java.mvc.handler.RequestMappingInfoHandlerMapping}，继承 AbstractHandlerMethodMapping
 * 抽象类，定义了使用的泛型 T 为{@link org.springframework.web.servlet.mvc.method.RequestMappingInfo}类，即
 * Mapping 类型就是 RequestMappingInfo 。
 * <p>
 * <h2>这样有什么好处呢？</h2>
 * RequestMappingInfoHandlerMapping 定义了使用了 RequestMappingInfo 对象，而 其子类RequestMappingHandlerMapping
 * 使用了 @RequestMapping 注解，来生成 RequestMappingInfo 对象。这样，如果未来我们自己定义了自己的注解，或者其他方式来
 * 生成 RequestMappingHandlerMapping 对象，未尝不可。
 * <ul>
 * <li>「3. AbstractHandlerMethodMapping」 的定位</li>
 * <li>「4. RequestMappingInfoHandlerMapping」 的定位</li>
 * <li>「5. RequestMappingHandlerMapping」 的定位</li>
 * </ul>
 * <h2>4.1 构造方法</h2>
 * {@link com.java.mvc.handler.RequestMappingInfoHandlerMapping#RequestMappingInfoHandlerMapping}
 * <h3>4.2 RequestMappingInfo</h3>
 * RequestMappingInfo 不是 RequestMappingInfoHandlerMapping 的内部类，但是是 RequestMappingInfo-HandlerMapping 的前缀。
 * <p>
 * {@link RequestMappingInfo}，实现 RequestCondition 接口，请求匹配信息。
 * <p>
 * 关于{@link org.springframework.web.servlet.mvc.condition.RequestCondition} ，请求条件接口，定义了三个方法，分别是
 * <p>
 * <ul>
 * <li>#combine(T other) 合并方法</li>
 * <li>#getMatchingCondition(HttpServletRequest request) 匹配方法</li>
 * <li>#compareTo(T other, HttpServletRequest request) 比较方法</li>
 * </ul>
 * <h3>4.2.1 构造方法</h3>
 * <ul>
 * <li>{@link RequestMappingInfo#name}</li>
 * <li>{@link RequestMappingInfo#patternsCondition}</li>
 * <li>{@link RequestMappingInfo#methodsCondition}</li>
 * <li>{@link RequestMappingInfo#paramsCondition}</li>
 * <li>{@link RequestMappingInfo#headersCondition}</li>
 * <li>{@link RequestMappingInfo#consumesCondition}</li>
 * <li>{@link RequestMappingInfo#producesCondition}</li>
 * <li>{@link RequestMappingInfo#customConditionHolder}</li>
 * </ul>
 * 这里，我们可以看到各种条件。实际上，和 @RequestMapping 注解是一一对应的。所以，每个属性的详细解释，胖友也可以看看{@link RequestMapping}
 * <p>
 * <b>实际上，我们日常使用最多的还是 patternsCondition 请求路径条件，和 methodsCondition 请求方法条件。</b>
 * <h3>4.2.2 getMatchingCondition</h3>
 * {@link RequestMappingInfo#getMatchingCondition}方法，从当前 RequestMappingInfo 获得匹配的条件。如果匹配，则基于其
 * 匹配的条件，创建新的 RequestMappingInfo 对象。如果不匹配，则返回 null 。
 * <p>
 * 那么可能胖友会疑惑，如果一个 @RequestMapping(value = "user/login") 注解，并未写 RequestMethod 的条件，岂不是会报空？
 * 实际上不会。在这种情况下，会创建一个 RequestMethodsRequestCondition 对象，并且在匹配时，直接返回自身。
 * <h3>4.2.3 compareTo</h3>
 * {@link RequestMappingInfo#compareTo}方法，比较优先级。
 * <p>
 * 虽然代码非常长，实际都是按照优先级，逐个调用每个属性对应的 #compareTo方法，直到比到不相等
 * <h3>4.2.4 PatternsRequestCondition</h3>
 * {@link PatternsRequestCondition}，继承 AbstractRequestCondition 抽象类，请求路径条件。
 * <p>
 * https://www.cnblogs.com/fangjian0423/p/springMVC-request-mapping.html
 * <h2>4.3 getMappingPathPatterns</h2>
 * {@link RequestMappingInfoHandlerMapping#getMappingPathPatterns}方法，获得 Mapping 对应的请求路径集合。
 * <p>
 * 该方法，在 「3.2.2 register」 中，看到对该方法的调用。
 * <h2>4.4 getMatchingMapping</h2>
 * {@link RequestMappingInfoHandlerMapping#getMatchingMapping}
 * <ul>
 * <li>方法内部，调用的就是 「4.2.2 getMatchingCondition」 的方法。</li>
 * <li>该方法，在 「3.6.1 lookupHandlerMethod」 中，看到对该方法的调用。</li>
 * </ul>
 * <h2>4.5 handleMatch</h2>
 * {@link RequestMappingInfoHandlerMapping#handleMatch}方法，覆写父类的方法，设置更多的属性，到请求中。
 * <h2>4.6 handleNoMatch</h2>
 * {@link RequestMappingInfoHandlerMapping#handleNoMatch}方法,覆写父类方法，处理无匹配 Mapping 的情况。主要用途是，
 * 给出为什么找不到 Mapping 的原因。
 * 返回{@link AbstractHandlerMethodMappingComponent}
 */
class RequestMappingInfoHandlerMappingAnalyze {

}