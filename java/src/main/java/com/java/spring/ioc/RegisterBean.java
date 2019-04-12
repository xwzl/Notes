package com.java.spring.ioc;

import com.java.spring.bean.DependedInjectBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

/**
 * <h2>注册解析的 BeanDefinitions</h2>
 * <p>
 * 解析工作分为三步：
 * <ul>
 * <li>解析默认标签。</li>
 * <li>解析默认标签后下得自定义标签。</li>
 * <li>注册解析后的 BeanDefinition 。</li>
 * </ul>
 * 经过前面两个步骤的解析，这时的 BeanDefinition 已经可以满足后续的使用要求了，
 * 那么接下来的工作就是将这些 BeanDefinition 进行注册，也就是完成第三步。
 *
 * <h2>1. BeanDefinitionReaderUtils</h2>
 * 在{@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#processBeanDefinition(Element, BeanDefinitionParserDelegate)}
 * 注册 BeanDefinition ，由 {@link com.java.spring.util.BeanDefinitionReaderUtils#registerBeanDefinition
 * (BeanDefinitionHolder, BeanDefinitionRegistries)} ()} 完成。
 * <p>
 * {@link RegisterBean#summary()}加载Bean总结
 *
 * @author xuweizhi
 * @date 2019/03/24 0:07
 */
public class RegisterBean {

