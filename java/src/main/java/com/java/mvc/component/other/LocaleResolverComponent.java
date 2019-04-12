package com.java.mvc.component.other;

import com.java.mvc.resource.AcceptHeaderLocaleResolver;

/**
 * {@link RequestToViewNameTranslatorComponent}
 * <h2>1. LocaleResolver</h2>
 * LocaleResolver 是一个解决国际化的策略接口,它包含两个方法：#resolveLocale(...) 和 #setLocale(...) .
 * <p>
 * 实现此接口的类：{@link #image()}
 * <h2>2. AcceptHeaderLocaleResolver</h2>
 * SpringMVC 默认使用的是：{@link AcceptHeaderLocaleResolver} 。
 * <p>
 * {@link AcceptHeaderLocaleResolver#resolveLocale}方法的处理流程：
 * <ul>
 * <li>如果 HTTP 请求头里不含有 Accept-Language ，并且默认的语言环境 defaultLocale 不为空，则使用默认的语言环境。否则，从请求里获得 Locale 。</li>
 * <li>而一般的我们没有配置 supportedLocales 与defaultLocale 属性(需要配置注入)，所以 AcceptHeaderLocaleResolver 使用Accept-Language 来构造 Locale 对象。</li>
 * </ul>
 * 用得少，不想洗写
 *
 * {@link ThemeResolverComponent} 实际使用很少，所以艿艿也没特别细看。
 *
 * @author xuweizhi
 * @date 2019/04/02 12:33
 */
public class LocaleResolverComponent {

    /**
     * <image src="../../image/22.png"></image>
     */
    public void image() {

    }

}
