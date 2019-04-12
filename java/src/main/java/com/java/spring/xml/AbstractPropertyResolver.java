/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java.spring.xml;

import com.java.spring.bean.BeanTypeConvert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Abstract base class for resolving properties against any underlying source.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 */
public abstract class AbstractPropertyResolver implements ConfigurablePropertyResolver {

    protected final Log logger = LogFactory.getLog(getClass());

    // 类型转换去
    private volatile ConfigurableConversionService conversionService;
    // 占位符
    private PropertyPlaceholderHelper nonStrictHelper;
    //
    private PropertyPlaceholderHelper strictHelper;
    // 设置是否抛出异常
    private boolean ignoreUnresolvableNestedPlaceholders = false;
    // 占位符前缀
    private String placeholderPrefix = SystemPropertyUtils.PLACEHOLDER_PREFIX;
    // 占位符后缀
    private String placeholderSuffix = SystemPropertyUtils.PLACEHOLDER_SUFFIX;
    // 与默认值的分割
    private String valueSeparator = SystemPropertyUtils.VALUE_SEPARATOR;
    // 必须要有的字段值
    private final Set<String> requiredProperties = new LinkedHashSet<>();


    @Override
    public ConfigurableConversionService getConversionService() {
        // Need to provide an independent DefaultConversionService, not the
        // shared DefaultConversionService used by PropertySourcesPropertyResolver.
        ConfigurableConversionService cs = this.conversionService;
        if (cs == null) {
            synchronized (this) {
                cs = this.conversionService;
                if (cs == null) {
                    cs = new DefaultConversionService();
                    this.conversionService = cs;
                }
            }
        }
        return cs;
    }

    @Override
    public void setConversionService(ConfigurableConversionService conversionService) {
        Assert.notNull(conversionService, "ConversionService must not be null");
        this.conversionService = conversionService;
    }

    /**
     * Set the prefix that placeholders replaced by this resolver must begin with.
     * <p>The default is "${".
     *
     * @see org.springframework.util.SystemPropertyUtils#PLACEHOLDER_PREFIX
     */
    @Override
    public void setPlaceholderPrefix(String placeholderPrefix) {
        Assert.notNull(placeholderPrefix, "'placeholderPrefix' must not be null");
        this.placeholderPrefix = placeholderPrefix;
    }

    /**
     * Set the suffix that placeholders replaced by this resolver must end with.
     * <p>The default is "}".
     *
     * @see org.springframework.util.SystemPropertyUtils#PLACEHOLDER_SUFFIX
     */
    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {
        Assert.notNull(placeholderSuffix, "'placeholderSuffix' must not be null");
        this.placeholderSuffix = placeholderSuffix;
    }

    /**
     * Specify the separating character between the placeholders replaced by this
     * resolver and their associated default value, or {@code null} if no such
     * special character should be processed as a value separator.
     * <p>The default is ":".
     *
     * @see org.springframework.util.SystemPropertyUtils#VALUE_SEPARATOR
     */
    @Override
    public void setValueSeparator(@Nullable String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }

