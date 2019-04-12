package com.java.spring.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.support.ResourceEditorRegistrar;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.context.weaving.LoadTimeWeaverAwareProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract implementation of the {@link org.springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 *
 * <p>In contrast to a plain BeanFactory, an ApplicationContext is supposed
 * to detect special beans defined in its internal bean factory:
 * Therefore, this class automatically registers
 * {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor BeanFactoryPostProcessors},
 * {@link org.springframework.beans.factory.config.BeanPostProcessor BeanPostProcessors}
 * and {@link org.springframework.context.ApplicationListener ApplicationListeners}
 * which are defined as beans in the context.
 *
 * <p>A {@link org.springframework.context.MessageSource} may also be supplied
 * as a bean in the context, with the name "messageSource"; otherwise, message
 * resolution is delegated to the parent context. Furthermore, a multicaster
 * for application events can be supplied as "applicationEventMulticaster" bean
 * of type {@link org.springframework.context.event.ApplicationEventMulticaster}
 * in the context; otherwise, a default multicaster of type
 * {@link org.springframework.context.event.SimpleApplicationEventMulticaster} will be used.
 *
 * <p>Implements resource loading through extending
 * {@link org.springframework.core.io.DefaultResourceLoader}.
 * Consequently treats non-URL resource paths as class path resources
 * (supporting full class path resource names that include the package path,
 * e.g. "mypackage/myresource.dat"), unless the {@link #getResourceByPath}
 * method is overwritten in a subclass.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Stephane Nicoll
 * @see #refreshBeanFactory
 * @see #getBeanFactory
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.beans.factory.config.BeanPostProcessor
 * @see org.springframework.context.event.ApplicationEventMulticaster
 * @see org.springframework.context.ApplicationListener
 * @see org.springframework.context.MessageSource
 * @since January 21, 2001
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    /**
     * Name of the MessageSource bean in the factory.
     * If none is supplied, message resolution is delegated to the parent.
     *
     * @see MessageSource
     */
    public static final String MESSAGE_SOURCE_BEAN_NAME = "messageSource";

    /**
     * Name of the LifecycleProcessor bean in the factory.
     * If none is supplied, a DefaultLifecycleProcessor is used.
     *
     * @see org.springframework.context.LifecycleProcessor
     * @see org.springframework.context.support.DefaultLifecycleProcessor
     */
    public static final String LIFECYCLE_PROCESSOR_BEAN_NAME = "lifecycleProcessor";

    /**
     * Name of the ApplicationEventMulticaster bean in the factory.
     * If none is supplied, a default SimpleApplicationEventMulticaster is used.
     *
     * @see org.springframework.context.event.ApplicationEventMulticaster
     * @see org.springframework.context.event.SimpleApplicationEventMulticaster
     */
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";


    static {
        // Eagerly load the ContextClosedEvent class to avoid weird classloader issues
        // on application shutdown in WebLogic 8.1. (Reported by Dustin Woods.)
        ContextClosedEvent.class.getName();
    }


    /**
     * Logger used by this class. Available to subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Unique id for this context, if any.
     */
    private String id = ObjectUtils.identityToString(this);

    /**
     * Display name.
     */
    private String displayName = ObjectUtils.identityToString(this);

    /**
     * Parent context.
     */
    @Nullable
    private ApplicationContext parent;

    /**
     * Environment used by this context.
     */
    @Nullable
    private ConfigurableEnvironment environment;

    /**
     * BeanFactoryPostProcessors to apply on refresh.
     */
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    /**
     * System time in milliseconds when this context started.
     */
    private long startupDate;

    /**
     * Flag that indicates whether this context is currently active.
     */
    private final AtomicBoolean active = new AtomicBoolean();

    /**
     * Flag that indicates whether this context has been closed already.
     */
    private final AtomicBoolean closed = new AtomicBoolean();

    /**
     * Synchronization monitor for the "refresh" and "destroy".
     */
    private final Object startupShutdownMonitor = new Object();

    /**
     * Reference to the JVM shutdown hook, if registered.
     */
    @Nullable
    private Thread shutdownHook;

    /**
     * ResourcePatternResolver used by this context.
     */
    private ResourcePatternResolver resourcePatternResolver;

    /**
     * LifecycleProcessor for managing the lifecycle of beans within this context.
     */
    @Nullable
    private LifecycleProcessor lifecycleProcessor;

    /**
     * MessageSource we delegate our implementation of this interface to.
     */
    @Nullable
    private MessageSource messageSource;

    /**
     * Helper class used in event publishing.
     */
    @Nullable
    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * Statically specified listeners.
     */
    private final Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<>();

    /**
     * Local listeners registered before refresh.
     */
    @Nullable
    private Set<ApplicationListener<?>> earlyApplicationListeners;

    /**
     * ApplicationEvents published before the multicaster setup.
     */
    @Nullable
    private Set<ApplicationEvent> earlyApplicationEvents;


    /**
     * Create a new AbstractApplicationContext with no parent.
     */
    public AbstractApplicationContext() {
        this.resourcePatternResolver = getResourcePatternResolver();
    }

    /**
     * Create a new AbstractApplicationContext with the given parent context.
     *
     * @param parent the parent context
     */
    public AbstractApplicationContext(@Nullable ApplicationContext parent) {
        this();
        setParent(parent);
    }


    //---------------------------------------------------------------------
    // Implementation of ApplicationContext interface
    //---------------------------------------------------------------------

    /**
     * Set the unique id of this application context.
     * <p>Default is the object id of the context instance, or the name
     * of the context bean if the context is itself defined as a bean.
     *
     * @param id the unique id of the context
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    /**
     * Set a friendly name for this context.
     * Typically done during initialization of concrete context implementations.
     * <p>Default is the object id of the context instance.
     */
    public void setDisplayName(String displayName) {
        Assert.hasLength(displayName, "Display name must not be empty");
        this.displayName = displayName;
    }

    /**
     * Return a friendly name for this context.
     *
     * @return a display name for this context (never {@code null})
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Return the parent context, or {@code null} if there is no parent
     * (that is, this context is the root of the context hierarchy).
     */
    @Override
    @Nullable
    public ApplicationContext getParent() {
        return this.parent;
    }

    /**
     * Set the {@code Environment} for this application context.
     * <p>Default value is determined by {@link #createEnvironment()}. Replacing the
     * default with this method is one option but configuration through {@link
     * #getEnvironment()} should also be considered. In either case, such modifications
     * should be performed <em>before</em> {@link #refresh()}.
     *
     * @see AbstractApplicationContext#createEnvironment()
     */
    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Return the {@code Environment} for this application context in configurable
     * form, allowing for further customization.
     * <p>If none specified, a default environment will be initialized via
     * {@link #createEnvironment()}.
     */
    @Override
    public ConfigurableEnvironment getEnvironment() {
        if (this.environment == null) {
            this.environment = createEnvironment();
        }
        return this.environment;
    }

    /**
     * Create and return a new {@link StandardEnvironment}.
     * <p>Subclasses may override this method in order to supply
     * a custom {@link ConfigurableEnvironment} implementation.
     */
    protected ConfigurableEnvironment createEnvironment() {
        return new StandardEnvironment();
    }

    /**
     * Return this context's internal bean factory as AutowireCapableBeanFactory,
     * if already available.
     *
     * @see #getBeanFactory()
     */
    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return getBeanFactory();
    }

    /**
     * Return the timestamp (ms) when this context was first loaded.
     */
    @Override
    public long getStartupDate() {
        return this.startupDate;
    }

    /**
     * Publish the given event to all listeners.
     * <p>Note: Listeners get initialized after the MessageSource, to be able
     * to access it within listener implementations. Thus, MessageSource
     * implementations cannot publish events.
     *
     * @param event the event to publish (may be application-specific or a
     *              standard framework event)
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        publishEvent(event, null);
    }

    /**
     * Publish the given event to all listeners.
     * <p>Note: Listeners get initialized after the MessageSource, to be able
     * to access it within listener implementations. Thus, MessageSource
     * implementations cannot publish events.
     *
     * @param event the event to publish (may be an {@link ApplicationEvent}
     *              or a payload object to be turned into a {@link PayloadApplicationEvent})
     */
    @Override
    public void publishEvent(Object event) {
        publishEvent(event, null);
    }

    /**
     * Publish the given event to all listeners.
     *
     * @param event     the event to publish (may be an {@link ApplicationEvent}
     *                  or a payload object to be turned into a {@link PayloadApplicationEvent})
     * @param eventType the resolved event type, if known
     * @since 4.2
     */
    protected void publishEvent(Object event, @Nullable ResolvableType eventType) {
        Assert.notNull(event, "Event must not be null");

        // Decorate event as an ApplicationEvent if necessary
        ApplicationEvent applicationEvent;
        if (event instanceof ApplicationEvent) {
            applicationEvent = (ApplicationEvent) event;
        } else {
            applicationEvent = new PayloadApplicationEvent<>(this, event);
            if (eventType == null) {
                eventType = ((PayloadApplicationEvent) applicationEvent).getResolvableType();
            }
        }

        // Multicast right now if possible - or lazily once the multicaster is initialized
        if (this.earlyApplicationEvents != null) {
            this.earlyApplicationEvents.add(applicationEvent);
        } else {
            getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
        }

        // Publish event via parent context as well...
        if (this.parent != null) {
            if (this.parent instanceof AbstractApplicationContext) {
                ((AbstractApplicationContext) this.parent).publishEvent(event, eventType);
            } else {
                this.parent.publishEvent(event);
            }
        }
    }

    /**
     * Return the internal ApplicationEventMulticaster used by the context.
     *
     * @return the internal ApplicationEventMulticaster (never {@code null})
     * @throws IllegalStateException if the context has not been initialized yet
     */
    ApplicationEventMulticaster getApplicationEventMulticaster() throws IllegalStateException {
        if (this.applicationEventMulticaster == null) {
            throw new IllegalStateException("ApplicationEventMulticaster not initialized - " +
                    "call 'refresh' before multicasting events via the context: " + this);
        }
        return this.applicationEventMulticaster;
    }

    /**
     * Return the internal LifecycleProcessor used by the context.
     *
     * @return the internal LifecycleProcessor (never {@code null})
     * @throws IllegalStateException if the context has not been initialized yet
     */
    LifecycleProcessor getLifecycleProcessor() throws IllegalStateException {
        if (this.lifecycleProcessor == null) {
            throw new IllegalStateException("LifecycleProcessor not initialized - " +
                    "call 'refresh' before invoking lifecycle methods via the context: " + this);
        }
        return this.lifecycleProcessor;
    }

    /**
     * Return the ResourcePatternResolver to use for resolving location patterns
     * into Resource instances. Default is a
     * {@link org.springframework.core.io.support.PathMatchingResourcePatternResolver},
     * supporting Ant-style location patterns.
     * <p>Can be overridden in subclasses, for extended resolution strategies,
     * for controller in a web environment.
     * <p><b>Do not call this when needing to resolve a location pattern.</b>
     * Call the context's {@code getResources} method instead, which
     * will delegate to the ResourcePatternResolver.
     *
     * @return the ResourcePatternResolver for this context
     * @see #getResources
     * @see org.springframework.core.io.support.PathMatchingResourcePatternResolver
     */
    protected ResourcePatternResolver getResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver(this);
    }


    //---------------------------------------------------------------------
    // Implementation of ConfigurableApplicationContext interface
    //---------------------------------------------------------------------

    /**
     * Set the parent of this application context.
     * <p>The parent {@linkplain ApplicationContext#getEnvironment() environment} is
     * {@linkplain ConfigurableEnvironment#merge(ConfigurableEnvironment) merged} with
     * this (child) application context environment if the parent is non-{@code null} and
     * its environment is an instance of {@link ConfigurableEnvironment}.
     *
     * @see ConfigurableEnvironment#merge(ConfigurableEnvironment)
     */
    @Override
    public void setParent(@Nullable ApplicationContext parent) {
        this.parent = parent;
        if (parent != null) {
            Environment parentEnvironment = parent.getEnvironment();
            if (parentEnvironment instanceof ConfigurableEnvironment) {
                getEnvironment().merge((ConfigurableEnvironment) parentEnvironment);
            }
        }
    }

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
        Assert.notNull(postProcessor, "BeanFactoryPostProcessor must not be null");
        this.beanFactoryPostProcessors.add(postProcessor);
    }

    /**
     * Return the list of BeanFactoryPostProcessors that will get applied
     * to the internal BeanFactory.
     */
    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactoryPostProcessors;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        Assert.notNull(listener, "ApplicationListener must not be null");
        if (this.applicationEventMulticaster != null) {
            this.applicationEventMulticaster.addApplicationListener(listener);
        }
        this.applicationListeners.add(listener);
    }

    /**
     * Return the list of statically specified ApplicationListeners.
     * 这里每一个方法都非常重要，需要一个一个地解释说明。
     */
    public Collection<ApplicationListener<?>> getApplicationListeners() {
        return this.applicationListeners;
    }

    @Override
    public void refresh() throws BeansException, IllegalStateException {
        synchronized (this.startupShutdownMonitor) {
            // 准备刷新
            // 1.上下文环境初始化上下文环境，对系统的环境变量或者系统属性进行准备和校验,如环境变量中必须设置某个值才能运行，
            // 否则不能运行，这个时候可以在这里加这个校验，重写 initPropertySources 方法就好了
            prepareRefresh();

            // 创建并初始化 BeanFactory
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

            // 填充BeanFactory功能,上面获取获取的 BeanFactory 除了加载了一些 BeanDefinition 就没有其他任何东西了，
            // 这个时候其实还不能投入生产，因为还少配置了一些东西，比如 context的 ClassLoader 和 后置处理器等等。
            prepareBeanFactory(beanFactory);

            try {
                //提供子类覆盖的额外处理，即子类处理自定义的BeanFactoryPostProcess，空方法，也不会调用父类的方法
                postProcessBeanFactory(beanFactory);

                // 激活各种BeanFactory处理器
                invokeBeanFactoryPostProcessors(beanFactory);

                // 注册拦截Bean创建的Bean处理器，即注册 BeanPostProcessor
                registerBeanPostProcessors(beanFactory);

                // 初始化上下文中的资源文件，如国际化文件的处理等
                initMessageSource();

                // 初始化上下文事件广播器
                initApplicationEventMulticaster();

                // 给子类扩展初始化其他Bean，预留给 AbstractApplicationContext 的子类用于初始
                // 化其他特殊的 bean，该方法需要在所有单例 bean 初始化之前调用。
                onRefresh();

                // 在所有bean中查找listener bean，然后注册到广播器中
                registerListeners();

                // 初始化剩下的单例Bean(非延迟加载的)
                finishBeanFactoryInitialization(beanFactory);

                // 完成刷新过程,通知生命周期处理器lifecycleProcessor刷新过程,同时发出ContextRefreshEvent通知别人
                // 主要是调用 LifecycleProcessor#onRefresh() ，并且发布事件（ContextRefreshedEvent）。
                finishRefresh();
            } catch (BeansException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Exception encountered during context initialization - " +
                            "cancelling refresh attempt: " + ex);
                }

                //  销毁已经创建的Bean
                destroyBeans();

                // 重置容器激活标签
                cancelRefresh(ex);

                // 抛出异常
                throw ex;
            } finally {
                // Reset common introspection caches in Spring's core, since we
                // might not ever need metadata for singleton beans anymore...
                resetCommonCaches();
            }
        }
    }

    /**
     * Prepare this context for refreshing, setting its startup date and
     * active flag as well as performing any initialization of property sources.
     * 设置 context 启动时间
     * 设置 context 的当前状态
     * 初始化 context environment 中占位符
     * 对属性进行必要的验证
     */
    protected void prepareRefresh() {
        // Switch to active.
        // 设置启动日期
        this.startupDate = System.currentTimeMillis();
        // 设置 context 当前状态
        this.closed.set(false);
        this.active.set(true);

        if (logger.isDebugEnabled()) {
            if (logger.isTraceEnabled()) {
                logger.trace("Refreshing " + this);
            } else {
                logger.debug("Refreshing " + getDisplayName());
            }
        }

        // Initialize any placeholder property sources in the context environment.
        // 初始化context environment（上下文环境）中的占位符属性来源
        initPropertySources();

        // Validate that all properties marked as required are resolvable:
        // see ConfigurablePropertyResolver#setRequiredProperties
        // 对属性进行必要的验证
        getEnvironment().validateRequiredProperties();

        // Store pre-refresh ApplicationListeners...
        if (this.earlyApplicationListeners == null) {
            this.earlyApplicationListeners = new LinkedHashSet<>(this.applicationListeners);
        } else {
            // Reset local application listeners to pre-refresh state.
            this.applicationListeners.clear();
            this.applicationListeners.addAll(this.earlyApplicationListeners);
        }

        // Allow for the collection of early ApplicationEvents,
        // to be published once the multicaster is available...
        this.earlyApplicationEvents = new LinkedHashSet<>();
    }

    /**
     * <p>Replace any stub property sources with actual instances.
     *
     * @see org.springframework.core.env.PropertySource.StubPropertySource
     * @see org.springframework.web.context.support.WebApplicationContextUtils#initServletPropertySources
     */
    protected void initPropertySources() {
        // For subclasses: do nothing by default.
    }

    /**
     * Tell the subclass to refresh the internal bean factory.
     *
     * @return the fresh BeanFactory instance
     * @see #refreshBeanFactory()
     * @see #getBeanFactory()
     */
    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        /**
         * 核心{@link AbstractRefreshableApplicationContext#refreshBeanFactory}
         */
        refreshBeanFactory();
        return getBeanFactory();
    }

    /**
     * Configure the factory's standard context characteristics,
     * such as the context's ClassLoader and post-processors.
     * 上面获取获取的 BeanFactory 除了加载了一些 BeanDefinition 就没有其他任何东西了，这个时候
     * 其实还不能投入生产，因为还少配置了一些东西，比如 context的 ClassLoader 和 后置处理器等等。
     *
     * @param beanFactory the BeanFactory to configure
     */
    public void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // 设置beanFactory的classLoader，设置线程上下文类加载器
        beanFactory.setBeanClassLoader(getClassLoader());

        // 设置beanFactory的表达式语言处理器,Spring3开始增加了对语言表达式的支持,默认可以使用#{bean.xxx}的形式来调用相关属性值
        beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
        // 为beanFactory增加一个默认的propertyEditor
        beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));

        // 添加ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 设置忽略自动装配的接口
        beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
        beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
        beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
        beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
        beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
        beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);

        // 设置几个自动装配的特殊规则
        beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
        beanFactory.registerResolvableDependency(ResourceLoader.class, this);
        beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
        beanFactory.registerResolvableDependency(ApplicationContext.class, this);

        // Register early post-processor for detecting inner beans as ApplicationListeners.
        beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));

        // 增加对AspectJ的支持
        if (beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
            beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
            // Set a temporary ClassLoader for type matching.
            beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
        }

        // 注册默认的系统环境bean
        if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
            beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
        }
        if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
            beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
        }
        if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
            beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
        }
    }

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for registering special
     * BeanPostProcessors etc in certain ApplicationContext implementations.
     * <ul>
     * <li>添加 ServletContextAwareProcessor 到 BeanFactory 容器中，该 processor 实现 BeanPostProcessor 接口，主要用于将ServletContext 传递给实现了 ServletContextAware 接口的 bean</li>
     * <li>忽略 ServletContextAware、ServletConfigAware</li>
     * <li>注册 WEB 应用特定的域（scope）到 beanFactory 中，以便 WebApplicationContext 可以使用它们。比如 “request” , “session” , “globalSession” , “application”</li>
     * <li> 注册 WEB 应用特定的 Environment bean 到 beanFactory 中，以便WebApplicationContext 可以使用它们。如：”contextParameters”, “contextAttributes”</li>
     * </ul>
     *
     * @param beanFactory the bean factory used by the application context
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

        //Spring5 没有实现。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
        //beanFactory.addBeanPostProcessor(new ServletContextAwareProcessor(this.servletContext, this.servletConfig));
        //beanFactory.ignoreDependencyInterface(ServletContextAware.class);
        //beanFactory.ignoreDependencyInterface(ServletConfigAware.class);
        //
        //WebApplicationContextUtils.registerWebApplicationScopes(beanFactory, this.servletContext);
        //WebApplicationContextUtils.registerEnvironmentBeans(beanFactory, this.servletContext, this.servletConfig);
    }

    /**
     * Instantiate and invoke all registered BeanFactoryPostProcessor beans,
     * respecting explicit order if given.
     * <p>Must be called before singleton instantiation.
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());

        // Detect a LoadTimeWeaver and prepare for weaving, if found in the meantime
        // (e.g. through an @Bean method registered by ConfigurationClassPostProcessor)
        if (beanFactory.getTempClassLoader() == null && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
            beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
            beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
        }
        // Spring5之前 但是处理逻辑较为单一，就是对所有的 BeanDefinitionRegistryPostProcessors 、手动注册的 BeanFactoryPostProcessor
        // 以及通过配置文件方式的 BeanFactoryPostProcessor 按照 PriorityOrdered 、 Ordered、no ordered 三种方式分开处理、调用。
    /*    // 定义一个 set 保存所有的 BeanFactoryPostProcessors
        Set<String> processedBeans = new HashSet<>();

        // 如果当前 BeanFactory 为 BeanDefinitionRegistry
        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
            // BeanFactoryPostProcessor 集合
            List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
            // BeanDefinitionRegistryPostProcessor 集合
            List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

            // 迭代注册的 beanFactoryPostProcessors
            for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
                // 如果是 BeanDefinitionRegistryPostProcessor，则调用 postProcessBeanDefinitionRegistry 进行注册，
                // 同时加入到 registryProcessors 集合中
                if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
                    BeanDefinitionRegistryPostProcessor registryProcessor =
                            (BeanDefinitionRegistryPostProcessor) postProcessor;
                    registryProcessor.postProcessBeanDefinitionRegistry(registry);
                    registryProcessors.add(registryProcessor);
                }
                else {
                    // 否则当做普通的 BeanFactoryPostProcessor 处理
                    // 添加到 regularPostProcessors 集合中即可，便于后面做后续处理
                    regularPostProcessors.add(postProcessor);
                }
            }

            // 用于保存当前处理的 BeanDefinitionRegistryPostProcessor
            List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

            // 首先处理实现了 PriorityOrdered (有限排序接口)的 BeanDefinitionRegistryPostProcessor
            String[] postProcessorNames =
                    beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
            for (String ppName : postProcessorNames) {
                if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
                    currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                    processedBeans.add(ppName);
                }
            }

            // 排序
            sortPostProcessors(currentRegistryProcessors, beanFactory);

            // 加入registryProcessors集合
            registryProcessors.addAll(currentRegistryProcessors);

            // 调用所有实现了 PriorityOrdered 的 BeanDefinitionRegistryPostProcessors 的 postProcessBeanDefinitionRegistry()
            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);

            // 清空，以备下次使用
            currentRegistryProcessors.clear();

            // 其次，调用是实现了 Ordered（普通排序接口）的 BeanDefinitionRegistryPostProcessors
            // 逻辑和 上面一样
            postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
            for (String ppName : postProcessorNames) {
                if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
                    currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                    processedBeans.add(ppName);
                }
            }
            sortPostProcessors(currentRegistryProcessors, beanFactory);
            registryProcessors.addAll(currentRegistryProcessors);
            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
            currentRegistryProcessors.clear();

            // 最后调用其他的 BeanDefinitionRegistryPostProcessors
            boolean reiterate = true;
            while (reiterate) {
                reiterate = false;
                // 获取 BeanDefinitionRegistryPostProcessor
                postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
                for (String ppName : postProcessorNames) {

                    // 没有包含在 processedBeans 中的（因为包含了的都已经处理了）
                    if (!processedBeans.contains(ppName)) {
                        currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                        processedBeans.add(ppName);
                        reiterate = true;
                    }
                }

                // 与上面处理逻辑一致
                sortPostProcessors(currentRegistryProcessors, beanFactory);
                registryProcessors.addAll(currentRegistryProcessors);
                invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                currentRegistryProcessors.clear();
            }

            // 调用所有 BeanDefinitionRegistryPostProcessor (包括手动注册和通过配置文件注册)
            // 和 BeanFactoryPostProcessor(只有手动注册)的回调函数(postProcessBeanFactory())
            invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
            invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
        }

        else {
            // 如果不是 BeanDefinitionRegistry 只需要调用其回调函数（postProcessBeanFactory()）即可
            invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
        }

        //
        String[] postProcessorNames =
                beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

        // 这里同样需要区分 PriorityOrdered 、Ordered 和 no Ordered
        List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
        List<String> orderedPostProcessorNames = new ArrayList<>();
        List<String> nonOrderedPostProcessorNames = new ArrayList<>();
        for (String ppName : postProcessorNames) {
            // 已经处理过了的，跳过
            if (processedBeans.contains(ppName)) {
                // skip - already processed in first phase above
            }
            // PriorityOrdered
            else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
                priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
            }
            // Ordered
            else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
                orderedPostProcessorNames.add(ppName);
            }
            // no Ordered
            else {
                nonOrderedPostProcessorNames.add(ppName);
            }
        }

        // First, PriorityOrdered 接口
        sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
        invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

        // Next, Ordered 接口
        List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>();
        for (String postProcessorName : orderedPostProcessorNames) {
            orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
        }
        sortPostProcessors(orderedPostProcessors, beanFactory);
        invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

        // Finally, no ordered
        List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
        for (String postProcessorName : nonOrderedPostProcessorNames) {
            nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
        }
        invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

        // Clear cached merged bean definitions since the post-processors might have
        // modified the original metadata, e.g. replacing placeholders in values...
        beanFactory.clearMetadataCache();
    }*/
    }

    /**
     * 实例化并调用已经注入的 BeanPostProcessor
     * 必须在应用中 bean 实例化之前调用
     * Instantiate and invoke all registered BeanPostProcessor beans,
     * respecting explicit order if given.
     * <p>Must be called before any instantiation of application beans.
     */
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
    }

    /**
     * Initialize the MessageSource.
     * Use parent's if none defined in this context.
     */
    protected void initMessageSource() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 包含 “messageSource” bean
        if (beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)) {
            this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            // 如果有父类
            // HierarchicalMessageSource 分级处理的 MessageSource
            if (this.parent != null && this.messageSource instanceof HierarchicalMessageSource) {
                HierarchicalMessageSource hms = (HierarchicalMessageSource) this.messageSource;
                if (hms.getParentMessageSource() == null) {
                    // 如果没有注册父 MessageSource，则设置为父类上下文的的 MessageSource
                    hms.setParentMessageSource(getInternalParentMessageSource());
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Using MessageSource [" + this.messageSource + "]");
            }
        } else {
            // 使用 空 MessageSource
            DelegatingMessageSource dms = new DelegatingMessageSource();
            dms.setParentMessageSource(getInternalParentMessageSource());
            this.messageSource = dms;
            beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
            if (logger.isDebugEnabled()) {
                logger.debug("Unable to locate MessageSource with name '" + MESSAGE_SOURCE_BEAN_NAME +
                        "': using default [" + this.messageSource + "]");
            }
        }
    }

    /**
     * Initialize the ApplicationEventMulticaster.
     * Uses SimpleApplicationEventMulticaster if none defined in the context.
     *
     * @see org.springframework.context.event.SimpleApplicationEventMulticaster
     */
    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 如果存在 applicationEventMulticaster bean，则获取赋值
        if (beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)) {
            this.applicationEventMulticaster =
                    beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
            if (logger.isDebugEnabled()) {
                logger.debug("Using ApplicationEventMulticaster [" + this.applicationEventMulticaster + "]");
            }
        } else {
            // 没有则新建 SimpleApplicationEventMulticaster，并完成 bean 的注册
            this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
            beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
            if (logger.isDebugEnabled()) {
                logger.debug("Unable to locate ApplicationEventMulticaster with name '" +
                        APPLICATION_EVENT_MULTICASTER_BEAN_NAME +
                        "': using default [" + this.applicationEventMulticaster + "]");
            }
        }
    }

    /**
     * Initialize the LifecycleProcessor.
     * Uses DefaultLifecycleProcessor if none defined in the context.
     *
     * @see org.springframework.context.support.DefaultLifecycleProcessor
     */
    protected void initLifecycleProcessor() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        if (beanFactory.containsLocalBean(LIFECYCLE_PROCESSOR_BEAN_NAME)) {
            this.lifecycleProcessor =
                    beanFactory.getBean(LIFECYCLE_PROCESSOR_BEAN_NAME, LifecycleProcessor.class);
            if (logger.isTraceEnabled()) {
                logger.trace("Using LifecycleProcessor [" + this.lifecycleProcessor + "]");
            }
        } else {
            DefaultLifecycleProcessor defaultProcessor = new DefaultLifecycleProcessor();
            defaultProcessor.setBeanFactory(beanFactory);
            this.lifecycleProcessor = defaultProcessor;
            beanFactory.registerSingleton(LIFECYCLE_PROCESSOR_BEAN_NAME, this.lifecycleProcessor);
            if (logger.isTraceEnabled()) {
                logger.trace("No '" + LIFECYCLE_PROCESSOR_BEAN_NAME + "' bean, using " +
                        "[" + this.lifecycleProcessor.getClass().getSimpleName() + "]");
            }
        }
    }

    /**
     * Template method which can be overridden to add context-specific refresh work.
     * Called on initialization of special beans, before instantiation of singletons.
     * <p>This implementation is empty.
     *
     * @throws BeansException in case of errors
     * @see #refresh()
     */
    protected void onRefresh() throws BeansException {
        // For subclasses: do nothing by default.
    }

    /**
     * Add beans that implement ApplicationListener as listeners.
     * Doesn't affect other listeners, which can be added without being beans.
     */
    protected void registerListeners() {
        // 注册静态 监听器
        for (ApplicationListener<?> listener : getApplicationListeners()) {
            getApplicationEventMulticaster().addApplicationListener(listener);
        }

        String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
        for (String listenerBeanName : listenerBeanNames) {
            getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
        }

        // 至此，已经完成将监听器注册到ApplicationEventMulticaster中，下面将发布前期的事件给监听器。
        Set<ApplicationEvent> earlyEventsToProcess = this.earlyApplicationEvents;
        this.earlyApplicationEvents = null;
        if (earlyEventsToProcess != null) {
            for (ApplicationEvent earlyEvent : earlyEventsToProcess) {
                getApplicationEventMulticaster().multicastEvent(earlyEvent);
            }
        }
    }

    /**
     * Finish the initialization of this context's bean factory,
     * initializing all remaining singleton beans.
     */
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 初始化转换器
        if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
                beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
            beanFactory.setConversionService(
                    beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
        }

        // 如果之前没有注册 bean 后置处理器（例如PropertyPlaceholderConfigurer），则注册默认的解析器
        if (!beanFactory.hasEmbeddedValueResolver()) {
            beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
        }

        // 初始化 Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
        String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
        for (String weaverAwareName : weaverAwareNames) {
            getBean(weaverAwareName);
        }

        // 停止使用临时的 ClassLoader
        beanFactory.setTempClassLoader(null);

        //
        beanFactory.freezeConfiguration();

        // 初始化所有剩余的单例（非延迟初始化）
        beanFactory.preInstantiateSingletons();
    }

    /**
     * Finish the refresh of this context, invoking the LifecycleProcessor's
     * onRefresh() method and publishing the
     * {@link org.springframework.context.event.ContextRefreshedEvent}.
     */
    protected void finishRefresh() {
        // Clear context-level resource caches (such as ASM metadata from scanning).
        clearResourceCaches();

        // Initialize lifecycle processor for this context.
        initLifecycleProcessor();

        // Propagate refresh to lifecycle processor first.
        getLifecycleProcessor().onRefresh();

        // Publish the final event.
        publishEvent(new ContextRefreshedEvent(this));

        // Participate in LiveBeansView MBean, if active.
        //LiveBeansView.registerApplicationContext(this);
    }

    /**
     * Cancel this context's refresh attempt, resetting the {@code active} flag
     * after an exception got thrown.
     *
     * @param ex the exception that led to the cancellation
     */
    protected void cancelRefresh(BeansException ex) {
        this.active.set(false);
    }

    /**
     * Reset Spring's common reflection metadata caches, in particular the
     * {@link ReflectionUtils}, {@link AnnotationUtils}, {@link ResolvableType}
     * and {@link CachedIntrospectionResults} caches.
     *
     * @see ReflectionUtils#clearCache()
     * @see AnnotationUtils#clearCache()
     * @see ResolvableType#clearCache()
     * @see CachedIntrospectionResults#clearClassLoader(ClassLoader)
     * @since 4.2
     */
    protected void resetCommonCaches() {
        ReflectionUtils.clearCache();
        AnnotationUtils.clearCache();
        ResolvableType.clearCache();
        CachedIntrospectionResults.clearClassLoader(getClassLoader());
    }


    /**
     * Register a shutdown hook with the JVM runtime, closing this context
     * on JVM shutdown unless it has already been closed at that time.
     * <p>Delegates to {@code doClose()} for the actual closing procedure.
     *
     * @see Runtime#addShutdownHook
     * @see #close()
     * @see #doClose()
     */
    @Override
    public void registerShutdownHook() {
        if (this.shutdownHook == null) {
            // No shutdown hook registered yet.
            this.shutdownHook = new Thread() {
                @Override
                public void run() {
                    synchronized (startupShutdownMonitor) {
                        doClose();
                    }
                }
            };
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
    }

    /**
     * Callback for destruction of this instance, originally attached
     * to a {@code DisposableBean} implementation (not anymore in 5.0).
     * <p>The {@link #close()} method is the native way to shut down
     * an ApplicationContext, which this method simply delegates to.
     *
     * @deprecated as of Spring Framework 5.0, in favor of {@link #close()}
     */
    @Deprecated
    public void destroy() {
        close();
    }

    /**
     * Close this application context, destroying all beans in its bean factory.
     * <p>Delegates to {@code doClose()} for the actual closing procedure.
     * Also removes a JVM shutdown hook, if registered, as it's not needed anymore.
     *
     * @see #doClose()
     * @see #registerShutdownHook()
     */
    @Override
    public void close() {
        synchronized (this.startupShutdownMonitor) {
            //调用 #doClose() 方法，发布 ContextClosedEvent 事件，销毁所有 Bean（单例），关闭 BeanFactory 。
            doClose();
            // If we registered a JVM shutdown hook, we don't need it anymore now:
            // We've already explicitly closed the context.
            if (this.shutdownHook != null) {
                try {
                    Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
                } catch (IllegalStateException ex) {
                    // ignore - VM is already shutting down
                }
            }
        }
    }

    /**
     * Actually performs context closing: publishes a ContextClosedEvent and
     * destroys the singletons in the bean factory of this application context.
     * <p>Called by both {@code close()} and a JVM shutdown hook, if any.
     *
     * @see org.springframework.context.event.ContextClosedEvent
     * @see #destroyBeans()
     * @see #close()
     * @see #registerShutdownHook()
     */
    protected void doClose() {
        // Check whether an actual close attempt is necessary...
        if (this.active.get() && this.closed.compareAndSet(false, true)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Closing " + this);
            }

            //LiveBeansView.unregisterApplicationContext(this);

            try {
                // Publish shutdown event.
                publishEvent(new ContextClosedEvent(this));
            } catch (Throwable ex) {
                logger.warn("Exception thrown from ApplicationListener handling ContextClosedEvent", ex);
            }

            // Stop all Lifecycle beans, to avoid delays during individual destruction.
            if (this.lifecycleProcessor != null) {
                try {
                    this.lifecycleProcessor.onClose();
                } catch (Throwable ex) {
                    logger.warn("Exception thrown from LifecycleProcessor on context close", ex);
                }
            }

            // Destroy all cached singletons in the context's BeanFactory.
            destroyBeans();

            // Close the state of this context itself.
            closeBeanFactory();

            // Let subclasses do some final clean-up if they wish...
            onClose();

            // Reset local application listeners to pre-refresh state.
            if (this.earlyApplicationListeners != null) {
                this.applicationListeners.clear();
                this.applicationListeners.addAll(this.earlyApplicationListeners);
            }

            // Switch to inactive.
            this.active.set(false);
        }
    }

    /**
     * Template method for destroying all beans that this context manages.
     * The default implementation destroy all cached singletons in this context,
     * invoking {@code DisposableBean.destroy()} and/or the specified
     * "destroy-method".
     * <p>Can be overridden to add context-specific bean destruction steps
     * right before or right after standard singleton destruction,
     * while the context's BeanFactory is still active.
     *
     * @see #getBeanFactory()
     * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#destroySingletons()
     */
    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    /**
     * Template method which can be overridden to add context-specific shutdown work.
     * The default implementation is empty.
     * <p>Called at the end of {@link #doClose}'s shutdown procedure, after
     * this context's BeanFactory has been closed. If custom shutdown logic
     * needs to execute while the BeanFactory is still active, override
     * the {@link #destroyBeans()} method instead.
     */
    protected void onClose() {
        // For subclasses: do nothing by default.
    }

    @Override
    public boolean isActive() {
        return this.active.get();
    }

    /**
     * Assert that this context's BeanFactory is currently active,
     * throwing an {@link IllegalStateException} if it isn't.
     * <p>Invoked by all {@link BeanFactory} delegation methods that depend
     * on an active context, i.e. in particular all bean accessor methods.
     * <p>The default implementation checks the {@link #isActive() 'active'} status
     * of this context overall. May be overridden for more specific checks, or for a
     * no-op if {@link #getBeanFactory()} itself throws an exception in such a case.
     */
    protected void assertBeanFactoryActive() {
        if (!this.active.get()) {
            if (this.closed.get()) {
                throw new IllegalStateException(getDisplayName() + " has been closed already");
            } else {
                throw new IllegalStateException(getDisplayName() + " has not been refreshed yet");
            }
        }
    }


    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------

    @Override
    public Object getBean(String name) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(requiredType, args);
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanProvider(requiredType);
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanProvider(requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        assertBeanFactoryActive();
        return getBeanFactory().isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        assertBeanFactoryActive();
        return getBeanFactory().isPrototype(name);
    }

    @Override
    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        assertBeanFactoryActive();
        return getBeanFactory().isTypeMatch(name, typeToMatch);
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        assertBeanFactoryActive();
        return getBeanFactory().isTypeMatch(name, typeToMatch);
    }

    @Override
    @Nullable
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        assertBeanFactoryActive();
        return getBeanFactory().getType(name);
    }

    @Override
    public String[] getAliases(String name) {
        return getBeanFactory().getAliases(name);
    }


    //---------------------------------------------------------------------
    // Implementation of ListableBeanFactory interface
    //---------------------------------------------------------------------

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return getBeanFactory().getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType type) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public String[] getBeanNamesForType(@Nullable Class<?> type) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanNamesForType(type, includeNonSingletons, allowEagerInit);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(@Nullable Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
            throws BeansException {

        assertBeanFactoryActive();
        return getBeanFactory().getBeansOfType(type, includeNonSingletons, allowEagerInit);
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        assertBeanFactoryActive();
        return getBeanFactory().getBeanNamesForAnnotation(annotationType);
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
            throws BeansException {

        assertBeanFactoryActive();
        return getBeanFactory().getBeansWithAnnotation(annotationType);
    }

    @Override
    @Nullable
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
            throws NoSuchBeanDefinitionException {

        assertBeanFactoryActive();
        return getBeanFactory().findAnnotationOnBean(beanName, annotationType);
    }


    //---------------------------------------------------------------------
    // Implementation of HierarchicalBeanFactory interface
    //---------------------------------------------------------------------

    @Override
    @Nullable
    public BeanFactory getParentBeanFactory() {
        return getParent();
    }

    @Override
    public boolean containsLocalBean(String name) {
        return getBeanFactory().containsLocalBean(name);
    }

    /**
     * Return the internal bean factory of the parent context if it implements
     * ConfigurableApplicationContext; else, return the parent context itself.
     *
     * @see org.springframework.context.ConfigurableApplicationContext#getBeanFactory
     */
    @Nullable
    protected BeanFactory getInternalParentBeanFactory() {
        return (getParent() instanceof ConfigurableApplicationContext ?
                ((ConfigurableApplicationContext) getParent()).getBeanFactory() : getParent());
    }


    //---------------------------------------------------------------------
    // Implementation of MessageSource interface
    //---------------------------------------------------------------------

    @Override
    public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
        return getMessageSource().getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException {
        return getMessageSource().getMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return getMessageSource().getMessage(resolvable, locale);
    }

    /**
     * Return the internal MessageSource used by the context.
     *
     * @return the internal MessageSource (never {@code null})
     * @throws IllegalStateException if the context has not been initialized yet
     */
    private MessageSource getMessageSource() throws IllegalStateException {
        if (this.messageSource == null) {
            throw new IllegalStateException("MessageSource not initialized - " +
                    "call 'refresh' before accessing messages via the context: " + this);
        }
        return this.messageSource;
    }

    /**
     * Return the internal message source of the parent context if it is an
     * AbstractApplicationContext too; else, return the parent context itself.
     */
    @Nullable
    protected MessageSource getInternalParentMessageSource() {
        return (getParent() instanceof AbstractApplicationContext ?
                ((AbstractApplicationContext) getParent()).messageSource : getParent());
    }


    //---------------------------------------------------------------------
    // Implementation of ResourcePatternResolver interface
    //---------------------------------------------------------------------

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        return this.resourcePatternResolver.getResources(locationPattern);
    }


    //---------------------------------------------------------------------
    // Implementation of Lifecycle interface
    //---------------------------------------------------------------------

    @Override
    public void start() {
        getLifecycleProcessor().start();
        publishEvent(new ContextStartedEvent(this));
    }

    @Override
    public void stop() {
        getLifecycleProcessor().stop();
        publishEvent(new ContextStoppedEvent(this));
    }

    @Override
    public boolean isRunning() {
        return (this.lifecycleProcessor != null && this.lifecycleProcessor.isRunning());
    }


    //---------------------------------------------------------------------
    // Abstract methods that must be implemented by subclasses
    //---------------------------------------------------------------------

    /**
     * Subclasses must implement this method to perform the actual configuration load.
     * The method is invoked by {@link #refresh()} before any other initialization work.
     * <p>A subclass will either create a new bean factory and hold a reference to it,
     * or return a single BeanFactory instance that it holds. In the latter case, it will
     * usually throw an IllegalStateException if refreshing the context more than once.
     *
     * @throws BeansException        if initialization of the bean factory failed
     * @throws IllegalStateException if already initialized and multiple refresh
     *                               attempts are not supported
     */
    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;

    /**
     * Subclasses must implement this method to release their internal bean factory.
     * This method gets invoked by {@link #close()} after all other shutdown work.
     * <p>Should never throw an exception but rather log shutdown failures.
     */
    protected abstract void closeBeanFactory();

    /**
     * Subclasses must return their internal bean factory here. They should implement the
     * lookup efficiently, so that it can be called repeatedly without a performance penalty.
     * <p>Note: Subclasses should check whether the context is still active before
     * returning the internal bean factory. The internal factory should generally be
     * considered unavailable once the context has been closed.
     *
     * @return this application context's internal bean factory (never {@code null})
     * @throws IllegalStateException if the context does not hold an internal bean factory yet
     *                               (usually if {@link #refresh()} has never been called) or if the context has been
     *                               closed already
     * @see #refreshBeanFactory()
     * @see #closeBeanFactory()
     */
    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;


    /**
     * Return information about this context.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getDisplayName());
        sb.append(", started on ").append(new Date(getStartupDate()));
        ApplicationContext parent = getParent();
        if (parent != null) {
            sb.append(", parent: ").append(parent.getDisplayName());
        }
        return sb.toString();
    }

}
