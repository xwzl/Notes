package com.java.netty.netty.hwserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * server 初始化
 * @author xuweizhi
 * @date 2018/12/05 22:16
 */
public class HWServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //相当于拦截器
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("HWHttpServerHandler",new HWHttpServerHandler());
    }

}
