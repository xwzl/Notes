package com.java.netty.google.grpc;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;

/**
 * @author xuweizhi
 * @date 2018/12/13 15:31
 */
public class GrpcServer {

    private Server server;

    private void start() throws IOException {

        this.server = ServerBuilder.forPort(8888).addService(new StudentServiceImpl()).build().start();

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        System.out.println("服务器初始化完毕");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关机JVM");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里");

    }

    private void stop() {
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
            //this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) {

        try {
            GrpcServer server = new GrpcServer();
            server.start();
            server.awaitTermination();
        } catch (Exception e) {
        }
    }

}
