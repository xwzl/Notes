package com.java.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 若没有MyLongToStringDecoder处理器，泛型则是接收第一个处理器的数据类Long
 *
 * @author xuweizhi
 * @date 2019/01/20 22:11
 */
public class HandlerServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        ctx.writeAndFlush(654321L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
