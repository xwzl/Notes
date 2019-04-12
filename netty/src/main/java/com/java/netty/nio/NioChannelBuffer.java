package com.java.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <h2>Buffer 中的参数项</h2>
 * <p>
 * 索引	                说明
 * capacity	       缓冲区数组的总长度
 * position	       下一个要操作的数据元素的位置
 * limit	       缓冲区数组中不可操作的下一个元素的位置，limit<=capacity
 * mark	           用于记录当前 position 的前一个位置或者默认是 0
 *
 * @author xuweizhi
 * @date 2018/12/14 17:40
 */
public class NioChannelBuffer {

    public static void main(String[] args) throws IOException {

        FileOutputStream out = new FileOutputStream("netty/src/niofile/NioChannelBuffer");
        FileChannel channel = out.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] bytes = "This is a good day !".getBytes();

        for (int i = 0; i < bytes.length; i++) {
            byteBuffer.put(bytes[i]);
        }

        byteBuffer.flip();

        channel.write(byteBuffer);
        channel.close();
    }
}
