package com.java.netty.netty.handler;

import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuweizhi
 * @date 2019/01/20 22:02
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx, Long msg, io.netty.buffer.ByteBuf out) throws Exception {
        System.out.println("encode invoked!");
        System.out.println(msg);
        out.writeLong(msg);
    }
}
