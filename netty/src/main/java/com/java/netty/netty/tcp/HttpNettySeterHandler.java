package com.java.netty.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author xuweizhi
 * @date 2018/12/06 22:14
 */
public class HttpNettySeterHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取远程客户端的地址
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        ctx.channel().writeAndFlush("来自爸爸的问候:" + UUID.randomUUID());
    }

    /**
     * 服务端一般会处理异常 关闭连接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
