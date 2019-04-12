package com.java.netty.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *
 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 *
 * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
 *
 * scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你
 * 可以方便的处理消息头和消息体.
 *
 * 之前我们read或write都是操作一个buffer 装满之后重新定义position位置重新在往里面读或写，而Scattering 不仅可以传一个buffer 还可以传buffer数组 比如
 * 我们一个buffer[0] 长度2  buffer[1] 长度5    buffer[2] 长度9，只有把第一个读满 在去读第二个，而Gathering 写也可以传buffer数组。
 * 那么什么时候会这么使用呢：
 * 比如我们在网络操作的时候 自定义协议 第一个传递过来的请求数据 第一个 buffer[1] 长度5   buffer[2] 长度9 作为Header buffer[3] 作为 Body 
 *
 * @author xuweizhi
 * @date 2018/12/17 23:03
 */
public class GatheringAndScattering {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8888);
        server.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[0] = ByteBuffer.allocate(3);
        buffers[0] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = server.accept();
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength) {
                long r = socketChannel.read(buffers);
                byteRead += r;
                System.out.println("byteRead:" + byteRead);
                Arrays.asList(buffers).stream().map(byteBuffer -> "position:" + byteBuffer.position() + "limit:" + byteBuffer.limit()).forEach(System.out::println);
            }
            Arrays.asList(buffers).forEach(ByteBuffer::flip);

            long writeByte = 0;
            while (writeByte < messageLength) {
                long r = socketChannel.write(buffers);
                writeByte += r;
            }

            Arrays.asList(buffers).forEach(ByteBuffer::clear);
            System.out.println("byteRead:" + byteRead + ",ByteWritten:" + writeByte + ",messageLenth:" + messageLength);
        }
    }
}

