package com.java.netty.spring.server;

import com.java.netty.spring.protocol.message.HeartbeatResponsePacket;
import com.java.netty.spring.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuweizhi
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message msg) throws Exception {
        if (msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
            log.info("收到客户端发来的心跳消息：{}", msg.toString());
            //回应pong
            ctx.writeAndFlush(new HeartbeatResponsePacket());
        } else if (msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
            log.info("收到客户端的业务消息：{}", msg.toString());
            ctx.writeAndFlush(msg);
        }
    }
}
