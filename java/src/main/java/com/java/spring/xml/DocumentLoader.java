package com.java.spring.xml;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

public interface DocumentLoader {

        /**
         * 获取 Document 的策略
         *
         * @param inputSource    方法参数，加载 Document 的 Resource 资源。
         * @param entityResolver 方法参数，解析文件的解析器。
         * @param errorHandler   方法参数，处理加载 Document 对象的过程的错误。
         * @param validationMode 方法参数，验证模式。
         * @param namespaceAware 方法参数，命名空间支持。如果要提供对 XML 名称空间的支持，则需要值为 true 。
         * @return f
         * @throws Exception e
         */
        Document loadDocument(
                InputSource inputSource, EntityResolver entityResolver,
                ErrorHandler errorHandler, int validationMode, boolean namespaceAware)
                throws Exception;

    }