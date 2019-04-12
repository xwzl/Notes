package com.java.spring.bean;

/**
 * 如果从单例缓存中没有获取到单例 Bean 对象，则说明两种两种情况：
 * <ul>
 * <li>该 Bean 的 scope 不是 singleton</li>
 * <li>该 Bean 的 scope 是 singleton ，但是没有初始化完成。</li>
 * 针对这两种情况，Spring 是如何处理的呢？统一加载并完成初始化！这部分内容的篇幅较长，拆分为两部分：
 * <ul>
 * <li>第一部分，主要是一些检测、parentBeanFactory 以及依赖处理。</li>
 * <li>第二部分则是各个 scope 的初始化。</li>
 * </ul>
 * </ul>
 * <h3>1. 检测</h3>
 * 在前面就提过，Spring 只解决单例模式下的循环依赖，对于原型模式的循环依赖则是抛出 BeanCurrentlyInCreationException 异常，所以首先检查该 beanName 是否处于原型模式下的循环依赖。
 * <blockquote><pre>
 *  if (isPrototypeCurrentlyInCreation(beanName)) {
 *     throw new BeanCurrentlyInCreationException(beanName);
 * }
 * </pre></blockquote>
 * 调用 #isPrototypeCurrentlyInCreation(String beanName) 方法，判断当前 Bean 是否正在创建。
 * <blockquote><pre>
 * protected boolean isPrototypeCurrentlyInCreation(String beanName) {
 * 	Object curVal = this.prototypesCurrentlyInCreation.get();
 * 	return (curVal != null &&
 * 			(curVal.equals(beanName)  // 相等
 *                     || (curVal instanceof Set && ((Set<?>) curVal).contains(beanName)))); // 包含
 * }
 * </pre></blockquote>
 * 其实检测逻辑和单例模式一样，一个“集合”存放着正在创建的 Bean ，从该集合中进行判断即可，只不过单例模式的“集合”为 Set ，而原型模式的则是 ThreadLocal 。prototypesCurrentlyInCreation 定义如下：
 * <blockquote><pre>
 * private final ThreadLocal<Object> prototypesCurrentlyInCreation = new NamedThreadLocal<>("Prototype beans currently in creation");
 * </pre></blockquote>
 *记得返回{@link DependedInjectBean#prototypeModeDependencyCheck()}
 * @author xuweizhi
 * @date 2019/03/24 12:39
 */
public class PrototypeModeDependencyCheck {
}
