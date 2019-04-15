package com.java.base.annotation.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/15 17:57
 */
public class MyInsertMapping {

    private String sql;

    private String nameSpace;

    private List<String> paramList = new ArrayList<>();

    private List<String> paramNameList = new ArrayList<>();

    public MyInsertMapping(String sql, String nameSpace, List<String> paramList, List<String> paramNameList) {
        this.sql = sql;
        this.nameSpace = nameSpace;
        this.paramList = paramList;
        this.paramNameList = paramNameList;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    public List<String> getParamNameList() {
        return paramNameList;
    }

    public void setParamNameList(List<String> paramNameList) {
        this.paramNameList = paramNameList;
    }
}
