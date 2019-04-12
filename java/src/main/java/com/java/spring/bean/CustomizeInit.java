package com.java.spring.bean;

import com.java.spring.xml.dependcy.RootBeanDefinition;
import org.springframework.beans.factory.InitializingBean;

/**
 * Spring 在 bean 初始化时进行三个检测扩展，也就是说我们可以对 bean 进行三个不同的定制化处理，Aware详情见{@link AwareBean}
 * 和后置处理{@link BeanPostProcessor}，这里分析 InitializingBean 接口和 init-method 方法；
 * <h2>1. InitializingBean</h2>
 * Spring 的 org.springframework.beans.factory.InitializingBean 接口，为 bean 提供了定义初始化方法的方式，
 * 它仅包含了一个方法：#afterPropertiesSet() 。
 * <p>
 * 该方法在 BeanFactory 设置完了所有属性之后被调用,该方法允许 bean 实例设置了所有 bean 属性时执行初始化工作，如果该过程出现了错误则需要抛出异常。
 * <p>
 * Spring 在完成实例化后，设置完所有属性，进行 “Aware 接口” 和 “BeanPostProcessor 前置处理”之后，会接着检测当前 bean 对象是
 * 否实现了 InitializingBean 接口。如果是，则会调用其 #afterPropertiesSet() 方法，进一步调整 bean 实例对象的状态。
 * <h3>1.1 示例</h3>
 * {@link InitializingBeanTest}
 * <h3>1.2 invokeInitMethods</h3>
 * 上面提到 bean 初始化阶段{@link com.java.spring.xml.AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)}，
 * Spring 容器会主动检查当前 bean 是否已经实现了 InitializingBean 接口，如果实现了，则会掉用其 #afterPropertiesSet() 方法。这个主
 * 动检查、调用的动作是由 #invokeInitMethods(String beanName, final Object bean, @Nullable RootBeanDefinition mbd) 方法
 * 来完成的。
 * <ul>
 * <li>首先，检测当前 bean 是否实现了 InitializingBean 接口，如果实现了则调用其 #afterPropertiesSet() 方法。</li>
 * <li>然后，再检查是否也指定了 init-method，如果指定了则通过反射机制调用指定的 init-method 方法。</li>
 * </ul>
 * 虽然该接口为 Spring 容器的扩展性立下了汗马功劳，但是如果真的让我们的业务对象来实现这个接口就显得不是那么的友好了，Spring 的一个核心理念就
 * 是无侵入性，但是如果我们业务类实现这个接口就显得 Spring 容器具有侵入性了。所以 Spring 还提供了另外一种实现的方式：init-method 方法
 * <h3>2. init-method</h3>
 * 在分析分析 bean> 标签解析过程中，我们提到了有关于 init-method 属性,该属性用于在 bean 初始化时指定执行方法，可以用来替代实现 InitializingBean 接口。
 * {@link InitializingBeanTest#afterPropertiesSet()}方法设置init标签
 * 完全可以达到和 InitializingBean 一样的效果，而且在代码中我们没有看到丝毫 Spring 侵入的现象。所以通过 init-method 我们可以使用业务对象
 * 中定义的任何方法来实现 bean 实例对象的初始化定制化，而不再受制于 InitializingBean的 #afterPropertiesSet() 方法。同时我们可以使用 beans>
 * 标签的 default-init-method 属性来统一指定初始化方法，这样就省了需要在每个 <bean> 标签中都设置 init-method 这样的繁琐工作了。比如在
 * default-init-method 规定所有初始化操作全部以 initBean() 命名。
 * Bean的生命周期{@link BeanLifeCycle}
 *
 * @author xuweizhi
 * @date 2019/03/25 23:26
 */
public class CustomizeInit {

    public static void main(String[] args) {
        //执行结果 name: chenssy 2 号
       /* InitializingBeanTest test = (InitializingBeanTest) factory.getBean("initializingBeanTest");
        System.out.println("name ：" + test.getName());*/
    }

}

class InitializingBeanTest implements InitializingBean {

    private String name;

    /**
     * 执行初始化的时候，可以修改值
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanTest initializing...");
        this.name = "chenssy 2 号";
    }
    //配置如下
    //<bean id="initializingBeanTest" class="org.springframework.core.test.InitializingBeanTest">
    //    <property name="name" value="chenssy 1 号"/>
    //</bean>

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * init-method指定的方法，方法可任意定制
     * 其输出结果是name chenssy 3 号
     */
    public void setOtherName() {
        System.out.println("InitializingBeanTest setOtherName...");
        this.name = "chenssy 3 号";
    }
    //也可以在init-method方法中设置
    //init-method方式改变注入bean的值
    //<bean id="initializingBeanTest" class="org.springframework.core.test.InitializingBeanTest"
    //        init-method="setOtherName">
    //    <property name="name" value="chenssy 1 号"/>
    //</bean>
}