package com.java.spring.bean;

import com.java.spring.xml.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypeConverter;

/**
 * {@link BeanTypeConvert}
 * <p>
 * 在实例化 Bean 阶段，我们从 BeanDefinition 得到的并不是我们最终想要的 Bean 实例，而是 BeanWrapper 实例
 * {@link AbstractAutowireCapableBeanFactory#doCreateBean}
 * <p>
 * 所以这里 BeanWrapper 是一个从 BeanDefinition 到 Bean 直接的中间产物，我们可以称它为”低级 bean“。在一般情况下，
 * 我们不会在实际项目中用到它。BeanWrapper 是 Spring 框架中重要的组件类，它就相当于一个代理类，Spring 委托 BeanWrapper
 * 完成 Bean 属性的填充工作。在 Bean 实例被 InstantiationStrategy 创建出来后，Spring 容器会将 Bean 实例通过
 * BeanWrapper 包裹起来，代码实现{@link BeanWrapperImpl}。
 * <p>
 * 就 BeanWrapper 来进行分析说明，先看整体的结构：{@link #bean}
 *
 * <h3>{@link PropertyAccessor}</h3>
 * 可以访问属性的通用型接口（例如对象的 bean 属性或者对象中的字段），作为 BeanWrapper 的基础接口。
 * <ul>
 * <li>#isReadableProperty(String propertyName) 方法：判断指定 property 是否可读，是否包含 getter 方法。</li>
 * <li>#isWritableProperty(String propertyName) 方法：判断指定 property 是否可写,是否包含 setter 方法。</li>
 * <li>#getPropertyType(...) 方法：获取指定 propertyName 的类型</li>
 * <li>#getPropertyType(...) 方法：获取指定 propertyName 的类型</li>
 * </ul>
 * <h3>{@link PropertyEditorRegistry}</h3>
 * 用于注册 JavaBean 的 PropertyEditors，对 PropertyEditorRegistrar 起核心作用的中心接口。由 BeanWrapper 扩展，
 * BeanWrapperImpl 和 DataBinder 实现。
 * <p>
 * 根据接口提供的方法，PropertyEditorRegistry 就是用于 PropertyEditor 的注册和发现，而 PropertyEditor 是 Java
 * 内省里面的接口，用于改变指定 property 属性的类型。
 * <h3>{@link TypeConverter}</h3>
 * 定义类型转换的接口，通常与 PropertyEditorRegistry 接口一起实现（但不是必须），但由于 TypeConverter 是基于线程不安全的
 * PropertyEditors ，因此 TypeConverters 本身也不被视为线程安全。
 * <p>
 * 这里解释下，在 Spring 3 后，不在采用 PropertyEditors 类作为 Spring 默认的类型转换接口，而是采用 ConversionService
 * 体系，但 ConversionService 是线程安全的，所以在 Spring 3 后，如果你所选择的类型转换器是 ConversionService 而不是
 * PropertyEditors 那么 TypeConverters 则是线程安全的。
 *
 * <h2>BeanWrapper 继承上述三个接口，那么它就具有三重身份：</h2>
 * <ol>
 * <li>属性编辑器</li>
 * <li>属性编辑器注册表</li>
 * <li>类型转换器</li>
 * </ol>
 * <p>
 * BeanWrapper 继承 ConfigurablePropertyAccessor 接口，该接口除了继承上面介绍的三个接口外还集成了 Spring 的 ConversionService 类型转换体系。
 * #setConversionService(ConversionService conversionService) 和 #getConversionService() 方法，则是用于集成
 * Spring 的 ConversionService 类型转换体系。
 * <h2>BeanWrapper</h2>
 * Spring 的 低级 JavaBean 基础结构的接口，一般不会直接使用，而是通过 BeanFactory 或者 DataBinder 隐式使用。
 * 它提供分析和操作标准 JavaBeans 的操作：获取和设置属性值、获取属性描述符以及查询属性的可读性/可写性的能力。
 * <h3>下面几个方法比较重要：</h3>
 * <ul>
 * <li>#getWrappedInstance() 方法：获取包装对象的实例。</li>
 * <li>#getWrappedClass() 方法：获取包装对象的类型。</li>
 * <li>#getPropertyDescriptors() 方法：获取包装对象所有属性的 PropertyDescriptor 就是这个属性的上下文。</li>
 * <li>#getPropertyDescriptor(String propertyName) 方法：获取包装对象指定属性的上下文。</li>
 * </ul>
 * <h3>BeanWrapperImpl</h3>
 * BeanWrapper 接口的默认实现，用于对Bean的包装，实现上面接口所定义的功能很简单包括设置获取被包装的对象，获取被包装bean的属性描述器.
 * <h3>小结</h3>
 * BeanWrapper 体系相比于 Spring 中其他体系是比较简单的，它作为 BeanDefinition 向 Bean 转换过程中的中间产物，承载了 Bean
 * 实例的包装、类型转换、属性的设置以及访问等重要作用。
 * {@link BeanInstantiationStrategy}
 * @date 2019/03/27 13:16
 */
public class BeanWrapper {

    /**
     * <image src="../../../../../resources/static/spring/BeanWrapper.png"></image>
     */
    void bean() {

    }
}
