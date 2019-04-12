

package com.java.spring.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;

/**
 * EntityResolver implementation for the Spring beans DTD,
 * to load the DTD from the Spring class path (or JAR file).
 *
 * <p>Fetches "spring-beans.dtd" from the class path xml
 * "/org/springframework/beans/factory/xml/spring-beans.dtd",
 * no matter whether specified as some local URL that includes "spring-beans"
 * in the DTD name or as "http://www.springframework.org/dtd/spring-beans-2.0.dtd".
 *
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 * @since 04.06.2003
 * @see org.springframework.beans.factory.xml.ResourceEntityResolver
 */
public class BeansDtdResolver implements EntityResolver {

	private static final Log logger = LogFactory.getLog(BeansDtdResolver.class);

	/**
	 * DTD 文件的后缀
	 */
	private static final String DTD_EXTENSION = ".dtd";
	/**
	 * Spring Bean DTD 的文件名
	 */
	private static final String DTD_NAME = "spring-beans";

	/**
	 * 我们可以看到，加载 DTD 类型的 BeansDtdResolver#resolveEntity(...) 过程，只是对 systemId 进行了简单的校验
	 * （从最后一个 / 开始，内容中是否包含 spring-beans），然后构造一个 InputSource 对象，并设置 publicId、systemId
	 * 属性，然后返回
	 */
	public InputSource resolveEntity(String publicId, String systemId) throws IOException {
		if (logger.isTraceEnabled()) {
			logger.trace("Trying to resolve XML entity with public ID [" + publicId +
					"] and system ID [" + systemId + "]");
		}
		// 必须以 .dtd 结尾
		if (systemId != null && systemId.endsWith(DTD_EXTENSION)) {
			// 获取最后一个 / 的位置
			int lastPathSeparator = systemId.lastIndexOf('/');
			// 获取 spring-beans 的位置
			int dtdNameStart = systemId.indexOf(DTD_NAME, lastPathSeparator);
			// 找到
			if (dtdNameStart != -1) {
				String dtdFile = DTD_NAME + DTD_EXTENSION;
				if (logger.isTraceEnabled()) {
					logger.trace("Trying to locate [" + dtdFile + "] in Spring jar on classpath");
				}
				try {
					// 创建 ClassPathResource 对象
					Resource resource = new ClassPathResource(dtdFile, getClass());
					// 创建 InputSource 对象，并设置 publicId、systemId 属性
					InputSource source = new InputSource(resource.getInputStream());
					source.setPublicId(publicId);
					source.setSystemId(systemId);
					if (logger.isTraceEnabled()) {
						logger.trace("Found beans DTD [" + systemId + "] in classpath: " + dtdFile);
					}
					return source;
				} catch (IOException ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Could not resolve beans DTD [" + systemId + "]: not found in classpath", ex);
					}
				}
			}
		}

		// 使用默认行为，从网络上下载
		// Use the default behavior -> download from website or wherever.
		return null;
	}

	@Override
	public String toString() {
		return "EntityResolver for spring-beans DTD";
	}

}
