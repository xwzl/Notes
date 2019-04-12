package com.java.netty.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuweizhi
 * @date 2018/12/14 17:25
 */
public class NioChannel {

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream("netty/src/niofile/NioChannelTest");
        FileChannel channel = in.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Chacacter:" + (char) b);
        }

        in.close();
    }

}
