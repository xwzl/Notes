package com.java.netty.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author xuweizhi
 * @date 2018/12/06 22:06
 */
public class TcpServer {

    public static void main(String[] args) throws InterruptedException {
        /*
          EventLoopGroup 在后续的循环过程中，selector过程中连接客户端
          bossGroup 接收客户端的连接，转发给workGroup 本质上是一个死循环，等待客户端发送该数据
          默认是本机核心数*2 作为线程，但是我们只需要一个线程组作为监听就行，通常设置1
          workGroup 请求与处理
         */
        //底层通过一个线程生成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // group 成员变量的赋值
            // channel class反射方法
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyServerInitializer());
            //   doBind 方法
            //   future 接口 封装异步计算的结果 package java.util.concurrent;
            //   isCanceller 是否被取消
            //   isDone 成功 失败 异常 都会返回true
            //   canceller 取消操作
            //   get 获取异步计算返回结果值
            //   netty 加强的future街口，通过通知的形式获取方法的完成的事件，获取准确的值。
            //   由于Netty中的所有I/O操作都是异步的，因此Netty为了解决调用者如何获取异步操作结果而专门设计了ChannelFuture接口
            /**
             * SIZE_TABLE存放字节缓冲区的大小,通过记录上一次的缓冲数据大小；动态的增加较少缓冲数据量，如果数据稳定，则保持当前缓冲大小。
             * {@link AdaptiveRecvByteBufAllocator#SIZE_TABLE}
             * The {@link RecvByteBufAllocator}that automatically increases and
             * decreases the predicted buffer size on feed back.
             * <p>
             * It gradually increases the expected number of readable bytes if the previous
             * read fully filled the allocated buffer.  It gradually decreases the expected
             * number of readable bytes if the read operation was not able to fill a certain
             * amount of the allocated buffer two times consecutively.  Otherwise, it keeps
             * returning the same prediction.
             */
            ChannelFuture bind = bootstrap.bind(8888).sync();

            bind.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
