package com.java.base.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <h2>最原始BIO</h2>
 * 网络编程的基本模型是C/S模型，即两个进程间的通信。
 * <p>
 * 服务端提供IP和监听端口，客户端通过连接操作想服务端监听的地址发起连接请求，
 * 通过三次握手连接，如果连接成功建立，双方就可以通过套接字进行通信。
 * <p>
 * 传统的同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口；Socket
 * 负责发起连接操作。连接成功后，双方通过输入和输出流进行同步阻塞式通信。
 *
 * <h2>存在的问题：</h2>
 *
 * <ul>
 * <li>同一时间，服务器只能接受来自于客户端A的请求信息；虽然客户端A和客户端B的请求
 * 是同时进行的，但客户端B发送的请求信息只能等到服务器接受完A的请求数据后，才能被接受。
 * (acceptor只有在接受完client1的请求后才能接受client2的请求)</li>
 * <li>由于服务器一次只能处理一个客户端请求，当处理完成并返回后（或者异常时），才能
 * 进行第二次请求的处理。很显然，这样的处理方式在高并发的情况下，是不能采用的。</li>
 * </ul>
 * <h2>改进</h2>
 * <p>
 * 此种BIO通信模型的服务端，通常由一个独立的Acceptor线程负责监听客户端的连接，它接收
 * 到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理没处理完成后，通过输出流
 * 返回应答给客户端，线程销毁。即典型的一请求一应答通宵模型。
 * <h2>存在的问题：</h2>
 *
 * <ul>
 * <li>虽然在服务器端，请求的处理交给了一个独立线程进行，但是操作系统通知accept()的方式
 * 还是单个的。也就是，实际上是服务器接收到数据报文后的“业务处理过程”可以多线程，但是数据
 * 报文的接受还是需要一个一个的来(acceptor只有在接受完client1的请求后才能接受client2
 * 的请求)，下文会验证。</li>
 * <li>在linux系统中，可以创建的线程是有限的。我们可以通过cat /proc/sys/kernel/threads-max，
 * 命令查看可以创建的最大线程数。当然这个值是可以更改的，但是线程越多，CPU切换所需的时间也就越长，用
 * 来处理真正业务的需求也就越少。</li>
 * <li>创建一个线程是有较大的资源消耗的。JVM创建一个线程的时候，即使这个线程不做任何的工作，JVM都会
 * 分配一个堆栈空间。这个空间的大小默认为128K，您可以通过-Xss参数进行调整。</li>
 * <li>另外，如果您的应用程序大量使用长连接的话，线程是不会关闭的。这样系统资源的消耗更容易失控。</li>
 * </ul>
 * <h2>阻塞的问题根源</h2>
 * <p>
 * 服务器线程发起一个accept动作，询问操作系统 是否有新的socket套接字信息从端口xx发送过来。
 * <p>
 * 注意，是询问操作系统。也就是说socket套接字的IO模式支持是基于操作系统的，那么自然
 * 同步IO/异步IO的支持就是需要操作系统级别的了。
 * <p>
 * 如果操作系统没有发现有套接字从指定的端口xx来，那么操作系统就会等待。这样serverSocket.accept()
 * 方法就会一直等待。这就是为什么accept()方法为什么会阻塞：它内部的实现是使用的操作系统级别的同步IO。
 * <h3>阻塞IO 和 非阻塞IO </h3>
 * 这两个概念是程序级别的。主要描述的是程序请求操作系统IO操作后，如果IO资源没有准备好，那么程序该如何
 * 处理的问题：前者等待；后者继续执行（并且使用线程一直轮询，直到有IO资源准备好了）
 * <h3>同步IO 和非同步IO </h3>
 * 这两个概念是操作系统级别的。主要描述的是操作系统在收到程序请求IO操作后，如果IO资源没有准备好，该如
 * 何处理相应程序的问题：前者不响应，直到IO资源准备好以后；后者返回一个标记（好让程序和自己知道以后的
 * 数据往哪里通知），当IO资源准备好以后，再用事件机制返回给程序。
 */
public class BioSocketServer {
    //默认的端口号
    private static int DEFAULT_PORT = 8083;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            System.out.println("监听来自于" + DEFAULT_PORT + "的端口信息");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        //这个wait不涉及到具体的实验逻辑，只是为了保证守护线程在启动所有线程后，进入等待状态
        synchronized (Object.class) {
            try {
                BioSocketServer.class.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class SocketServerThread implements Runnable {
    private Socket socket;

    public SocketServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            //下面我们收取信息
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Integer sourcePort = socket.getPort();
            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            //使用线程，同样无法解决read方法的阻塞问题，
            //也就是说read方法处同样会被阻塞，直到操作系统有数据准备好
            int realLen = in.read(contextBytes, 0, maxLen);
            //读取信息
            String message = new String(contextBytes, 0, realLen);

            //下面打印信息
            System.out.println("服务器收到来自于端口：" + sourcePort + "的信息：" + message);

            //下面开始发送信息
            out.write("回发响应信息！".getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //试图关闭
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
