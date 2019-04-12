package com.java.spring.bean;

import org.springframework.beans.BeanWrapper;

/**
 * <h1>IoC 之加载 Bean：总结</h1>
 * bean 的初始化节点，由第一次(显式或者隐式)调用 #getBean(...) 方法来开启，所以我们从这个方法开始。
 * 详情见{@link DependedInjectBean}
 * <h2>#doGetBean(...) 方法，可以分为以下几个过程：</h2>
 * <ol>
 * <li>转换 beanName 。因为我们调用 #getBean(...) 方法传入的 name 并不一定就是 beanName，
 * 可以传入 aliasName，FactoryBean，所以这里需要进行简单的转换过程。</li>
 * <li>尝试从缓存中加载单例 bean 。</li>
 * <li>bean 的实例化。</li>
 * <li>原型模式的依赖检查。因为 Spring 只会解决单例模式的循环依赖，对于原型模式的循环依赖都
 * 是直接抛出 BeanCurrentlyInCreationException 异常。</li>
 * <li>尝试从 parentBeanFactory 获取 bean 实例。如果 parentBeanFactory != null && !
 * containsBeanDefinition(beanName) 则尝试从 parentBeanFactory 中获取 bean 实例对象，
 * 因为 !containsBeanDefinition(beanName) 就意味着定义的 xml 文件中没有 beanName 相应
 * 的配置，这个时候就只能从 parentBeanFactory 中获取。</li>
 * <li>获取 RootBeanDefinition，并对其进行合并检查。从缓存中获取已经解析的 RootBeanDefinition 。
 * 同时，如果父类不为 null 的话，则会合并父类的属性。</li>
 * <li>依赖检查。某个 bean 依赖其他 bean ，则需要先加载依赖的 bean。</li>
 * <li>对不同的 scope 进行处理。</li>
 * <li>类型转换处理。如果传递的 requiredType 不为 null，则需要检测所得到 bean 的类型是否与该 requiredType
 * 一致。如果不一致则尝试转换，当然也要能够转换成功，否则抛出 BeanNotOfRequiredTypeException 异常。</li>
 * </ol>
 * <h2>1. 从缓存中获取 bean</h2>
 * <p>
 * Spring 中根据 scope 可以将 bean 分为以下几类：singleton、prototype 和 其他，这样分的原因在于
 * Spring 在对不同 scope 处理的时候是这么处理的：
 *
 * <ul>
 * <li>singleton ：在 Spring 的 IoC 容器中只存在一个对象实例，所有该对象的引用都共享这个实例。
 * Spring 容器只会创建该 bean 定义的唯一实例，这个实例会被保存到缓存中，并且对该bean的所有后续请
 * 求和引用都将返回该缓存中的对象实例。</li>
 * <li>prototype ：每次对该bean的请求都会创建一个新的实例</li>
 * <li>其它
 * <ul>
 * <li>request：每次 http 请求将会有各自的 bean 实例。</li>
 * <li>session：在一个 http session 中，一个 bean 定义对应一个 bean 实例。</li>
 * <li>global session：在一个全局的 http session 中，一个 bean 定义对应一个 bean 实例。 </li>
 * </ul>
 * </li>
 * </ul>
 * <h2>2. 创建 bean 实例对象</h2>
 * 如果缓存中没有，也没有 parentBeanFactory ，则会调用 #createBean方法，创建 bean 实例。该方法主
 * 要是在处理不同 scope 的 bean 的时候进行调用。详情看{@link CreateBean}
 * <p>
 * 该方法是定义在 AbstractBeanFactory 中的抽象方法，其含义是根据给定的 BeanDefinition 和 args 实
 * 例化一个 bean 对象。如果该 BeanDefinition 存在父类，则该 BeanDefinition 已经合并了父类的属性。
 * 所有 Bean 实例的创建都会委托给该方法实现。
 * <p>
 * 该抽象方法的默认实现是在类 AbstractAutowireCapableBeanFactory 中实现，该方法其实只是做一些检查
 * 和验证工作，真正的初始化工作是由 #doCreateBean() 方法来实现.
 *
 * <h3>#doCreateBean(...) 方法，是创建 bean 实例的核心方法，它的整体思路是：</h3>
 * <ol>
 * <li>如果是单例模式，则清除 factoryBeanInstanceCache 缓存，同时返回 BeanWrapper 实例对象，当然如果存在。</li>
 * <li>如果缓存中没有 BeanWrapper 或者不是单例模式，则调用 #createBeanInstance(...) 方法，实例化
 * bean，主要是将 BeanDefinition 转换为 BeanWrapper 。</li>
 * <li>MergedBeanDefinitionPostProcessor 的应用。</li>
 * <li>单例模式的循环依赖处理。</li>
 * <li>调用 #populateBean方法，进行属性填充。将所有属性填充至 bean 的实例中。</li>
 * <li>调用 #initializeBean方法，初始化 bean 。</li>
 * <li>依赖检查。</li>
 * <li>注册 DisposableBean 。</li>
 * </ol>
 * <h2>2.1 实例化bean</h3>
 * 如果缓存中没有 BeanWrapper 实例对象或者该 bean 不是 singleton，则调用 #createBeanInstance(...) 方法。
 * 创建 bean 实例。该方法主要是根据参数 BeanDefinition、args[] 来调用构造函数实例化 bean 对象。过程较为复杂，
 * 详情见{@link #createBeanInstance()}
 * <h2>2.2 属性填充</h2>
 * 属性填充其实就是将 BeanDefinition 的属性值赋值给 BeanWrapper 实例对象的过程。在填充的过程需要根据注入的类型不同来区
 * 分是根据类型注入还是名字注入，当然在这个过程还会涉及循环依赖的问题的。
 * <p>
 * 详情见{@link #populateBean}
 * <h2>2.3 初始化 bean</h2>
 * 初始化 bean 为 #createBean(...) 方法的最后一个过程,详情见
 * {@link #initializeBean()}
 * <h2>3. 从 bean 实例中获取对象</h2>
 * 无论是从单例缓存中获取的 bean 实例 还是通过 #createBean(...) 方法来创建的 bean 实例，最终都会调用 #getObjectForBeanInstance(...)
 * 方法来根据传入的 bean 实例获取对象，按照 Spring 的传统，该方法也只是做一些检测工作，真正的实现逻辑是委托给 #getObjectFromFactoryBean(...) 方法来实现。
 * 详情见{@link #getObjectFromFactoryBean()}
 * * @author xuweizhi
 *
 * @date 2019/03/25 22:50
 */
