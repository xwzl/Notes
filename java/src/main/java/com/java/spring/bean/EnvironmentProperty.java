package com.java.spring.bean;


import com.java.spring.xml.AbstractEnvironment;
import com.java.spring.xml.ConfigurablePropertyResolver;
import com.java.spring.xml.PropertyResolver;
import com.java.spring.xml.PropertySourcesPropertyResolver;
import org.springframework.core.env.AbstractPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

/**
 * {@link BeanDefinitionRegistries}
 * Spring 的环境 & 属性来做一个分析说明
 *
 * <h2>1. 概括</h2>
 * Spring 环境 & 属性由四个部分组成：PropertySource、Proper tyResolver、Profile 和 Environment。
 * <ul>
 * <li>PropertySource：属性源，key-value 属性对抽象，用于配置数据。</li>
 * <li>PropertyResolver：属性解析器，用于解析属性配置</li>
 * <li>Profile：剖面，只有激活的剖面的组件/配置才会注册到 Spring 容器，类似于 Spring Boot 中的 profile 。</li>
 * <li>Environment：环境，Profile 和 PropertyResolver 的组合。</li>
 * </ul>
 * 下面是整个体系的结构图：{@link #image()}
 * 下面就针对上面结构图对 Spring 的 Properties & Environment 做一个详细的分析。
 * <h2>2. Properties</h2>
 * <h3>2.1 PropertyResolver</h3>
 * <b>属性解析器，用于解析任何基础源的属性的接口</b>
 * {@link PropertyResolver}
 * <p>
 * 从 API 上面我们就知道属性解析器 PropertyResolver 的作用了。下面是一个简单的运用。
 * {@link #testProperty()}
 * <p>
 * PropertyResolver 体系结构
 * <ul>
 * <li>ConfigurablePropertyResolver：供属性类型转换的功能</li>
 * <li>AbstractPropertyResolver：解析属性文件的抽象基类</li>
 * <li>ropertySourcesPropertyResolver：PropertyResolver 的实现者，他对一组 PropertySources 提供属性解析服务</li>
 * </ul>
 * <h3>2.2 ConfigurablePropertyResolver</h3>
 * <b>提供属性类型转换的功能</b>
 * <p>
 * 通俗点说就是 ConfigurablePropertyResolver 提供属性值类型转换所需要的 ConversionService。
 * {@link ConfigurablePropertyResolver}
 * <p>
 * 从 ConfigurablePropertyResolver 所提供的方法来看，除了访问和设置 ConversionService 外，主要还提供了一些解析规则之类的方法。
 * <p>
 * 就 Properties 体系而言，PropertyResolver 定义了访问 Properties 属性值的方法，而 ConfigurablePropertyResolver
 * 则定义了解析 Properties 一些相关的规则和值进行类型转换所需要的 Service。
 * <p>
 * 该体系有两个实现者：AbstractPropertyResolver 和 PropertySourcesPropertyResolver，其中 AbstractPropertyResolver
 * 为实现的抽象基类，PropertySourcesPropertyResolver 为真正的实现者。
 * <h3>AbstractPropertyResolver</h3>
 * <b>解析属性文件的抽象基类</b>
 * AbstractPropertyResolver 作为基类它仅仅只是设置了一些解析属性文件所需要配置或者转换器，如 #setConversionService(...)、
 * #setPlaceholderPrefix(...)、#setValueSeparator(...) 。其实这些方法的实现都比较简单，都是设置或者获取 AbstractPropertyResolver
 * 所提供的属性{@link AbstractPropertyResolver}
 * <h2>2.4 PropertySourcesPropertyResolver</h2>
 * <b>PropertyResolver 的实现者，他对一组 PropertySources 提供属性解析服务</b>
 * 它仅有一个成员变量：PropertySources 。该成员变量内部存储着一组 PropertySource，表示 key-value 键值对的源的抽象基类，即一个
 * PropertySource 对象则是一个 key-value 键值对。
 * {@link PropertySourcesPropertyResolver#getProperty(String)}
 * <h2>2.5 convertValueIfNecessary</h2>
 * 是不是感觉到非常的熟悉，该方法就是完成类型转换的。
 * {@link com.java.spring.xml.AbstractPropertyResolver#convertValueIfNecessary}
 * <h3>3. Environment</h3>
 * <b>表示当前应用程序正在运行的环境</b>
 * <p>
 * 应用程序的环境有两个关键方面：profile 和 properties。
 *
 * <ul>
 * <li>properties 的方法由 PropertyResolver 定义。</li>
 * <li>profile 则表示当前的运行环境，对于应用程序中的 properties 而言，并不是所有的都会加载到系统中，只有其属性与 profile
 * 一直才会被激活加载，</li>
 * </ul>
 * <p>
 * 所以 Environment 对象的作用，是确定哪些配置文件（如果有）当前处于活动状态，以及默认情况下哪些配置文件（如果有）应处于活动状态。
 * properties 在几乎所有应用程序中都发挥着重要作用，并且有多种来源：属性文件，JVM 系统属性，系统环境变量，JNDI，servlet 上下文
 * 参数，ad-hoc 属性对象，映射等。同时它继承 PropertyResolver 接口，所以与属性相关的 Environment 对象其主要是为用户提供方便
 * 的服务接口，用于配置属性源和从中属性源中解析属性。
 * 详情查看{@link Environment}或者{@link com.java.spring.xml.Environment}
 * <p>
 * Environment 体系结构图如下：{@link #image1()}
 * <ul>
 * <li>PropertyResolver：提供属性访问功能</li>
 * <li>Environment：提供访问和判断 profiles 的功能</li>
 * <li>ConfigurableEnvironment：提供设置激活的 profile 和默认的 profile 的功能以及操作 Properties 的工具</li>
 * <li>ConfigurableWebEnvironment：提供配置 Servlet 上下文和 Servlet 参数的功能</li>
 * <li>AbstractEnvironment：实现了 ConfigurableEnvironment 接口，默认属性和存储容器的定义，并且实现了 ConfigurableEnvironment 的方法，并且为子类预留可覆盖了扩展方法</li>
 * <li>StandardEnvironment：继承自 AbstractEnvironment ，非 Servlet(Web) 环境下的标准 Environment 实现</li>
 * <li>StandardServletEnvironment：继承自 StandardEnvironment ，Servlet(Web) 环境下的标准 Environment 实现</li>
 * </ul>
 * <h3>3.1 ConfigurableEnvironment</h3>
 * 提供设置激活的 profile 和默认的 profile 的功能以及操作 Properties 的工具
 * <p>
 * 该类除了继承 Environment 接口外还继承了 ConfigurablePropertyResolver 接口，所以它即具备了设置 profile 的功能也具备了操
 * 作 Properties 的功能。同时还允许客户端通过它设置和验证所需要的属性，自定义转换服务等功能。{@link ConfigurableEnvironment }
 * <h3>3.2 AbstractEnvironment</h3>
 * Environment 的基础实现
 * <p>
 * 允许通过设置 ACTIVE_PROFILES_PROPERTY_NAME 和DEFAULT_PROFILES_PROPERTY_NAME 属性指定活动和默认配置文件。子类的主要区
 * 别在于它们默认添加的 PropertySource 对象。而 AbstractEnvironment 则没有添加任何内容。
 * {@link #yy()}
 * <p>
 * {@link AbstractEnvironment#setActiveProfiles}
 * {@link AbstractEnvironment#getActiveProfiles()}
 * {@link ApplicationContextResolver}
 * @author xuweizhi
 * @date 2019/03/27 14:26
 */
