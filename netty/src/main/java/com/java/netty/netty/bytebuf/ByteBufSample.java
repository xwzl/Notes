package com.java.netty.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Sequential Access Indexing
 * ByteBuf provides two pointer variables to support sequential read and write operations - readerIndex for a read operation and writerIndex for a write operation respectively. The following diagram shows how a buffer is segmented into three areas by the two pointers:
 * 已读可丢弃的字节         未读的字节           可写的字节
 * +-------------------+------------------+------------------+
 * | discardable bytes |  readable bytes  |  writable bytes  |
 * |                   |     (CONTENT)    |                  |
 * +-------------------+------------------+------------------+
 * |                   |                  |                  |
 * 0      <=      readerIndex   <=   writerIndex    <=    capacity
 * <p>
 * clear 方法重置ReaderIndex和WriterIndex指针，但是不会重置内容
 * <p>
 * Heap Buffer（缓冲区）
 * <p>
 * 这是最常用的类型，ByteBuf将数据存储到JVM的堆空间中，并且将实际的数据存放到byte buffer中来实现
 * <p>
 * 优点：由于数据存储在JVM的堆中，因此可以快速的创建与快速的释放，并且它提供了直接访问内部字节数组的方法。
 * 缺点：每次读写数据时，都需要将数据复制到直接缓冲区中再进行网络传输。
 * <p>
 * Direct Buffer（直接缓冲区）
 * 在堆之外直接分配内存空间，直接缓冲区并不会占用堆的容量空间，因为它是由操作系统在本地内存进行的数据分配。
 * 优点：在使用Socket进行数据传递时，性能非常好，因为数据直接位于操作系统的本地内存中，所以不要从JVM将数据复制到直接缓冲区
 * 缺点：因为Direct Buffer 是直接在操作系统内存中，所以内存空间中的分配与释放要比堆空间更复杂，而且速度要慢一些。
 * <p>
 * Netty通过提供内存池来解决这个问题。直接缓冲区并不支持通过支持字节数组的方式来访问数据
 * <p>
 * 重点: 对于后端的业务消息的编解码来说，推荐使用HeapByteBuf:对于I/O通信线程在读写缓冲区，推荐使用DirectByteBuf.
 * <p>
 * Composite Buffer(复合缓冲区，容纳其它buffer)
 * <p>
 * JDK的ByteBuffer与ByteBuf之间的差异对比：
 * <p>
 * 1.Netty的ByteBuf采用了读写索引分离的策略（readerIndex和writerIndex）,一个初始化（里面尚未有任何数据）的ByteBuf的
 * readerIndex与writerIndex值都为0.
 * 2.当读索引与写索引处于同一个位置时，如果我们继续读取，那么就会跑出IndexOutOfBoundsException
 * 3.对于ByteBuf的任何读写操作都会分别单独维护读索引与写索引。macCapacity最大容量默认的限制就是Integer.MAX_VALUE。
 * <p>
 * <p>
 * JDK的ByteBuffer的缺点
 * 1. final byte[] hb;这是JDK的ByteBuffer对象中用于存储数据的对象声明；可以看到，其字节数据是被声明为final的，也就是长
 * 度固定不变的。一旦分配好就不能动态扩容与收缩，而且待存储的数据字节很大时，就很有时就很有可能出现IndexOutOfBoundsException。
 * 如果出现这个异常，那就需要在存储之前完全确定好待存储的字节大小。如果ByteBuffer的空间不足，我们只有一种解决方案：创建一个全新的
 * ByteBuffer对象，然后将之前的ByteBuffer中的数据复制过去，这一切操作都是由开发者自己来完成。
 * 2. ByteBuffer只使用一个position指针来标识位置信息，在进行读写切换时就需要调用flip方法或rewind方法，使用起来很不方便。
 * <p>
 * Netty的ByteBuffer的优点：
 * 1.存储字节的数组是动态的，其最大默认值是Integer.MAX_VALUE。这里的动态性是体现在write方法中的，write方法在执行时会判断
 * buffer容量，如果不足则自动扩容
 * 2.ByteBuf的读写是完全分开的，使用起来就特别方便。
 *
 * 引用计数
 * Netty中ReferenceCounted接口，此对象的实例需要被显示的释放。
 *
 * 当实例被实例化，其初始值为1，每次调用retain和release方法相应的增加1或者减去1，当数值为0的时候对象会被显示的释放，再次访问
 * 时会出现访问无效。如果此接口的实例对象是另外一个实例对象的容器，外层数据释放时，其内部计数也应该释放。
 *
 * 自旋锁，自己更新自己的数据，无限循环，有退出条件。
 *
 * AtomicIntegerFieldUpdater要点总结
 *
 * 1.更新器更新的必须是int类型变量，不能是其包装类型。
 * 2.更新器更新的必须是volatile类型变量，确保线程之间共享变量是的可见性,以及反正从排序。
 * 3.变量不能是static的，必须是实例变量的。因为Unsafe.objectFieldOffset()方法不支持静态变量（CAS操作本质上是通过对象实例的偏移量来直接进行赋值）。
 * 4.更新器只能修改它可见范围内的变量，因为更新器是通过反射来得到这个变量，如果变量不可见就会报错。
 *
 * 如果更新变量是包装类型，那么可以使用AtomicReferenceFieldUpdater来进行更新。
 *
 *
 *
 * @author xuweizhi
 * @date 2019/01/14 16:24
 */
