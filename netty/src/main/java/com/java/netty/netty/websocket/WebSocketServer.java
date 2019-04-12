package com.java.netty.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author xuweizhi
 * @date 2018/12/09 22:26
 */
public class WebSocketServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(boosGroup, workGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new WebSocketServerInitializer());
            ChannelFuture channelFuture = bootStrap.bind(new InetSocketAddress(8881)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
