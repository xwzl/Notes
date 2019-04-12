package com.java.netty.spring.client;

import com.java.netty.spring.protocol.protobuf.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 */
@Component
@Slf4j
public class NettyClient {
    private EventLoopGroup group = new NioEventLoopGroup();
    @Value("${netty.port}")
    private int port;
    @Value("${netty.host}")
    private String host;
    private SocketChannel socketChannel;

    public void sendMsg(MessageBase.Message message) {
        socketChannel.writeAndFlush(message);
    }

    @PostConstruct
    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientHandlerInitilizer());
        ChannelFuture future = bootstrap.connect();
        //客户端断线重连逻辑
        future.addListener((ChannelFutureListener) future1 -> {
            // 监听客户端是否连接成功
            if (future1.isSuccess()) {
                log.info("连接Netty服务端成功");
            } else {
                log.info("连接失败，进行断线重连");
                //ChannelFuture添加一个监听器，如果客户端连接服务端失败，调用 channel().eventLoop().schedule()方法执行重试逻辑
                future1.channel().eventLoop().schedule(this::start, 20, TimeUnit.SECONDS);
            }
        });
        socketChannel = (SocketChannel) future.channel();
    }

    //@PreDestroy
    //public void destroy() {
    //    log.info("关闭客户端连接");
    //    socketChannel.close();
    //    group.shutdownGracefully();
    //}
}
