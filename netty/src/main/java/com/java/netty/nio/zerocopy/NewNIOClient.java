package com.java.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xuweizhi
 * @date 2018/12/20 17:22
 */
public class NewNIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        socketChannel.configureBlocking(true);

        String filename = "E:\\BaiduYunDownload\\《深入掌握Git与实战开发》\\Git应用开发详解（第2讲 课程内容解读）-吾爱程序猿论坛.mp4";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        long startTime = System.currentTimeMillis();

        // 实际传输的字节数，把文件的内容从第position位置开始，传递长度为length
        // 零拷贝值得是数据只从磁盘读出到内存中，然后protocol引擎通过收集方法，把数据直接从内存中把数据写到socket
        // windows 应该不支持这个
        // 将从通道关联的文件，将文件中的字节传递到给定可写的btye channle中，这个方法比简单的循环效率要高，很多操作系
        // 统可以传输字节直接从文件系统缓存中直接传输，而不是去复制他们。
        long l = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数组:" + l + ",耗时:" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }

}
