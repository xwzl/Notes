package com.java.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 自定义XML标签
 * <h2>使用自定义标签</h2>
 * 扩展 Spring 自定义标签配置一般需要以下几个步骤：
 * <ul>
 * <li>创建一个需要扩展的组件。</li>
 * <li>定义一个 XSD 文件，用于描述组件内容。</li>
 * <li>创建一个实现 org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser 接口的类，用来解析 XSD 文件中的定义和组件定义。</li>
 * <li>创建一个 Handler，继承 org.springframework.beans.factory.xml.NamespaceHandlerSupport 抽象类 ，用于将组件注册到 Spring 容器。</li>
 * <li>编写 spring.handlers 和 Spring.schemas 文件。</li>
 * </ul>
 * <h3>1.1 创建一个组件</h3>
 * 该组件就是一个普通的 Java Bean，没有任何特别之处。{@link User}
 * <h3>1.2 定义XSD文件</h3>
 * {@link ParseDefineTag#xsd()}
 * <h3>1.3 定义 Parser 类</h3>
 * 定义一个 Parser 类，该类继承 AbstractSingleBeanDefinitionParser ，并实现 #getBeanClass
 * (Element element) 和 #doParse(Element element, BeanDefinitionBuilder builder) 两个
 * 方法。主要是用于解析 XSD 文件中的定义和组件定义。{@link UserDefinitionParser}
 * <h2>1.4 定义 NamespaceHandler 类</h2>
 * 定义 NamespaceHandler 类，继承 NamespaceHandlerSupport ,主要目的是将组件注册到 Spring 容器中。
 * {@link UserNamespaceHandler}
 * <h2>定义 spring.handlers 文件</h2>
 * http\://www.cmsblogs.com/schema/user=org.springframework.core.customelement.UserNamespaceHandler
 * <h2>1.6 定义 Spring.schemas 文件</h2>
 * http\://www.cmsblogs.com/schema/user.xsd=user.xsd
 * <h2>1.7 运行</h2>
 * 经过上面几个步骤，就可以使用自定义的标签了。在 xml 配置文件中使用如下：
 * {@link ParseDefineTag#run()}
 * <h2>2. 解析自定义标签</h2>
 * 上面已经演示了 Spring 自定义标签的使用，下面就来分析自定义标签的解析过程。
 * <h2>2.1 parseCustomElement</h2>
 * {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#parseBeanDefinitions(Element, BeanDefinitionParserDelegate)}
 * 方法，负责标签的解析工作，根据命名空间的不同进行不同标签的解析.其中，自定义标签由 BeanDefinitionParserDelegate 的
 * #parseCustomElement(Element ele, BeanDefinition containingBd) 方法来实现。
 * {@link com.java.spring.xml.BeanDefinitionParserDelegate#parseCustomElement(Element)}
 * <p>
 * {@link ParseDefineTag#getNamespaceHandlerResolver()}
 *
 * @author xuweizhi
 * @date 2019/03/23 22:12
 */
public class ParseDefineTag {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.getUserName() + "----" + user.getEmail());
    }

    /**
     * 上面除了对 User 这个 Java Bean 进行了描述外，还定义了 xmlns="http://www.cmsblogs.com/schema/user" 和 targetNamespace=
     * "http://www.cmsblogs.com/schema/user" 这两个值，这两个值在后面是有大作用的
     */
    public void xsd() {
        //<?xml version="1.0" encoding="UTF-8"?>
        //<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.cmsblogs.com/schema/user" targetNamespace="http://www.cmsblogs.com/schema/user" elementFormDefault="qualified">
        //
        //    <xsd:element name="user">
        //        <xsd:complexType>
        //            <xsd:attribute name="id" type="xsd:string" />
        //            <xsd:attribute name="userName" type="xsd:string" />
        //            <xsd:attribute name="email" type="xsd:string" />
        //        </xsd:complexType>
        //    </xsd:element>
        //
        //</xsd:schema>
    }

    public void run() {
        //<?xml version="1.0" encoding="UTF-8"?>
        //<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        //       xmlns:myTag="http://www.cmsblogs.com/schema/user"
        //       xsi:schemaLocation="http://www.springframework.org/schema/beans
        //       http://www.springframework.org/schema/beans/spring-beans.xsd
        //        http://www.cmsblogs.com/schema/user http://www.cmsblogs.com/schema/user.xsd">
        //
        //    <myTag:user id="user" email="12233445566@qq.com" userName="chenssy" />
        //
        //</beans>
    }

    /**
     * <h3>NamespaceHandlerResolver 的初始化</h3>
     * 那么，NamespaceHandlerResolver 是什么时候进行初始化的呢？
     * <p>
     * 在注册 BeanDefinition 时
     * <ul>
     * <li>首先，是通过 XmlBeanDefinitionReader 的 #createBeanDefinitionDocumentReader() 方法，获取 Document 解析器 BeanDefinitionDocumentReader 实例。</li>
     * <li>然后，调用 BeanDefinitionDocumentReader 实例的 #registerBeanDefinitions(Document doc, XmlReaderContext
     * readerContext) 方法，进行注册。而该方法需要提供两个参数，一个是 Document 实例 doc，一个是 XmlReaderContext 实例
     * readerContext 。readerContext 实例对象由 XmlBeanDefinitionReader 的 #createReaderContext(Resource resource)
     * 方法创建。namespaceHandlerResolver 实例对象就是在这个时候初始化的，{@link com.java.spring.xml.XmlBeanDefinitionReader#createReaderContext(Resource)}
     * XmlReaderContext 构造函数中最后一个参数就是 NamespaceHandlerResolver 对象，该对象由 {@link com.java.spring.xml.XmlBeanDefinitionReader#getNamespaceHandlerResolver)} 提供</li>
     * <li></li>
     * </ul>
     * 我们可以看到，NamespaceHandlerResolver 对象的最终类型是 org.springframework.beans.factory.xml.DefaultNamespaceHandlerResolver 。
     * <p>
     * 下一步{@link ParseDefineTag#resove}
     */
    public void getNamespaceHandlerResolver() {

    }

    /**
     * 所以， getNamespaceHandlerResolver().resolve(namespaceUri) 调用的就是 DefaultNamespaceHandlerResolver
     * 的 #resolve(String namespaceUri) 方法。{@link com.java.spring.xml.DefaultNamespaceHandlerResolver#resolve(String namespaceUri)}
     * <p>
     * 完成后返回 NamespaceHandler 对象，然后调用其 #parse(Element element, ParserContext parserContext) 方法开始自定义标签的解析。
     * {@link ParseDefineTag#parse()}
     */
    public void resove() {

    }

    /**
     * {@link com.java.spring.xml.NamespaceHandlerSupport#parse(Element, ParserContext)}
     * <h2>小结</h2>
     * 至此，自定义标签的解析过程已经分析完成了。其实整个过程还是较为简单：
     * <ul>
     * <li>首先，会加载 spring.handlers 文件，将其中内容进行一个解析，形成 <namespaceUri, 类路径> 这样的一个映射。</li>
     * <li>然后，根据获取的 namespaceUri 就可以得到相应的类路径，对其进行初始化等到相应的 NamespaceHandler 对象。</li>
     * <li>之后，调用该 NamespaceHandler 的 #parse(...) 方法，在该方法中根据标签的 localName 得到相应的 BeanDefinitionParser 实例对象。</li>
     * <li>最后，调用该 BeanDefinitionParser 的 #parse(...) 方法。该方法定义在 AbstractBeanDefinitionParser 抽象类中，核心逻辑封装在其 #parseInternal(...) 方法中，该方法返回一个 AbstractBeanDefinition 实例对象，其主要是在 AbstractSingleBeanDefinitionParser 中实现。对于自定义的 Parser 类，其需要实现 #getBeanClass() 或者 #getBeanClassName() 任一方法，和 #doParse(...) 方法。</li>
     * </ul>
     * 整体流程如
     * <image src="../image/02.png" style="widtd:300px;height:100px;"></image>
     * 下一步注册已经解析的Bean{@link RegisterBean}
     */
    public void parse() {

    }
}

/**
 * 自定义组件
 */
@Data
class User {

    private String id;
    private String userName;
    private String email;

}

class UserDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");

        if (StringUtils.hasText(id)) {
            builder.addPropertyValue("id", id);
        }

        if (StringUtils.hasText(userName)) {
            builder.addPropertyValue("userName", userName);
        }

        if (StringUtils.hasText(email)) {
            builder.addPropertyValue("email", email);
        }
    }

}

class UserNamespaceHandler extends NamespaceHandlerSupport {

    /**
     * 直接调用父类 {@link com.java.spring.xml.NamespaceHandlerSupport#registerBeanDefinitionParser} 的方法，
     * 注册指定元素的 BeanDefinitionParser 解析器
     */
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserDefinitionParser());
    }

}