public class BeanSummery {


    /**
     * AbstractAutowireCapableBeanFactory.java
     * <ul>
     * <li>1. 处，如果存在 Supplier 回调，则调用 #obtainFromSupplier方法，进行初始化。</li>
     * <li>2. 处，如果存在工厂方法，则使用工厂方法进行初始化。</li>
     * <li>3. 处，首先判断缓存，如果缓存中存在，即已经解析过了，则直接使用已经解析了的。根据 constructorArgumentsResolved 参数来判断：
     * <ul>
     * <li>3.1 处，是使用构造函数自动注入，即调用 #autowireConstructor方法。</li>
     * <li>3.2 处，还是默认构造函数，即调用 #instantiateBean方法</li>
     * </ul>
     * </li>
     * <li>4. 处，如果缓存中没有，则需要先确定到底使用哪个构造函数来完成解析工作，因为一个类有多个构造函数，
     * 每个构造函数都有不同的构造参数，所以需要根据参数来锁定构造函数并完成初始化。
     * <ul>
     * <li>4.1 处，如果存在参数，则使用相应的带有参数的构造函数，即调用 #autowireConstructor 方法。</li>
     * <li>4.2 处，否则，使用默认构造函数，即调用 #instantiateBean方法。</li>
     * </ul>
     * </li>
     * </ul>
     * 其实核心思想还是在于根据不同的情况执行不同的实例化策略，主要是包括如下四种策略：
     * <ol>
     * <li>Supplier 回调</li>
     * <li>#instantiateUsingFactoryMethod(...) 方法，工厂方法初始化</li>
     * <li>#autowireConstructor(...) 方法，构造函数自动注入初始化</li>
     * <li>#instantiateBean(...) 方法，默认构造函数注入</li>
     * </ol>
     * 其实无论哪种策略，他们的实现逻辑都差不多：确定构造函数和构造方法，然后实例化。只不过相对于 Supplier 回调和默认构造函
     * 数注入而言，工厂方法初始化和构造函数自动注入初始化会比较复杂，因为他们构造函数和构造参数的不确定性，Spring 需要花大量
     * 的精力来确定构造函数和构造参数，如果确定了则好办，直接选择实例化策略即可。当然在实例化的时候会根据是否有需要覆盖或者动
     * 态替换掉的方法，因为存在覆盖或者织入的话需要创建动态代理将方法织入，这个时候就只能选择 CGLIB 的方式来实例化，否则直接
     * 利用反射的方式即可。
     */
    public BeanWrapper createBeanInstance() {
        //// Make sure bean class is actually resolved at this point.
        //// 解析 bean ，将 bean 类名解析为 class 引用。
        //Class<?> beanClass = resolveBeanClass(mbd, beanName);
        //
        //if (beanClass != null && !Modifier.isPublic(beanClass.getModifiers()) && !mbd.isNonPublicAccessAllowed()) { // 校验
        //    throw new BeanCreationException(mbd.getResourceDescription(), beanName,
        //            "Bean class isn't public, and non-public access not allowed: " + beanClass.getName());
        //}
        //
        //// 1. 如果存在 Supplier 回调，则使用给定的回调方法初始化策略
        //Supplier<?> instanceSupplier = mbd.getInstanceSupplier();
        //if (instanceSupplier != null) {
        //    return obtainFromSupplier(instanceSupplier, beanName);
        //}
        //
        //// 2. 使用 FactoryBean 的 factory-method 来创建，支持静态工厂和实例工厂
        //if (mbd.getFactoryMethodName() != null)  {
        //    return instantiateUsingFactoryMethod(beanName, mbd, args);
        //}
        //
        //// 3. Shortcut when re-creating the same bean...
        //boolean resolved = false;
        //boolean autowireNecessary = false;
        //if (args == null) {
        //    // constructorArgumentLock 构造函数的常用锁
        //    synchronized (mbd.constructorArgumentLock) {
        //        // 如果已缓存的解析的构造函数或者工厂方法不为空，则可以利用构造函数解析
        //        // 因为需要根据参数确认到底使用哪个构造函数，该过程比较消耗性能，所有采用缓存机制
        //        if (mbd.resolvedConstructorOrFactoryMethod != null) {
        //            resolved = true;
        //            autowireNecessary = mbd.constructorArgumentsResolved;
        //        }
        //    }
        //}
        //// 已经解析好了，直接注入即可
        //if (resolved) {
        //    // <3.1> autowire 自动注入，调用构造函数自动注入
        //    if (autowireNecessary) {
        //        return autowireConstructor(beanName, mbd, null, null);
        //    } else {
        //        // 3.2 使用默认构造函数构造
        //        return instantiateBean(beanName, mbd);
        //    }
        //}
        //
        //// Candidate constructors for autowiring?
        //// 4 确定解析的构造函数
        //// 主要是检查已经注册的 SmartInstantiationAwareBeanPostProcessor
        //Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
        //// 4.1 有参数情况时，创建 Bean 。先利用参数个数，类型等，确定最精确匹配的构造方法。
        //if (ctors != null || mbd.getResolvedAutowireMode() == AUTOWIRE_CONSTRUCTOR ||
        //        mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args))  {
        //    return autowireConstructor(beanName, mbd, ctors, args);
        //}
        //
        //// Preferred constructors for default construction?
        //// 4.1 选择构造方法，创建 Bean 。
        //ctors = mbd.getPreferredConstructors();
        //if (ctors != null) {
        //    return autowireConstructor(beanName, mbd, ctors, null); // args = null
        //}
        //
        //// No special handling: simply use no-arg constructor.
        //// 4.2 有参数时，又没获取到构造方法，则只能调用无参构造方法来创建实例了(兜底方法)
        //return instantiateBean(beanName, mbd);
        return null;
    }


