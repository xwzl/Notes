package com.java.spring.xml;

public interface Environment extends PropertyResolver {

    /**
     * 返回此环境下激活的配置文件集
     */
    String[] getActiveProfiles();

    /**
     * 如果未设置激活配置文件，则返回默认的激活的配置文件集
     */
    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);

}
