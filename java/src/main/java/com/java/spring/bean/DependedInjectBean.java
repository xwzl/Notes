package com.java.spring.bean;

import com.java.spring.xml.AbstractAutowireCapableBeanFactory;
import com.java.spring.xml.dependcy.RootBeanDefinition;
import org.springframework.beans.BeanWrapper;

/**
 * 加载Bean
 * <h1>概述</h1>
 * Spring IoC 容器所起的作用如下图所示，它会以某种方式加载 Configuration Metadata，将其解析注册到容器内部，然后回根据这些信息绑定整个系统的对象，最终组装成一个可用的基于轻量级容器的应用系统。
 * image src="../image/03.jpg"></image>
 * Spring 在实现上述功能中，将整个流程分为两个阶段：容器初始化阶段和加载bean 阶段。分别如下：
 * <h3>容器初始化阶段：</h3>
 * <ul>
 * <li>首先，通过某种方式加载 Configuration Metadata (主要是依据 Resource、ResourceLoader 两个体系) 。</li>
 * <li>然后，容器会对加载的 Configuration MetaData 进行解析和分析，并将分析的信息组装成 BeanDefinition 。</li>
 * <li>最后，将 BeanDefinition 保存注册到相应的 BeanDefinitionRegistries 中。</li>
 * <li>至此，Spring IoC 的初始化工作完成。</li>
 * </ul>
 * <h3>加载 Bean 阶段：</h3>
 * <ul>
 * <li>经过容器初始化阶段后，应用程序中定义的 bean 信息已经全部加载到系统中了，当我们显示或者隐式地调用 BeanFactory#getBean(...) 方法时，则会触发加载 Bean 阶段。</li>
 * <li>在这阶段，容器会首先检查所请求的对象是否已经初始化完成了，如果没有，则会根据注册的 Bean 信息实例化请求的对象，并为其注册依赖，然后将其返回给请求方。</li>
 * <li>至此第二个阶段也已经完成。</li>
 * </ul>
 * ioc包下的类已经深入分析了
 * <h2>1. getBean</h2>
 * {@link DependedInjectBean#getBean()}
 * <h2>2. doGetBean</h2>
 * {@link DependedInjectBean#doGetBean()}
 * <h2>3. 小结</h2>
 * 至此 BeanFactory#getBean(...) 方法的过程讲解完了。
 * <ul>
 * <li>分析从缓存中获取单例 Bean ，以及对 Bean 的实例中获取对象</li>
 * <li>如果从单例缓存中获取 Bean ，Spring 是怎么加载的呢？所以第二部分是分析 Bean 加载，以及 Bean 的依赖处理</li>
 * <li>Bean 已经加载了，依赖也处理完毕了，第三部分则分析各个作用域的 Bean 初始化过程。</li>
 * </ul>
 * <h3>doGetBean</h3>
 * {@link com.java.spring.xml.AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}方法中有一个重要方法
 * {@link com.java.spring.xml.AbstractBeanFactory#createBean(String, RootBeanDefinition, Object[])} 方法
 * CreateBean方法的默认实现是：{@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 * 在确定生成的Bean没有代理对象的情况下，{@link AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])}
 * <h3>doCreateBean</h3>
 * doCreateBean这这个方法主要用于完成bean的创建和初始化工作，我们可以将其分为四个过程：
 * <ul>
 * <li>createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args) 方法，实例化 bean 。详情查看{@link CreateBean}</li>
 * <li>循环依赖的处理{@link CyclicDependence}</li>
 * <li>{@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)} 方法，进行属性填充详情{@link FillBeanAttribute}</li>
 * <li>初始化bean{@link InitBean}</li>
 * </ul>
 * IoC 之加载 Bean:总结{@link BeanSummery}
 *
 * @author xuweizhi
 * @date 2019/03/24 11:18
 */
public class DependedInjectBean {

    /**
     * 当我们显示或者隐式地调用 BeanFactory#getBean(String name) 方法时，则会触发加载 Bean 阶段。代码如下:
     * <blockquote><pre>
     * // AbstractBeanFactory.java
     * public Object getBean(String name) throws BeansException {
     * 	return doGetBean(name, null, null, false);
     * }
     * </pre></blockquote>
     * 内部调用 doGetBean(String name, final Class<T> requiredType, Object[] args, boolean typeCheckOnly) 方法，其接受四个方法参数：
     * <ul>
     * <li>name ：要获取 Bean 的名字</li>
     * <li>requiredType ：要获取 bean 的类型</li>
     * <li>args ：创建 Bean 时传递的参数。这个参数仅限于创建 Bean 时使用。</li>
     * <li>typeCheckOnly ：是否为类型检查。</li>
     * </ul>
     */
    void getBean() {

    }

