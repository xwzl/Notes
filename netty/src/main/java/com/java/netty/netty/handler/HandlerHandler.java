package com.java.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author xuweizhi
 * @date 2019/01/20 22:18
 */
public class HandlerHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output" + msg);
        ctx.writeAndFlush("from client" + LocalDateTime.now());
    }

    /**
     * 因为处理器的泛型参数是Long,所以处理器只接long类型的参数，不然无法编码
     * 饶过客户端的处理器，可以用ByteBuf，但是数据格式转换会出错。
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(123456L);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello world", Charset.forName("UTF-8")));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
