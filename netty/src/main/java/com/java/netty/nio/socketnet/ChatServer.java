package com.java.netty.nio.socketnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author xuweizhi
 * @date 2018/12/18 18:44
 */
public class ChatServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //非阻塞IO
        socketChannel.configureBlocking(false);
        ServerSocket serverSocket = socketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8000);
        serverSocket.bind(address);

        //创建并注册选择器，初始化注册器，关注连接事件
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                //阻塞 等待它所关注的事件集合，当有key值的时候返true,返回所关注事件的数量
                selector.select();
                //获取的是selectedKeys不是keys
                Set<SelectionKey> keys = selector.selectedKeys();
                keys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        //表示客户端发起连接，获取channel对象，然后把对应的channel与selector绑定，注册。
                        if (selectionKey.isAcceptable()) {
                            //为什么能强制转换，因为只有ServerSocketChannel注册到了Selector上面。
                            ServerSocketChannel socketChannel1 = (ServerSocketChannel) selectionKey.channel();
                            //返回与客户端建立连接的SocketChannel对象
                            client = socketChannel1.accept();
                            //为新的通道注册事件，之前的channel只起选择连接作用，新的对象关注读事件
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            String key = UUID.randomUUID().toString();
                            clientMap.put(key, client);
                        } else if (selectionKey.isReadable()) {
                            //获取注册的channel对象
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            int count = client.read(buffer);

                            if (count > 0) {
                                buffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(buffer).array());
                                System.out.println(receiveMessage);

                                String clientKey = null;

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        clientKey = entry.getKey();
                                    }
                                }

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel sends = entry.getValue();

                                    ByteBuffer writerBuffer = ByteBuffer.allocate(1024);

                                    //写入数据
                                    writerBuffer.put((clientKey + ":" + receiveMessage).getBytes());

                                    writerBuffer.flip();

                                    sends.write(writerBuffer);
                                }
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //System.out.println("xxxxxx");
                //清空所有的key,否则会一直遍历
                keys.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
