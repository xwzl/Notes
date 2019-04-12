package com.java.spring.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.xml.sax.InputSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link EntityResolver} implementation that attempts to resolve schema URLs into
 * local {@link ClassPathResource classpath resources} using a set of mappings files.
 *
 * <p>By default, this class will look for mapping files in the classpath using the pattern:
 * {@code META-INF/spring.schemas} allowing for multiple files to exist on the
 * classpath at any one time.
 * <p>
 * The format of {@code META-INF/spring.schemas} is a properties
 * file where each line should be of the form {@code systemId=schema-location}
 * where {@code schema-location} should also be a schema file in the classpath.
 * Since systemId is commonly a URL, one must be careful to escape any ':' characters
 * which are treated as delimiters in properties files.
 *
 * <p>The pattern for the mapping files can be overidden using the
 * {@link #PluggableSchemaResolver(ClassLoader, String)} constructor
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public class PluggableSchemaResolver implements EntityResolver {

    /**
     * The location of the file that defines schema mappings.
     * Can be present in multiple JAR files.
     */
    public static final String DEFAULT_SCHEMA_MAPPINGS_LOCATION = "META-INF/spring.schemas";


    private static final Log logger = LogFactory.getLog(PluggableSchemaResolver.class);

    @Nullable
    private final ClassLoader classLoader;

    /**
     * Schema 文件地址
     */
    private final String schemaMappingsLocation;

    /**
     * Stores the mapping of schema URL -> local schema path.
     * namespaceURI 与 Schema 文件地址的映射集合
     */
    private volatile Map<String, String> schemaMappings;

    /**
     * Loads the schema URL -> schema file location mappings using the default
     * mapping file pattern "META-INF/spring.schemas".
     *
     * @param classLoader the ClassLoader to use for loading
     *                    (can be {@code null}) to use the default ClassLoader)
     * @see PropertiesLoaderUtils#loadAllProperties(String, ClassLoader)
     */
    public PluggableSchemaResolver(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.schemaMappingsLocation = DEFAULT_SCHEMA_MAPPINGS_LOCATION;
    }

    /**
     * Loads the schema URL -> schema file location mappings using the given
     * mapping file pattern.
     *
     * @param classLoader            the ClassLoader to use for loading
     *                               (can be {@code null}) to use the default ClassLoader)
     * @param schemaMappingsLocation the location of the file that defines schema mappings
     *                               (must not be empty)
     * @see PropertiesLoaderUtils#loadAllProperties(String, ClassLoader)
     */
    public PluggableSchemaResolver(@Nullable ClassLoader classLoader, String schemaMappingsLocation) {
        Assert.hasText(schemaMappingsLocation, "'schemaMappingsLocation' must not be empty");
        this.classLoader = classLoader;
        this.schemaMappingsLocation = schemaMappingsLocation;
    }

    /**
     * 首先调用 #getSchemaMappings() 方法，获取一个映射表(systemId 与其在本地的对照关系)。
     * <p>
     * 然后，根据传入的 systemId 获取该 systemId 在本地的路径 resourceLocation 。
     * <p>
     * 最后，根据 resourceLocation ，构造 InputSource 对象。
     */
    public InputSource resolveEntity(String publicId, String systemId) throws IOException {
        if (logger.isTraceEnabled()) {
            logger.trace("Trying to resolve XML entity with public id [" + publicId +
                    "] and system id [" + systemId + "]");
        }

        if (systemId != null) {
            // 获得 Resource 所在位置
            String resourceLocation = getSchemaMappings().get(systemId);
            if (resourceLocation != null) {
                // 创建 ClassPathResource
                Resource resource = new ClassPathResource(resourceLocation, this.classLoader);
                try {
                    // 创建 InputSource 对象，并设置 publicId、systemId 属性
                    InputSource source = new InputSource(resource.getInputStream());
                    source.setPublicId(publicId);
                    source.setSystemId(systemId);
                    if (logger.isTraceEnabled()) {
                        logger.trace("Found XML schema [" + systemId + "] in classpath: " + resourceLocation);
                    }
                    return source;
                } catch (FileNotFoundException ex) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Could not find XML schema [" + systemId + "]: " + resource, ex);
                    }
                }
            }
        }
        return null;
    }

    private Map<String, String> getSchemaMappings() {
        Map<String, String> schemaMappings = this.schemaMappings;
        // 双重检查锁，实现 schemaMappings 单例
        if (schemaMappings == null) {
            synchronized (this) {
                schemaMappings = this.schemaMappings;
                if (schemaMappings == null) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("Loading schema mappings from [" + this.schemaMappingsLocation + "]");
                    }
                    try {
                        // 以 Properties 的方式，读取 schemaMappingsLocation
                        Properties mappings = PropertiesLoaderUtils.loadAllProperties(this.schemaMappingsLocation, this.classLoader);
                        if (logger.isTraceEnabled()) {
                            logger.trace("Loaded schema mappings: " + mappings);
                        }
                        // 将 mappings 初始化到 schemaMappings 中
                        schemaMappings = new ConcurrentHashMap<>(mappings.size());
                        CollectionUtils.mergePropertiesIntoMap(mappings, schemaMappings);
                        this.schemaMappings = schemaMappings;
                    } catch (IOException ex) {
                        throw new IllegalStateException(
                                "Unable to load schema mappings from location [" + this.schemaMappingsLocation + "]", ex);
                    }
                }
            }
        }
        return schemaMappings;
    }


    @Override
    public String toString() {
        return "EntityResolver using mappings " + getSchemaMappings();
    }

}