    /**
     * {@link com.java.spring.xml.AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}
     * 以下方法都是在doGetBean方法中执行,对应其中的编号
     * <ul>
     * <li>1. 获取beanName{@link com.java.spring.xml.AbstractBeanFactory#transformedBeanName(String)}</li>
     * <li>2. 从单例 Bean 缓存中获取 Bean {@link #singleton()}</li>
     * <li>3. 原型模式依赖检查{@link #prototypeModeDependencyCheck()}</li>
     * <li>4. 从 parentBeanFactory 获取 Bean {@link #acquireBean()}</li>
     * <li>5. 指定的 Bean 标记为已经创建或即将创建{@link #markedBean()}</li>
     * <li>6. 获取 BeanDefinition{@link #acquireBeanDefinition()}</li>
     * <li>7. 依赖 Bean 处理{@link #dependedBean()}</li>
     * <li>8. 不同作用域的 Bean 实例化{@link #otherBeanInit()}</li>
     * <li>9. 类型转换{@link #typeConverter()}</li>
     * </ul>
     */
    void doGetBean() {

    }

    /**
     * 我们知道单例模式的 Bean 在整个过程中只会被创建一次。第一次创建后会将该 Bean 加载到缓存中。后面，在获取 Bean 就会直接从单例缓存中获取。
     * <p>
     * x> 处，如果从缓存中得到了 Bean 对象，则需要调用 #getObjectForBeanInstance(Object beanInstance, String name, String beanName,
     * RootBeanDefinition mbd) 方法，对 Bean 进行实例化处理。因为，缓存中记录的是最原始的 Bean 状态，我们得到的不一定是我们最终想要的 Bean 。
     *
     * <h2>From 《Spring 源码深度解析》P83 页</h2>
     * 一般情况下，Spring 通过反射机制利用 bean 的 class 属性指定实现类来实例化 bean 。某些情况下，实例化 bean 过程比较复杂，如果按照传统的方式，则需要在 中提供大量的配置信息，配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。Spring 为此提供了一个 FactoryBean 的工厂类接口，用户可以通过实现该接口定制实例化 bean 的逻辑。
     * <p>
     * FactoryBean 接口对于 Spring 框架来说战友重要的地址，Spring 自身就提供了 70 多个 FactoryBean 的实现。它们隐藏了实例化一些复杂 bean 的细节，给上层应用带来了便利。
     * 详情见{@link SingletonBean},看完后记得返回{@link #doGetBean()} 第三步原型分析
     */
    public void singleton() {
        // 从缓存中或者实例工厂中获取 Bean 对象
        // Eagerly check singleton cache for manually registered singletons.
        //Object sharedInstance = getSingleton(beanName);
        //if (sharedInstance != null && args == null) {
        //    if (logger.isTraceEnabled()) {
        //        if (isSingletonCurrentlyInCreation(beanName)) {
        //            logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
        //                    "' that is not fully initialized yet - a consequence of a circular reference");
        //        } else {
        //            logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
        //        }
        //    }
        //    // <x> 完成 FactoryBean 的相关处理，并用来获取 FactoryBean 的处理结果
        //    bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
        //}
    }

    /**
     * <h2> 原型模式依赖检查</h2>
     * 对应代码段如下：
     * <blockquote><pre>
     * // Fail if we're already creating this bean instance:
     * // We're assumably within a circular reference.
     * // 因为 Spring 只解决单例模式下得循环依赖，在原型模式下如果存在循环依赖则会抛出异常。
     * if (isPrototypeCurrentlyInCreation(beanName)) {
     * 	throw new BeanCurrentlyInCreationException(beanName);
     * }
     * </pre></blockquote>
     * Spring 只处理单例模式下得循环依赖，对于原型模式的循环依赖直接抛出异常。主要原因还是在于，和 Spring <b>解决循环依赖的策略有关.</b>
     * <ul>
     * <li><b>对于单例( Singleton )模式</b>， Spring 在创建 Bean 的时候并不是等 Bean 完全创建完成后才会将 Bean 添加至缓存中，而是不等 Bean 创建完成就会将创建 Bean 的 ObjectFactory 提早加入到缓存中，这样一旦下一个 Bean 创建的时候需要依赖 bean 时则直接使用 ObjectFactroy 。</li>
     * <li>但是<b>原型( Prototype )模式</b>，我们知道是没法使用缓存的，所以 Spring 对原型模式的循环依赖处理策略则是不处理。</li>
     * </ul>
     * 详情见{@link PrototypeModeDependencyCheck},看完后记得返回{@link #doGetBean()} 第四步从 parentBeanFactory 获取 Bean
     */
    public void prototypeModeDependencyCheck() {

    }

