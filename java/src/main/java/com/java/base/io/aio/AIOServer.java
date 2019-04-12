package com.java.base.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Administrator
 */
public class AIOServer implements Runnable {

    /**
     * 端口
     */
    private int port = 8889;

    /**
     * 线程池大小
     */
    private int threadSize = 10;

    /**
     * 异步IO循环组
     */
    protected AsynchronousChannelGroup asynchronousChannelGroup;


    /**
     * 异步IO通道
     */
    protected AsynchronousServerSocketChannel serverChannel;

    public AIOServer(int port, int threadSize) {
        this.port = port;
        this.threadSize = threadSize;
        init();
    }

    private void init() {
        try {
            //初始化线程池
            asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
            //指定线程池执行任务
            serverChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            //绑定端口
            serverChannel.bind(new InetSocketAddress(port));
            System.out.println("listening on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (serverChannel == null) {
                return;
            }
            //指定服务器和处理器处理任务
            serverChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AIOServer>() {
                final ByteBuffer echoBuffer = ByteBuffer.allocateDirect(1024);

                @Override
                public void completed(AsynchronousSocketChannel result, AIOServer attachment) {
                    System.out.println("==============================================================");
                    System.out.println("server process begin ...");
                    try {
                        System.out.println("client host: " + result.getRemoteAddress());
                        echoBuffer.clear();
                        result.read(echoBuffer).get();
                        echoBuffer.flip();
                        System.out.println("received : " + Charset.defaultCharset().decode(echoBuffer));

                        //并发情况下生成一个随机数
                        int random = ThreadLocalRandom.current().nextInt(5);

                        printProcess(random);
                        System.out.println("server deal request execute: " + random + "s");

                        String msg = "server test msg-" + Math.random();
                        System.out.println("server send data: " + msg);
                        result.write(ByteBuffer.wrap(msg.getBytes()));
                        System.out.println("server process end ...");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        // 监听新的请求，递归调用。
                        attachment.serverChannel.accept(attachment, this);
                    }

                }

                @Override
                public void failed(Throwable exc, AIOServer attachment) {
                    System.out.println("received failed");
                    exc.printStackTrace();
                    // 监听新的请求，递归调用。
                    attachment.serverChannel.accept(attachment, this);
                }
            });
            System.out.println("我会执行吗？？");
            //让线程处于等待状态
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printProcess(int s) throws InterruptedException {
        String dot = "";
        for (int i = 0; i < s; i++) {
            Thread.sleep(1000);
            dot += ".";
            System.out.println(dot);

        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new AIOServer(8989, 19)).start();
    }
}