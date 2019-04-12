package com.java.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * ChannelHandlerContext获取上一个处理处理之后的数据
 * 对于这个示例来说，客户端启动激活触发active事件，向服务端发送消息
 * 1.传递过来的二进制流信息，经过MyByteToLongDecoder处理器的处理，把消息转换为Long类型的数据
 * 2.在经过MessageToMessageDecoder处理器的处理，把转换过后的类型转换其他类型，或者java对象
 *
 * @author xuweizhi
 * @date 2019/01/21 10:43
 */
public class MyLongToStringDecoder extends MessageToMessageDecoder<Long> {

    /**
     * 上一个入站处理的类型是Long类型
     * @param ctx 处理器上下文对象
     * @param msg 上个处理传递过来的信息，以存在out中的数据类型为准
     * @param out 向下一个处理器传递的数据
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
        System.out.println("MyLongToStringDecoder decode invoked!");
        out.add(String.valueOf(msg));
    }

}
