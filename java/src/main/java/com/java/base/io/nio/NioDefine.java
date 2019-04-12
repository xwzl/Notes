package com.java.base.io.nio;

/**
 * <h2>多路复用IO模型</h2>
 * <h3>场景描述</h3>
 * <p>
 * 一个餐厅同时有100位客人到店，当然到店后第一件要做的事情就是点菜。但是问题来了，餐厅老板
 * 为了节约人力成本目前只有一位大堂服务员拿着唯一的一本菜单等待客人进行服务。
 * <p>
 * <b>方法A</b>：无论有多少客人等待点餐，服务员都把仅有的一份菜单递给其中一位客人，然后站在客人身
 * 旁等待这个客人完成点菜过程。在记录客人点菜内容后，把点菜记录交给后堂厨师。然后是第二位客
 * 人。。。。然后是第三位客人。很明显，只有脑袋被门夹过的老板，才会这样设置服务流程。因为随
 * 后的80位客人，再等待超时后就会离店（还会给差评）。
 * <p>
 * <b>方法B</b>：老板马上新雇佣99名服务员，同时印制99本新的菜单。每一名服务员手持一本菜单负责一位
 * 客人（关键不只在于服务员，还在于菜单。因为没有菜单客人也无法点菜）。在客人点完菜后，记录
 * 点菜内容交给后堂厨师（当然为了更高效，后堂厨师最好也有100名）。这样每一位客人享受的就是
 * VIP服务咯，当然客人不会走，但是人力成本可是一个大头哦（亏死你）。
 * <p>
 * <b>方法C</b>：就是改进点菜的方式，当客人到店后，自己申请一本菜单。想好自己要点的才后，就呼叫服务
 * 员。服务员站在自己身边后记录客人的菜单内容。将菜单递给厨师的过程也要进行改进，并不是每一
 * 份菜单记录好以后，都要交给后堂厨师。服务员可以记录号多份菜单后，同时交给厨师就行了。那么
 * 这种方式，对于老板来说人力成本是最低的；对于客人来说，虽然不再享受VIP服务并且要进行一定
 * 的等待，但是这些都是可接受的；对于服务员来说，基本上她的时间都没有浪费，基本上被老板压杆
 * 了最后一滴油水。
 * <p>
 * <b>到店情况</b>：并发量。到店情况不理想时，一个服务员一本菜单，当然是足够了。所以不同的老板在不
 * 同的场合下，将会灵活选择服务员和菜单的配置。
 * <p>
 * <b>客人</b> ：客户端请求
 * <p>
 * <b>点餐内容</b>：客户端发送的实际数据
 * <p>
 * <b>老板</b>：操作系统
 * <p>
 * <b>人力成本</b>：系统资源
 * <p>
 * <b>菜单</b>：文件状态描述符。操作系统对于一个进程能够同时持有的文件状态描述符的个数是有限制的，
 * 在linux系统中$ulimit -n查看这个限制值，当然也是可以（并且应该）进行内核参数调整的。
 * <p>
 * <b>服务员</b>：操作系统内核用于IO操作的线程（内核线程）
 * <p>
 * <b>厨师</b>：应用程序线程（当然厨房就是应用程序进程咯）
 * <p>
 * <b>餐单传递方式</b>：包括了阻塞式和非阻塞式两种。
 * <ul>
 * <li>方法A：阻塞式/非阻塞式 同步IO</li>
 * <li>方法B：使用线程进行处理的 阻塞式/非阻塞式 同步IO</li>
 * <li>方法C：阻塞式/非阻塞式 多路复用IO</li>
 * </ul>
 *
 * <h2>多路复用IO实现方式</h2>
 * <p>
 * 目前流程的多路复用IO实现主要包括四种：select、poll、epoll、kqueue。下表是他们的一些重要特性的比较：
 * <p>
 * IO模型	相对性能	关键思路	            操作系统	        JAVA支持情况
 * select	较高	    Reactor	            windows/Linux	支持,Reactor模式(反应器设计模式)。Linux操作系统的 kernels 2.4内核版本之前，默认使用select；而目前windows下对同步IO的支持，都是select模型
 * poll	    较高	    Reactor	            Linux	        Linux下的JAVA NIO框架，Linux kernels 2.6内核版本之前使用poll进行支持。也是使用的Reactor模式
 * epoll	高	    Reactor/Proactor	Linux	        Linux kernels 2.6内核版本及以后使用epoll进行支持；Linux kernels 2.6内核版本之前使用poll进行支持；另外一定注意，由于Linux下没有Windows下的IOCP技术提供真正的 异步IO 支持，所以Linux下使用epoll模拟异步IO
 * kqueue	高	    Proactor	        Linux	        目前JAVA的版本不支持
 * <p>
 * 多路复用IO技术最适用的是“高并发”场景，所谓高并发是指1毫秒内至少同时有上千个连接
 * 请求准备好。其他情况下多路复用IO技术发挥不出来它的优势。另一方面，使用JAVA NIO
 * 进行功能实现，相对于传统的Socket套接字实现要复杂一些，所以实际应用中，需要根据
 * 自己的业务需求进行技术选择。
 * <h2>NIO</h2>
 * JDK 1.4中的java.nio.*包中引入新的Java I/O库，其目的是提高速度。实际上，“旧”
 * 的I/O包已经使用NIO重新实现过，即使我们不显式的使用NIO编程，也能从中受益。速度的
 * 提高在文件I/O和网络I/O中都可能会发生，但本文只讨论后者。
 * <p>
 * NIO我们一般认为是New I/O（也是官方的叫法），因为它是相对于老的I/O类库新增的（其
 * 实在JDK 1.4中就已经被引入了，但这个名词还会继续用很久，即使它们在现在看来已经是
 * “旧”的了，所以也提示我们在命名时，需要好好考虑），做了很大的改变。但民间跟多人称之
 * 为Non-block I/O，即非阻塞I/O，因为这样叫，更能体现它的特点。而下文中的NIO，不是
 * 指整个新的I/O库，而是非阻塞I/O。
 * <h2>Channel</h2>
 * <p>
 * 通道，被建立的一个应用程序和操作系统交互事件、传递内容的渠道（注意是连接到操作系统）。
 * 一个通道会有一个专属的文件状态描述符。那么既然是和操作系统进行内容的传递，那么说明应用
 * 程序可以通过通道读取数据，也可以通过通道向操作系统写数据。
 * <p>
 * A channel represents an open connection to an entity such as a hardware
 * device, a file, a network socket, or a program component that is capable
 * of performing one or more distinct I/O operations, for controller reading or
 * writing.
 * <p>
 * A channel is either open or closed. A channel is open upon creation, and
 * once closed it remains closed. Once a channel is closed, any attempt to
 * invoke an I/O operation upon it will cause a ClosedChannelException to be
 * thrown. Whether or not a channel is open may be tested by invoking its isOpen
 * method.
 *
 * <ul>
 * <li>所有被Selector（选择器）注册的通道，只能是继承了SelectableChannel类的子类。如上图所示</li>
 * <li>ServerSocketChannel：应用服务器程序的监听通道。只有通过这个通道，应用程序才能向操作系统
 * 注册支持“多路复用IO”的端口监听。同时支持UDP协议和TCP协议。</li>
 * <li>ScoketChannel：TCP Socket套接字的监听通道，一个Socket套接字对应了一个客户端IP：端口
 * 到服务器IP：端口的通信连接。</li>
 * <li>DatagramChannel：UDP 数据报文的监听通道。</li>
 * </ul>
 * <h2>buffer</h2>
 * 数据缓存区：在JAVA NIO 框架中，为了保证每个通道的数据读写速度JAVA NIO 框架为每一种需要支持数据
 * 读写的通道集成了Buffer的支持。
 * <p>
 * 这句话怎么理解呢？例如ServerSocketChannel通道它只支持对OP_ACCEPT事件的监听，所以它是不能直接
 * 进行网络数据内容的读写的。所以ServerSocketChannel是没有集成Buffer的。
 * <p>
 * Buffer有两种工作模式：写模式和读模式。在读模式下，应用程序只能从Buffer中读取数据，不能进行写操作。
 * 但是在写模式下，应用程序是可以进行读操作的，这就表示可能会出现脏读的情况。所以一旦您决定要从Buffer
 * 中读取数据，一定要将Buffer的状态改为读模式。
 * <p>
 * 索引	              说明
 * capacity	    缓冲区数组的总长度
 * position	    下一个要操作的数据元素的位置
 * limit	    缓冲区数组中不可操作的下一个元素的位置，limit<=capacity
 * mark	        用于记录当前 position 的前一个位置或者默认是 0
 * <h2>Selector</h2>
 * Selector的英文含义是“选择器”，不过根据我们详细介绍的Selector的岗位职责，您可以把它称之为“轮询代
 * 理器”、“事件订阅器”、“channel容器管理机”都行。
 * <p>
 * 事件订阅和Channel管理：
 * 应用程序将向Selector对象注册需要它关注的Channel，以及具体的某一个Channel会对哪些IO事件感兴趣。
 * Selector中也会维护一个“已经注册的Channel”的容器。以下代码来自WindowsSelectorImpl实现类中，
 * 对已经注册的Channel的管理容器：
 * <p>
 * 1. 轮询代理： 应用层不再通过阻塞模式或者非阻塞模式直接询问操作系统“事件有没有发生”，而是由Selector代其询问。
 * <p>
 * 2. 实现不同操作系统的支持： 之前已经提到过，多路复用IO技术是需要操作系统进行支持的，其特点就是操作
 * 系统可以同时扫描同一个端口上不同网络连接的时间。所以作为上层的JVM，必须要为不同操作系统的多路复用IO
 * 实现编写不同的代码。
 *
 * @author xuweizhi
 * @date 2019/03/19 22:19
 */
