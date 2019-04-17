package com.java.frame.http;

import com.java.frame.handler.MyRequestHandler;
import com.java.frame.util.StringUtils;
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
        MyPort port = (MyPort) single.get("com.java.frame.http.MyPort");
        if (StringUtils.isNotEmpty(port.getPort())) {
            this.port = Integer.valueOf(port.getPort());
        }

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer(single, handlers));

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(this.port)).sync();
        if (StringUtils.isNotEmpty(port.getContext())) {
            System.out.println(" server start up on port : " + this.port + " and Application context :" + port.context);
        } else {
            System.out.println(" server start up on port : " + this.port);
        }
        f.channel().closeFuture().sync();

    }

}