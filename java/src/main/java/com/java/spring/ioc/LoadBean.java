package com.java.spring.ioc;

import com.java.spring.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.BeansDtdResolver;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

import java.io.InputStream;

/**
 * 整个过程就分为三个步骤:资源定位、装载、注册
 * {@link LoadBean#main(String[])}
 * <h2>loadBeanDefinitions</h2>
 * {@link com.java.spring.xml.XmlBeanDefinitionReader#loadBeanDefinitions(Resource)}这才是加载资源的真正实现，所以我们直接从该方
 * 法入手 注解
 * <p>
 * 参考{@link XmlBeanDefinitionReader#loadBeanDefinitions(Resource)}
 * 下一步分析Importbiaoqian{@link ParseImportTag}
 *
 * @author xuweizhi
 * @date 2019/03/21 17:14
 */
public class LoadBean {

    private DefaultDocumentLoader documentLoader;

    private ErrorHandler errorHandler;

    /**
     * 整个过程就分为三个步骤:资源定位、装载、注册
     *
     * <h3>资源定位</h3>
     * 我们一般用外部资源来描述 Bean 对象，所以在初始化 IoC 容器的第一步就是需要定位这个外部资源。
     * <h3>装载</h3>
     * 装载就是 BeanDefinition 的载入。BeanDefinitionReader 读取、解析 Resource 资源，也就是
     * 将用户定义的 Bean 表示成 IoC 容器的内部数据结构：BeanDefinition 。在 IoC 容器内部维护着一个
     * BeanDefinition Map 的数据结构,在配置文件中每一个 bean 都对应着一个 BeanDefinition 对象。
     * <b>BeanDefinitionReader ，主要定义资源文件读取并转换为 BeanDefinition 的各个功能。</b>
     * <h3>注册</h3>
     * 向 IoC 容器注册在第二步解析好的 BeanDefinition，这个过程是通过 BeanDefinitionRegistries
     * 接口来实现的。在 IoC 容器内部其实是将第二个过程解析得到的 BeanDefinition 注入到一个 HashMap
     * 容器中，IoC 容器就是通过这个 HashMap 来维护这些 BeanDefinition 的。
     * <ul>
     * <li>在这里需要注意的一点是这个过程并没有完成依赖注入（Bean 创建），Bean 创建是发生在应用第一次调用
     * #getBean(...) 方法，向容器索要 Bean 时。</li>
     * <li>当然我们可以通过设置预处理，即对某个 Bean 设置 lazyinit = false 属性，那么这个 Bean 的依赖
     * 注入就会在容器初始化的时候完成。</li>
     * </ul>
     * <p>
     * 简单的说，上面步骤的结果是，<b>XML Resource => XML Document => Bean Definition 。</b>
     * {@link LoadBean#validation()}
     * {@link LoadBean#document()}
     */
    public static void main(String[] args) {

        //获取资源
        ClassPathResource resource = new ClassPathResource("bean.xml");
        //获取 BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //根据新建的 BeanFactory 创建一个 BeanDefinitionReader 对象，该 Reader 对象为资源的解析器
        com.java.spring.xml.XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //装载资源 进入方法
        reader.loadBeanDefinitions(resource);
        System.out.println("傻逼1");
    }


