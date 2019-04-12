package com.java.spring.bean;


import com.java.spring.util.ConversionServiceFactoryBean;
import com.java.spring.xml.AbstractAutowireCapableBeanFactory;
import com.java.spring.xml.GenericConversionService;

/**
 * {@link PropertyOverrideConfigurer}
 * <h1>Spring 类型转换体系</h1>
 * ﻿我们知道不管 Bean 对象里面的属性时什么类型，他们都是通过 XML 、Properties 或者其他方式来配置这些属性对象类型的。在 Spring
 * 容器加载过程中，<b>这些属性都是以String 类型加载进容器的，但是最终都需要将这些 String 类型的属性转换 Bean 对象属性所对应真正的
 * 类型，要想完成这种由字符串到具体对象的转换</b>，就需要这种转换规则相关的信息，而这些信息以及转换过程由 Spring 类型转换体系来完成。
 * <p>
 * 们依然以 xml 为例，在 Spring 容器加载阶段，容器将 xml 文件中定义的 bean> 解析为 BeanDefinition，BeanDefinition 中存储着
 * 我们定义一个 bean 需要的所有信息，包括属性，这些属性是以 String 类型的存储的。当用户触发 Bean 实例化阶段时，Spring 容器会将这些
 * 属性转换为这些属性真正对应的类型。我们知道在 Bean 实例化阶段，属性的注入是在实例化 Bean 阶段的属性注入阶段，即 AbstractAutowireCapableBeanFactory
 * 的 #populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw) 方法。
 * <p>
 * 在{@link AbstractAutowireCapableBeanFactory#populateBean}方法中，会将 BeanDefinition 中定
 * 义的属性值翻译为 PropertyValue ，然后调用 #applyPropertyValues方法，进行属性应用。其中 PropertyValue 用于保存单个 bean
 * 属性的信息和值的对象。
 * <p>
 * 在 #applyPropertyValues 方法中，会调用 {@link AbstractAutowireCapableBeanFactory#convertForProperty}
 * 行属性转换.
 * <p>
 * 其 UML 类图如下：
 * <p>
 * {@link #image()};
 *
 * <ul>
 * <li>ConfigurableConversionService：ConversionService 的配置接口，继承 ConversionService 和 ConverterRegistry
 * 两个接口，用于合并他们两者的操作，以便于通过 add 和 remove 的方式添加和删除转换器。</li>
 * <li>GenericConversionService：ConversionService 接口的基础实现，适用于大部分条件下的转换工作，通过 ConfigurableConversionService
 * 接口间接地将 ConverterRegistry 实现为注册 API 。</li>
 * <li>DefaultConversionService：ConversionService 接口的默认实现，适用于大部分条件下的转换工作。</li>
 * </ul>
 * 回归到 TypeConverterDelegate 的#convertIfNecessary方法，在该方法中，如果没有自定义的属性编辑器，则调用 ConversionService
 * 接口的 convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType)，方法定义：
 * <ul>
 * <li>source ：要转换的源对象，可以为 null 。</li>
 * <li>sourceType：source 的类型的上下文，如果 source 为 null ，则可以为 null 。</li>
 * <li>targetType ：source 要转换的类型的上下文。</li>
 * </ul>
 * #convert(...) 方法，将给定的源对象 source 转换为指定的 targetType 。TypeDescriptors 提供有关发生转换的源位置和目标位置的附加上下文，
 * 通常是对象字段或属性位置。该方法由子类 {@link GenericConversionService#convert} 实现：
 * <h2>转换器的注入</h2>
 * {@link GenericConversionService#addConverter}
 * <p>
 * 实际上，大体的调用流是如下：TypeConverterSupport => ConversionService => Converter
 * <h2>自定义类型转换器</h2>
 * ConversionService 有三个子类，每个子类针对不同的类型转换：
 * <ul>
 * <li>Converter<S,T>: 将 S 类型对象转为 T 类型对象。</li>
 * <li>GenericConverter: 会根据源类对象及目标类对象所在的宿主类中的上下文信息进行类型转换。</li>
 * <li>ConverterFactory: 将相同系列多个 “同质” Converter 封装在一起。如果希望将一种类型的对象转换为另一种类型及其子类的对象(例如将 String 转换为
 * Number 及 Number 子类(Integer、Long、Double 等)对象)可使用该转换器工厂类。</li>
 * </ul>
 * <b>那么如何自定义类型转换器？分两步走：</b>
 * <ul>
 * <li>实现 Converter / GenericConverter / ConverterFactory 接口</li>
 * <li>将该类注册到 ConversionServiceFactoryBean 中。</li>
 * </ul>
 * <p>
 * ConversionServiceFactoryBean 实现了 InitializingBean 接口实现 #afterPropertiesSet() 方法，我们知道在 Bean 实例化 Bean 阶段，
 * Spring 容器会检查当前 Bean 是否实现了 InitializingBean 接口，如果是则执行相应的初始化方法。
 * {@link ConversionServiceFactoryBean#afterPropertiesSet()}
 *
 * <h2>示例</h2>
 * {@link #example()}
 *
 * {@link  BeanWrapper}
 * @author xuweizhi
 * @date 2019/03/27 10:49
 */
public class BeanTypeConvert {

    /**
     * <image src="../../../../../resources/static/spring/Conversion.png"></image>
     */
    public void image() {

    }

    public void example(){
        // class StudentConversionService implements Converter<String, StudentService>{
        //
        //    @Override
        //    public StudentService convert(String source) {
        //        if(StringUtils.hasLength(source)){
        //            String[] sources = source.split("#");
        //
        //            StudentService studentService = new StudentService();
        //            studentService.setAge(Integer.parseInt(sources[0]));
        //            studentService.setName(sources[1]);
        //
        //            return studentService;
        //        }
        //        return null;
        //    }
        //
        //}

        //配置
        //<bean id="conversionService"
        //          class="org.springframework.context.support.ConversionServiceFactoryBean">
        //    <property name="converters">
        //        <set>
        //            <ref bean="studentConversionService"/>
        //        </set>
        //     </property>
        // </bean>
        //
        //<bean id="studentConversionService" class="org.springframework.core.conversion.StudentConversionService"/>
        //
        //<bean id="student" class="org.springframework.core.conversion.Student">
        //    <property name="studentService" value="18#chenssy"/>
        //</bean>
    }
}



