package com.java.spring.bean;

import com.java.spring.xml.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.FactoryBean;

/**
 * 改代码在{@link com.java.spring.xml.AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}方法中,
 * 调用 #getSingleton(String beanName) 方法，从缓存中获取 Bean 。
 * <h2>1. getSingleton</h2>
 * Spring 对单例模式的 bean 只会创建一次。后续，如果再获取该 Bean ，则是直接从单例缓存中获取，该过程就体现在 #getSingleton(String beanName) 方法中。
 * {@link DefaultSingletonBeanRegistry#getSingleton(String)},到这里从缓存中获取 bean 的过程已经分析完毕了，我们再看开篇的代码段，
 * 从缓存中获取 Bean 后，若其不为 null 且 args 为空，则会调用{@link com.java.spring.xml.AbstractBeanFactory#getObjectForBeanInstance
 * (Object, String, String, RootBeanDefinition)}方法，进行处理。
 * <ul>
 * <li>为什么会有这么一段呢？因为我们从缓存中获取的 bean 是最原始的 Bean ，并不一定使我们最终想要的 Bean 。</li>
 * <li>怎么办呢？调用 #getObjectForBeanInstance(...) 方法，进行处理，该方法的定义为获取给定 Bean 实例的对象，该对象要么是 bean 实例本身，要么就是 FactoryBean 创建的 Bean 对象。</li>
 * </ul>
 * <p>
 * 该方法主要是进行检测工作的，主要如下：
 * <ul>
 * <li><1> 处，若 name 为工厂相关的（以 & 开头），且 beanInstance 为 NullBean 类型则直接返回，如果 beanInstance 不为 FactoryBean 类型则抛出 BeanIsNotAFactoryException 异常。这里主要是校验 beanInstance 的正确性。</li>
 * <li><2> 处，如果 beanInstance 不为 FactoryBean 类型或者 name 也不是与工厂相关的，则直接返回 beanInstance 这个 Bean 对象。这里主要是对非 FactoryBean 类型处理。</li>
 * <li><3> 处，如果 BeanDefinition 为空，则从 factoryBeanObjectCache 中加载 Bean 对象。如果还是空，则可以断定 beanInstance 一定是 FactoryBean 类型，则委托 #getObjectFromFactoryBean(FactoryBean<?> factory, String beanName, boolean shouldPostProcess) 方法，进行处理，使用 FactoryBean 获得 Bean 对象。</li>
 * </ul>
 * <p>
 * 所以实际上，#getObjectForBeanInstance(...) 方法的重心，就是使用 FactoryBean 对象，获得( 或者创建 )其 Bean 对象，即调用 #getObjectFromFactoryBean(...) 方法。
 * <h3>2 getObjectFromFactoryBean</h3>
 * 从上面可以看出， #getObjectForBeanInstance(Object beanInstance, String name, String beanName,RootBeanDefinition mbd) 方法，分成两种情况：
 * <ul>
 * <li>第一种，当该实例对象为<b>非 FactoryBean 类型</b>，直接返回给定的 Bean 实例对象 beanInstance 。</li>
 * <li>第二种，当该实例对象为FactoryBean 类型，从 FactoryBean ( beanInstance ) 中，获取 Bean 实例对象。</li>
 * </ul>
 * 第二种，通过 {@link com.java.spring.xml.FactoryBeanRegistrySupport#getObjectFromFactoryBean(FactoryBean, String, boolean)}方法来实现。
 * <p>
 * 主要流程如下：
 * <ul>
 * <li>若为单例且单例 Bean 缓存中存在 beanName ，则 <1> 进行后续处理（跳转到下一步），否则，则 <2> 从 FactoryBean 中获取 Bean 实例对象。   </li>
 * <li><1.1> 首先，获取锁。其实我们在前面篇幅中发现了大量的同步锁，锁住的对象都是 this.singletonObjects，主要是因为在单例模式中必须要保证全局唯一。</li>
 * <li><1.2> 然后，从 factoryBeanObjectCache 缓存中获取实例对象 object 。若 object 为空，则调用 {@link com.java.spring.xml.FactoryBeanRegistrySupport#doGetObjectFromFactoryBean(FactoryBean, String)}方法，从 FactoryBean 获取对象，其实内部就是调用 FactoryBean#getObject() 方法。
 * 在 x> 处，可以看到，调用 FactoryBean#getObject() 方法，获取 Bean 对象</li>
 * <li><1.3> 如果需要后续处理( shouldPostProcess = true )，则进行进一步处理，步骤如下：
 * <ul>
 * <li>若该 Bean 处于创建中（#isSingletonCurrentlyInCreation(String beanName) 方法返回 true ），则返回非处理的 Bean 对象，而不是存储它。</li>
 * <li>调用 #beforeSingletonCreation(String beanName) 方法，进行创建之前的处理。默认实现将该 Bean 标志为当前创建的。</li>
 * <li>调用 #postProcessObjectFromFactoryBean(Object object, String beanName) 方法，对从 FactoryBean 获取的 Bean 实例对象进行后置处理。详细解析，</li>
 * <li>调用 #afterSingletonCreation(String beanName) 方法，进行创建 Bean 之后的处理，默认实现是将该 bean 标记为不再在创建中。</li>
 * </ul>
 * </li>
 * <li><1.4> 最后，加入到 factoryBeanObjectCache 缓存中。</li>
 * </ul>
 * <h3>该方法应该就是创建 Bean 实例对象中的核心方法之一了。这里我们关注三个方法：</h3>
 * <ul>
 * <li>#beforeSingletonCreation(String beanName)</li>
 * <li>#afterSingletonCreation(String beanName)</li>
 * <li>#postProcessObjectFromFactoryBean(Object object, String beanName)</li>
 * </ul>
 * <h2>isSingletonCurrentlyInCreation</h2>
 * 可能有小伙伴觉得前面两个方法不是很重要，LZ 可以肯定告诉你，这两方法是非常重要的操作，因为他们记录着 Bean 的加载状态，是检测当前 Bean 是否处于创建中的关键之处，对解决 Bean 循环依赖起着关键作用。
 * <ul>
 * <li>{@link com.java.spring.xml.FactoryBeanRegistrySupport#beforeSingletonCreation(String)} 方法，用于添加标志，当前 bean 正处于创建中</li>
 * <li> {@link com.java.spring.xml.FactoryBeanRegistrySupport#afterSingletonCreation(String)}方法，用于移除标记，当前 Bean 不处于创建中。</li>
 * </ul>
 * isSingletonCurrentlyInCreation(String beanName) 方法，是用于检测当前 Bean 是否处于创建之中
 * <p>
 * 至此，从缓存中获取 Bean 对象过程已经分析完毕了。
 * 如果从单例缓存中没有获取到单例 Bean ，则 Spring 是如何处理的{@link DependedInjectBean#singleton()}
 * @author xuweizhi
 * @date 2019/03/24 12:32
 */
public class SingletonBean {

    // 从缓存中或者实例工厂中获取 Bean 对象
    // Eagerly check singleton cache for manually registered singletons.
    //    Object sharedInstance = getSingleton(beanName);
    //if (sharedInstance != null && args == null) {
    //        if (logger.isTraceEnabled()) {
    //            if (isSingletonCurrentlyInCreation(beanName)) {
    //                logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
    //                        "' that is not fully initialized yet - a consequence of a circular reference");
    //            } else {
    //                logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
    //            }
    //        }
    //        // 完成 FactoryBean 的相关处理，并用来获取 FactoryBean 的处理结果
    //        bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
    //    }

}
