package com.java.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author xuweizhi
 * @date 2018/12/17 22:42
 */
public class MapperByteBuffers {

    //内存映射文件
    public static void main(String[] args) throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile("netty/src/niofile/mapper", "rw");
        FileChannel channel = accessFile.getChannel();
        //直接与内存打交道，修改值在内存中
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(1, (byte) 'a');
        map.put(3, (byte) 'a');

        FileLock lock = channel.lock(5, 3, true);
        System.out.println("Valid:"+lock.isValid());
        System.out.println("Shared"+lock.isShared());

        lock.release();
        accessFile.close();

    }
}
