package com.java.spring.xml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionOverrideException;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuweizhi
 * @date 2019/03/24 0:39
 */
@Slf4j
public class DefaultListableBeanFactory {

    // DefaultListableBeanFactory.java

    /** Whether to allow re-registration of a different definition with the same name. */
    private boolean allowBeanDefinitionOverriding = true;

    /** Map of bean definition objects, keyed by bean name. */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    /** List of bean definition names, in registration order. */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);
    /** List of names of manually registered singletons, in registration order. */
    private volatile Set<String> manualSingletonNames = new LinkedHashSet<>(16);
    /** Cached array of bean definition names in case of frozen configuration. */

    private volatile String[] frozenBeanDefinitionNames;

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException {

        // 校验 beanName 与 beanDefinition 非空
        Assert.hasText(beanName, "Bean name must not be empty");
        Assert.notNull(beanDefinition, "BeanDefinition must not be null");

        // <1> 校验 BeanDefinition 。
        // 这是注册前的最后一次校验了，主要是对属性 methodOverrides 进行校验。
        // 主要是对 AbstractBeanDefinition 的 methodOverrides 属性进行校验。
        if (beanDefinition instanceof AbstractBeanDefinition) {
            try {
                ((AbstractBeanDefinition) beanDefinition).validate();
            } catch (BeanDefinitionValidationException ex) {
                throw new BeanDefinitionStoreException(beanDefinition.getResourceDescription(), beanName,
                        "Validation of bean definition failed", ex);
            }
        }

        // <2> 从缓存中获取指定 beanName 的 BeanDefinition
        BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName);
        // <3> 如果已经存在,则根据 allowBeanDefinitionOverriding 标志来判断是否允许覆盖。
        // 如果允许则直接覆盖。否则，抛出 BeanDefinitionStoreException 异常。
        if (existingDefinition != null) {
            // 如果存在但是不允许覆盖，抛出异常
            if (!isAllowBeanDefinitionOverriding()) {
                throw new BeanDefinitionOverrideException(beanName, null, null);
                // 覆盖 beanDefinition 大于 被覆盖的 beanDefinition 的 ROLE ，打印 info 日志
            } else if (existingDefinition.getRole() < beanDefinition.getRole()) {
                // e.g. was ROLE_APPLICATION, now overriding with ROLE_SUPPORT or ROLE_INFRASTRUCTURE
                if (log.isInfoEnabled()) {
                    log.info("Overriding user-defined bean definition for bean '" + beanName +
                            "' with a framework-generated bean definition: replacing [" +
                            existingDefinition + "] with [" + beanDefinition + "]");
                }
                // 覆盖 beanDefinition 与 被覆盖的 beanDefinition 不相同，打印 debug 日志
            } else if (!beanDefinition.equals(existingDefinition)) {
                if (log.isDebugEnabled()) {
                    log.debug("Overriding bean definition for bean '" + beanName +
                            "' with a different definition: replacing [" + existingDefinition +
                            "] with [" + beanDefinition + "]");
                }
                // 其它，打印 debug 日志
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Overriding bean definition for bean '" + beanName +
                            "' with an equivalent definition: replacing [" + existingDefinition +
                            "] with [" + beanDefinition + "]");
                }
            }
            // 允许覆盖，直接覆盖原有的 BeanDefinition 到 beanDefinitionMap 中。
            this.beanDefinitionMap.put(beanName, beanDefinition);
            // <4> 如果未存在，缓存中没有指定 beanName 的 BeanDefinition，则判断当前阶段是否已经开始了 Bean 的创建阶段？
            // 如果是，则需要对 beanDefinitionMap 进行加锁控制并发问题，否则直接设置即可。
        } else {
            // 检测创建 Bean 阶段是否已经开启，如果开启了则需要对 beanDefinitionMap 进行并发控制
            if (hasBeanCreationStarted()) {
                // beanDefinitionMap 为全局变量，避免并发情况
                // Cannot modify startup-time collection elements anymore (for stable iteration)
                synchronized (this.beanDefinitionMap) {
                    // 添加到 BeanDefinition 到 beanDefinitionMap 中。===============添加值
                    this.beanDefinitionMap.put(beanName, beanDefinition);
                    // 添加 beanName 到 beanDefinitionNames 中
                    List<String> updatedDefinitions = new ArrayList<>(this.beanDefinitionNames.size() + 1);
                    updatedDefinitions.addAll(this.beanDefinitionNames);
                    updatedDefinitions.add(beanName);
                    this.beanDefinitionNames = updatedDefinitions;
                    // 从 manualSingletonNames 移除 beanName
                    if (this.manualSingletonNames.contains(beanName)) {
                        Set<String> updatedSingletons = new LinkedHashSet<>(this.manualSingletonNames);
                        updatedSingletons.remove(beanName);
                        this.manualSingletonNames = updatedSingletons;
                    }
                }
            } else {
                // Still in startup registration phase
                // 添加到 BeanDefinition 到 beanDefinitionMap 中。而 BeanDefinition 的缓存也不是神奇的东西，
                // 就是定义一个 Map ：key 为 beanName 。value 为 BeanDefinition 对象。
                this.beanDefinitionMap.put(beanName, beanDefinition);
                // 添加 beanName 到 beanDefinitionNames 中
                this.beanDefinitionNames.add(beanName);
                // 从 manualSingletonNames 移除 beanName
                this.manualSingletonNames.remove(beanName);
            }

            this.frozenBeanDefinitionNames = null;
        }

        // <5> 若缓存中存在该 beanName 或者单例 bean 集合中存在该 beanName ，则调用 #resetBeanDefinition(String beanName) 方法，
        // 重置 BeanDefinition 缓存。
        if (existingDefinition != null || containsSingleton(beanName)) {
            resetBeanDefinition(beanName);
        }
    }

    private boolean hasBeanCreationStarted() {
        return false;
    }

    private void resetBeanDefinition(String beanName) {

    }

    private boolean containsSingleton(String beanName) {
        return false;
    }

    private boolean isAllowBeanDefinitionOverriding() {
        return false;
    }
}
