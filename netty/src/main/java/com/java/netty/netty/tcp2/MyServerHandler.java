package com.java.netty.netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 *  每一次客户端连接，server段的Initialier都会被调用一次，重新想通道中添加handler处理器。
 * 因此计数每次都会被重置
 *
 * @author xuweizhi
 * @date 2019/01/20 22:11
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] b = new byte[msg.readableBytes()];
        msg.readBytes(b);

        String value = new String(b, Charset.forName("UTF-8"));
        System.out.println("服务端接收到的消息内容" + value);
        System.out.println("服务端接收到的消息数量" + (++this.count));

        ByteBuf copiedBuffer = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("UTF-8"));
        ctx.writeAndFlush(copiedBuffer);

    }

}
