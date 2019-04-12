package com.java.netty.nio.socketnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuweizhi
 * @date 2018/12/18 23:09
 */
public class ChatClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 8000));

            while (true) {
                //阻塞事件，有事件在返回
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    //返回true，则表示与服务端建立连接
                    if (key.isConnectable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        //连接是否处于正在进行连接
                        if (client.isConnectionPending()) {
                            //完成连接，真正建立好连接
                            client.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);

                            //客户端与服务端已经建立连接,线程池对象,创建线程使其不阻塞。键盘录入是阻塞函数
                            ExecutorService service = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            service.submit(() -> {
                                while (true) {
                                    try {
                                        writeBuffer.clear();

                                        InputStreamReader reader = new InputStreamReader(System.in);
                                        BufferedReader br = new BufferedReader(reader);

                                        String sendMessage = br.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                        //注册读取时间，获取服务端返回过来的数据
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel receive = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = receive.read(buffer);
                        if (read > 0) {
                            String receiveMessage = new String(buffer.array(), 0, read);
                            System.out.println(receiveMessage);
                        }
                    }
                }
                keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
