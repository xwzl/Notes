package com.java.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * ReplayingDecoder<T> T代表状态管理器，可以为void,比ByteToMessageDecoder更常用
 * <p>
 * 读源码 参考Netty自定义协议
 * public class IntegerHeaderFrameDecoder extends ByteToMessageDecoder {
 *
 * @author xuweizhi
 * @Override protected void decode(ChannelHandlerContext ctx,
 * ByteBuf buf, List<Object> out) throws Exception {
 * <p>
 * if (buf.readableBytes() < 4) {
 * return;
 * }
 * <p>
 * //自定义协议头信息
 * buf.markReaderIndex();
 * int length = buf.readInt();
 * <p>
 * //判断ByteBuf中出头信息长度之外的字节数据，是否满足头长度的字节数。
 * if (buf.readableBytes() < length) {
 * buf.resetReaderIndex();
 * return;
 * }
 * <p>
 * out.add(buf.readBytes(length));
 * }
 * }
 * @date 2019/01/20 22:58
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2 decode invoked !");
        //不用判断传递数据的长度
        out.add(in.readLong());
        }

        }