    /**
     * doGetBean()方法中对应代码片段如下
     * <blockquote><pre>
     * // 如果当前容器中没有找到，则从父类容器中加载
     * // Check if bean definition exists in this factory.
     * BeanFactory parentBeanFactory = getParentBeanFactory();
     * if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
     *     // Not found -> check parent.
     *     String nameToLookup = originalBeanName(name);
     *     // 如果，父类容器为 AbstractBeanFactory ，直接递归查找
     *     if (parentBeanFactory instanceof AbstractBeanFactory) {
     *         return ((AbstractBeanFactory) parentBeanFactory).doGetBean(
     *                 nameToLookup, requiredType, args, typeCheckOnly);
     *     // 用明确的 args 从 parentBeanFactory 中，获取 Bean 对象
     *     } else if (args != null) {
     *         // Delegation to parent with explicit args.
     *         return (T) parentBeanFactory.getBean(nameToLookup, args);
     *     // 用明确的 requiredType 从 parentBeanFactory 中，获取 Bean 对象
     *     } else if (requiredType != null) {
     *         // No args -> delegate to standard getBean method.
     *         return parentBeanFactory.getBean(nameToLookup, requiredType);
     *     // 直接使用 nameToLookup 从 parentBeanFactory 获取 Bean 对象
     *     } else {
     *         return (T) parentBeanFactory.getBean(nameToLookup);
     *     }
     * }
     * </pre></blockquote>
     * 如果当前容器缓存中没有相对应的 BeanDefinition 对象，则会尝试从父类工厂（parentBeanFactory）中加载，然后再去递归调用 #getBean(...) 方法。
     * {@link #doGetBean()} 第五步指定的 Bean 标记为已经创建或即将创建
     */
    public void acquireBean() {

    }

    /**
     * 看完后记得返回{@link #doGetBean()} 第六步获取 BeanDefinition
     */
    public void markedBean() {
        // 如果不是仅仅做类型检查则是创建bean，这里需要记录
        //if (!typeCheckOnly) {
        //    markBeanAsCreated(beanName); 进入方法
        //}
    }

    /**
     * 因为从 XML 配置文件中读取到的 Bean 信息是存储在GenericBeanDefinition 中的。但是，所有的 Bean 后续处理都是针对于 RootBeanDefinition 的，所以这里需要进行一个转换。
     * <p>
     * 转换的同时，如果父类 bean 不为空的话，则会一并合并父类的属性。
     * 看完后记得返回{@link #doGetBean()} 第七步 依赖 Bean 处理
     */
    public void acquireBeanDefinition() {
        //doGetBean()方法中对应代码片段如下
        // 从容器中获取 beanName 相应的 GenericBeanDefinition 对象，并将其转换为 RootBeanDefinition 对象
        //final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
        // 检查给定的合并的 BeanDefinition
        //checkMergedBeanDefinition(mbd, beanName, args);
    }

    /**
     * <ul>
     * <li>每个 Bean 都不是单独工作的，它会依赖其他 Bean，其他 Bean 也会依赖它。</li>
     * <li>对于依赖的 Bean ，它会优先加载，所以，在 Spring 的加载顺序中，在初始化某一个 Bean 的时候，首先会初始化这个 Bean 的依赖。</li>
     * </ul>
     * 看完后记得返回{@link #doGetBean()} 第八步 不同作用域的 Bean 实例化
     */
    public void dependedBean() {
        //对应代码段如下：
        // Guarantee initialization of beans that the current bean depends on.
        // 处理所依赖的 bean
        //String[] dependsOn = mbd.getDependsOn();
        //if (dependsOn != null) {
        //    for (String dep : dependsOn) {
        //        // 若给定的依赖 bean 已经注册为依赖给定的 bean
        //        // 即循环依赖的情况，抛出 BeanCreationException 异常
        //        if (isDependent(beanName, dep)) {
        //            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
        //                    "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
        //        }
        //        // 缓存依赖调用
        //        registerDependentBean(dep, beanName);
        //        try {
        //            // 递归处理依赖 Bean
        //            getBean(dep);
        //        } catch (NoSuchBeanDefinitionException ex) {
        //            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
        //                    "'" + beanName + "' depends on missing bean '" + dep + "'", ex);
        //        }
        //    }
        //}
    }

