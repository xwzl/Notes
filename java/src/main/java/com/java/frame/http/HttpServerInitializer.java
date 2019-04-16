package com.java.frame.http;

import com.java.frame.handler.MyRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.Map;

/**
 * @author xuweizhi
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    Map<String, Object> single;

    Map<String, Map<MyRequestHandler, String>> handlers;

    public HttpServerInitializer(Map<String, Object> single, Map<String, Map<MyRequestHandler, String>> handlers) {
        this.single = single;
        this.handlers = handlers;
    }

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
        pipeline.addLast(new HttpRequestHandler(single,handlers));

    }

}