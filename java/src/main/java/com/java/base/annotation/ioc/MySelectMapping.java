package com.java.base.annotation.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/15 13:37
 */
public class MySelectMapping {

    private String sql;

    private List<String> paramList = new ArrayList<>();

    private List<String> paramNameList = new ArrayList<>();


    public MySelectMapping() {
    }

    public MySelectMapping(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
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
