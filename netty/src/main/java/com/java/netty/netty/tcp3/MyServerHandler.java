package com.java.netty.netty.tcp3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author xuweizhi
 * @date 2019/01/21 23:51
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {


    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("服务端接收到的数据：");
        System.out.println("长度:" + length);
        System.out.println("内容：" + new String(content, Charset.forName("UTF-8")));

        System.out.println("服务端接收到的消息数量" + (++this.count));

        String responseLength = UUID.randomUUID().toString();
        int messageLength = responseLength.getBytes("UTF-8").length;
        byte[] responseContent = responseLength.getBytes("UTF-8");

        PersonProtocol personProtocol = new PersonProtocol();

        personProtocol.setLength(messageLength);
        personProtocol.setContent(responseContent);

        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}