    /**
     * <h2>总结</h2>
     * IoC 容器的初始化过程分为三步骤：Resource 定位、BeanDefinition 的载入和解析，BeanDefinition 注册。
     * <h3>Resource 定位</h3>
     * 我们一般用外部资源来描述 Bean 对象，所以在初始化 IoC 容器的第一步就是需要定位这个外部资源,详情参考{@link Resource}
     * <h3>BeanDefinition 的装载和解析</h3>
     * 装载就是 BeanDefinition 的载入。BeanDefinitionReader 读取、解析 Resource 资源，也就是将用户定义的 Bean 表示成 IoC 容器的内部数据结构：BeanDefinition 。
     * <ul>
     * <li>在 IoC 容器内部维护着一个 BeanDefinition Map 的数据结构</li>
     * <li>在配置文件中每一个 <bean> 都对应着一个 BeanDefinition 对象。</li>
     * </ul>
     * <h3>BeanDefinition 注册</h3>
     * 向 IoC 容器注册在第二步解析好的 BeanDefinition，这个过程是通过 BeanDefinitionRegistries 接口来实现的。在 IoC 容器内部其实是将第二个过程解析得到的 BeanDefinition 注入到一个 HashMap 容器中，IoC 容器就是通过这个 HashMap 来维护这些 BeanDefinition 的。
     * <ul>
     * <li>在这里需要注意的一点是这个过程<b>并没有完成依赖注入（Bean 创建），Bean 创建是发生在应用第一次调用 #getBean(...) 方法，向容器索要 Bean 时。</b></li>
     * <li>当然我们可以通过设置预处理，即对某个 Bean 设置 lazyinit = false 属性，那么这个 Bean 的依赖注入就会在容器初始化的时候完成。</li>
     * </ul>
     * <h3>1. Resource 定位</h3>
     * Spring 为了解决资源定位的问题，提供了两个接口：Resource、ResourceLoader，其中：
     * <ul>
     * <li>Resource 接口是 Spring 统一资源的抽象接口</li>
     * <li>ResourceLoader 则是 Spring 资源加载的统一抽象。</li>
     * </ul>
     * 更多详情查看{@link Resource}类
     * Resource 资源的定位需要 Resource 和 ResourceLoader 两个接口互相配合，在上面那段代码中 new ClassPathResource("bean.xml") 为我们定义了资源，那么 ResourceLoader 则是在什么时候初始化的呢？看 XmlBeanDefinitionReader 构造方法：
     * <blockquote><pre>
     *     // XmlBeanDefinitionReader.java
     *      public XmlBeanDefinitionReader(BeanDefinitionRegistries registry) {
     * 	        super(registry);
     *      }
     * </pre></blockquote>
     * 直接调用父类 AbstractBeanDefinitionReader 构造方法，代码如下：
     * <blockquote><pre>
     * // AbstractBeanDefinitionReader.java
     *
     * protected AbstractBeanDefinitionReader(BeanDefinitionRegistries registry) {
     * 	Assert.notNull(registry, "BeanDefinitionRegistries must not be null");
     * 	this.registry = registry;
     * 	// Determine ResourceLoader to use.
     * 	if (this.registry instanceof ResourceLoader) {
     * 		this.resourceLoader = (ResourceLoader) this.registry;
     *        }	else {
     * 		this.resourceLoader = new PathMatchingResourcePatternResolver();
     *    }
     *
     * 	// Inherit Environment if possible
     * 	if (this.registry instanceof EnvironmentCapable) {
     * 		this.environment = ((EnvironmentCapable) this.registry).getEnvironment();
     *    } else {
     * 		this.environment = new StandardEnvironment();
     *    }
     * }
     * </pre></blockquote>
     * 核心在于设置 resourceLoader 这段，如果设置了 ResourceLoader 则用设置的，否则使用 PathMatchingResourcePatternResolver ，该类是一个集大成者的 ResourceLoader。
     * <h3>BeanDefinition 的载入和解析</h3>
     * reader.loadBeanDefinitions(resource); 代码段，开启 BeanDefinition 的解析过程。如下：
     * <blockquote><pre></pre>
     * // XmlBeanDefinitionReader.java
     * public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
     * return loadBeanDefinitions(new EncodedResource(resource));
     * }
     * </blockquote>
     * <ul>
     * <li>在这个方法会将资源 resource 包装成一个 EncodedResource 实例对象，然后调用 #loadBeanDefinitions(EncodedResource encodedResource) 方法。而将 Resource 封装成 EncodedResource 主要是为了对 Resource 进行编码，保证内容读取的正确性。代码如下：
     * <blockquote><pre>
     * // XmlBeanDefinitionReader.java
     *
     * public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
     * 	// ... 省略一些代码
     * 	try {
     * 		// 将资源文件转为 InputStream 的 IO 流
     * 		InputStream inputStream = encodedResource.getResource().getInputStream();
     * 		try {
     * 			// 从 InputStream 中得到 XML 的解析源
     * 			InputSource inputSource = new InputSource(inputStream);
     * 			if (encodedResource.getEncoding() != null) {
     * 				inputSource.setEncoding(encodedResource.getEncoding());
     *                        }
     * 			// ... 具体的读取过程
     * 			return doLoadBeanDefinitions(inputSource, encodedResource.getResource());* 		}
     * 		finally {
     * 			inputStream.close();
     *        }
     *    }
     * 	// 省略一些代码
     * }
     *     </pre></blockquote>
     * </li>
     * <li>从 encodedResource 源中获取 xml 的解析源，然后调用 #doLoadBeanDefinitions(InputSource inputSource, Resource resource) 方法，执行具体的解析过程。
     * <blockquote><pre>
     * // XmlBeanDefinitionReader.java
     *
     * protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
     * 			throws BeanDefinitionStoreException {
     * 	try {
     * 		// 获取 XML Document 实例
     * 		Document doc = doLoadDocument(inputSource, resource);
     * 		// 根据 Document 实例，注册 Bean 信息
     * 		int count = registerBeanDefinitions(doc, resource);
     * 		return count;
     *        }
     * 	// ... 省略一堆配置
     * }
     *     </pre></blockquote>
     * 在该方法中主要做两件事：
     * <ul>
     * <li>根据 xml 解析源获取相应的 Document 对象。详细解析，见{@link LoadBean#document()} 。</li>
     * <li>调用 #registerBeanDefinitions(Document doc, Resource resource) 方法，开启 BeanDefinition 的解析注册过程。详细解析，{@link RegisterBean}</li>
     * </ul>
     * </li>
     * </ul>
     * <h3>2.1 转换为 Document 对象</h3>
     * 调用 #doLoadDocument(InputSource inputSource, Resource resource) 方法，会将 Bean 定义的资源转换为 Document 对象。代码如下：
     * <blockquote><pre>
     * // XmlBeanDefinitionReader.java
     *
     * protected Document doLoadDocument(InputSource inputSource, Resource resource) throws Exception {
     * 	return this.documentLoader.loadDocument(inputSource, getEntityResolver(), this.errorHandler,
     * 			getValidationModeForResource(resource), isNamespaceAware());
     * }
     * </pre></blockquote>
     * 该方法接受五个参数：
     * <ul>
     * <li>inputSource ：加载 Document 的 Resource 源。</li>
     * <li>entityResolver ：解析文件的解析器。{@link LoadBean#entityResolver}</li>
     * <li>errorHandler ：处理加载 Document 对象的过程的错误。</li>
     * <li>validationMode ：验证模式。{@link LoadBean#validation()}</li>
     * <li>namespaceAware ：命名空间支持。如果要提供对 XML 名称空间的支持，则为 true 。</li>
     * </ul>
     * 在类 DefaultDocumentLoader 中提供了实现,详情见{@link com.java.spring.xml.DefaultDocumentLoader#loadDocument(InputSource, EntityResolver, ErrorHandler, int, boolean)}
     * <h3>2.2 注册 BeanDefinition 流程</h3>
     * 这到这里，就已经将定义的 Bean 资源文件，载入并转换为 Document 对象了。那么，下一步就是如何将其解析为 SpringIoC 管理的 BeanDefinition 对象，并将其注册到容器中。
     * 这个过程由方法{@link com.java.spring.xml.XmlBeanDefinitionReader#registerBeanDefinitions(Document, org.springframework.core.io.Resource)}完成
     * <blockquote><pre>
     * // XmlBeanDefinitionReader.java
     *
     * public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
     * 	// 创建 BeanDefinitionDocumentReader 对象
     * 	BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
     * 	// 获取已注册的 BeanDefinition 数量
     * 	int countBefore = getRegistry().getBeanDefinitionCount();
     * 	// 创建 XmlReaderContext 对象
     * 	// 注册 BeanDefinition
     * 	documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
     * 	// 计算新注册的 BeanDefinition 数量
     * 	return getRegistry().getBeanDefinitionCount() - countBefore;
     * }
     * </pre></blockquote>
     * <ul>
     * <li>首先，创建 BeanDefinition 的解析器 BeanDefinitionDocumentReader 。</li>
     * <li>然后，调用该 BeanDefinitionDocumentReader 的 #registerBeanDefinitions(Document doc, XmlReaderContext readerContext) 方法，开启解析过程，这里使用的是委派模式，具体的实现由子类 DefaultBeanDefinitionDocumentReader 完成。</li>
     * </ul>
     * <h4>2.2.1 对 Document 对象的解析</h4>
     * 从 Document 对象中获取根元素 root，然后调用 #doRegisterBeanDefinitions(Element root)` 方法，开启真正的解析过程。
     * 详情见{@link com.java.spring.xml.DefaultBeanDefinitionDocumentReader#doRegisterBeanDefinitions(Element)}
     * <ul>
     * <li>#preProcessXml(Element root)、#postProcessXml(Element root) 为前置、后置增强处理，目前 Spring 中都是空实现。</li>
     * <li>#parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) 是对根元素 root 的解析注册过程。其代码如下：
     * <blockquote><pre>
     * // DefaultBeanDefinitionDocumentReader.java
     *
     * protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
     *     // 如果根节点使用默认命名空间，执行默认解析
     *     if (delegate.isDefaultNamespace(root)) {
     *         // 遍历子节点
     *         NodeList nl = root.getChildNodes();
     *         for (int i = 0; i < nl.getLength(); i++) {
     *             Node node = nl.item(i);
     *             if (node instanceof Element) {
     *                 Element ele = (Element) node;
     *                 // 如果该节点使用默认命名空间，执行默认解析
     *                 if (delegate.isDefaultNamespace(ele)) {
     *                     parseDefaultElement(ele, delegate);
     *                 // 如果该节点非默认命名空间，执行自定义解析
     *                 } else {
     *                     delegate.parseCustomElement(ele);
     *                 }
     *             }
     *         }
     *     // 如果根节点非默认命名空间，执行自定义解析
     *     } else {
     *         delegate.parseCustomElement(root);
     *     }
     * }
     * </pre></blockquote>
     * 迭代 root 元素的所有子节点，对其进行判断：
     *    <ul>
     *        <li>若节点为默认命名空间，则调用 #parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) 方法，开启默认标签的解析注册过程。
     *        {@link ParseImportTag}
     *        {@link ParseBeanTag}
     *        </li>
     *        <li>否则，调用 BeanDefinitionParserDelegate#parseCustomElement(Element ele) 方法，开启自定义标签的解析注册过程。详细解析，见
     *        {@link ParseDefineTag}
     *        </li>
     *    </ul>
     * </li>
     * </ul>
     * <h2>3. 小结</h2>
     * 至此，整个 IoC 的初始化过程就已经完成了，从 Bean 资源的定位，转换为 Document 对象，接着对其进行解析，最后注册到 IoC 容器中，都已经完美地完成了。现在 IoC 容器中已经建立了整个 Bean 的配置信息，这些 Bean 可以被检索、使用、维护，他们是控制反转的基础，是后面注入 Bean 的依赖。最后用一张流程图来结束这篇总结之文。
     * <image src="../image/01.jpg"></image>
     * 下一步分析Bean的加载{@link DependedInjectBean}
     */
    private void summary() {
        // 根据 Xml 配置文件创建 Resource 资源对象。ClassPathResource 是 Resource 接口的子类，
        // bean.xml 文件中的内容是我们定义的 Bean 信息。
        ClassPathResource resource = new ClassPathResource("bean.xml");
        //创建一个 BeanFactory 。DefaultListableBeanFactory 是 BeanFactory 的一个子类，BeanFactory
        //作为一个接口，其实它本身是不具有独立使用的功能的，而 DefaultListableBeanFactory 则是真正可以独
        //立使用的 IoC 容器，它是整个 Spring IoC 的始祖，在后续会有专门的Class来分析它。
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //创建 XmlBeanDefinitionReader 读取器，用于载入 BeanDefinition 。
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //开始 BeanDefinition 的载入和注册进程，完成后的 BeanDefinition 放置在 IoC 容器中。
        reader.loadBeanDefinitions(resource);
    }

    static {
        RegisterBean register = new RegisterBean();
        register.summary();
    }
}