    /**
     * AbstractAutowireCapableBeanFactory.java
     * <ul>
     * <li><1> ,根据 hasInstantiationAwareBeanPostProcessors 属性来判断，是否需要在注入属性之前给 InstantiationAwareBeanPostProcessors
     * 最后一次改变 bean 的机会。此过程可以控制 Spring 是否继续进行属性填充。</li>
     * <li>统一存入到 PropertyValues 中，PropertyValues 用于描述 bean 的属性。</li>
     * <ul>
     * <li><2> ，根据注入类型( AbstractBeanDefinition#getResolvedAutowireMode() 方法的返回值 )的不同来判断：
     * <ul>
     * <li>是根据名称来自动注入（#autowireByName(...)）</li>
     * <li>还是根据类型来自动注入（#autowireByType(...)）</li>
     * </ul>
     * </li>
     * <li><3> ，进行 BeanPostProcessor 处理。</li>
     * <li><4> ，依赖检测。</li>
     * </ul>
     * <li><5> ，将所有 PropertyValues 中的属性，填充到 BeanWrapper 中。</li>
     * </ul>
     */
    protected void populateBean() {
        //// 没有实例化对象
        //if (bw == null) {
        //    // 有属性，则抛出 BeanCreationException 异常
        //    if (mbd.hasPropertyValues()) {
        //        throw new BeanCreationException(
        //                mbd.getResourceDescription(), beanName, "Cannot apply property values to null instance");
        //        // 没有属性，直接 return 返回
        //    } else {
        //        // Skip property population phase for null instance.
        //        return;
        //    }
        //}
        //
        //// <1> 在设置属性之前给 InstantiationAwareBeanPostProcessors 最后一次改变 bean 的机会
        //// Give any InstantiationAwareBeanPostProcessors the opportunity to modify the
        //// state of the bean before properties are set. This can be used, for controller,
        //// to support styles of field injection.
        //boolean continueWithPropertyPopulation = true;
        //if (!mbd.isSynthetic()  // bean 不是"合成"的，即未由应用程序本身定义
        //        && hasInstantiationAwareBeanPostProcessors()) { // 是否持有 InstantiationAwareBeanPostProcessor
        //    // 迭代所有的 BeanPostProcessors
        //    for (BeanPostProcessor bp : getBeanPostProcessors()) {
        //        if (bp instanceof InstantiationAwareBeanPostProcessor) { // 如果为 InstantiationAwareBeanPostProcessor
        //            InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
        //            // 返回值为是否继续填充 bean
        //            // postProcessAfterInstantiation：如果应该在 bean上面设置属性则返回 true，否则返回 false
        //            // 一般情况下，应该是返回true 。
        //            // 返回 false 的话，将会阻止在此 Bean 实例上调用任何后续的 InstantiationAwareBeanPostProcessor 实例。
        //            if (!ibp.postProcessAfterInstantiation(bw.getWrappedInstance(), beanName)) {
        //                continueWithPropertyPopulation = false;
        //                break;
        //            }
        //        }
        //    }
        //}
        //// 如果后续处理器发出停止填充命令，则终止后续操作
        //if (!continueWithPropertyPopulation) {
        //    return;
        //}
        //
        //// bean 的属性值
        //PropertyValues pvs = (mbd.hasPropertyValues() ? mbd.getPropertyValues() : null);
        //
        //// <2> 自动注入
        //if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_NAME || mbd.getResolvedAutowireMode() == AUTOWIRE_BY_TYPE) {
        //    // 将 PropertyValues 封装成 MutablePropertyValues 对象
        //    // MutablePropertyValues 允许对属性进行简单的操作，并提供构造函数以支持Map的深度复制和构造。
        //    MutablePropertyValues newPvs = new MutablePropertyValues(pvs);
        //    // Add property values based on autowire by name if applicable.
        //    // 根据名称自动注入
        //    if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_NAME) {
        //        autowireByName(beanName, mbd, bw, newPvs);
        //    }
        //    // Add property values based on autowire by type if applicable.
        //    // 根据类型自动注入
        //    if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_TYPE) {
        //        autowireByType(beanName, mbd, bw, newPvs);
        //    }
        //    pvs = newPvs;
        //}
        //
        //// 是否已经注册了 InstantiationAwareBeanPostProcessors
        //boolean hasInstAwareBpps = hasInstantiationAwareBeanPostProcessors();
        //// 是否需要进行【依赖检查】
        //boolean needsDepCheck = (mbd.getDependencyCheck() != AbstractBeanDefinition.DEPENDENCY_CHECK_NONE);
        //
        //// <3> BeanPostProcessor 处理
        //PropertyDescriptor[] filteredPds = null;
        //if (hasInstAwareBpps) {
        //    if (pvs == null) {
        //        pvs = mbd.getPropertyValues();
        //    }
        //    // 遍历 BeanPostProcessor 数组
        //    for (BeanPostProcessor bp : getBeanPostProcessors()) {
        //        if (bp instanceof InstantiationAwareBeanPostProcessor) {
        //            InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
        //            // 对所有需要依赖检查的属性进行后处理
        //            PropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);
        //            if (pvsToUse == null) {
        //                // 从 bw 对象中提取 PropertyDescriptor 结果集
        //                // PropertyDescriptor：可以通过一对存取方法提取一个属性
        //                if (filteredPds == null) {
        //                    filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
        //                }
        //                pvsToUse = ibp.postProcessPropertyValues(pvs, filteredPds, bw.getWrappedInstance(), beanName);
        //                if (pvsToUse == null) {
        //                    return;
        //                }
        //            }
        //            pvs = pvsToUse;
        //        }
        //    }
        //}
        //
        //// <4> 依赖检查
        //if (needsDepCheck) {
        //    if (filteredPds == null) {
        //        filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
        //    }
        //    // 依赖检查，对应 depends-on 属性
        //    checkDependencies(beanName, mbd, filteredPds, pvs);
        //}
        //
        //// <5> 将属性应用到 bean 中
        //if (pvs != null) {
        //    applyPropertyValues(beanName, mbd, bw, pvs);
        //}
    }