public class ByteBufSample {


    /**
     * ByteBuf
     * <p>
     * 抽象类分为两个维度
     * <p>
     * 1. 池化或者未池化
     * 2. 堆上或者的非堆上
     * <p>
     * Netty ByteBuffer所提供的3中缓冲区
     * <p>
     * 1. heap buffer
     * 2. direct buffer
     * 3. composite buffer 复合缓冲区前两者的结合
     */
    public static void main(String[] args) {
        //baseGrammar();
        //method();
        //simple();

        CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer();

        ByteBuf buffer = Unpooled.buffer(10);

        ByteBuf directBuffer = Unpooled.directBuffer(8);
        compositeBuffer.addComponents(buffer, directBuffer);

        Iterator<ByteBuf> bufIterator = compositeBuffer.iterator();
        while (bufIterator.hasNext()) {
            System.out.println(bufIterator.next());
        }
        compositeBuffer.forEach(System.out::println);
    }

    /**
     * 简单操作
     */
    private static void simple() {
        // 不管是什么类型的数据，最终进行传输的数据是二进制数据
        ByteBuf buffer = Unpooled.copiedBuffer("发达按时发达发顺丰sss 的发斯蒂芬发达sdasd ", Charset.forName("UTF-8"));

        // 如果是堆内内存，返回为true,由字节数组存储数据
        if (buffer.hasArray()) {

            byte[] bytes = buffer.array();
            //System.out.println(buffer);
            String s = new String(bytes, Charset.forName("UTF-8"));
            System.out.println(s);

            //System.out.println(buffer.readerIndex());
            //System.out.println(buffer.writerIndex());
            //System.out.println(buffer.arrayOffset());
            //System.out.println(buffer.capacity());

            int length = buffer.readableBytes();
            System.out.println("可读的长度" + length);
            byte[] b = new byte[length];
            System.arraycopy(bytes, 0, b, 0, length);

            String s1 = new String(b, Charset.forName("UTF-8"));
            System.out.println(s1);
        }
    }

    private static void baseSyntax() {
        //非池化，用完就销毁。
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        // clear 方法重置ReaderIndex和WriterIndex指针，但是不会重置内容
        // discardReadBytes()废弃已读的指针，读指针变为0，写指针变为可读长度，内容发生拷贝，如果不重新覆盖，读取的数据有可能造成部分重叠。
        buffer.clear();

        for (int i = 0; i < 10; i++) {
            System.out.println(buffer.getByte(i));
        }
    }

    /**
     * 注意：
     * ByteBuf通过索引访问Byte时并不会改变真实的读索引与写索引。
     * 我们可以通过ByteBuf的readerIndex()与writerIndex方法分别直接修改读索引和写索引。
     */
    public static void method() {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.readerIndex(i));
        }
        buffer.discardReadBytes();
        //buffer.clear();
        for (int i = 0; i < 10; i++) {
            System.out.println(buffer.getByte(i));
        }
    }


}
