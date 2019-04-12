package com.java.spring.bean;

import com.java.spring.xml.dependcy.RootBeanDefinition;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

/**
 * 在{@link com.java.spring.xml.AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}方法中
 * 有一个核心方法没有讲到， #createBean(String beanName, RootBeanDefinition mbd, Object[] args) 方法
 * <ul>
 * <li>该方法定义在 AbstractBeanFactory 中，其含义是根据给定的 BeanDefinition 和 args 实例化一个 Bean 对象。</li>
 * <li>如果该 BeanDefinition 存在父类，则该 BeanDefinition 已经合并了父类的属性。</li>
 * <li>所有 Bean 实例的创建，都会委托给该方法实现。</li>
 * <li>该方法接受三个方法参数：
 * <ul>
 * <li>beanName ：bean 的名字。</li>
 * <li>mbd ：已经合并了父类属性的（如果有的话）BeanDefinition 对象。</li>
 * <li>args ：用于构造函数或者工厂方法创建 Bean 实例对象的参数。</li>
 * </ul>
 * </li>
 * </ul>
 * <h2>createBean 默认实现</h2>
 * 该抽象方法的默认实现是在类 AbstractAutowireCapableBeanFactory 中实现，
 * {@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 * <h3>AOP在此实现</h3>
 * 如果代理对象不为空，则直接返回代理对象，这一步骤有非常重要的作用，Spring 后续实现 AOP 就是基于这个地方判断的
 * 该方法内{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd)}
 * before 为实例化前的后处理器应用，after 为实例化后的后处理器应用。
 * <h3>doCreateBean</h3>
 * 用户最终创建bean
 * {@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])}
 * 以下方法都是基于createBeanInstance（）内部代码
 * {@link #createBeanInstance()}
 * <h3>{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])} </h3>
 * 对于 #createBeanInstance(...) 方法而言，他就是选择合适实例化策略来为 bean 创建实例对象，具体的策略有：
 * <ul>
 * <li>Supplier 回调方式{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#obtainFromSupplier(Supplier, String)} </li>
 * <li>工厂方法初始化{@link com.java.spring.xml.ConstructorResolver#instantiateUsingFactoryMethod(String, RootBeanDefinition, Object[])} </li>
 * <li>构造函数自动注入初始化{@link com.java.spring.xml.ConstructorResolver#autowireConstructor(String, RootBeanDefinition, Constructor[], Object[])}</li>
 * <li>默认构造函数注入。</li>
 * </ul>
 * 其中，工厂方法初始化和构造函数自动注入初始化两种方式最为复杂，主要是因为构造函数和构造参数的不确定性，
 * Spring 需要花大量的精力来确定构造函数和构造参数，如果确定了则好办，直接选择实例化策略即可。
 * <p>
 * 当然，在实例化的时候会根据是否有需要覆盖或者动态替换掉的方法，因为存在覆盖或者织入的话需要创建动态代理
 * 将方法织入，这个时候就只能选择 CGLIB 的方式来实例化，否则直接利用反射的方式即可，方便快捷。
 * <p>
 * 到这里 #createBeanInstance(...) 的过程就已经分析完毕了，下篇介绍 #doCreateBean(...) 方法中的第
 * 二个过程：循环依赖的处理。其实，在整个 bean 的加载过程中都涉及到了循环依赖的处理，所以下面分析并不是仅
 * 仅只针对 #doCreateBean(...) 方法中的循环依赖处理，而是 Spring 在整个 IoC 体系中是如何解决循环依赖的。
 * <p>
 * 现在完成了创建Bean工，加下来分析{@link FillBeanAttribute}
 *
 * @author xuweizhi
 * @date 2019/03/24 15:56
 */
public class CreateBean {

