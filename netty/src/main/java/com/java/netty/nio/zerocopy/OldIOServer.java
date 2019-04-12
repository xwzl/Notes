package com.java.netty.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuweizhi
 * @date 2018/12/20 17:05
 */
public class OldIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            //阻塞
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[4096];
                while (true) {
                    int readCount = inputStream.read(bytes, 0, bytes.length);
                    if(readCount == -1){
                         break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
