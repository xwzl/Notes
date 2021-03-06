/*
 * Copyright 2002-2015 the original author or authors.
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

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * SPI for parsing an XML document that contains Spring bean definitions.
 * Used by {@link XmlBeanDefinitionReader} for actually parsing a DOM document.
 *
 * <p>Instantiated per document to parse: implementations can hold
 * state in instance variables during the execution of the
 * {@code registerBeanDefinitions} method &mdash; for controller, global
 * settings that are defined for all bean definitions in the document.
 * {@link org.springframework.beans.factory.xml.BeanDefinitionDocumentReader#registerBeanDefinitions(Document doc, XmlReaderContext readerContext)} 方法，
 * 注册 BeanDefinition ，在接口 BeanDefinitionDocumentReader 中定义
 * <p>
 * 从给定的 Document 对象中解析定义的 BeanDefinition 并将他们注册到注册表中。方法接收两个参数：
 * <p>
 * doc 方法参数：待解析的 Document 对象。
 * readerContext 方法，解析器的当前上下文，包括目标注册表和被解析的资源。它是根据 Resource 来创建的，见
 * {@link DefaultBeanDefinitionDocumentReader#doRegisterBeanDefinitions(Element)}
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 18.12.2003
 * @see XmlBeanDefinitionReader#setDocumentReaderClass
 */
public interface BeanDefinitionDocumentReader {

	/**
	 * Read bean definitions from the given DOM document and
	 * register them with the registry in the given reader context.
	 * @param doc the DOM document
	 * @param readerContext the current context of the reader
	 * (includes the target registry and the resource being parsed)
	 * @throws BeanDefinitionStoreException in case of parsing errors
	 */
	void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
			throws BeanDefinitionStoreException;

}