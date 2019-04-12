package com.java.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuweizhi
 * @date 2018/12/17 14:20
 */
public class NioDirectReadAndWrite {

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream("netty/src/niofile/input1.txt");
        FileOutputStream out = new FileOutputStream("netty/src/niofile/output1.txt");

        FileChannel inputChannel = in.getChannel();
        FileChannel outputChannel = out.getChannel();

        // 直接缓存区域 底层调用的是c或者C++的代码实现
        // 详情参见
        // Buffer 中的address 代表的是堆外内存的地址值，表示堆外内存和堆内存交互的联系保障
        // 堆内存：   JVM能直接调用的内存空间，buffer修改快，但是与外设打交道慢
        // 堆外内存： 操作系统调用的内存，直接与IO设备打交道，速度快，性能高
        // 内核=>堆内存：
        // 用户=>无法操作
        // DirectByteBuffer 通过JNI的方式调用操作堆外内存，与IO设备打交道,实现零拷贝。
        // HeapByteBuffer 把JVM内存模型中对数据，先拷贝一份到堆外内存，然后在与外设打交道？为什么不直接操作JVM中的数据呢？
        // 因为系统在操作过程中，JVM内存中的数据有可能部分或者全部被回收，导致数据混乱。
        //
        // 6.1.3. 为何一定要复制到DirectByteBuffer来读写（系统调用）
        // GC会回收无用对象，同时还会进行碎片整理，移动对象在内存中的位置，来减少内存碎片。DirectByteBuffer不受GC控制。如果不用
        // DirectByteBuffer而是用HeapByteBuffer，如果在调用系统调用时，发生了GC，导致HeapByteBuffer内存位置发生了变化，但
        // 是内核态并不能感知到这个变化导致系统调用读取或者写入错误的数据。所以一定要通过不受GC影响的DirectByteBuffer来进行IO系统调用。
        //
        // 假设我们要从网络中读入一段数据，再把这段数据发送出去的话，采用Non-direct ByteBuffer的流程是这样的：
        //
        // 网络 –> 临时的DirectByteBuffer –> 应用 Non-direct ByteBuffer –> 临时的Direct ByteBuffer –> 网络
        //
        // 这种方式是直接在堆外分配一个内存(即，native memory)来存储数据，
        // 程序通过JNI直接将数据读/写到堆外内存中。因为数据直接写入到了堆外内存中，所以这种方式就不会再在JVM管控的堆内再分配内存来
        // 存储数据了，也就不存在堆内内存和堆外内存数据拷贝的操作了。这样在进行I/O操作时，只需要将这个堆外内存地址传给JNI的I/O的
        // 函数就好了。
        // 采用Direct ByteBuffer的流程是这样的：
        // 网络 –> 应用 Direct ByteBuffer –> 网络
        //
        // 可以看到，除开构造和析构临时Direct ByteBuffer的时间外，起码还能节约两次内存拷贝的时间。那么是否在任何情况下都采用Direct
        // Buffer呢？
        //
        // 不是。对于大部分应用而言，两次内存拷贝的时间几乎可以忽略不计，而构造和析构DirectBuffer的时间却相对较长。在JVM的实现当中，
        // 某些方法会缓存一部分临时Direct ByteBuffer，意味着如果采用Direct ByteBuffer仅仅能节约掉两次内存拷贝的时间，
        // 而无法节约构造和析构的时间。就用Sun的实现来说，write(ByteBuffer)和read(ByteBuffer)方法都会缓存临时Direct ByteBuffer，
        // 而write(ByteBuffer[])和read(ByteBuffer[])每次都生成新的临时Direct ByteBuffer。
        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

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
