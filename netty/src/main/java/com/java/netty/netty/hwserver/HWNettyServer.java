package com.java.netty.netty.hwserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * HWServer 服务 基于Http服务。
 *
 * @author xuweizhi
 * @date 2018/12/05 22:03
 */
public class HWNettyServer {

    public static void main(String[] args) throws InterruptedException {

        /*
          事件循环组 两条线组 相当于无限死循环
          1.bossGroup 获取客户端发过来的连接或者请求 转发给workGroup
          2.workGroup 处理bossGroup转发的连接
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup() ;
        try {
            // 服务器启动类，帮助类和辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new HWServerInitializer());
            // 绑定接口
            ChannelFuture bind = serverBootstrap.bind(8888).sync();
            bind.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
