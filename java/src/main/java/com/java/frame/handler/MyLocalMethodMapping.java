package com.java.frame.handler;

/**
 * @author xuweizhi
 * @date 2019/04/11 20:49
 */
public class MyLocalMethodMapping {

    private String value;

    private String methodName;

    private String className;

    private Class<?>[] methodParamClass;

    private String[] methodParamValues;

    private String description;

    public MyLocalMethodMapping() {
    }

    public MyLocalMethodMapping(String value, String methodName, String className, Class<?>[] methodParamClass, String[] methodParamValues, String description) {
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

    public Class<?>[] getMethodParamClass() {
        return methodParamClass;
    }

    public void setMethodParamClass(Class<?>[] methodParamClass) {
        this.methodParamClass = methodParamClass;
    }

    public String[] getMethodParamValues() {
        return methodParamValues;
    }

    public void setMethodParamValues(String[] methodParamValues) {
        this.methodParamValues = methodParamValues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
