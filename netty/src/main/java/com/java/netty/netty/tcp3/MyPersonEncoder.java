package com.java.netty.netty.tcp3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuweizhi
 * @date 2019/01/21 23:48
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonProtocol encode invoked!");
        //先写长度，在写实际内容
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
