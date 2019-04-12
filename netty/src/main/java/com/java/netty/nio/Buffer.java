package com.java.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * <h2>Buffer 中的参数项</h2>
 * <p>
 * 索引	                说明
 * capacity	       缓冲区数组的总长度
 * position	       下一个要操作的数据元素的位置
 * limit	       缓冲区数组中不可操作的下一个元素的位置，limit<=capacity
 * mark	           用于记录当前 position 的前一个位置或者默认是 0
 * <p>
 * 我们通过 ByteBuffer.allocate(11) 方法创建一个 11 个 byte 的数组缓冲区，初始状态如上图所示，position
 * 的位置为 0，capacity 和 limit 默认都是数组长度。当我们写入 5 个字节时位置变化如下图所示：
 * <p>
 * 这时我们需要将缓冲区的 5 个字节数据写入 Channel 通信信道，所以我们需要调用 byteBuffer.flip() 方法，数组
 * 的状态又发生如下变化：
 * <p>
 * 这时底层操作系统就可以从缓冲区中正确读取这 5 个字节数据发送出去了。在下一次写数据之前我们在调一下 clear() 方法。
 * 缓冲区的索引状态又回到初始位置。
 * <p>
 * 这里还要说明一下 mark，当我们调用 mark() 时，它将记录当前 position 的前一个位置，当我们调用 reset 时，position
 * 将恢复 mark 记录下来的值。
 * <p>
 * 还有一点需要说明，通过 Channel 获取的 I/O 数据首先要经过操作系统的 Socket 缓冲区再将数据复制到 Buffer 中，
 * 这个的操作系统缓冲区就是底层的 TCP 协议关联的 RecvQ 或者 SendQ 队列，从操作系统缓冲区到用户缓冲区复制数据比较
 * 耗性能，Buffer 提供了另外一种直接操作操作系统缓冲区的的方式即 ByteBuffer.allocateDirector(size)，这个方
 * 法返回的 byteBuffer 就是与底层存储空间关联的缓冲区，它的操作方式与 linux2.4 内核的 sendfile 操作方式类似。
 *
 * @author xuweizhi
 * @date 2018/12/14 16:37
 */
public class Buffer {

    //  NIO 非阻塞流
    //  java.io最核心的概念是流(Stream),面向流的编程。流是信息的载体，分为输入或者输出流，对于一个流来说只能是输出流或者输入流。
    //
    //  java.nio拥有三个核心概念。<br>
    //  Selector,Channel和Buffer.<br>
    //  在java.nio中，我们面向（block）块或缓冲区(buffer)编程的。
    public static void main(String[] args) {

        //初始化intBuffer变量，缓冲区的大小为10
        //buffer本身就是一块内存，底层实现上，他实际上就是一个数组。数据的读写都是通过buffer来实现的的。
        IntBuffer intBuffer = IntBuffer.allocate(10);

        //Buffer最重要的三个属性
        //Capacity 表示Buffer的容量，长度固定且永远不能为负
        System.out.println("Capacity:" + intBuffer.capacity());
        //Limit指的是第一个无法被读或者写的元素的索引，永远不可能为负数或者超过Capacity
        System.out.println("Limit:" + intBuffer.limit());
        //Position 下一个将要被读或者写的元素的索引，永远不会超过limit或者为负
        System.out.println("Position:" + intBuffer.limit());

        //写数据
        for (int i = 0; i < 5; i++) {
            int data = new SecureRandom().nextInt(20);
            intBuffer.put(data);
        }

        System.out.println("Before flip  buffer" + intBuffer.mark());
        //状态的反转 从写变成读
        intBuffer.flip();
        System.out.println("After flip buffer：" + intBuffer.mark());

        while (intBuffer.hasRemaining()) {
            System.out.println("Buffer" + intBuffer.mark());
            System.out.println(intBuffer.get());
        }
        System.out.println(intBuffer.mark());
    }
}
