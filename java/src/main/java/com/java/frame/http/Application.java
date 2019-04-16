package com.java.frame.http;

/**
 * @author xuweizhi
 */
public class Application {

    public static void main(String[] args) throws Exception {
        // 8081为启动端口
        HttpServer server = new HttpServer(8081);
        server.start();
    }
}