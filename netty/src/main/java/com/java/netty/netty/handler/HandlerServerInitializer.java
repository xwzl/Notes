package com.java.netty.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 若处理器链中某个处理器木有接收到响应的类型，处理器就不会处理相关的类型转换。
 *
 * @author xuweizhi
 * @date 2019/01/20 22:06
 */
public class HandlerServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        //channelPipeline.addLast(new MyByteToLongDecoder());
        channelPipeline.addLast(new MyByteToLongDecoder2());
        channelPipeline.addLast(new MyLongToStringDecoder());
        channelPipeline.addLast(new MyLongToByteEncoder());
        channelPipeline.addLast(new HandlerServerHandler());

    }
}
