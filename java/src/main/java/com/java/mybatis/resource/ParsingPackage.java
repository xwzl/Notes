package com.java.mybatis.resource;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.parsing.PropertyParser;
import org.apache.ibatis.parsing.XPathParser;
import org.xml.sax.EntityResolver;

import java.util.Properties;

/**
 * 断点调试
 * <p>
 * {@link XPathParser#XPathParser(String, boolean, Properties, EntityResolver)}
 * {@link XMLMapperEntityResolver#resolveEntity}
 * {@link XMLMapperEntityResolver#getInputSource}
 * {@link PropertyParser#parse(String, Properties)} 解析xml  '${}'
 *
 * @author xuweizhi
 * @date 2019/04/08 11:29
 */
public class ParsingPackage {
}
