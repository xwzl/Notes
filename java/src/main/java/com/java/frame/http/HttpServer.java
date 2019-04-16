package com.java.frame.http;

import com.java.frame.handler.MyRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * @author xuweizhi
 */
public class HttpServer {

    int port;

    Map<String, Object> single;

    Map<String, Map<MyRequestHandler, String>> handlers;

    public HttpServer(int port) {
        this.port = port;
    }


    public HttpServer(int port, Map<String, Object> single, Map<String, Map<MyRequestHandler, String>> handlers) {
        this.single = single;
        this.handlers = handlers;
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer(single,handlers));

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println(" server start up on port : " + port);
        f.channel().closeFuture().sync();

    }

}