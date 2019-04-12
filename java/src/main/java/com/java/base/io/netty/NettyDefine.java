package com.java.base.io.netty;

/**
 * <h2>Java NIO,AIO的不足之处</h2>
 * <ul>
 * <li>虽然JAVA NIO 和 JAVA AIO框架提供了多路复用IO/异步IO的支持，但是并没有
 * 提供上层“信息格式”的良好封装。例如前两者并没有提供针对 ProtocolBuffer、JSON
 * 这些信息格式的封装，但是Netty框架提供了这些数据格式封装（基于责任链模式的编码和
 * 解码功能）</li>
 * <li>要编写一个可靠的、易维护的、高性能的（注意它们的排序）NIO/AIO服务器应用。除
 * 了框架本身要兼容实现各类操作系统的实现外。更重要的是它应该还要处理很多上层特有服务，
 * 例如：客户端的权限、还有上面提到的信息格式封装、简单的数据读取。这些Netty框架都提
 * 供了响应的支持。</li>
 * <li>JAVA NIO框架存在一个poll/epoll bug：Selector doesn’t block on Selector.
 * select(timeout)，不能block意味着CPU的使用率会变成100%（这是底层JNI的问题，上层要处
 * 理这个异常实际上也好办）。当然这个bug只有在Linux内核上才能重现。这个问题在JDK 1.7版本
 * 中还没有被完全解决：http://bugs.java.com/bugdatabase/view_bug.do?bug_id=2147719。
 * 虽然Netty 4.0中也是基于JAVA NIO框架进行封装的（上文中已经给出了Netty中NioServerSocketChannel
 * 类的介绍），但是Netty已经将这个bug进行了处理。</li>
 * </ul>
 *
 * <h2>Netty介绍</h2>
 * Netty是由JBOSS提供的一个java开源框架。Netty提供异步的、事件驱动的网络应用程序框架和工具，
 * 用以快速开发高性能、高可靠性的网络服务器和客户端程序。但实际上呢，Netty框架并不只是封装了多
 * 路复用的IO模型，也包括提供了传统的阻塞式/非阻塞式 同步IO的模型封装。Netty提供了对TCP、UDP
 * 和文件传输的支持，作为一个异步NIO框架，Netty的所有IO操作都是异步非阻塞的，通过Future-Listener
 * 机制，用户可以方便的主动获取或者通过通知机制获得IO操作结果。作为当前最流行的NIO框架，Netty在
 * 互联网领域、大数据分布式计算领域、游戏行业、通信行业等获得了广泛的应用，一些业界著名的开源组件
 * 也基于Netty的NIO框架构建。
 *
 * @author xuweizhi
 * @date 2019/03/19 23:14
 */
public class NettyDefine {

