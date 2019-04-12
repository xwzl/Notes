package com.java.netty.spring.protocol.message.encoder;

import com.java.netty.spring.protocol.message.HeartbeatRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 *
 * @author xuweizhi
 */
public class HeartbeatEncoder extends MessageToByteEncoder<HeartbeatRequestPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HeartbeatRequestPacket msg, ByteBuf out) throws Exception {
        out.writeByte(msg.getVersion());
        out.writeByte(msg.getCommand());
    }
}
