package com.java.netty.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 * @author xuweizhi
 * @date 2019/01/20 22:16
 */
public class HandlerClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        //channelPipeline.addLast(new MyByteToLongDecoder());
        channelPipeline.addLast(new MyByteToLongDecoder2());
        channelPipeline.addLast(new MyLongToByteEncoder());
        channelPipeline.addLast(new HandlerHandler());

    }
}