    /**
     * XmlBeanDefinitionReader.java
     * EntityResolver 解析器
     * <p>
     * 如果 ResourceLoader 不为 null，则根据指定的 ResourceLoader 创建一个 ResourceEntityResolver 对象。
     * <p>
     * 如果 ResourceLoader 为 null ，则创建 一个 DelegatingEntityResolver 对象。该 Resolver 委托给默认的
     * BeansDtdResolver 和 PluggableSchemaResolver 。
     * <p>
     * 这个方法，一共涉及四个 EntityResolver 的子类：
     * {@link BeansDtdResolver}或者{@link com.java.spring.resolver.BeansDtdResolver}：实现
     * EntityResolver 接口，Spring Bean dtd 解码器，用来从 classpath 或者jar 文件中加载 dtd 。
     * <p>
     * jar 文件中加载 dtd 。部分代码如下：
     *
     * <blockquote><pre>
     *     private static final String DTD_EXTENSION = ".dtd";
     *
     *     private static final String DTD_NAME = "spring-beans";
     * </pre></blockquote>
     * <p>
     * {@link org.springframework.beans.factory.xml.PluggableSchemaResolver}，实现 EntityResolver 接口，
     * 读取classpath 下的所有 "META-INF/spring.schemas"成一个 namespaceURI 与 Schema 文件地址的 map 。参考{@link com.java.spring.resolver.PluggableSchemaResolver}
     * <p>
     * {@link org.springframework.beans.factory.xml.DelegatingEntityResolver} ：实现 EntityResolver 接口，分别代理
     * dtd 的 BeansDtdResolver 和 xml schemas 的 PluggableSchemaResolver 。{@link com.java.spring.resolver.PluggableSchemaResolver}
     * <p>
     * {@link org.springframework.beans.factory.xml.ResourceEntityResolver } ：继承自 DelegatingEntityResolver 类，
     * 通过 ResourceLoader 来解析实体的引用。
     * <p>
     * resolveEntity通过子类的解析方法解析相应的XML
     */
    public EntityResolver entityResolver;


    /**
     * <h2>DTD 与 XSD 的区别</h2>
     * <h3>1.DTD</h3>
     * DTD(Document Type Definition)，即文档类型定义，为 XML 文件的验证机制，属于 XML 文件中组成的一部分。
     * DTD 是一种保证 XML 文档格式正确的有效验证方式，它定义了相关 XML 文档的元素、属性、排列方式、元素的内容类
     * 型以及元素的层次结构。其实 DTD 就相当于 XML 中的 “词汇”和“语法”，我们可以通过比较 XML 文件和 DTD 文件
     * 来看文档是否符合规范，元素和标签使用是否正确。
     * <p>
     * 要在 Spring 中使用 DTD，需要在 Spring XML 文件头部声明：
     * <p>
     * <?xml version="1.0" encoding="UTF-8"?>
     * <!DOCTYPE beans PUBLIC  "-//SPRING//DTD BEAN//EN"  "http://www.springframework.org/dtd/spring-beans.dtd">
     * <p>
     * DTD 在一定的阶段推动了 XML 的发展，但是它本身存在着一些缺陷：
     * <ul>
     * <li>它没有使用 XML 格式，而是自己定义了一套格式，相对解析器的重用性较差；而且 DTD 的构建和访问没有标准的编程接口，因而解析器很难简单的解析 DTD 文档。</li>
     * <li>DTD 对元素的类型限制较少；同时其他的约束力也叫弱。</li>
     * <li>DTD 扩展能力较差。</li>
     * <li>基于正则表达式的 DTD 文档的描述能力有限。</li>
     * </ul>
     * <h3>2. XSD</h3>
     * 针对 DTD 的缺陷，W3C 在 2001 年推出 XSD。XSD（XML Schemas Definition）即 XML Schema 语言。XML Schema
     * 本身就是一个 XML文档，使用的是 XML 语法，因此可以很方便的解析 XSD 文档。相对于 DTD，XSD 具有如下优势：
     *
     * <ul>
     * <li>XML Schema 基于 XML ，没有专门的语法。</li>
     * <li>XML Schema 可以象其他 XML 文件一样解析和处理。</li>
     * <li>XML Schema 比 DTD 提供了更丰富的数据类型。</li>
     * <li>XML Schema 提供可扩充的数据模型。</li>
     * <li>XML Schema 支持综合命名空间。</li>
     * <li>XML Schema 支持属性组。</li>
     * </ul>
     * <p>
     * {@link com.java.spring.xml.XmlValidationModeDetector#detectValidationMode(InputStream)}
     */
    public void validation() {

    }