    /**
     * 初始化 bean 的方法其实就是三个步骤的处理，而这三个步骤主要还是根据用户设定的来进行初始化，这三个过程为：
     * <ul>
     * <li><1> 激活 Aware 方法。{@link AwareBean}</li>
     * <li><3> 后置处理器的应用。{@link BeanPostProcessor }</li>
     * <li><2> 激活自定义的 init{@link CustomizeInit} 方法。</li>
     * </ul>
     */
    public void initializeBean() {
        //if (System.getSecurityManager() != null) { // 安全模式
        //    AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
        //        // <1> 激活 Aware 方法，对特殊的 bean 处理：Aware、BeanClassLoaderAware、BeanFactoryAware
        //        invokeAwareMethods(beanName, bean);
        //        return null;
        //    }, getAccessControlContext());
        //} else {
        //    // <1> 激活 Aware 方法，对特殊的 bean 处理：Aware、BeanClassLoaderAware、BeanFactoryAware
        //    invokeAwareMethods(beanName, bean);
        //}
        //
        //// <2> 后处理器，before
        //Object wrappedBean = bean;
        //if (mbd == null || !mbd.isSynthetic()) {
        //    wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
        //}
        //
        //// <3> 激活用户自定义的 init 方法
        //try {
        //    invokeInitMethods(beanName, wrappedBean, mbd);
        //} catch (Throwable ex) {
        //    throw new BeanCreationException(
        //            (mbd != null ? mbd.getResourceDescription() : null),
        //            beanName, "Invocation of init method failed", ex);
        //}
        //
        //// <2> 后处理器，after
        //if (mbd == null || !mbd.isSynthetic()) {
        //    wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        //}
        //
        //return wrappedBean;
    }

