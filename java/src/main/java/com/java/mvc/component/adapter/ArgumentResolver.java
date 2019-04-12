package com.java.mvc.component.adapter;

import com.java.mvc.resource.*;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * <h2>1. 概述</h2>
 * {@link InvocableMethod} 一文，我们来分享{@link  HandlerMethodArgumentResolver}
 * ，HandlerMethod 的参数解析器接口。
 * <p>
 * 两个方法，分别是是否支持解析该参数、以及解析该参数。
 * <h2>2. 类图</h2>
 * {@link #image()}
 * <h2>3. HandlerMethodArgumentResolverComposite</h2>
 * {@link Arg1}
 * <h2>4. AbstractNamedValueMethodArgumentResolver</h2>
 * {@link Arg2}
 * <h2>5. RequestParamMethodArgumentResolver</h2>
 * {@link Arg3}
 * <h2>6. PathVariableMethodArgumentResolver</h2>
 * {@link  PathVariableMethodArgumentResolver} ，实现 HandlerMethodArgumentResolver 接口，处理带有 @RequestParam 注解，
 * 但是注解上无 name 属性的 Map 类型的参数的 RequestParamMethodArgumentResolver 实现类。
 * <h3>7.1 supportsParameter</h3>
 * 实现 {@link  PathVariableMethodArgumentResolver#supportsParameter(MethodParameter parameter)}方法
 * <h3>7.2 createNamedValueInfo</h3>
 * {@link  PathVariableMethodArgumentResolver#createNamedValueInfo}
 * <h3>7.3 resolveName</h3>
 * {@link  PathVariableMethodArgumentResolver#resolveName}
 * <h3>7.4 createNamedValueInfo</h3>
 * {@link  PathVariableMethodArgumentResolver#handleMissingValue(String, MethodParameter)},抛出 MissingPathVariableException 异常。
 * <h3>7.5 handleResolvedValue</h3>
 * 重写 {@link  PathVariableMethodArgumentResolver#handleResolvedValue}方法，添加获得的属性值到请求的 View.PATH_VARIABLES 属性种。
 *
 * {@link ReturnValue}
 * @author xuweizhi
 * @date 2019/04/01 16:06
 */
public class ArgumentResolver {

    /**
     * <image src="../../image/15.png"></image>
     */
    public void image() {

    }

}

/**
 * {@link HandlerMethodArgumentResolverComposite}，实现 HandlerMethodArgumentResolver 接口，
 * 复合的 HandlerMethodArgumentResolver 实现类。
 * <h2>3.1 构造方法</h2>
 * {@link HandlerMethodArgumentResolverComposite#argumentResolvers}
 * {@link HandlerMethodArgumentResolverComposite#argumentResolverCache }
 * <h2>3.2 getArgumentResolver</h2>
 * {@link HandlerMethodArgumentResolverComposite#getArgumentResolver} 方法，获得方法参数对应的
 * HandlerMethodArgumentResolver 对象。
 * <h2>3.3 supportsParameter</h2>
 * {@link HandlerMethodArgumentResolverComposite#supportsParameter}方法，如果能获得到对应的
 * HandlerMethodArgumentResolver 处理器，则说明支持。
 * <h2>3.4 resolveArgument</h2>
 * {@link HandlerMethodArgumentResolverComposite#resolveArgument}方法，解析指定参数的值。
 */
class Arg1 {

}

/**
 * {@link AbstractNamedValueMethodArgumentResolver}，实现 ValueMethodArgumentResolver 接口，基于
 * 名字获取值的HandlerMethodArgumentResolver 抽象基类。
 * <p>
 * 例如说，@RequestParam(value = "username") 注解的参数，就是从请求中获得 username 对应的参数值。
 * <p>
 * AbstractNamedValueMethodArgumentResolver 的子类不多，如下图所示：{@link #image()}
 * <p>
 * 虽然不多，但是我们仅仅分析常用的，分别是
 * <ul>
 * <li>RequestParamMethodArgumentResolver ，基于 @RequestParam 注解( 也可不加该注解的请求参数 )的方法参数，
 * 在 「5. RequestParamMethodArgumentResolver」 中，详细解析。</li>
 * <li>PathVariableMethodArgumentResolver ，基于 @PathVariable 注解的方法参数，在 「6. PathVariableMethodArgumentResolver」
 * 中，详细解析。</li>
 * </ul>
 * <h2>4.1 构造方法</h2>
 * {@link AbstractNamedValueMethodArgumentResolver#namedValueInfoCache}属性，MethodParameter 和
 * NamedValueInfo 的映射，作为缓存。
 * <h2>4.2 NamedValueInfo</h2>
 * {@link AbstractNamedValueMethodArgumentResolver.NamedValueInfo} ，是 AbstractNamedValueMethodArgumentResolver 的静态类.
 * <h2>4.3 getNamedValueInfo</h2>
 * {@link AbstractNamedValueMethodArgumentResolver#getNamedValueInfo} 方法，获得方法参数对应的 NamedValueInfo 对象。
 * <h2>4.4 resolveArgument</h2>
 * {@link AbstractNamedValueMethodArgumentResolver#resolveArgument}方法，解析指定参数的值。
 */
class Arg2 {

    /**
     * <image src="../../image/16.png"></image>
     */
    public void image() {

    }

}

/**
 * 对应@RequestParam 注解解析
 * {@link RequestParamMethodArgumentResolver }，实现 UriComponentsContributor 接口，继承 AbstractNamedValueMethodArgumentResolver
 * 抽象类，请求参数的 HandlerMethodArgumentResolver 实现类，处理普通的请求参数。
 * <h2>5.1 构造方法</h2>
 * <h2></h2>
 * {@link RequestParamMethodArgumentResolver#useDefaultResolution }
 * <h2>5.2 supportsParameter</h2>
 * {@link RequestParamMethodArgumentResolver#supportsParameter }
 * <h2>5.3 createNamedValueInfo</h2>
 * {@link RequestParamMethodArgumentResolver#createNamedValueInfo} 方法，创建 NamedValueInfo 对象。
 * <p>
 * 虽然，在无 @RequestMapping 时，返回的 RequestParamNamedValueInfo 对象的 name 属性为 ""
 *
 * <h2>5.4 resolveNam</h2>
 * <p>
 * 实现 {@link RequestParamMethodArgumentResolver#resolveName} 方法，获得参数的值。
 * <h2>5.5 handleMissingValue</h2>
 * 重写 {@link RequestParamMethodArgumentResolver#handleMissingValue(String, MethodParameter, NativeWebRequest)}
 * 根据参数的类型，做更详细的异常抛出。
 */
class Arg3 {

    /**
     * <image src="../../image/16.png"></image>
     */
    public void image() {

    }

}