    /**
     * 我们关注创建 bean 过程中的第一个步骤：实例化 bean，对应的方法为 {@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])}
     * 用于实例化 Bean 对象。它会根据不同情况，选择不同的实例化策略来完成 Bean 的初始化，主要包括：
     * <ul>
     * <li>Supplier 回调：#obtainFromSupplier(final String beanName, final RootBeanDefinition mbd) 方法。/li>
     * <li>工厂方法初始化：#instantiateUsingFactoryMethod(String beanName, RootBeanDefinition mbd, @Nullable Object[] explicitArgs) 方法。</li>
     * <li>构造函数自动注入初始化：#autowireConstructor(final String beanName, final RootBeanDefinition mbd, Constructor<?>[] chosenCtors, final Object[] explicitArgs) 方法。</li>
     * <li>默认构造函数注入：#instantiateBean(final String beanName, final RootBeanDefinition mbd) 方法。</li>
     * </ul>
     */
    public void createBeanInstance() {

    }

    /**
     * {@link org.springframework.beans.BeanUtils#instantiateClass(Constructor, Object...)}
     * <1> 首先，是获取实例化 Bean 的策略 InstantiationStrategy 对象。
     * <2> 然后，调用其 #instantiate(RootBeanDefinition bd, String beanName, BeanFactory owner,
     * Constructor<?> ctor, Object... args) 方法，该方法在 SimpleInstantiationStrategy 中实现。
     * {@link com.java.spring.xml.SimpleInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, String, BeanFactory, Constructor, Object...)}
     * {@link org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, String, BeanFactory, Constructor, Object...)}
     * {@link com.java.spring.xml.SimpleInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, String, BeanFactory)}
     */
    public void instantiateClass() {
        //Assert.notNull(ctor, "Constructor must not be null");
        //try {
        //    // 设置构造方法，可访问
        //    ReflectionUtils.makeAccessible(ctor);
        //    // 使用构造方法，创建对象
        //    return (KotlinDetector.isKotlinReflectPresent() && KotlinDetector.isKotlinType(ctor.getDeclaringClass()) ?
        //            KotlinDelegate.instantiateClass(ctor, args) : ctor.newInstance(args));
        //    // 各种异常的翻译，最终统一抛出 BeanInstantiationException 异常
        //} catch (InstantiationException ex) {
        //    throw new BeanInstantiationException(ctor, "Is it an abstract class?", ex);
        //} catch (IllegalAccessException ex) {
        //    throw new BeanInstantiationException(ctor, "Is the constructor accessible?", ex);
        //} catch (IllegalArgumentException ex) {
        //    throw new BeanInstantiationException(ctor, "Illegal arguments for constructor", ex);
        //} catch (InvocationTargetException ex) {
        //    throw new BeanInstantiationException(ctor, "Constructor threw exception", ex.getTargetException());
        //}
        //}


    }

    // CglibSubclassingInstantiationStrategy.java
    //public Object instantiate(@Nullable Constructor<?> ctor, Object... args) {
    //    // 通过 Cglib 创建一个代理类
    //    Class<?> subclass = createEnhancedSubclass(this.beanDefinition);
    //    Object instance;
    //    // 没有构造器，通过 BeanUtils 使用默认构造器创建一个bean实例
    //    if (ctor == null) {
    //        instance = BeanUtils.instantiateClass(subclass);
    //    } else {
    //        try {
    //            // 获取代理类对应的构造器对象，并实例化 bean
    //            Constructor<?> enhancedSubclassConstructor = subclass.getConstructor(ctor.getParameterTypes());
    //            instance = enhancedSubclassConstructor.newInstance(args);
    //        } catch (Exception ex) {
    //            throw new BeanInstantiationException(this.beanDefinition.getBeanClass(),
    //                    "Failed to invoke constructor for CGLIB enhanced subclass [" + subclass.getName() + "]", ex);
    //        }
    //    }
    //    // SPR-10785: set callbacks directly on the instance instead of in the
    //    // enhanced class (via the Enhancer) in order to avoid memory leaks.
    //    // 为了避免 memory leaks 异常，直接在 bean 实例上设置回调对象
    //    Factory factory = (Factory) instance;
    //    factory.setCallbacks(new Callback[] {NoOp.INSTANCE,
    //            new LookupOverrideMethodInterceptor(this.beanDefinition, this.owner),
    //            new ReplaceOverrideMethodInterceptor(this.beanDefinition, this.owner)});
    //    return instance;
    //}
}