    /**
     * Spring Bean 的作用域默认为 singleton 。当然，还有其他作用域，如 prototype、request、session 等。
     * 不同的作用域会有不同的初始化策略。
     * 详情见{@link SpringOtherBeanInit},看完后记得返回{@link #doGetBean()} 第九步 不同作用域的 Bean 实例化
     */
    public void otherBeanInit() {
        // bean 实例化
        // Create bean instance.
        //if (mbd.isSingleton()) { // 单例模式
        //    sharedInstance = getSingleton(beanName, () -> {
        //        try {
        //            return createBean(beanName, mbd, args);
        //        }
        //        catch (BeansException ex) {
        //            // Explicitly remove instance from singleton cache: It might have been put there
        //            // eagerly by the creation process, to allow for circular reference resolution.
        //            // Also remove any beans that received a temporary reference to the bean.
        //            // 显式从单例缓存中删除 Bean 实例
        //            // 因为单例模式下为了解决循环依赖，可能他已经存在了，所以销毁它。 TODO 芋艿
        //            destroySingleton(beanName);
        //            throw ex;
        //        }
        //    });
        //    bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
        //} else if (mbd.isPrototype()) { // 原型模式
        //    // It's a prototype -> create a new instance.
        //    Object prototypeInstance;
        //    try {
        //        beforePrototypeCreation(beanName);
        //        prototypeInstance = createBean(beanName, mbd, args);
        //    } finally {
        //        afterPrototypeCreation(beanName);
        //    }
        //    bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
        //} else {
        //    // 从指定的 scope 下创建 bean
        //    String scopeName = mbd.getScope();
        //    final Scope scope = this.scopes.get(scopeName);
        //    if (scope == null) {
        //        throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
        //    }try {
        //        Object scopedInstance = scope.get(beanName, () -> {
        //            beforePrototypeCreation(beanName);
        //            try {
        //                return createBean(beanName, mbd, args);
        //            } finally {
        //                afterPrototypeCreation(beanName);
        //            }
        //        });
        //        bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
        //    } catch (IllegalStateException ex) {
        //        throw new BeanCreationException(beanName,
        //                "Scope '" + scopeName + "' is not active for the current thread; consider " +
        //                        "defining a scoped proxy for this bean if you intend to refer to it from a singleton",
        //                ex);
        //    }
        //}
    }

    /**
     * 在调用 #doGetBean(...) 方法时，有一个 requiredType 参数。该参数的功能就是将返回的 Bean 转换为 requiredType 类型。
     * <p>
     * 当然就一般而言，我们是不需要进行类型转换的，也就是 requiredType 为空（比如 #getBean(String name) 方法）。但有，可能会存在这种情况，比如我们返回的 Bean 类型为 String ，
     * 我们在使用的时候需要将其转换为 Integer，那么这个时候 requiredType 就有用武之地了。当然我们一般是不需要这样做的。
     */
    public void typeConverter() {
        // 检查需要的类型是否符合 bean 的实际类型
        // Check if required type matches the type of the actual bean instance.
        //if (requiredType != null && !requiredType.isInstance(bean)) {
        //    try {
        //        // 执行转换
        //        T convertedBean = getTypeConverter().convertIfNecessary(bean, requiredType);
        //        // 转换失败，抛出 BeanNotOfRequiredTypeException 异常
        //        if (convertedBean == null) {
        //            throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
        //        }
        //        return convertedBean;
        //    } catch (TypeMismatchException ex) {
        //        if (logger.isTraceEnabled()) {
        //            logger.trace("Failed to convert bean '" + name + "' to required type '" +
        //                    ClassUtils.getQualifiedName(requiredType) + "'", ex);
        //        }
        //        throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
        //    }
        //}
    }

}
