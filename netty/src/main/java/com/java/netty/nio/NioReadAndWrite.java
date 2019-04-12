package com.java.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuweizhi
 * @date 2018/12/17 14:20
 */
public class NioReadAndWrite {

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream("netty/src/niofile/input.txt");
        FileOutputStream out = new FileOutputStream("netty/src/niofile/output.txt");

        FileChannel inputChannel = in.getChannel();
        FileChannel outputChannel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);
            System.out.println("reade:"+read);
            if (read == -1) {
                break;
            }
            buffer.flip();

            outputChannel.write(buffer);

            //buffer.flip();
        }

        inputChannel.close();
        outputChannel.close();

    }
}
