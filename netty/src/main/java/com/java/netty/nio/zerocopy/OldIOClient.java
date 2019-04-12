package com.java.netty.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author xuweizhi
 * @date 2018/12/20 17:11
 */
public class OldIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);

        String filename = "E:\\BaiduYunDownload\\aaaa.zip";
        InputStream in = new FileInputStream(filename);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = in.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总数" + total + ",耗时" + (System.currentTimeMillis() - startTime ));

        dataOutputStream.close();
        socket.close();
        in.close();
    }

}
