package com.java.netty.netty.heart;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 * @date 2018/12/06 23:57
 */
public class HeartServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //空闲状态监测处理器
        pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
        pipeline.addLast(new HeartServerHandler());

    }

}