    /**
     * 在 XmlBeanDefinitionReader#doLoadDocument(InputSource inputSource, Resource xml) 方法，中做了两件事情：
     * <p>
     * 调用 #getValidationModeForResource(Resource xml) 方法，获取指定资源（xml）的验证模式。
     * <p>
     * 调用 DocumentLoader#loadDocument(InputSource inputSource, EntityResolver entityResolver, ErrorHandler errorHandler,
     * int validationMode, boolean namespaceAware) 方法，获取 XML Document 实例。
     *
     * <h2>DocumentLoader</h2>
     * <p>
     * 获取 Document 的策略，由接口 {@link com.java.spring.xml.DocumentLoader} 定义。代码如下
     *
     * <h2>DefaultDocumentLoader</h2>
     * <p>
     * 该方法由 DocumentLoader 的默认实现类 {@link com.java.spring.xml.DefaultDocumentLoader }实现。
     * {@link  com.java.spring.xml.DefaultDocumentLoader#loadDocument(InputSource, EntityResolver, ErrorHandler, int, boolean)}
     * <h2>EntityResolver</h2>
     * 通过 DocumentLoader#loadDocument(...) 方法来获取 Document 对象时，有一个方法参数 entityResolver 。
     * 该参数是通过 {@link com.java.spring.xml.XmlBeanDefinitionReader#getEntityResolver()}  方法来获取的。
     * <p>
     * {@link com.java.spring.xml.XmlBeanDefinitionReader#getEntityResolver()}方法，返回指定的解析器，如果没有指定，则构造一个未指定的默认解析器。
     * <p></p>
     * <b>EntityResolver 的作用就是，通过实现它，应用可以自定义如何寻找【验证文件】的逻辑。</b>
     * <p>
     * 在 loadDocument 方法中涉及一个参数 EntityResolver ，何为EntityResolver？官网这样解释：
     * <p>
     * 如果 SAX 应用程序需要实现自定义处理外部实体，则必须实现此接口并使用 setEntityResolver 方法向SAX 驱动器注册一个实例。也就是说，对于解析
     * 一个XML，SAX 首先读取该 XML 文档上的声明，根据声明去寻找相应的 DTD 定义，以便对文档进行一个验证。默认的寻找规则，即通过网络（实现上就是声
     * 明的DTD的URI地址）来下载相应的DTD声明，并进行认证。下载的过程是一个漫长的过程，而且当网络中断或不可用时，这里会报错，就是因为相应的DTD声明
     * 没有被找到的原因。
     * <p>
     * EntityResolver 的作用是项目本身就可以提供一个如何寻找 DTD 声明的方法，即由程序来实现寻找 DTD 声明的过程，比如我们将 DTD 文件放到项目中某
     * 处，在实现时直接将此文档读取并返回给 SAX 即可。这样就避免了通过网络来寻找相应的声明。
     * <p>
     * 是不是看到此处，有点懵逼，不是说好了分享获取 Document 对象，结果内容主要是 EntityResolver 呢？因为，从 XML 中获取 Document 对象，已经有
     * javax.xml 库进行解析。而 EntityResolver 的重点，是在于如何获取【验证文件】，从而验证用户写的 XML 是否通过验证
     */
    public void document() {

    }

    /**
     * 获取 XML Document 对象后，会根据该对象和 Resource 资源对象调用 {@link XmlBeanDefinitionReader#registerBeanDefinitions(Document, Resource)}
     * 方法，开始注册 BeanDefinitions 之旅
     * {@link XmlBeanDefinitionReader#registerBeanDefinitions(Document, Resource)}
     * <p>
     * {@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#parseBeanDefinitions(Element, BeanDefinitionParserDelegate)}
     * <h2>小结</h2>
     * 至此，XmlBeanDefinitionReader#doLoadBeanDefinitions(InputSource inputSource, Resource xml) 方法中，做的三件事情已经全部分析完毕，
     * 下面将对 BeanDefinition 的解析过程做详细分析说明。参考{@link SpringBeanDefinition#main(String[])}
     * <p>
     * 另外，XmlBeanDefinitionReader#doLoadBeanDefinitions(InputSource inputSource, Resource xml) 方法，
     * 整体时序图如,image/o1.png
     */
    public void registerBeanDefinition() {

    }


}


