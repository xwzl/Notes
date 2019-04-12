package com.java.spring.xml;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringValueResolver;

import java.util.*;

/**
 * Visitor class for traversing {@link BeanDefinition} objects, in particular
 * the property values and constructor argument values contained in them,
 * resolving bean metadata values.
 *
 * <p>Used by {@link PropertyPlaceholderConfigurer} to parse all String values
 * contained in a BeanDefinition, resolving any placeholders found.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @see BeanDefinition
 * @see BeanDefinition#getPropertyValues
 * @see BeanDefinition#getConstructorArgumentValues
 * @see PropertyPlaceholderConfigurer
 * @since 1.2
 */
public class BeanDefinitionVisitor {

    @Nullable
    private StringValueResolver valueResolver;


    /**
     * Create a new BeanDefinitionVisitor, applying the specified
     * value resolver to all bean metadata values.
     *
     * @param valueResolver the StringValueResolver to apply
     */
    public BeanDefinitionVisitor(StringValueResolver valueResolver) {
        Assert.notNull(valueResolver, "StringValueResolver must not be null");
        this.valueResolver = valueResolver;
    }

    /**
     * Create a new BeanDefinitionVisitor for subclassing.
     * Subclasses need to override the {@link #resolveStringValue} method.
     */
    protected BeanDefinitionVisitor() {
    }


    /**
     * Traverse the given BeanDefinition object and the MutablePropertyValues
     * and ConstructorArgumentValues contained in them.
     * 我们可以看到该方法基本访问了 BeanDefinition 中所有值得访问的东西了，包括 parent 、class 、
     * factory-bean 、factory-method 、scope 、property 、constructor-arg 。
     *
     * @param beanDefinition the BeanDefinition object to traverse
     * @see #resolveStringValue(String)
     */
    public void visitBeanDefinition(BeanDefinition beanDefinition) {
        visitParentName(beanDefinition);
        visitBeanClassName(beanDefinition);
        visitFactoryBeanName(beanDefinition);
        visitFactoryMethodName(beanDefinition);
        visitScope(beanDefinition);
        if (beanDefinition.hasPropertyValues()) {
            //关注点，进入方法
            visitPropertyValues(beanDefinition.getPropertyValues());
        }
        if (beanDefinition.hasConstructorArgumentValues()) {
            ConstructorArgumentValues cas = beanDefinition.getConstructorArgumentValues();
            visitIndexedArgumentValues(cas.getIndexedArgumentValues());
            visitGenericArgumentValues(cas.getGenericArgumentValues());
        }
    }

    protected void visitParentName(BeanDefinition beanDefinition) {
        String parentName = beanDefinition.getParentName();
        if (parentName != null) {
            String resolvedName = resolveStringValue(parentName);
            if (!parentName.equals(resolvedName)) {
                beanDefinition.setParentName(resolvedName);
            }
        }
    }

    protected void visitBeanClassName(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        if (beanClassName != null) {
            String resolvedName = resolveStringValue(beanClassName);
            if (!beanClassName.equals(resolvedName)) {
                beanDefinition.setBeanClassName(resolvedName);
            }
        }
    }

    protected void visitFactoryBeanName(BeanDefinition beanDefinition) {
        String factoryBeanName = beanDefinition.getFactoryBeanName();
        if (factoryBeanName != null) {
            String resolvedName = resolveStringValue(factoryBeanName);
            if (!factoryBeanName.equals(resolvedName)) {
                beanDefinition.setFactoryBeanName(resolvedName);
            }
        }
    }

    protected void visitFactoryMethodName(BeanDefinition beanDefinition) {
        String factoryMethodName = beanDefinition.getFactoryMethodName();
        if (factoryMethodName != null) {
            String resolvedName = resolveStringValue(factoryMethodName);
            if (!factoryMethodName.equals(resolvedName)) {
                beanDefinition.setFactoryMethodName(resolvedName);
            }
        }
    }

    protected void visitScope(BeanDefinition beanDefinition) {
        String scope = beanDefinition.getScope();
        if (scope != null) {
            String resolvedScope = resolveStringValue(scope);
            if (!scope.equals(resolvedScope)) {
                beanDefinition.setScope(resolvedScope);
            }
        }
    }

    /**
     * 过程就是对属性数组进行遍历，调用 #resolveValue(Object value)方法，对属性进行解析获取最新值，如果新值和旧值不等，则用新值替换旧值。
     *
     * @param pvs
     */
    protected void visitPropertyValues(MutablePropertyValues pvs) {
        PropertyValue[] pvArray = pvs.getPropertyValues();
        // 遍历 PropertyValue 数组
        for (PropertyValue pv : pvArray) {
            // 解析真值===================进入方法
            Object newVal = resolveValue(pv.getValue());
            if (!ObjectUtils.nullSafeEquals(newVal, pv.getValue())) {
                // 设置到 PropertyValue 中
                pvs.add(pv.getName(), newVal);
            }
        }
    }

