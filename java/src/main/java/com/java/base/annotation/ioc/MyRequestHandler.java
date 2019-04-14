package com.java.base.annotation.ioc;

/**
 * MyController RequestHandler
 *
 * @author xuweizhi
 * @date 2019/04/14 15:26
 */
public class MyRequestHandler {

    public final static String PREFIX = "/";

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!url.startsWith(PREFIX)) {
            url = PREFIX + url;
        }
        this.url = url;
    }
}
