package com.java.frame.handler;

/**
 * @author xuweizhi
 * @date 2019/04/15 17:57
 */
public class MyUpdateMapping {


    private String sql;

    private String nameSpace;



    public MyUpdateMapping(String sql, String nameSpace) {
        this.sql = sql;
        this.nameSpace = nameSpace;
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



}
