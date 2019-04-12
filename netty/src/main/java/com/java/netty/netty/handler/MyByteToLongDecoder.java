package com.java.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 入站处理器，相当于解码
 * @author xuweizhi
 * @date 2019/01/20 21:44
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoked!");
        System.out.println(in.readableBytes());
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }

}
