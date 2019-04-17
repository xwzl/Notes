package com.java.frame.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyController RequestHandler
 *
 * @author xuweizhi
 * @date 2019/04/14 15:26
 */
public class MyRequestHandler {

    public final static String PREFIX = "/";

    private String url;

    private Map<String, Class<?>> mapping = new HashMap<>();

    private String methodName;

    private Class<?>[] methodParamTypes;

    private List<String> list = new ArrayList<>();

    private String controllerName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!url.startsWith(PREFIX)) {
            url = PREFIX + url;
        }
        this.url = url;
    }

    public Map<String, Class<?>> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, Class<?>> mapping) {
        this.mapping = mapping;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getMethodParamTypes() {
        return methodParamTypes;
    }

    public void setMethodParamTypes(Class<?>[] methodParamTypes) {
        this.methodParamTypes = methodParamTypes;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
}
