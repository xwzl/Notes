package com.java.base.io.aio.why;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 和同步IO一样，异步IO也是由操作系统进行支持的。微软的windows系统提供了一种异步IO技术：
 * IOCP（I/O CompletionPort，I/O完成端口）；
 * <p>
 * Linux下由于没有这种异步IO技术，所以使用的是epoll（上文介绍过的一种多路复用IO技术的实
 * 现）对异步IO进行模拟。
 * <h2>要点讲解</h2>
 * 在JAVA NIO框架中，我们说到了一个重要概念“selector”（选择器）。它负责代替应用查询中所
 * 有已注册的通道到操作系统中进行IO事件轮询、管理当前注册的通道集合，定位发生事件的通道等操
 * 操作；但是在JAVA AIO框架中，由于应用程序不是“轮询”方式，而是订阅-通知方式，所以不再需
 * 要“selector”（选择器）了，改由channel通道直接到操作系统注册监听。
 *
 * JAVA AIO框架中，只实现了两种网络IO通道“AsynchronousServerSocketChannel”（服务器
 * 监听通道）、“AsynchronousSocketChannel”（socket套接字通道）。但是无论哪种通道他们
 * 都有独立的fileDescriptor（文件标识符）、attachment（附件，附件可以使任意对象，类似“通
 * 道上下文”），并被独立的SocketChannelReadHandle类实例引用。我们通过debug操作来看看它
 * 们的引用结构：
 */
public class AioSocketServer {


    private static final Object waitObject = new Object();

    public static void main(String[] args) throws Exception {
        /*
         * 对于使用的线程池技术，我一定要多说几句
         * 1、Executors是线程池生成工具，通过这个工具我们可以很轻松的生成“固定大小的线程池”、“调度池”、“可伸缩线程数量的池”。具体请看API Doc
         * 2、当然您也可以通过ThreadPoolExecutor直接生成池。
         * 3、这个线程池是用来得到操作系统的“IO事件通知”的，不是用来进行“得到IO数据后的业务处理的”。要进行后者的操作，您可以再使用一个池（最好不要混用）
         * 4、您也可以不使用线程池（不推荐），如果决定不使用线程池，直接AsynchronousServerSocketChannel.open()就行了。
         * */
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open(group);

        //设置要监听的端口“0.0.0.0”代表本机所有IP设备
        serverSocket.bind(new InetSocketAddress("0.0.0.0", 8083));
        //为AsynchronousServerSocketChannel注册监听，注意只是为AsynchronousServerSocketChannel通道注册监听
        //并不包括为 随后客户端和服务器 socketchannel通道注册的监听
        serverSocket.accept(null, new ServerSocketChannelHandle(serverSocket));

        //等待，以便观察现象（这个和要讲解的原理本身没有任何关系，只是为了保证守护线程不会退出）
        synchronized (waitObject) {
            waitObject.wait();
        }
    }
}


