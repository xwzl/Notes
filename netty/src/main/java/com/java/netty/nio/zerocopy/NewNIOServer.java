package com.java.netty.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xuweizhi
 * @date 2018/12/20 17:22
 */
public class NewNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel socketServerChannel = ServerSocketChannel.open();
        ServerSocket socket = socketServerChannel.socket();
        // 当一个端口断开连接，端口会保持超时状态，在一段时间内不能连接这个端口。TimeWait
        // 当在绑定端口之前，设置ReuseAddress 属性围true,则端口可直接被复用，而不用等待超时状态。
        // 当一个TCP链接被关闭的时候，链接会保持一段时间超时的状态，通常称为TIME_WAIT状态（socket的状态），对于一个应用尝试去绑定一个
        // 服务器上的某一个端口号上，当服务器这个端口号上的这个链接处于timeout状态时，那么这个应用是不能绑定到这个端口号上，如果启用了
        // setReuseAddress 那么socket链接就可以绑定到这个端口号上，即便这个链接处于timeout状态。也就是说之前有一个socket链接被关闭掉
        // 了，但是并不是说 关闭掉的链接的端口号马上就可以被其他socket链接所使用了，相反这个端口号会在被关闭后的一小段时间内处于超时的状态
        // 也叫TIME_WAIT状态,如果有一个新的socket想要绑定到这个端口号上时 就会提示端口被占用。
        socket.setReuseAddress(true);
        socket.bind(new InetSocketAddress(8888));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = socketServerChannel.accept();
            socketChannel.configureBlocking(true);

            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                    //重置position位置
                    byteBuffer.rewind();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
