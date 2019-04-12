package com.java.netty.nio.socketnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuweizhi
 * @date 2018/12/18 12:06
 */
public class SelectorSever {

    // 通过事件监控，来回切换端口
    // talnet localhost 8888来测试端口
    public static void main(String[] args) throws IOException, InterruptedException {

        int[] ports = new int[5];

        ports[0] = 8001;
        ports[1] = 8002;
        ports[2] = 8003;
        ports[3] = 8004;
        ports[4] = 8005;

        // 选择器可有系统提供的默认方法创建，也可由用户自定义创建。它总是打开的只有调用Close方法才会关闭。
        // 将带有selectorChannel注册到的selector上,它们注册的结果通过SelectionKey标志，有三种标志集合。
        // KeySet：所有事件的集合，比如连接，未连接
        // Selected-key：表示感兴趣的集合，是key Set的集合。
        // Cancelled-key: 表示不感兴趣的集合，但是Selector的取消，通道上面注册的selector还未取消，下一
        // 次被选择时才会被取消。
        // 初始化时为空
        Selector selector = Selector.open();
        System.out.println(SelectorProvider.provider().getClass());
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            //配置是否阻塞
            socketChannel.configureBlocking(false);

            //与通道相关连的socket
            ServerSocket serverSocket = socketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            // 把selector注册到channel上,每次注册都会创建一个新的SelectoionKey对象。
            // 取消一个并不会立即消失，下一次注册的时候才会消失。
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口:" + ports[i]);

        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers:" + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("SelectionKey：" + selectionKeys);
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey sKey = iter.next();
                if (sKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) sKey.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    //不移除，监听事件依然存在
                    iter.remove();
                    System.out.println("获取客户端连接：" + accept);
                } else if (sKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) sKey.channel();
                    int byteRead = 0;
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0) {
                            break;
                        }
                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取：" + byteRead + "，来自于" + socketChannel);
                    //一定要调用这个方法
                    iter.remove();
                }

            }

        }
    }

}

class My {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SocketChannel socketChannel = serverSocketChannel.accept();

        socketChannel.bind(new InetSocketAddress(12888));
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    channel.configureBlocking(false);
                    SocketChannel accept = channel.accept();
                    accept.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获取客户端连接：" + accept);
                }

            }


        }
    }
}