    protected void visitIndexedArgumentValues(Map<Integer, ConstructorArgumentValues.ValueHolder> ias) {
        for (ConstructorArgumentValues.ValueHolder valueHolder : ias.values()) {
            Object newVal = resolveValue(valueHolder.getValue());
            if (!ObjectUtils.nullSafeEquals(newVal, valueHolder.getValue())) {
                valueHolder.setValue(newVal);
            }
        }
    }

    protected void visitGenericArgumentValues(List<ConstructorArgumentValues.ValueHolder> gas) {
        for (ConstructorArgumentValues.ValueHolder valueHolder : gas) {
            Object newVal = resolveValue(valueHolder.getValue());
            if (!ObjectUtils.nullSafeEquals(newVal, valueHolder.getValue())) {
                valueHolder.setValue(newVal);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Nullable
    protected Object resolveValue(@Nullable Object value) {
        if (value instanceof BeanDefinition) {
            visitBeanDefinition((BeanDefinition) value);
        } else if (value instanceof BeanDefinitionHolder) {
            visitBeanDefinition((BeanDefinition) ((BeanDefinitionHolder) value).getBeanDefinition());
        } else if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String newBeanName = resolveStringValue(ref.getBeanName());
            if (newBeanName == null) {
                return null;
            }
            if (!newBeanName.equals(ref.getBeanName())) {
                return new RuntimeBeanReference(newBeanName);
            }
        } else if (value instanceof RuntimeBeanNameReference) {
            RuntimeBeanNameReference ref = (RuntimeBeanNameReference) value;
            String newBeanName = resolveStringValue(ref.getBeanName());
            if (newBeanName == null) {
                return null;
            }
            if (!newBeanName.equals(ref.getBeanName())) {
                return new RuntimeBeanNameReference(newBeanName);
            }
        } else if (value instanceof Object[]) {
            visitArray((Object[]) value);
        } else if (value instanceof List) {
            visitList((List) value);
        } else if (value instanceof Set) {
            visitSet((Set) value);
        } else if (value instanceof Map) {
            visitMap((Map) value);
        } else if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
            String stringValue = typedStringValue.getValue();
            if (stringValue != null) {
                String visitedString = resolveStringValue(stringValue);
                typedStringValue.setValue(visitedString);
            }
            // 由于 Properties 中的是 String，所以重点在此处================进入方法
        } else if (value instanceof String) {
            return resolveStringValue((String) value);
        }
        return value;
    }

    protected void visitArray(Object[] arrayVal) {
        for (int i = 0; i < arrayVal.length; i++) {
            Object elem = arrayVal[i];
            Object newVal = resolveValue(elem);
            if (!ObjectUtils.nullSafeEquals(newVal, elem)) {
                arrayVal[i] = newVal;
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void visitList(List listVal) {
        for (int i = 0; i < listVal.size(); i++) {
            Object elem = listVal.get(i);
            Object newVal = resolveValue(elem);
            if (!ObjectUtils.nullSafeEquals(newVal, elem)) {
                listVal.set(i, newVal);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void visitSet(Set setVal) {
        Set newContent = new LinkedHashSet();
        boolean entriesModified = false;
        for (Object elem : setVal) {
            int elemHash = (elem != null ? elem.hashCode() : 0);
            Object newVal = resolveValue(elem);
            int newValHash = (newVal != null ? newVal.hashCode() : 0);
            newContent.add(newVal);
            entriesModified = entriesModified || (newVal != elem || newValHash != elemHash);
        }
        if (entriesModified) {
            setVal.clear();
            setVal.addAll(newContent);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void visitMap(Map<?, ?> mapVal) {
        Map newContent = new LinkedHashMap();
        boolean entriesModified = false;
        for (Map.Entry entry : mapVal.entrySet()) {
            Object key = entry.getKey();
            int keyHash = (key != null ? key.hashCode() : 0);
            Object newKey = resolveValue(key);
            int newKeyHash = (newKey != null ? newKey.hashCode() : 0);
            Object val = entry.getValue();
            Object newVal = resolveValue(val);
            newContent.put(newKey, newVal);
            entriesModified = entriesModified || (newVal != val || newKey != key || newKeyHash != keyHash);
        }
        if (entriesModified) {
            mapVal.clear();
            mapVal.putAll(newContent);
        }
    }

    /**
     * Resolve the given String value, for controller parsing placeholders.
     *
     * @param strVal the original String value
     * @return the resolved String value
     */
    @Nullable
    protected String resolveStringValue(String strVal) {
        if (this.valueResolver == null) {
            throw new IllegalStateException("No StringValueResolver specified - pass a resolver " +
                    "object into the constructor or override the 'resolveStringValue' method");
        }
        /**
         * 解析真值valueResolver 是我们在构造 BeanDefinitionVisitor 实例时传入的 String 类型解析器
         * PlaceholderResolvingStringValueResolver，调用其 #resolveStringValue(String strVal) 方法，
         *{@link com.java.spring.xml.PropertyPlaceholderConfigurer.PlaceholderResolvingStringValueResolver#resolveStringValue}
         */
        String resolvedValue = this.valueResolver.resolveStringValue(strVal);
        // Return original String if not modified.
        return (strVal.equals(resolvedValue) ? strVal : resolvedValue);
    }

}