    /**
     * Set whether to throw an exception when encountering an unresolvable placeholder
     * nested within the value of a given property. A {@code false} value indicates strict
     * resolution, i.e. that an exception will be thrown. A {@code true} value indicates
     * that unresolvable nested placeholders should be passed through in their unresolved
     * ${...} form.
     * <p>The default is {@code false}.
     *
     * @since 3.2
     */
    @Override
    public void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders) {
        this.ignoreUnresolvableNestedPlaceholders = ignoreUnresolvableNestedPlaceholders;
    }

    @Override
    public void setRequiredProperties(String... requiredProperties) {
        for (String key : requiredProperties) {
            this.requiredProperties.add(key);
        }
    }

    @Override
    public void validateRequiredProperties() {
        MissingRequiredPropertiesException ex = new MissingRequiredPropertiesException();
        for (String key : this.requiredProperties) {
            if (this.getProperty(key) == null) {
                //ex.addMissingRequiredProperty(key);
            }
        }
        if (!ex.getMissingRequiredProperties().isEmpty()) {
            throw ex;
        }
    }

    @Override
    public boolean containsProperty(String key) {
        return (getProperty(key) != null);
    }

    @Override
    @Nullable
    public String getProperty(String key) {
        return getProperty(key, String.class);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null ? value : defaultValue);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        T value = getProperty(key, targetType);
        return (value != null ? value : defaultValue);
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        String value = getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Required key '" + key + "' not found");
        }
        return value;
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> valueType) throws IllegalStateException {
        T value = getProperty(key, valueType);
        if (value == null) {
            throw new IllegalStateException("Required key '" + key + "' not found");
        }
        return value;
    }

    @Override
    public String resolvePlaceholders(String text) {
        if (this.nonStrictHelper == null) {
            this.nonStrictHelper = createPlaceholderHelper(true);
        }
        return doResolvePlaceholders(text, this.nonStrictHelper);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        if (this.strictHelper == null) {
            this.strictHelper = createPlaceholderHelper(false);
        }
        return doResolvePlaceholders(text, this.strictHelper);
    }

    /**
     * Resolve placeholders within the given string, deferring to the value of
     * {@link #setIgnoreUnresolvableNestedPlaceholders} to determine whether any
     * unresolvable placeholders should raise an exception or be ignored.
     * <p>Invoked from {@link #getProperty} and its variants, implicitly resolving
     * nested placeholders. In contrast, {@link #resolvePlaceholders} and
     * {@link #resolveRequiredPlaceholders} do <i>not</i> delegate
     * to this method but rather perform their own handling of unresolvable
     * placeholders, as specified by each of those methods.
     *
     * @see #setIgnoreUnresolvableNestedPlaceholders
     * @since 3.2
     */
    protected String resolveNestedPlaceholders(String value) {
        // 用于解析给定字符串中的占位符，同时根据 ignoreUnresolvableNestedPlaceholders 的值，来确定是否对不可解析的占位符的处理方法：
        // 是忽略还是抛出异常（该值由 #setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders)
        // 方法来设置）
        // 如果 this.ignoreUnresolvableNestedPlaceholders 为 true ，则调用 #resolvePlaceholders(String text) 方法，否则调
        // 用 #resolveRequiredPlaceholders(String text) 方法，但是无论是哪个方法，最终都会到 #doResolvePlaceholders(String
        // text, PropertyPlaceholderHelper helper) 方法。
        return (this.ignoreUnresolvableNestedPlaceholders ?
                resolvePlaceholders(value) : resolveRequiredPlaceholders(value));
    }

    private PropertyPlaceholderHelper createPlaceholderHelper(boolean ignoreUnresolvablePlaceholders) {
        return new PropertyPlaceholderHelper(this.placeholderPrefix, this.placeholderSuffix,
                this.valueSeparator, ignoreUnresolvablePlaceholders);
    }

    /**
     * String 类型的 text：待解析的字符串
     * PropertyPlaceholderHelper 类型的 helper：用于解析占位符的工具类。
     * PropertyPlaceholderHelper 是用于处理包含占位符值的字符串，构造该实例需要四个参数：
     * <ul>
     * <li>placeholderPrefix：占位符前缀。</li>
     * <li>placeholderSuffix：占位符后缀。</li>
     * <li>valueSeparator：占位符变量与关联的默认值之间的分隔符。</li>
     * <li>ignoreUnresolvablePlaceholders：指示是否忽略不可解析的占位符（true）或抛出异常（false）。</li>
     * </ul>
     * {@link com.java.spring.xml.PropertyPlaceholderHelper#replacePlaceholders}
     */
    private String doResolvePlaceholders(String text, PropertyPlaceholderHelper helper) {
        //该方法接收两个参数，一个是待解析的字符串 value ，一个是 PlaceholderResolver 类型的 placeholderResolver ，他是定义占位符解析的策略类。
        return helper.replacePlaceholders(text, this::getPropertyAsRawString);
    }

    /**
     * Convert the given value to the specified target type, if necessary.
     *
     * @param value      the original property value
     * @param targetType the specified target type for property retrieval
     * @return the converted value, or the original value if no conversion
     * is necessary
     * @since 4.3.5
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T convertValueIfNecessary(Object value, @Nullable Class<T> targetType) {
        if (targetType == null) {
            return (T) value;
        }
        ConversionService conversionServiceToUse = this.conversionService;
        if (conversionServiceToUse == null) {
            // Avoid initialization of shared DefaultConversionService if
            // no standard type conversion is needed in the first place...
            if (ClassUtils.isAssignableValue(targetType, value)) {
                return (T) value;
            }
            conversionServiceToUse = DefaultConversionService.getSharedInstance();
        }
        /**
         * 首先，获取类型转换服务 conversionService 。若为空，则判断是否可以通过反射来设置，如果可以则直接强转返回，否则构造一个
         * DefaultConversionService 实例。最后调用其 #convert(Object source, Class<T> targetType) 方法，完成类型转换。
         * 后续就是 Spring 类型转换体系的事情了，不了解参考{@link BeanTypeConvert}
         */
        return conversionServiceToUse.convert(value, targetType);
    }


    /**
     * Retrieve the specified property as a raw String,
     * i.e. without resolution of nested placeholders.
     *
     * @param key the property name to resolve
     * @return the property value or {@code null} if none found
     */
    @Nullable
    protected abstract String getPropertyAsRawString(String key);

}
