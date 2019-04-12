package com.java.netty.netty.tcp3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author xuweizhi
 * @date 2019/01/22 0:05
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    /**
     * TCP粘包，服务端不清楚客户端传递了传递了几条数据，只认为它是一个流，默认为一个消息，所以只触发一次
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String messageToClient = "sent form client";
            byte[] content = messageToClient.getBytes(Charset.forName("UTF-8"));
            int length = messageToClient.getBytes(Charset.forName("UTF-8")).length;
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);
            ctx.writeAndFlush(personProtocol);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的数据：");
        System.out.println("长度:" + length);
        System.out.println("内容：" + new String(content, Charset.forName("UTF-8")));

        System.out.println("客户端端接收到的消息数量" + (++this.count));

    }
}
