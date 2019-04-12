package com.java.netty.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());
            Channel channel = bootstrap.connect("localhost", 8888).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(reader.readLine()+"\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