public class NioDefine {

    /**
     * <h2>JAVA NIO 框架简要设计分析</h2>
     * 通过上文的描述，我们知道了多路复用IO技术是操作系统的内核实现。在不同的操作系统，甚至同一系列
     * 操作系统的版本中所实现的多路复用IO技术都是不一样的。那么作为跨平台的JAVA JVM来说如何适应多
     * 种多样的多路复用IO技术实现呢？面向对象的威力就显现出来了：无论使用哪种实现方式，他们都会有“选
     * 择器”、“通道”、“缓存”这几个操作要素，那么可以为不同的多路复用IO技术创建一个统一的抽象组，并且
     * 为不同的操作系统进行具体的实现。JAVA NIO中对各种多路复用IO的支持，主要的基础是java.nio.
     * channels.core.SelectorProvider抽象类，其中的几个主要抽象方法包括：
     *
     * <ul>
     * <li>public abstract DatagramChannel openDatagramChannel()：
     * 创建和这个操作系统匹配的UDP通道实现。</li>
     * <li>public abstract AbstractSelectoropenSelector()：
     * 创建和这个操作系统匹配的NIO选择器，就像上文所述，不同的操作系统，不同的版本所默认支持的NIO模型是不一样的。</li>
     * <li>public abstract ServerSocketChannelopenServerSocketChannel()：
     * 创建和这个NIO模型匹配的服务器端通道。</li>
     * <li>public abstract SocketChannel openSocketChannel()：
     * 创建和这个NIO模型匹配的TCP，Socket套接字通道（用来反映客户端的TCP连接）</li>
     * </ul>
     * <h2>多路复用IO的优缺点</h2>
     * <ul>
     * <li>不用再使用多线程来进行IO处理了（包括操作系统内核IO管理模块和应用程序进程而言）。当然实际业
     * 务的处理中，应用程序进程还是可以引入线程池技术的</li>
     * <li>同一个端口可以处理多种协议，例如，使用ServerSocketChannel测测的服务器端口监听，既可以处
     * 理TCP协议又可以处理UDP协议。</li>
     * <li>操作系统级别的优化：多路复用IO技术可以是操作系统级别在一个端口上能够同时接受多个客户端的IO
     * 事件。同时具有之前我们讲到的阻塞式同步IO和非阻塞式同步IO的所有特点。Selector的一部分作用更相当
     * 于“轮询代理器”。</li>
     * <li>都是同步IO：目前我们介绍的阻塞式IO、非阻塞式IO甚至包括多路复用IO，这些都是基于操作系统级别
     * 对“同步IO”的实现。我们一直在说“同步IO”，一直都没有详细说，什么叫做“同步IO”。实际上一句话就可以
     * 说清楚：只有上层（包括上层的某种代理机制）系统询问我是否有某个事件发生了，否则我不会主动告诉上层系
     * 统事件发生了</li>
     * </ul>
     */
    public static void main(String[] args) {

    }
}
