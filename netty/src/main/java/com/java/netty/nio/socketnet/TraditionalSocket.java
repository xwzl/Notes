package com.java.netty.nio.socketnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuweizhi
 * @date 2018/12/18 9:11
 */
public class TraditionalSocket {

    //复杂性永远存在，一个线程对应一个客户端。
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        while (true) {
            //阻塞IO 多线程避免阻塞IO
            Socket socket = server.accept();
            new Thread(() -> {
                try {
                    socket.getInputStream();
                    // to do ...... 吃力时间可能很长，线程会发生阻塞
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
