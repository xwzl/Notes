package com.java.netty.netty.tcp3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 * @author xuweizhi
 * @date 2019/01/20 22:16
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new MyPersonDecoder());
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new MyClientHandler());
    }
}