    /**
     * 主要流程如下：
     *
     * <ul>
     * <li>若为单例且单例 Bean 缓存中存在 beanName ，则 <1> 进行后续处理（跳转到下一步），否则，则 <2> 从
     * FactoryBean 中获取 Bean 实例对象。</li>
     * <li><1.1> 首先，获取锁。其实我们在前面篇幅中发现了大量的同步锁，锁住的对象都是 this.singletonObjects，
     * 主要是因为在单例模式中必须要保证全局唯一。</li>
     * <li><1.2> 然后，从 factoryBeanObjectCache 缓存中获取实例对象 object 。若 object 为空，则调用 #doGetObjectFromFactoryBean
     * 方法，从 FactoryBean 获取对象，其实内部就是调用 FactoryBean#getObject() 方法。</li>
     * <li><1.3> 如果需要后续处理( shouldPostProcess = true )，则进行进一步处理，步骤如下：
     * <ul>
     * <li>若该 Bean 处于创建中（#isSingletonCurrentlyInCreation(String beanName) 方法返回 true ），则返回非处理的 Bean 对象，而不是存储它。</li>
     * <li>调用 #beforeSingletonCreation(String beanName) 方法，进行创建之前的处理。默认实现将该 Bean 标志为当前创建的。</li>
     * <li>调用 #postProcessObjectFromFactoryBean(Object object, String beanName) 方法，对从 FactoryBean 获取的 Bean 实例对象进行后置处理。</li>
     * <li>调用 #afterSingletonCreation(String beanName) 方法，进行创建 Bean 之后的处理，默认实现是将该 bean 标记为不再在创建中。</li>
     * </ul>
     * </li>
     * <li><1.4> 最后，加入到 factoryBeanObjectCache 缓存中。</li>
     * </ul>
     */
    public void getObjectFromFactoryBean() {
        // <1> 为单例模式且缓存中存在
        //if (factory.isSingleton() && containsSingleton(beanName)) {
        //    synchronized (getSingletonMutex()) { // <1.1> 单例锁
        //        // <1.2> 从缓存中获取指定的 factoryBean
        //        Object object = this.factoryBeanObjectCache.get(beanName);
        //        if (object == null) {
        //            // 为空，则从 FactoryBean 中获取对象
        //            object = doGetObjectFromFactoryBean(factory, beanName);
        //            // 从缓存中获取
        //            // Only post-process and store if not put there already during getObject() call above
        //            // (e.g. because of circular reference processing triggered by custom getBean calls)
        //            Object alreadyThere = this.factoryBeanObjectCache.get(beanName);
        //            if (alreadyThere != null) {
        //                object = alreadyThere;
        //            } else {
        //                // <1.3> 需要后续处理
        //                if (shouldPostProcess) {
        //                    // 若该 Bean 处于创建中，则返回非处理对象，而不是存储它
        //                    if (isSingletonCurrentlyInCreation(beanName)) {
        //                        // Temporarily return non-post-processed object, not storing it yet..
        //                        return object;
        //                    }
        //                    // 单例 Bean 的前置处理
        //                    beforeSingletonCreation(beanName);
        //                    try {
        //                        // 对从 FactoryBean 获取的对象进行后处理
        //                        // 生成的对象将暴露给 bean 引用
        //                        object = postProcessObjectFromFactoryBean(object, beanName);
        //                    } catch (Throwable ex) {
        //                        throw new BeanCreationException(beanName,
        //                                "Post-processing of FactoryBean's singleton object failed", ex);
        //                    } finally {
        //                        // 单例 Bean 的后置处理
        //                        afterSingletonCreation(beanName);
        //                    }
        //                }
        //                // <1.4> 添加到 factoryBeanObjectCache 中，进行缓存
        //                if (containsSingleton(beanName)) {
        //                    this.factoryBeanObjectCache.put(beanName, object);
        //                }
        //            }
        //        }
        //        return object;
        //    }
        //    // <2>
        //} else {
        //    // 为空，则从 FactoryBean 中获取对象
        //    Object object = doGetObjectFromFactoryBean(factory, beanName);
        //    // 需要后续处理
        //    if (shouldPostProcess) {
        //        try {
        //            // 对从 FactoryBean 获取的对象进行后处理
        //            // 生成的对象将暴露给 bean 引用
        //            object = postProcessObjectFromFactoryBean(object, beanName);
        //        } catch (Throwable ex) {
        //            throw new BeanCreationException(beanName, "Post-processing of FactoryBean's object failed", ex);
        //        }
        //    }
        //    return object;
        //}
    }


}
