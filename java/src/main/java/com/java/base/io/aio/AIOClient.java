package com.java.base.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * AsynchronousSocketChannel
 */
public class AIOClient implements Runnable {
    /**
     *异步通道组 封装处理异步通道的网络IO操作
     */
    private AsynchronousChannelGroup group;

    private String host;

    private int port;

    public AIOClient(String host, int port) {
        this.host = host;
        this.port = port;
        initGroup();
    }

    private void initGroup() {
        if (group == null) {
            try {
                //使用固定线程池实例化组
                group = AsynchronousChannelGroup.withCachedThreadPool(Executors.newFixedThreadPool(5), 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void send() {
        try {
            //异步流式socket通道 open方法创建 并绑定到组 group
            final AsynchronousSocketChannel client = AsynchronousSocketChannel.open(group);
            //连接
            client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {
                @Override
                public void completed(Void result, Object attachment) {
                    String msg = "client test msg-" + Math.random();
                    client.write(ByteBuffer.wrap(msg.getBytes()));
                    System.out.println(Thread.currentThread().getName() + " client send data:" + msg);

                    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    client.read(byteBuffer, this, new CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer result, Object attachment) {
                            System.out.println(Thread.currentThread().getName() + " client read data: " + new String(byteBuffer.array()));
                            try {
                                byteBuffer.clear();
                                if (client != null) {
                                    client.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            System.out.println("read faield");
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("client send field...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            send();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Override
    //protected void finalize() throws Throwable {
    //    super.finalize();
    //    group.awaitTermination(10000, TimeUnit.SECONDS);
    //}

    public static void main(String[] args) {
        try {
            new Thread(new AIOClient("127.0.0.1", 8989)).start();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //private AsynchronousSocketChannel client;
    //private String host;
    //private int port;
    //
    //public AIOClient(String host, int port) throws IOException {
    //    this.client = AsynchronousSocketChannel.open();
    //    this.host = host;
    //    this.port = port;
    //}
    //
    //public static void main(String[] args) {
    //    try {
    //        new Thread(new AIOClient("127.0.0.1", 8989)).start();
    //        System.in.read();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //
    //}
    //
    //@Override
    //public void run() {
    //    client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {
    //        @Override
    //        public void completed(Void result, Object attachment) {
    //            String msg = "client test msg-" + Math.random();
    //            client.write(ByteBuffer.wrap(msg.getBytes()));
    //            System.out.println("client send data:" + msg);
    //        }
    //
    //        @Override
    //        public void failed(Throwable exc, Object attachment) {
    //            System.out.println("client send field...");
    //        }
    //    });
    //
    //    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    //    client.read(byteBuffer, this, new CompletionHandler<Integer, Object>() {
    //        @Override
    //        public void completed(Integer result, Object attachment) {
    //            System.out.println(result);
    //            try {
    //                System.out.println("client read data: " + new String(byteBuffer.array(), "UTF-8"));
    //            } catch (UnsupportedEncodingException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //
    //        @Override
    //        public void failed(Throwable exc, Object attachment) {
    //            System.out.println("read faield");
    //        }
    //    });
    //}
}