package com.java.spring.xml;

import com.java.spring.ioc.LoadBean;
import com.java.spring.ioc.ParseDefineTag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.parsing.*;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.*;
import org.springframework.core.Constants;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    // 禁用验证模式
    private static final int VALIDATION_NONE = XmlValidationModeDetector.VALIDATION_NONE;
    // 自动获取验证模式
    private static final int VALIDATION_AUTO = XmlValidationModeDetector.VALIDATION_AUTO;
    // DTD 验证模式
    private static final int VALIDATION_DTD = XmlValidationModeDetector.VALIDATION_DTD;
    // XSD 验证模式
    private static final int VALIDATION_XSD = XmlValidationModeDetector.VALIDATION_XSD;

    /**
     * Constants instance for this class.
     */
    private static final Constants constants = new Constants(org.springframework.beans.factory.xml.XmlBeanDefinitionReader.class);

    /**
     * 验证模式。默认为自动模式。
     */
    private int validationMode = VALIDATION_AUTO;

    private boolean namespaceAware = false;

    private Class<? extends BeanDefinitionDocumentReader> documentReaderClass =
            (Class<? extends BeanDefinitionDocumentReader>) DefaultBeanDefinitionDocumentReader.class;

    private ProblemReporter problemReporter = new FailFastProblemReporter();

    private ReaderEventListener eventListener = new EmptyReaderEventListener();

    private SourceExtractor sourceExtractor = new NullSourceExtractor();

    @Nullable
    private NamespaceHandlerResolver namespaceHandlerResolver;

    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    @Nullable
    private EntityResolver entityResolver;

    private ErrorHandler errorHandler = new SimpleSaxErrorHandler(logger);

    /**
     * XML 验证模式探测器
     */
    private final XmlValidationModeDetector validationModeDetector = new XmlValidationModeDetector();

    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded =
            new NamedThreadLocal<>("XML bean definition resources currently being loaded");


    /**
     * Create new XmlBeanDefinitionReader for the given bean factory.
     *
     * @param registry the BeanFactory to load bean definitions into,
     *                 in the form of a BeanDefinitionRegistries
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }


    /**
     * Set whether to use XML validation. Default is {@code true}.
     * <p>This method switches namespace awareness on if validation is turned off,
     * in order to still process schema namespaces properly in such a scenario.
     *
     * @see #setValidationMode
     * @see #setNamespaceAware
     */
    public void setValidating(boolean validating) {
        this.validationMode = (validating ? VALIDATION_AUTO : VALIDATION_NONE);
        this.namespaceAware = !validating;
    }

    /**
     * Set the validation mode to use by name. Defaults to {@link #VALIDATION_AUTO}.
     *
     * @see #setValidationMode
     */
    public void setValidationModeName(String validationModeName) {
        setValidationMode(constants.asNumber(validationModeName).intValue());
    }

    /**
     * Set the validation mode to use. Defaults to {@link #VALIDATION_AUTO}.
     * <p>Note that this only activates or deactivates validation itself.
     * If you are switching validation off for schema files, you might need to
     * activate schema namespace support explicitly: see {@link #setNamespaceAware}.
     */
    public void setValidationMode(int validationMode) {
        this.validationMode = validationMode;
    }

    /**
     * Return the validation mode to use.
     */
    public int getValidationMode() {
        return this.validationMode;
    }

    /**
     * Set whether or not the XML parser should be XML namespace aware.
     * Default is "false".
     * <p>This is typically not needed when schema validation is active.
     * However, without validation, this has to be switched to "true"
     * in order to properly process schema namespaces.
     */
    public void setNamespaceAware(boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
    }

    /**
     * Return whether or not the XML parser should be XML namespace aware.
     */
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    /**
     * Specify which {@link org.springframework.beans.factory.parsing.ProblemReporter} to use.
     * <p>The default implementation is {@link org.springframework.beans.factory.parsing.FailFastProblemReporter}
     * which exhibits fail fast behaviour. External tools can provide an alternative implementation
     * that collates errors and warnings for display in the tool UI.
     */
    public void setProblemReporter(@Nullable ProblemReporter problemReporter) {
        this.problemReporter = (problemReporter != null ? problemReporter : new FailFastProblemReporter());
    }

    /**
     * Specify which {@link ReaderEventListener} to use.
     * <p>The default implementation is EmptyReaderEventListener which discards every event notification.
     * External tools can provide an alternative implementation to monitor the components being
     * registered in the BeanFactory.
     */
    public void setEventListener(@Nullable ReaderEventListener eventListener) {
        this.eventListener = (eventListener != null ? eventListener : new EmptyReaderEventListener());
    }

    /**
     * Specify the {@link SourceExtractor} to use.
     * <p>The default implementation is {@link NullSourceExtractor} which simply returns {@code null}
     * as the source object. This means that - during normal runtime execution -
     * no additional source metadata is attached to the bean configuration metadata.
     */
    public void setSourceExtractor(@Nullable SourceExtractor sourceExtractor) {
        this.sourceExtractor = (sourceExtractor != null ? sourceExtractor : new NullSourceExtractor());
    }

    /**
     * Specify the {@link NamespaceHandlerResolver} to use.
     * <p>If none is specified, a default instance will be created through
     * {@link #createDefaultNamespaceHandlerResolver()}.
     */
    public void setNamespaceHandlerResolver(@Nullable NamespaceHandlerResolver namespaceHandlerResolver) {
        this.namespaceHandlerResolver = namespaceHandlerResolver;
    }

    /**
     * Specify the {@link DocumentLoader} to use.
     * <p>The default implementation is {@link DefaultDocumentLoader}
     * which loads {@link Document} instances using JAXP.
     */
    public void setDocumentLoader(@Nullable DocumentLoader documentLoader) {
        this.documentLoader = (documentLoader != null ? documentLoader : new DefaultDocumentLoader());
    }

    /**
     * Set a SAX entity resolver to be used for parsing.
     * <p>By default, {@link org.springframework.beans.factory.xml.ResourceEntityResolver} will be used. Can be overridden
     * for custom entity resolution, for controller relative to some specific base path.
     */
    public void setEntityResolver(@Nullable EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    /**
     * 如果 ResourceLoader 不为 null，则根据指定的 ResourceLoader 创建一个 ResourceEntityResolver 对象。
     *
     * 如果 ResourceLoader 为 null ，则创建 一个 DelegatingEntityResolver 对象。该 Resolver 委托给默认的 BeansDtdResolver 和 PluggableSchemaResolver 。
     *
     *
     * Return the EntityResolver to use, building a default resolver
     * if none specified.
     */
    protected EntityResolver getEntityResolver() {
        if (this.entityResolver == null) {
            // Determine default EntityResolver to use.
            ResourceLoader resourceLoader = getResourceLoader();
            if (resourceLoader != null) {
                this.entityResolver = (EntityResolver) new ResourceEntityResolver(resourceLoader);
            } else {
                this.entityResolver = (EntityResolver) new DelegatingEntityResolver(getBeanClassLoader());
            }
        }
        return this.entityResolver;
    }

    /**
     * Set an implementation of the {@code org.xml.sax.ErrorHandler}
     * interface for custom handling of XML parsing errors and warnings.
     * <p>If not set, a default SimpleSaxErrorHandler is used that simply
     * logs warnings using the logger instance of the view class,
     * and rethrows errors to discontinue the XML transformation.
     *
     * @see SimpleSaxErrorHandler
     */
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * XmlBeanDefinitionReader.java
     * <p>
     * Specify the {@link BeanDefinitionDocumentReader} implementation to use,
     * responsible for the actual reading of the XML bean definition document.
     * <p>The default is {@link DefaultBeanDefinitionDocumentReader}.
     *
     * @param documentReaderClass the desired BeanDefinitionDocumentReader implementation class
     */
    public void setDocumentReaderClass(Class<? extends BeanDefinitionDocumentReader> documentReaderClass) {
        this.documentReaderClass = documentReaderClass;
    }


    /**
     * <ul>
     * <li>从指定的 xml 文件加载 Bean Definition ，这里会先对 Resource 资源封装成 org.springframework.core.io.
     * support.EncodedResource 对象。这里为什么需要将 Resource 封装成 EncodedResource 呢？主要是为了对 Resource
     * 进行编码，保证内容读取的正确性。</li>
     * <li>然后，再调用 #loadBeanDefinitions(EncodedResource encodedResource) 方法，执行真正的逻辑实现。</li>
     * </ul>
     * Load bean definitions from the specified XML file.
     *
     * @param resource the xml descriptor for the XML file
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     */
    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(new EncodedResource(resource));
    }

    /**
     * <1> 处，通过 resourcesCurrentlyBeingLoaded.get() 代码，来获取已经加载过的资源，然后将
     * encodedResource 加入其中，如果 resourcesCurrentlyBeingLoaded 中已经存在该资源，则抛出
     * BeanDefinitionStoreException 异常。<b>为什么需要这么做呢？答案在 "Detected cyclic loading" ，
     * 避免一个 EncodedResource 在加载时，还没加载完成，又加载自身，从而导致死循环。也因此，在 <3>
     * 处，当一个 EncodedResource 加载完成后，需要从缓存中剔除。</b>
     * <p></p>
     * <2> 处理，从 encodedResource 获取封装的 Resource 资源，并从 Resource 中获取相应的 InputStream ，
     * 然后将 InputStream 封装为 InputSource ，最后调用 #doLoadBeanDefinitions(InputSource inputSource,
     * Resource xml) 方法，<b>执行加载 Bean Definition 的真正逻辑。</b>
     * <p>
     * <p>
     * Load bean definitions from the specified XML file.
     *
     * @param encodedResource the xml descriptor for the XML file,
     *                        allowing to specify an encoding to use for parsing the file
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     */
    public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
        Assert.notNull(encodedResource, "EncodedResource must not be null");
        if (logger.isTraceEnabled()) {
            logger.trace("Loading XML bean definitions from " + encodedResource);
        }

        // <1> 获取已经加载过的资源
        Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();
        if (currentResources == null) {
            currentResources = new HashSet<>(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }
        if (!currentResources.add(encodedResource)) { // 将当前资源加入记录中。如果已存在，抛出异常
            throw new BeanDefinitionStoreException("Detected cyclic loading of " + encodedResource + " - check your import definitions!");
        }
        try {
            // <2> 从 EncodedResource 获取封装的 Resource ，并从 Resource 中获取其中的 InputStream
            InputStream inputStream = encodedResource.getResource().getInputStream();
            try {
                InputSource inputSource = new InputSource(inputStream);
                if (encodedResource.getEncoding() != null) { // 设置编码
                    inputSource.setEncoding(encodedResource.getEncoding());
                }
                // 核心逻辑部分，执行加载 BeanDefinition
                return doLoadBeanDefinitions(inputSource, encodedResource.getResource());
            } finally {
                inputStream.close();
            }
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + encodedResource.getResource(), ex);
        } finally {
            // 从缓存中剔除该资源 <3>
            currentResources.remove(encodedResource);
            if (currentResources.isEmpty()) {
                this.resourcesCurrentlyBeingLoaded.remove();
            }
        }
    }

    /**
     * Load bean definitions from the specified XML file.
     *
     * @param inputSource the SAX InputSource to read from
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     */
    public int loadBeanDefinitions(InputSource inputSource) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(inputSource, "xml loaded through SAX InputSource");
    }

    /**
     * Load bean definitions from the specified XML file.
     *
     * @param inputSource         the SAX InputSource to read from
     * @param resourceDescription a description of the xml
     *                            (can be {@code null} or empty)
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     */
    public int loadBeanDefinitions(InputSource inputSource, @Nullable String resourceDescription)
            throws BeanDefinitionStoreException {

        return doLoadBeanDefinitions(inputSource, new DescriptiveResource(resourceDescription));
    }


    /**
     * Actually load bean definitions from the specified XML file.
     *
     * @param inputSource the SAX InputSource to read from
     * @param resource    the xml descriptor for the XML file
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of loading or parsing errors
     * @see #doLoadDocument
     * @see #registerBeanDefinitions
     */
    protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
            throws BeanDefinitionStoreException {
        try {
            // <1> 获取 XML Document 实例 进入
            Document doc = doLoadDocument(inputSource, resource);
            // <2> 根据 Document 实例，注册 Bean 信息 进入
            int count = registerBeanDefinitions(doc, resource);
            if (logger.isDebugEnabled()) {
                logger.debug("Loaded " + count + " bean definitions from " + resource);
            }
            return count;
        } catch (BeanDefinitionStoreException ex) {
            throw ex;
        } catch (SAXParseException ex) {
            throw new XmlBeanDefinitionStoreException(resource.getDescription(),
                    "Line " + ex.getLineNumber() + " in XML document from " + resource + " is invalid", ex);
        } catch (SAXException ex) {
            throw new XmlBeanDefinitionStoreException(resource.getDescription(),
                    "XML document from " + resource + " is invalid", ex);
        } catch (ParserConfigurationException ex) {
            throw new BeanDefinitionStoreException(resource.getDescription(),
                    "Parser configuration exception parsing XML from " + resource, ex);
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException(resource.getDescription(),
                    "IOException parsing XML document from " + resource, ex);
        } catch (Throwable ex) {
            throw new BeanDefinitionStoreException(resource.getDescription(),
                    "Unexpected exception parsing XML document from " + resource, ex);
        }
    }

    /**
     * 获取 XML Document 实例
     * <p>
     * 调用 #getValidationModeForResource(Resource xml) 方法，获取指定资源（xml）的验证模式。
     * 详情见{@link XmlBeanDefinitionReader#getValidationModeForResource(Resource)}
     * {@link LoadBean#validation()}
     * <p>
     * 调用 DocumentLoader#loadDocument(InputSource inputSource, EntityResolver entityResolver,
     * ErrorHandler errorHandler, int validationMode, boolean namespaceAware) 方法，获取 XML Document 实例
     * 详情见{@link LoadBean#document()}
     * <p>
     * registerBeanDefinitions 详情见registerBeanDefinitions{@link SpringRegisterBeanDefinitions6}
     * <p>
     * Actually load the specified document using the configured DocumentLoader.
     *
     * @param inputSource the SAX InputSource to read from
     * @param resource    the xml descriptor for the XML file
     * @return the DOM Document
     * @throws Exception when thrown from the DocumentLoader
     * @see #setDocumentLoader
     * @see DocumentLoader#loadDocument
     */
    protected Document doLoadDocument(InputSource inputSource, Resource resource) throws Exception {
        return this.documentLoader.loadDocument(inputSource, (org.xml.sax.EntityResolver) getEntityResolver(), this.errorHandler,
                getValidationModeForResource(resource), isNamespaceAware());
    }

    /**
     * <1> 处，调用 #getValidationMode() 方法，获取指定的验证模式( validationMode )。如果有手动指定，
     * 则直接返回。另外，对对于 validationMode 属性的设置和获得的代码，set和get方法
     * <2> 处，调用 {@link XmlValidationModeDetector#detectValidationMode(InputStream)}方法，
     * 自动获取验证模式。
     * <p>
     * Determine the validation mode for the specified {@link Resource}.
     * If no explicit validation mode has been configured, then the validation
     * mode gets {@link #detectValidationMode detected} from the given xml.
     * <p>Override this method if you would like full control over the validation
     * mode, even when something other than {@link #VALIDATION_AUTO} was set.
     *
     * @see #detectValidationMode
     */
    protected int getValidationModeForResource(Resource resource) {
        // <1> 获取指定的验证模式
        int validationModeToUse = getValidationMode();
        // 首先，如果手动指定，则直接返回
        if (validationModeToUse != VALIDATION_AUTO) {
            return validationModeToUse;
        }
        // <2>其次，自动获取验证模式
        int detectedMode = detectValidationMode(resource);
        if (detectedMode != VALIDATION_AUTO) {
            return detectedMode;
        }
        // <3> 最后，使用 VALIDATION_XSD 做为默认
        // Hmm, we didn't get a clear indication... Let's assume XSD,
        // since apparently no DTD declaration has been found up util
        // detection stopped (before finding the document's root tag).
        return VALIDATION_XSD;
    }

    /**
     * 核心在于 <x> 处，调用 {@link XmlBeanDefinitionReader#detectValidationMode(Resource)} 方法，获取相应的验证模式。
     * Detect which kind of validation to perform on the XML file identified
     * by the supplied {@link Resource}. If the file has a {@code DOCTYPE}
     * definition then DTD validation is used otherwise XSD validation is assumed.
     * <p>Override this method if you would like to customize resolution
     * of the {@link #VALIDATION_AUTO} mode.
     */
    protected int detectValidationMode(Resource resource) {
        // 不可读，抛出 BeanDefinitionStoreException 异常
        if (resource.isOpen()) {
            throw new BeanDefinitionStoreException(
                    "Passed-in Resource [" + resource + "] contains an open stream: " +
                            "cannot determine validation mode automatically. Either pass in a Resource " +
                            "that is able to create fresh streams, or explicitly specify the validationMode " +
                            "on your XmlBeanDefinitionReader instance.");
        }

        // 打开 InputStream 流
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException(
                    "Unable to determine validation mode for [" + resource + "]: cannot open InputStream. " +
                            "Did you attempt to load directly from a SAX InputSource without specifying the " +
                            "validationMode on your XmlBeanDefinitionReader instance?", ex);
        }

        // <x> 获取相应的验证模式
        try {
            return this.validationModeDetector.detectValidationMode(inputStream);
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("Unable to determine validation mode for [" +
                    resource + "]: an error occurred whilst reading from the InputStream.", ex);
        }
    }

    /**
     * Register the bean definitions contained in the given DOM document.
     * Called by {@code loadBeanDefinitions}.
     * <p>Creates a new instance of the parser class and invokes
     * {@code registerBeanDefinitions} on it.
     *
     * @param doc      the DOM document
     * @param resource the xml descriptor (for context information)
     * @return the number of bean definitions found
     * @throws BeanDefinitionStoreException in case of parsing errors
     * @see #loadBeanDefinitions
     * @see #setDocumentReaderClass
     * @see BeanDefinitionDocumentReader#registerBeanDefinitions
     */
    public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
        // <1> 创建 BeanDefinitionDocumentReader 对象
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
        // <2> 获取已注册的 BeanDefinition 数量
        int countBefore = getRegistry().getBeanDefinitionCount();
        // <3> 创建 XmlReaderContext 对象
        // <4> 注册 BeanDefinition
        documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
        // 计算新注册的 BeanDefinition 数量
        return getRegistry().getBeanDefinitionCount() - countBefore;
    }

    /**
     * documentReader 的类
     * <p>
     * 实例化 BeanDefinitionDocumentReader 对象。
     * <p>
     * documentReaderClass 的默认值为 DefaultBeanDefinitionDocumentReader.class 。
     * @see #createBeanDefinitionDocumentReader()
     */
    protected BeanDefinitionDocumentReader createBeanDefinitionDocumentReader() {
        return BeanUtils.instantiateClass(this.documentReaderClass);
    }

    /**
     * Create the {@link XmlReaderContext} to pass over to the document reader.
     */
    public XmlReaderContext createReaderContext(Resource resource) {
        //return new XmlReaderContext(xml, this.problemReporter, this.eventListener,
        //        this.sourceExtractor, this, getNamespaceHandlerResolver());
        return null;
    }

    /**
     * Lazily create a default NamespaceHandlerResolver, if not set before.
     * {@link ParseDefineTag#getNamespaceHandlerResolver()}
     * @see #createDefaultNamespaceHandlerResolver()
     */
    public NamespaceHandlerResolver getNamespaceHandlerResolver() {
        if (this.namespaceHandlerResolver == null) {
            //进入方法，最终返回的是org.springframework.beans.factory.xml.DefaultNamespaceHandlerResolver对象
            this.namespaceHandlerResolver = createDefaultNamespaceHandlerResolver();
        }
        return this.namespaceHandlerResolver;
    }

    /**
     * Create the default implementation of {@link NamespaceHandlerResolver} used if none is specified.
     * <p>The default implementation returns an instance of {@link DefaultNamespaceHandlerResolver}.
     *
     * @see DefaultNamespaceHandlerResolver#DefaultNamespaceHandlerResolver(ClassLoader)
     */
    protected NamespaceHandlerResolver createDefaultNamespaceHandlerResolver() {
        ClassLoader cl = (getResourceLoader() != null ? getResourceLoader().getClassLoader() : getBeanClassLoader());
        return new DefaultNamespaceHandlerResolver(cl);
    }

}