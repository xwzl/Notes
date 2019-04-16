package com.java.frame.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author xuweizhi
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        // http 编解码
        pipeline.addLast(new HttpServerCodec());

        // http 消息聚合器 512*1024为接收的最大contentlength
        // HttpObjectAggregator是Http消息聚合器，Aggregator这个单次就是“聚合，聚集”的意思。http消息在传输的过程中可能
        // 是一片片的消息片端，所以当服务器接收到的是一片片的时候，就需要HttpObjectAggregator来把它们聚合起来
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));

        // 请求处理器
        pipeline.addLast(new HttpRequestHandler());

    }

}