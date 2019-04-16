package com.java.frame.jdbc;

import com.java.frame.auto.MyComponent;
import com.java.frame.auto.MyValue;

/**
 * @author xuweizhi
 * @date 2019/04/15 12:50
 */
@MyComponent
public class DataSource {

    @MyValue("${url}")
    private String url;

    @MyValue("${username}")
    private String username;

    @MyValue("${password}")
    private String password;

    @MyValue("${className}")
    private String className;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
