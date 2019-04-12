package com.java.netty.thrift.thrift;

import com.java.netty.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author xuweizhi
 * @date 2018/12/11 13:24
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        //Socket 客户端与服务端建立的连接
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8888);

        //高可用的服务
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        //协议层(更高级): 以最大的能力压缩字节码文件，最小的文件 传输格式
        arg.protocolFactory(new TCompactProtocol.Factory());

        //传输(更底层):传输层用到的对象 以什么样的形式从一端传入另外一端
        //TSocket:阻塞式Socket
        //TFramedTransport:以frame为单位进行传输，非阻塞式服务中使用.
        //TFlieTransport:以文件形式进行传输。
        //TMemoryPort:将内存以I/O。Java实现时内部实际使用了简单的ByteArrayOutputStream
        //TZLibTransport:使用zib进行压缩，与其他方式联合使用。当前无java实现
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        //启动服务器 半同步半异步
        TServer tServer = new THsHaServer(arg);

        System.out.println("Thrift Server Started !");

        //死循环 异步非阻塞
        tServer.serve();
    }
}
