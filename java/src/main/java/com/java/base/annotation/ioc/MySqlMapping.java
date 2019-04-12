package com.java.base.annotation.ioc;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/11 20:49
 */
public class MySqlMapping {

    private String value;

    private String methodName;

    private String className;

    private List<String> methodParamClass;

    private List<String> methodParamValues;

    private String description;

    public MySqlMapping(String value, String methodName, String className, List<String> methodParamClass, List<String> methodParamValues, String description) {
        this.value = value;
        this.methodName = methodName;
        this.className = className;
        this.methodParamClass = methodParamClass;
        this.methodParamValues = methodParamValues;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getMethodParamClass() {
        return methodParamClass;
    }

    public void setMethodParamClass(List<String> methodParamClass) {
        this.methodParamClass = methodParamClass;
    }

    public List<String> getMethodParamValues() {
        return methodParamValues;
    }

    public void setMethodParamValues(List<String> methodParamValues) {
        this.methodParamValues = methodParamValues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
