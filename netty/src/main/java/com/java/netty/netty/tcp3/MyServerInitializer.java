package com.java.netty.netty.tcp3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 *  每一次客户端连接，server段的Initialier都会被调用一次，重新想通道中添加handler处理器。
 *
 * @author xuweizhi
 * @date 2019/01/20 22:06
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new MyPersonDecoder());
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new MyServerHandler());
    }
}
