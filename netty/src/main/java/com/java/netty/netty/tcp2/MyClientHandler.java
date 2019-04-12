package com.java.netty.netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author xuweizhi
 * @date 2019/01/20 22:18
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    /**
     * TCP粘包，服务端不清楚客户端传递了传递了几条数据，只认为它是一个流，默认为一个消息，所以只触发一次
     *
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("sent from client"+i, Charset.forName("UTF-8"));
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] b = new byte[msg.readableBytes()];
        msg.readBytes(b);

        String value = new String(b, Charset.forName("UTF-8"));
        System.out.println("客户端端接收到的消息内容" + value);
        System.out.println("客户端接收到的消息数量" + (++this.count));
    }
}
