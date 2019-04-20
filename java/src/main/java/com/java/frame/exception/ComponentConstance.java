package com.java.frame.exception;

/**
 * 组件常量值
 *
 * @author xuweizhi
 * @date 2019/04/14 15:51
 */
public class ComponentConstance {

    public final static String CONTROLLER_KEY = "#";

    public final static String MAPPER_KEY = "#";

    public final static String SERVICE_KEY = "#";

    public final static String BEAN_KEY = "#";

    public final static String SQL_PARAM_KEY = "#";

    public final static String ALIAS_KEY = "&";

    /**
     * 获取 #{}中的内容
     */
    public final static String SQL_PATTERN = "\\#\\{(.+?)\\}";

    /**
     * 获取括号内部正则表达式
     */
    public final static String BRACKETS_IN_PATTERN = "(?<=\\()(.+?)(?=\\))";

    public final static String ALL_DATA = "*";

    public final static String GET = "get";

    public final static String POST = "POST";

    public final static String FAVICON = "/favicon.ico";

    public final static String ICON = "icon.png";

    public final static String GITHUB = "static/GitHub.png";

}
