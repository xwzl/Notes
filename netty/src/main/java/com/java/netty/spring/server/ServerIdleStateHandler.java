package com.java.netty.spring.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 *
 * @author xuweizhi
 */
@Slf4j
public class ServerIdleStateHandler extends IdleStateHandler {
    /**
     * 设置空闲检测时间为 30s
     */
    private static final int READER_IDLE_TIME = 30;

    private int count = 0;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
        count = 0;
    }

    /**
     * 如果没有接收到客户端的心跳请求，关闭客户端的连接
     */
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info("{} 秒内没有读取到数据,关闭连接" + count, READER_IDLE_TIME);
        ctx.channel().close();
    }
}
