/*
 * Copyright 2002-2017 the original author or authors.
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

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/**
 * Base class for those {@link BeanDefinitionParser} implementations that
 * need to parse and define just a <i>single</i> {@code BeanDefinition}.
 *
 * <p>Extend this parser class when you want to create a single bean definition
 * from an arbitrarily complex XML element. You may wish to consider extending
 * the {@link AbstractSimpleBeanDefinitionParser} when you want to create a
 * single bean definition from a relatively simple custom XML element.
 *
 * <p>The resulting {@code BeanDefinition} will be automatically registered
 * with the {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}.
 * Your job simply is to {@link #doParse parse} the custom XML {@link Element}
 * into a single {@code BeanDefinition}.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @author Rick Evans
 * @see #getBeanClass
 * @see #getBeanClassName
 * @see #doParse
 * @since 2.0
 */
public abstract class AbstractSingleBeanDefinitionParser extends AbstractBeanDefinitionParser {

    /**
     * 所以对于 #parseInternal(Element element, ParserContext parserContext) 方法 而言，它总是期待它的子类能够
     * 实现 #getBeanClass((Element element) 、#doParse(Element element, BeanDefinitionBuilder builder)
     * 方法。其中，#doParse(Element element, BeanDefinitionBuilder builder) 方法尤为重要！如果，你不提供该方法
     * 的实现，怎么来解析自定义标签呢？
     * Creates a {@link BeanDefinitionBuilder} instance for the
     * {@link #getBeanClass bean Class} and passes it to the
     * {@link #doParse} strategy method.
     *
     * @param element       the element that is to be parsed into a single BeanDefinition
     * @param parserContext the object encapsulating the current state of the parsing process
     * @return the BeanDefinition resulting from the parsing of the supplied {@link Element}
     * @throws IllegalStateException if the bean {@link Class} returned from
     *                               {@link #getBeanClass(org.w3c.dom.Element)} is {@code null}
     * @see #doParse
     */
    @Override
    protected final AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        // 创建 BeanDefinitionBuilder 对象
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        // 获取父类元素
        String parentName = getParentName(element);
        if (parentName != null) {
            builder.getRawBeanDefinition().setParentName(parentName);
        }
        // 获取自定义标签中的 class，这个时候会去调用自定义解析中的 getBeanClass(),AbstractSingleBeanDefinitionParser 类并没有提供
        // 具体实现，而是直接返回 null ，意味着它希望子类能够重写该方法。当然，如果没有重写该方法，这会去调用 #getBeanClassName() ，判断
        // 子类是否已经重写了该方法。
        Class<?> beanClass = getBeanClass(element);
        if (beanClass != null) {
            builder.getRawBeanDefinition().setBeanClass(beanClass);
        } else {
            // beanClass 为 null，意味着子类并没有重写 getBeanClass() 方法，则尝试去判断是否重写了 getBeanClassName()
            String beanClassName = getBeanClassName(element);
            if (beanClassName != null) {
                builder.getRawBeanDefinition().setBeanClassName(beanClassName);
            }
        }
        // 设置 source 属性
        builder.getRawBeanDefinition().setSource(parserContext.extractSource(element));
        // 设置 scope 属性
        BeanDefinition containingBd = parserContext.getContainingBeanDefinition();
        if (containingBd != null) {
            // Inner bean definition must receive same scope as containing bean.
            builder.setScope(containingBd.getScope());
        }
        // 设置 lazy-init 属性
        if (parserContext.isDefaultLazyInit()) {
            // Default-lazy-init applies to custom bean definitions as well.
            builder.setLazyInit(true);
        }
        // 调用子类的 doParse() 进行解析,对于 #doParse(Element element, BeanDefinitionBuilder builder) 方法，则是直接空实现。

        doParse(element, parserContext, builder);
        return builder.getBeanDefinition();
    }

    /**
     * Determine the name for the parent of the currently parsed bean,
     * in case of the current bean being defined as a child bean.
     * <p>The default implementation returns {@code null},
     * indicating a root bean definition.
     *
     * @param element the {@code Element} that is being parsed
     * @return the name of the parent bean for the currently parsed bean,
     * or {@code null} if none
     */
    @Nullable
    protected String getParentName(Element element) {
        return null;
    }

    /**
     * Determine the bean class corresponding to the supplied {@link Element}.
     * <p>Note that, for application classes, it is generally preferable to
     * override {@link #getBeanClassName} instead, in order to avoid a direct
     * dependence on the bean implementation class. The BeanDefinitionParser
     * and its NamespaceHandler can be used within an IDE plugin then, even
     * if the application classes are not available on the plugin's classpath.
     *
     * @param element the {@code Element} that is being parsed
     * @return the {@link Class} of the bean that is being defined via parsing
     * the supplied {@code Element}, or {@code null} if none
     * @see #getBeanClassName
     */
    @Nullable
    protected Class<?> getBeanClass(Element element) {
        return null;
    }

    /**
     * Determine the bean class name corresponding to the supplied {@link Element}.
     *
     * @param element the {@code Element} that is being parsed
     * @return the class name of the bean that is being defined via parsing
     * the supplied {@code Element}, or {@code null} if none
     * @see #getBeanClass
     */
    @Nullable
    protected String getBeanClassName(Element element) {
        return null;
    }

    /**
     * 对于 #doParse(Element element, BeanDefinitionBuilder builder) 方法，则是直接空实现
     * <p>
     * Parse the supplied {@link Element} and populate the supplied
     * {@link BeanDefinitionBuilder} as required.
     * <p>The default implementation delegates to the {@code doParse}
     * version without ParserContext argument.
     *
     * @param element       the XML element being parsed
     * @param parserContext the object encapsulating the current state of the parsing process
     * @param builder       used to define the {@code BeanDefinition}
     * @see #doParse(Element, BeanDefinitionBuilder)
     */
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        doParse(element, builder);
    }

    /**
     * Parse the supplied {@link Element} and populate the supplied
     * {@link BeanDefinitionBuilder} as required.
     * <p>The default implementation does nothing.
     *
     * @param element the XML element being parsed
     * @param builder used to define the {@code BeanDefinition}
     */
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
    }

}
