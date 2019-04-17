package com.java.frame.http;


import com.java.frame.auto.MyComponent;
import com.java.frame.auto.MyValue;

/**
 * @author xuweizhi
 * @date 2019/04/17 21:54
 */
@MyComponent(alias = "port")
public class MyPort {

    /**
     * 端口
     */
    @MyValue("${port}")
    String port;

    /**
     * 请求上下文暂时未做，其实很简单啊，稍微修改一下{@link MyConfigure#parseController} 代码就可以解决了
     */
    @MyValue("${context}")
    String context;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