    /**
     * <h2>ByteBuf</h2>
     * <p>
     * 这里说一说Netty中几个比较特别的ByteBuf实现：
     * <ul>
     * <li>io.netty.buffer.EmptyByteBuf：这是一个初始容量和最大容量都为0的缓存区。一般我
     * 们用这种缓存区描述“没有任何处理结果”，并将其向下一个handler传递</li>
     * <li>io.netty.buffer.ReadOnlyByteBuf：这是一个不允许任何“写请求”的只读缓存区。一般
     * 是通过Unpooled.unmodifiableBuffer(ByteBuf)方法将某一个正常可读写的缓存区转变而成。
     * 如果我们需要在下一个Handler处理的过程中禁止写入任何数据到缓存区，就可以在这个handler中
     * 进行“只读缓存区”的转换。</li>
     * <li>io.netty.buffer.UnpooledDirectByteBuf：基本的JAVA NIO框架的ByteBuffer封装。
     * 一般我们直接使用这个缓存区实现来处理Handler事件。</li>
     * <li>io.netty.buffer.PooledByteBuf：Netty4.X版本的缓存新特性，主要是为了减少之前
     * unpoolByteBuf在创建和销毁时的GC时间。</li>
     * </ul>
     * <h2>Channel</h2>
     * <ul>
     * <li>Netty中的Channel专门代表网络通信，这个和JAVA NIO框架中的Channel不一样，后者中还
     * 有类似FileChannel本地文件IO通道。由于前者专门代表网络通信，所以它是由客户端地址+ 服务器
     * 地址 + 网络操作状态构成的，请参见io.netty.channel.Channel接口的定义。</li>
     * <li>每一个Netty中的Channel，比JAVA NIO中的Channel更抽象。这是为什么呢？在Netty中，
     * 不止封装了JAVA NIO的IO模型，还封装了JAVA BIO的阻塞同步IO通信模型。将他们在表现上都抽象
     * 成Channel了。这就是为什么Netty中有io.netty.channel.oio.AbstractOioChannel这样的
     * 抽象类。</li>
     * <li>其io.netty.channel.oio.AbstractOioChannel抽象类上的注解也说明得比较清楚：Abstract
     * base class for Channel implementations that use Old-Blocking-IO 您可以这样理解：
     * Netty的Channel更具业务抽象性。</li>
     * </ul>
     * <h2>ChannelPipeline和ChannelHandler</h2>
     * Netty中的每一个Channel，都有一个独立的ChannelPipeline，中文称为“通道水管”。只不过这个水管是
     * 双向的里面流淌着数据，数据可以通过这个“水管”流入到服务器，也可以通过这个“水管”从服务器流出。
     * <p>
     * 在ChannelPipeline中，有若干的过滤器。我们称之为“ChannelHandler”（处理器或者过滤器）。同“流入”
     * 和“流出”的概念向对应：用于处理/过滤流入数据的ChannelHandler，称之为“ChannelInboundHandler”；
     * 用于处理/过滤流出数据的ChannelHandler，称之为“ChannelOutboundHandler”。
     * <h2>责任链和适配器的应用</h2>
     * <ul>
     * <li>数据在ChannelPipeline中有一个一个的Handler进行处理，并形成一个新的数据状态。这是典型的“责任链”模式。</li>
     * <li>需要注意，虽然数据管道中的Handler是按照顺序执行的，但不代表某一个Handler会处理任何一种由
     * “上一个handler”发送过来的数据。某些Handler会检查传来的数据是否符合要求，如果不符合自己的处理
     * 要求，则不进行处理。</li>
     * <li>我们可以实现ChannelInboundHandler接口或者ChannelOutboundHandler接口，来实现我们自
     * 己业务的“数据流入处理器”或者“数据流出”处理器。</li>
     * <li>但是这两个接口的事件方法是比较多的，例如ChannelInboundHandler接口一共有11个需要实现的接
     * 口方法（包括父级ChannelHandler的，我们在下一节讲解Channel的生命周期时，回专门讲到这些事件的执
     * 行顺序和执行状态），一般情况下我们不需要把这些方法全部实现。！</li>
     * <li>所以Netty中增加了两个适配器
     * “ChannelInboundHandlerAdapter”和“ChannelOutboundHandlerAdapter”来帮助我们去实现我们
     * 只需要实现的事件方法。其他的事件方法我们就不需要关心了：</li>
     * </ul>
     * <h2>ChannelInboundHandler类举例</h2>
     *
     * <ul>
     * <li>HttpRequestDecoder：实现了Http协议的数据输入格式的解析。这个类将数据编码为HttpMessage对象，
     * 并交由下一个ChannelHandler进行处理。</li>
     * <li>ByteArrayDecoder：最基础的数据流输入处理器，将所有的byte转换为ByteBuf对象（一般的实现类是：
     * io.netty.buffer.UnpooledUnsafeDirectByteBuf）。我们进行一般的文本格式信息传输到服务器时，最好
     * 使用这个Handler将byte数组转换为ByteBuf对象。</li>
     * <li>DelimiterBasedFrameDecoder：这个数据流输入处理器，会按照外部传入的数据中给定的某个关键字符/关
     * 键字符串，重新将数据组装为新的段落并发送给下一个Handler处理器。后文中，我们将使用这个处理器进行TCP半包
     * 的问题。</li>
     * <li>还有很多直接支持标准数据格式解析的处理器，例如支持Google Protocol Buffers
     * 数据格式解析的ProtobufDecoder和ProtobufVarint32FrameDecoder处理器。</li>
     * </ul>
     * <h2>ChannelOutboundHandler类举例</h2>
     * <ul>
     * <li>HttpResponseEncoder：这个类和HttpRequestDecoder相对应，是将服务器端HttpReponse对象的描述
     * 转换成ByteBuf对象形式，并向外传播。</li>
     * <li>ByteArrayEncoder：这个类和ByteArrayDecoder，是将服务器端的ByteBuf对象转换成byte数组的形式，
     * 并向外传播。一般也和ByteArrayDecoder对象成对使用。</li>
     * <li>还有支持标准的编码成Google Protocol Buffers格式、JBoss Marshalling格式、ZIP压缩格式的
     * ProtobufEncoder、ProtobufVarint32LengthFieldPrepender、MarshallingEncoder、JZlibEncoder等</li>
     * </ul>
     * <h2>Netty对IO模型的封装</h2>
     * <b>阻塞和非阻塞：这个概念是针对应用程序而言</b>，是指应用程序中的线程在向操作系统发送IO请求后，是否一直
     * 等待操作系统的IO响应。如果是，那么就是阻塞式的；如果不是，那么应用程序一般会以轮询的方式以一定周期询问操
     * 作系统，直到某次获得了IO响应为止（轮序间隔应用程序线程可以做一些其他工作）。
     * <p></p>
     * <b>同步和异步：IO操作都是由操作系统进行</b>的（这里的IO操作是个广泛概念了：磁盘IO、网络IO都算），不同
     * 的操作系统对不同设备的IO操作都有不同的模式。同步和异步这两个概念都指代的操作系统级别，同步IO是指操作系统
     * 和设备进行交互时，必须等待一次完整的请求-响应完成，才能进行下一次操作（当然操作系统和设备本身也有很多技术
     * 加快这个反应过程，例如“磁盘预读”技术、数据缓存技术）；异步IO是指操作系统和设备进行交互时，不必等待本次得
     * 到响应，就可以直接进行下一次操作请求。设备处理完某次请求后，会主动给操作系统相应的响应通知。
     *
     * <ul>
     * <li>多路复用IO：多路复用IO，从本质上看还是一种同步IO，因为它没有100%消除IO_WAIT，操作系统也没有为它提
     * 供“主动通知”机制。但是多路复用IO的处理速度已经相当快了，利用设备执行IO操作的时间，操作系统可以继续执行IO
     * 请求。并同样采用周期性轮询的方式，获取一批IO操作请求的执行响应。操作系统支持的多路复用IO技术主要有select、
     * poll、epoll、kqueue。</li>
     * <li> 阻塞式同步IO模型：这个从字面上就很好理解了，应用程序请求IO操作，并一直等待处理结果；操作系统同时也进
     * 行IO操作，并等待设备的处理结果；可以看出，应用程序的请求线程和操作系统的内核线程都是等待状态。</li>
     * <li>非阻塞式同步IO模型：应用程序请求IO，并且不用一直等待返回结果就去做其他事情。隔一定的周期，再去询问操作
     * 系统上次IO操作有没有结果，直到某一次询问从操作系统拿到IO结果；操作系统内核线程在进行IO操作时，还是处理一直
     * 等待设备返回操作结果的状态。</li>
     * <li>非阻塞式多路复用IO模型：应用程序请求IO的工作采用非阻塞方式进行；操作系统采用多路复用模式工作。</li>
     * <li>非阻塞式异步IO模型：应用程序请求IO的工作采用非阻塞方式进行，但是不需要轮询了，因为操作系统异步IO其中一
     * 个主要特性就是：可以在有IO响应结果的时候，主动进行通知。</li>
     * </ul>
     * <p>
     * 以上这些IO工作模型，在JAVA中都能够找到对应的支持：传统的JAVA Socket套接字支持阻塞/非阻塞模式下的同步IO
     * （有的技术资料里面也称为OIO或者BIO）；JAVA NIO框架在不同操作系统下支持不同种类的多路复用IO技术（windows
     * 下的select模型、Linux下的poll/epoll模型）；JAVA AIO框架支持异步IO（windows下的IOCP和Linux使用
     * epoll的模拟AIO）
     * <p>
     * 实际上Netty是对JAVA BIO 、JAVA NIO框架的再次封装。让我们不再纠结于选用哪种底层实现。您可以理解成Netty/MINA
     * 框架是一个面向上层业务实现进行封装的“业务层”框架。而JAVA Socket框架、JAVA NIO框架、JAVA AIO框架更偏向于对下
     * 层技术实现的封装，是面向“技术层”的框架。
     * <h2>Netty对数据信息格式的封装</h2>
     * “技术层”框架本身只对IO模型技术实现进行了封装，并不关心IO模型中流淌的数据格式；“业务层”框架对数据格式也进行
     * 了处理，让我们可以抽出精力关注业务本身。
     * <ul>
     * <li>Protobuf数据协议的集成：Netty利用自身的Channelpipeline的设计对Protobuf数据协议进行了无缝结合。</li>
     * <li>JBoss Marshalling数据协议的集成：JBoss Marshalling是一个Java对象的序列化API包，修正了JDK自带的
     * 序列化包的很多问题，又保持跟 java.io.Serializable接口的兼容。Netty通过封装这个协议，可以帮助我们在客户端
     * -服务端简便的进行对象系列化和反序列化。</li>
     * <li>HTTP Request / HTTP Response 协议的集成：在Netty中，可以方便的接受和发送Http协议。也就是说，我们
     * 可以使用Netty搭建自己的WEB服务器，当然您还可以根据自己的业务要求，方便的设计类似于Struts、SpringMVC这样的
     * WEB框架。</li>
     * </ul>
     */
    public static void main(String[] args) {

    }

}
