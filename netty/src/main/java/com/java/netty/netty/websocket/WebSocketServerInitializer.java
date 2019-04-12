package com.java.netty.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author xuweizhi
 * @date 2018/12/09 22:34
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //http解码
        pipeline.addLast(new HttpServerCodec());
        //请求分块
        pipeline.addLast(new ChunkedWriteHandler());
        //将请求分段，然后聚合，聚合长度
        pipeline.addLast(new HttpObjectAggregator(8192));
        //ws://server:port/content_path/netty
        pipeline.addLast(new WebSocketServerProtocolHandler("/netty"));
        pipeline.addLast(new WebSocketServerHandler());

    }

}
