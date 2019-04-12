package com.java.netty.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author xuweizhi
 * @date 2018/12/06 22:14
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取远程客户端的地址
        Thread.sleep(1000);
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        ctx.channel().writeAndFlush("客户端的问候：" + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自星星的你！");
    }
}
