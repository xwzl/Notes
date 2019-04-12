package com.java.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio中的图谱参考 ./image/nio.jpg
 * <p>
 * Channel 和 Selector，它们是 NIO 中两个核心概念。我们还用前面的城市交通工具来继续比喻 NIO 的工作方式，
 * 这里的 Channel 要比 Socket 更加具体，它可以比作为某种具体的交通工具，如汽车或是高铁等，而 Selector
 * 可以比作为一个车站的车辆运行调度系统，它将负责监控每辆车的当前运行状态：是已经出战还是在路上等等，也就是它
 * 可以轮询每个 Channel 的状态。这里还有一个 Buffer 类，它也比 Stream 更加具体化，我们可以将它比作为车
 * 上的座位，Channel 是汽车的话Buffer就是汽车上的座位，Channel是高铁Buffer就是高铁上的座位，它始终是一
 * 个具体的概念，与 Stream 不同。Stream 只能代表是一个座位，至于是什么座位由你自己去想象，也就是你在去上车
 * 之前并不知道，这个车上是否还有没有座位了，也不知道上的是什么车，因为你并不能选择，这些信息都已经被封装在了
 * 运输工具（Socket）里面了，对你是透明的。
 * <p>
 * NIO 引入了 Channel、Buffer 和 Selector 就是想把这些信息具体化，让程序员有机会控制它们，如：当我们调用
 * write() 往 SendQ 写数据时，当一次写的数据超过 SendQ 长度是需要按照 SendQ 的长度进行分割，这个过程中需
 * 要有将用户空间数据和内核地址空间进行切换，而这个切换不是你可以控制的。而在 Buffer 中我们可以控制 Buffer 的
 * capacity，并且是否扩容以及如何扩容都可以控制。
 * 理解了这些概念后我们看一下，实际上它们是如何工作的。
 * <h2>总结：</h2>
 * <ul>
 * <li><b>Channel：</b>可以比作成具体的交通工具，比如汽车、高铁</li>
 * <li><b>Selector:</b>可以比作是具体交通工具的监控系统或是调试系统，负责监控具体交通工具的的当前运行状态，是
 * 已经出战还是在路上等等，也就是它可以轮询每个Channel （具体交通工具，比如高铁，例如G508、G509、G510）的状态</li>
 * <li><b>Buffer:</b>交通工具里的具体座位，Channel 是汽车的话 Buffer 就是汽车上的座位，Channel 是高铁 Buffer
 * 就是高铁上的座位，它始终是一个具体的概念</li>
 * </ul>
 * <p>
 * 调用 Selector 的静态工厂创建一个选择器，创建一个服务端的 Channel 绑定到一个 Socket 对象，并把这个通信信
 * 道注册到选择器上，把这个通信信道设置为非阻塞模式。然后就可以调用 Selector 的 selectedKeys 方法来检查已经
 * 注册在这个选择器上的所有通信信道是否有需要的事件发生，如果有某个事件发生时，将会返回所有的 SelectionKey，通
 * 过这个对象 Channel 方法就可以取得这个通信信道对象从而可以读取通信的数据，而这里读取的数据是 Buffer，这个
 * Buffer 是我们可以控制的缓冲器。
 * <p>
 * 在上面的这段程序中，是将 Server 端的监听连接请求的事件和处理请求的事件放在一个线程中，但是在实际应用中，我们通常
 * 会把它们放在两个线程中，一个线程专门负责监听客户端的连接请求，而且是阻塞方式执行的；另外一个线程专门来处理请求，这
 * 个专门处理请求的线程才会真正采用 NIO 的方式，像 Web 服务器 Tomcat 和 Jetty 都是这个处理方式。
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
 * @date 2019/03/19 15:47
 */
public class NioBuffer {

    public void selector() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置为非阻塞方式
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        // 注册监听的事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 取得所有key集合
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key
                            .channel();
                    // 接受到服务端的请求
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        // 读取数据
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();
                    }
                    it.remove();
                }
            }
        }
    }


}