public class EnvironmentProperty {

    /**
     * <image src="../../../../../resources/static/spring/Environment.png"></image>
     */
    public void image() {

    }

    /**
     * <image src="../../../../../resources/static/spring/women.png"></image>
     */
    public void image1() {

    }


    public void testProperty() {
        //PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);
        //
        //System.out.println(propertyResolver.getProperty("name"));
        //System.out.println(propertyResolver.getProperty("name", "chenssy"));
        //System.out.println(propertyResolver.resolvePlaceholders("my name is  ${name}"));
    }

    public void yy() {
        //3.2.1 setActiveProfiles
        //// AbstractEnvironment.java
        //
        //@Override
        //public void setActiveProfiles(String... profiles) {
        //    Assert.notNull(profiles, "Profile array must not be null");
        //    if (logger.isDebugEnabled()) {
        //        logger.debug("Activating profiles " + Arrays.asList(profiles));
        //    }
        //    synchronized (this.activeProfiles) {
        //        // 清空 activeProfiles
        //        this.activeProfiles.clear();
        //        // 遍历 profiles 数组，添加到 activeProfiles 中
        //        for (String profile : profiles) {
        //            // 校验
        //            validateProfile(profile);
        //            this.activeProfiles.add(profile);
        //        }
        //    }
        //}
        //该方法其实就是操作 activeProfiles 集合，在每次设置之前都会将该集合清空重新添加，添加之前调用 #validateProfile(String profile) 方法，对添加的 profile 进行校验，如下：
        //
        //// AbstractEnvironment.java
        //
        //protected void validateProfile(String profile) {
        //    if (!StringUtils.hasText(profile)) {
        //        throw new IllegalArgumentException("Invalid profile [" + profile + "]: must contain text");
        //    }
        //    if (profile.charAt(0) == '!') {
        //        throw new IllegalArgumentException("Invalid profile [" + profile + "]: must not begin with ! operator");
        //    }
        //}
        //这个校验过程比较弱，子类可以提供更加严格的校验规则。
        //3.2.2 getActiveProfile
        //从 getActiveProfiles() 方法，中我们可以猜出这个方法实现的逻辑：获取 activeProfiles 集合即可。代码如下：
        //
        //// AbstractEnvironment.java
        //
        //public String[] getActiveProfiles() {
        //    return StringUtils.toStringArray(doGetActiveProfiles());
        //}
        //委托给 #doGetActiveProfiles() 方法，代码实现：
        //
        //// AbstractEnvironment.java
        //
        //protected Set<String> doGetActiveProfiles() {
        //    synchronized (this.activeProfiles) {
        //        // 如果 activeProfiles 为空，则进行初始化
        //        if (this.activeProfiles.isEmpty()) {
        //            // 获得 ACTIVE_PROFILES_PROPERTY_NAME 对应的 profiles 属性值
        //            String profiles = getProperty(ACTIVE_PROFILES_PROPERTY_NAME);
        //            if (StringUtils.hasText(profiles)) {
        //                // 设置到 activeProfiles 中
        //                setActiveProfiles(StringUtils.commaDelimitedListToStringArray(
        //                        StringUtils.trimAllWhitespace(profiles)));
        //            }
        //        }
        //        return this.activeProfiles;
        //    }
        //}
        //如果 activeProfiles 为空，则从 Properties 中获取 spring.profiles.active 配置，如果不为空，则调用 #setActiveProfiles(String... profiles) 方法，设置 profile，最后返回。
    }

}

interface ConfigurableEnvironment extends Environment, ConfigurablePropertyResolver {

    /**
     * 指定该环境下的 profile 集
     */
    void setActiveProfiles(String... profiles);

    /**
     * 增加此环境的 profile
     */
    void addActiveProfile(String profile);

    /**
     * 设置默认的 profile
     */
    void setDefaultProfiles(String... profiles);

    /**
     * 返回此环境的 PropertySources
     */
    MutablePropertySources getPropertySources();

    /**
     * 尝试返回 System.getenv() 的值，若失败则返回通过 System.getenv(string) 的来访问各个键的映射
     */
    Map<String, Object> getSystemEnvironment();

    /**
     * 尝试返回 System.getProperties() 的值，若失败则返回通过 System.getProperties(string) 的来访问各个键的映射
     */
    Map<String, Object> getSystemProperties();

    void merge(ConfigurableEnvironment parent);
}
