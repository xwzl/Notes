package com.java.netty.netty.hwserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author xuweizhi
 * @date 2018/12/05 22:21
 */
public class HWHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 对客户端进行响应
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 浏览器会发起两次请求 一次是favicon.ico请求
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println(httpRequest.getClass());
            System.out.println(ctx.channel().remoteAddress());
            System.out.println("请求路径：" + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("这是一个好消息");
                return;
            }
            // 性能更高性一些 比ByteBuffer
            ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();
        }
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerRemoved");
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelInactive");
        super.channelInactive(ctx);
    }
}
