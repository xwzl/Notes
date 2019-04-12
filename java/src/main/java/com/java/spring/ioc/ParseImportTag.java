package com.java.spring.ioc;


import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.w3c.dom.Element;

/**
 * 在{@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#parseBeanDefinitions(Element, BeanDefinitionParserDelegate)}中分析到，
 * Spring 中有两种解析 Bean 的方式：
 * <ul>
 * <li>如果根节点或者子节点采用默认命名空间的话，则调用 #parseDefaultElement(...) 方法，进行默认标签解析</li>
 * <li>否则，调用 BeanDefinitionParserDelegate#parseCustomElement(...) 方法，进行自定义解析。</li>
 * </ul>
 * <blockquote><pre>
 *  // DefaultBeanDefinitionDocumentReader.java
 *
 * public static final String IMPORT_ELEMENT = "import";
 * public static final String ALIAS_ATTRIBUTE = "alias";
 * public static final String BEAN_ELEMENT = BeanDefinitionParserDelegate.BEAN_ELEMENT;
 * public static final String NESTED_BEANS_ELEMENT = "beans";
 *
 * private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
 * 	if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) { // import
 * 		importBeanDefinitionResource(ele);
 *        } else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) { // alias
 * 		processAliasRegistration(ele);
 *    } else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) { // bean
 * 		processBeanDefinition(ele, delegate);
 *    } else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) { // beans
 * 		// recurse
 * 		doRegisterBeanDefinitions(ele);
 *    }
 * }
 * </pre></blockquote>
 * 该方法的功能一目了然，分别是对四种不同的标签进行解析，分别是 import、alias、bean、beans 。咱门从第一个标签 import 开始。
 * <h2>1. import 示例</h2>
 * 经历过 Spring 配置文件的小伙伴都知道，如果工程比较大，配置文件的维护会让人觉得恐怖，文件太多了，
 * 想象将所有的配置都放在一个 spring.xml 配置文件中，哪种后怕感是不是很明显？
 * <p>
 * 所有针对这种情况 Spring 提供了一个分模块的思路，利用 import 标签，例如我们可以构造一个这样的 spring.xml 。
 * <p>
 * spring.xml 配置文件中，使用 import 标签的方式导入其他模块的配置文件。
 * <ul>
 * <li>如果有配置需要修改直接修改相应配置文件即可。</li>
 * <li>若有新的模块需要引入直接增加 import 即可。</li>
 * </ul>
 * 这样大大简化了配置后期维护的复杂度，同时也易于管理。
 * <h2>importBeanDefinitionResource</h2>
 * {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#importBeanDefinitionResource(Element)} 加载真正的bean对象
 * <p>
 * 至此，import 标签解析完毕，整个过程比较清晰明了：获取 source 属性值，得到正确的资源路径，然后调用
 * XmlBeanDefinitionReader#loadBeanDefinitions(Resource... resources) 方法，进行递归的
 * BeanDefinition 加载。
 * 下一步分析{@link ParseBeanTag}
 *
 * @author xuweizhi
 * @date 2019/03/23 11:26
 */
public class ParseImportTag {

    //<?xml version="1.0" encoding="UTF-8"?>
    //<beans xmlns="http://www.springframework.org/schema/beans"
    //xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    //xsi:schemaLocation="http://www.springframework.org/schema/beans
    //http://www.springframework.org/schema/beans/spring-beans.xsd">
    //
    //    <import xml="spring-student.xml"/>
    //
    //    <import xml="spring-student-dtd.xml"/>
    //
    //</beans>



}
