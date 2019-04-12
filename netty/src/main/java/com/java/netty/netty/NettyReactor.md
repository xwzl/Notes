# 1.Reactor模式在Netty中的实现 
###### 1.boss客户端向bossGroup发起连接请求，bossGroup监听op_accept事件处理连接请求,包含Selector和Accept的处理。
###### 2.bossGroup利用Accept方法的返回值，通过SocketChannel->NioSocketChannel(注册到Selector)把SelectKeys集合传递给workGroup.
###### 3.workGroup再通过轮询、监听op_read的方式出来bossGroup转发过来请求。
![](com\java\netty\netty\images\NettyReactor.png)
# 2.业务逻辑的处理--业务线程
# 3.论文分析
传统方式:一个客户端连接对应一个Socket对应一个线程，服务端存在大量的线程与客户单保持连接。

    缺点：
       1.服务端的资源是有限的，终会有线程占用满的情况。
       2.线程之间的上下文切换，系统开销很大，特别是线程数特别多的情况下。
       3.Socket创建连接，传递数据之后线程还会继续处于可运行状态，因为Socket不在传输数据，处于连接状态。服务端有大量线程存在，大部分处于空闲状态。

面临高并发和多线程会有很大的影响

![](com/java/netty/netty/images/ReactorDogli.png)

# 4.Reactor模式角色构成，一共有五种角色:

>  1.Handle(句柄或者描述符：本质上表示一种资源，是由操作系统提供的;该资源用于表示一个个的事件，比如文件描述符，针对网络编程中的Socket描述符。事件即可以来自于外部，也可以来自于内部；外部事件比如说客户端的连接请求，客户端发送过来的数据等；内部事件比如说操作系统所产生的定时器事件等等。它本质上就是一个文件描述符，它本身就是事件产生的发源地。

>  2.Sychronous Event Demultiplexer(同步事件分离器)：它本身是一个系统调用，用于等待事件的反生(事件可能是一个，可能是多个)，调用方在调用它的时候会被阻塞，一直阻塞到同步事件分离器上有事件产生为止。对于Linux来说，同步事件分离器指得就是常用的I/O多路复用机制，比如说select,poll,epoll等。同步事件分离器对应的组件是Java NIO 中的selector，对应的阻塞方法就是select方法。

>  3.Event Handler (事件处理器)：本身由多个回调方法构成，这些回调方法构成了与应用相关的对于某个事件的反馈。NIO并没有回调机制，Netty相比于Java NIO来说，在事件处理这个角色上进行了一个升级，它为我们开发者提供了大量的回调方法，供我们在特定事件产生时实现相应的回调方法进行业务逻辑的处理。由框架提供，最终由Initation Dispatcher调用。

>  4.Concrete Event Handler(具体事件处理器)：是事件处理器的实现，它本身实现了事件处理器所提供的各个回调方法，从而实现了特定于业务的逻辑。它本质上就是我们所编写的一个个处理器的实现。

>  5.Initiation Dispatcher(初始分发器)：他实际上就是Reactor角色，它本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供了应用进行事件处理器的注册，删除等设施。它本身就是整个事件处理器的核心所在，Initiation Dispatcher 会通过同步事件分离器来等待事件的发生。一旦事件发生，Inination Dispatcher 首先会分离出每一个事件，然后调用事件处理器，最后调用相关的回调方法来处理这些事件。

### 5.流程
    
&nbsp;&nbsp;首先执行Initiation Dispatcher,将若干个Event Handler(Concrete Handler)注册到其上(本身)，同时注册感兴趣的事件，事件由Handle(事件处理器本身包含Handle)标识。
Initiation Dispatcher循环就会被启用，然后同步事件分离器等待事件的发生，通过这些事件找到与之对应的handlers,调用具体的事件处理器方法。

1. 当应用向Initiation Dispatcher注册具体的事件处理器时，应用标识出该事件处理器希望Initiation Dispatcher在某个事件发生时向其通知的该事件，该事件与Handle关联。

2. Initiation Dispatcher要求每个事件处理器向其传递内部的handle,该handle向操作系统标志了事件处理器。 

3. 当所有的事件处理器注册完毕后，应用会调用handle_event方法来启动Initiation Dispatcher的事件循环。这时，Initiation Dispatcher会将每个注册的事件管理器的handle合并起来，并使用同步事件分离器等待这些事件的发生。比如说，TCP协议层使用select同步事件分离器操作来等待客户端发送的数据到达连接的SocketHandle上。 

4. 当与某个事件源对应的handle变为ready状态(比如说，TCP socket变为等待读状态时)，同步事件分离器就会通知Initiation Dispatcher。

5. Initiation Dispatcher会触发事件处理器的回调方法， 从而响应这个处于ready状态的handle.当事件发生时，Initiation Dispatcher将被事件源激活的Handle作为Key来寻找并分发恰当的事件处理器回调方法。

6. Initiation Dispatcher回调事件处理器的handle_events回调方法来执行特定于应用的功能（自己所编写的功能）,从而响应这个事件。所发生的事件类型可以作为该方法参数并被该方法内部使用来执行额外的特定于服务的分离与分发。

    
![](com/java/netty/netty/images/3.png)
### 6.ChannlePipeline与传统的拦截器不一样，入栈和出栈的拦截器不是同一个
  
  ChannelHandlerContext 是Channel和ChannelPipeline交互的桥梁·
  ChannelPipeline本身是与Channel与之关联的容器对象，ChannelPipeline存放的是一个又一个的ChannelHandlerContent对象（由框架本身提供），ChannelHandlerContext存放的是我们提供的或者Netty内置的ChannelHandler对象。
  因此ChanelPipeline存放的是Netty提供的ChannelHandlerContext对象。
  
  Netty Handler 拦截器入栈和出栈Handler是不一样的
  
  每一次客户端连接，server段的Initialier都会被调用一次，重新想通道中添加handler处理器。
 
  
### 7.Channel,Attributes和ChannlesOptions通过添加到Channel的Config成员变量中，实现注入。
   
    ChannelOption 维护传输过程中的协议
    
    class Channel{
    
      private ServerSocketChannelConfig config;
      
      ...... attributes;
    
    }
     
Netty 4.10之前，Channel和ChannelHandlerContext维护者自己的Key Value
Netty 4.10即之后，查看Channel和ChannelHandlerContext源码实现后，会看出ChannelHandlerContext实现是通过ChannelPipeline获取Channel对象，然后调用其实现类的attr方法存放数据，本质来上将他们的作用域是一样的

### 8.参考AbstractNioMessageChannel 内部类 UnSafe 底层实现 NioMessageUnsafe

    为什么相同的执行逻辑，还要进行当前线程是否事件循环组
    1.一个EventLoopGroup当中包含了一个或多个EventGroup
    2.一个EventLoop在它的整个声明周期中都只会与唯一的Thread进行绑定。
    3.所有由EventLoop所处理的各种I/O事件都将在它所关联的那个Thread上进行处理。        
    4.一个Channel在它的整个声明周期中会注册在一个EventLoop上。
    5.一个EventLoop在运行过程当中，会被分配给一个或多个Channel。
    
> 加入事件循环组之后，以任务调度的方式，等待任务执行。而事件循环组只有一个Thread,所以永远是单线程，避免了并发编程
所有处于同一个Channel的操作，任务的提交顺序和执行顺序一直，底层以队列方式FIFO形式实现，先进先出。所以不能让那个啥逻辑阻塞太久，另起线程池执行。

>重要结论：在Netty中，Channel的实现一定是线程安全的:基于此，我们可以存储一个Channel的引用，并且在需要向远程端点发送数据时，通过这个引用来调用Channel相应的方法，即便当时有很多线程都在使用它，也不会出现多线程问问题；而且消息一定会按照顺序发送出去。

>重要结论：我们业务开发中，不要将长时间执行的耗时任务放入到EventLoop的执行队列中，因为它将一直阻塞该线程所对应的所有Channel上的其他执行任务，如果我们需要进行阻塞调用或是耗时的操作（实际开发中很常见），那么我们就需要使用一个专门的EventExecutor(业务线程池)。即自定的Handler中的ChannelRead0方法逻辑调用时，需要另起线程池。

通常有两种方式：

 1.在ChannelHandler的回调方法汇中，实现自己定义的业务线程池，这样就可以实现异步调用。
 
 2.借助Netty提供的向ChannelPipeline添加ChannelHandler是调用的addLast方法来传递EventExecutor;
 
说明：默认情况下（调用addLast(handler),ChannelHandler中的回调方法都是由I/O线程所执行；如果调用了ChannelPipeline addLast(EventExecutorGroup group,ChannelHandler... handlers);方法，那么ChannelPipeline中回调方法就是由参数中的group线程组执行。
```
        @Override
        public final void register(EventLoop eventLoop, final ChannelPromise promise) {
            if (eventLoop == null) {
                throw new NullPointerException("eventLoop");
            }
            if (isRegistered()) {
                promise.setFailure(new IllegalStateException("registered to an event loop already"));
                return;
            }
            if (!isCompatible(eventLoop)) {
                promise.setFailure(
                        new IllegalStateException("incompatible event loop type: " + eventLoop.getClass().getName()));
                return;
            }

            AbstractChannel.this.eventLoop = eventLoop;
             
            /**
              *      为什么相同的执行逻辑，还要进行当前线程是否事件循环组
              *      1.一个EventLoopGroup当中包含了一个或多个EventGroup
              *      2.一个EventLoop在它的整个声明周期中都只会与唯一的Thread进行绑定。
              *      3.所有由EventLoop所处理的各种I/O事件都将在它所关联的那个Thread上进行处理。        
              *      4.一个Channel在它的整个声明周期中会注册在一个EventLoop上。
              *      5.一个EventLoop在运行过程当中，会被分配给一个或多个Channel。
              *      @Override
              *      public boolean inEventLoop(Thread thread) {
              *          return thread == this.thread;
              *      }
              */
            if (eventLoop.inEventLoop()) {
                register0(promise);
            } else {
                try {
                    eventLoop.execute(new Runnable() {
                        @Override
                        public void run() {
                            register0(promise);
                        }
                    });
                } catch (Throwable t) {
                    logger.warn(
                            "Force-closing a channel whose registration task was not accepted by an event loop: {}",
                            AbstractChannel.this, t);
                    closeForcibly();
                    closeFuture.setClosed();
                    safeSetFailure(promise, t);
                }
            }
        }
```

底层注册逻辑
```
      @Override
      protected void doRegister() throws Exception {
          boolean selected = false;
          for (;;) {
              try {
                  selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this);
                  return;
              } catch (CancelledKeyException e) {
                  if (!selected) {
                      // Force the Selector to select now as the "canceled" SelectionKey may still be
                      // cached and not removed because no Select.select(..) operation was called yet.
                      eventLoop().selectNow();
                      selected = true;
                  } else {
                      // We forced a select operation on the selector before but the SelectionKey is still cached
                      // for whatever reason. JDK bug ?
                      throw e;
                  }
              }
          }
      }

```

### 9.函数回调
    obj.method(a,b,c,function(result,error){
      
       if(!error){
          var data = result.data; 
       }else{
           error.errorObj;
       }
    
    })
### 10.Future 和 ChannelPromise解读

JDK锁提供的Future只能通过手工方式检查执行结果，而这个操作是会阻塞的；

Netty则对ChannelFuture进行了增强，通过ChannelFutureListener以回调的方式来获取执行结果，去除了手工检查阻塞的操作。

值得注意的是：ChannelFutureListener的operationComplete方法是由I/O线程执行的，因此要注意的是不要在这里执行耗时操作，否则需要通过另外的线程或线程池来执行。

ChannelPromise是ChannelFuture的一个特化，它提供了可写的操作。

### 11.ChannelInboundHandlerAdapter适配器模式，它的默认实现是调用下一个处理器的实现。SimpleChannelInboundHandler采用模板设计模式自动释放资源
```
    public abstract class SimpleChannelInboundHandler<I> extends ChannelInboundHandlerAdapter {
    
        private final TypeParameterMatcher matcher;
        private final boolean autoRelease;
    
        /**
         * see {@link #SimpleChannelInboundHandler(boolean)} with {@code true} as boolean parameter.
         */
        protected SimpleChannelInboundHandler() {
            this(true);
        }
    
        /**
         * Create a new instance which will try to detect the types to match out of the type parameter of the class.
         *
         * @param autoRelease   {@code true} if handled messages should be released automatically by passing them to
         *                      {@link ReferenceCountUtil#release(Object)}.
         */
        protected SimpleChannelInboundHandler(boolean autoRelease) {
            matcher = TypeParameterMatcher.find(this, SimpleChannelInboundHandler.class, "I");
            this.autoRelease = autoRelease;
        }
    
        /**
         * see {@link #SimpleChannelInboundHandler(Class, boolean)} with {@code true} as boolean value.
         */
        protected SimpleChannelInboundHandler(Class<? extends I> inboundMessageType) {
            this(inboundMessageType, true);
        }
    
        /**
         * Create a new instance
         *
         * @param inboundMessageType    The type of messages to match
         * @param autoRelease           {@code true} if handled messages should be released automatically by passing them to
         *                              {@link ReferenceCountUtil#release(Object)}.
         */
        protected SimpleChannelInboundHandler(Class<? extends I> inboundMessageType, boolean autoRelease) {
            matcher = TypeParameterMatcher.get(inboundMessageType);
            this.autoRelease = autoRelease;
        }
    
        /**
         * Returns {@code true} if the given message should be handled. If {@code false} it will be passed to the next
         * {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
         */
        public boolean acceptInboundMessage(Object msg) throws Exception {
            return matcher.match(msg);
        }
    
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            boolean release = true;
            try {
                if (acceptInboundMessage(msg)) {
                    @SuppressWarnings("unchecked")
                    I imsg = (I) msg;
                    channelRead0(ctx, imsg);
                } else {
                    release = false;
                    ctx.fireChannelRead(msg);
                }
            } finally {
                if (autoRelease && release) {
                    ReferenceCountUtil.release(msg); //引用减1，自动释放掉系统资源
                }
            }
        }
    
        /**
         * <strong>Please keep in mind that this method will be renamed to
         * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
         *
         * Is called for each message of type {@link I}.
         *
         * @param ctx           the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
         *                      belongs to
         * @param msg           the message to handle
         * @throws Exception    is thrown if an error occurred
         */
        protected abstract void channelRead0(ChannelHandlerContext ctx, I msg) throws Exception;
    }
```
### 11.Channel和ChannelHandlerContext的WriteAndFlush方法的区别
> Channel调用WriteAndFlush方法，在ChannelPipeline管道中从做左(第一个)到右（最后一个）依次执行,更多的处理效率和性能将会降低。

> ChannelHandlerContext调用WriteAndFlush方法,被调用的Handler开始，下一个处理开始处理这些数据，逐个调用。过少的处理，效率和性能更高。
 
  在Netty中有两种发送消息的方式，写入到Channel或者ChannelHandler所关联的那个ChannelHandlerContext中。对于前一种方式来说吗，消息会从ChannelPipeline的末尾开始流动；对于后一种方式来说，消息将从ChannelPipeline中的下一个ChannelHandler开始流动。
  
#### 结论
  
  1.ChannelHandlerContext与ChannelHandler之间的关联绑定关系是永远都不会发生改变的，因此对其缓存是没有任何问题的。
  
  2.对于与Channel的同名方法来说，ChannelHandlerContext的方法将会产生更短的事件流，所以我们应该在可能的情况下利用这个特性来提升应用性能。
  
### 12.使用NIO进行文件读取所涉及的步骤：
   1. 从FileInputStream对象获取到Channel对象。
   2. 创建Buffer.
   3. 将数据从channel中读取到Buffer对象中。  
   
   0<=mark<=position<=limit<=capacity;

   Mark:当前position的值。Reset回到当时保存的mark值
   
   flip()方法。
   
   1.将limit设为Position。
   
   2.将position设置为0。
   
   Clear()方法
   
   将limit设置为capacity，将position设为0，mark被废弃。
   
   compact()方法
    
   1.将所有未读的数据复制到buffer起始位置处。
   
   2.将position设为最后一个未读元素的后面。
   
   3.将limit设为capacity
   
   4.现在buffer准备好了，但是不会覆盖未读的数据。 
   
   注意：ByteBuf通过索引访问Byte时并不会改变真实的读索引与写索引；我们可以通过ByteBuf的readerIndex()与writerIndex方法分别直接修改读索引和写索引。
   
### 相对方法和绝对方法，相对方法考虑position的值

https://www.zhihu.com/question/57374068/answer/152691891

Java NIO中，关于DirectBuffer，HeapBuffer的疑问？

1.DirectBuffer 属于堆外存，那应该还是属于用户内存，而不是内核内存？

> Java NIO中的direct buffer（主要是DirectByteBuffer）其实是分两部分的：
```
       Java        |      native
                   |
 DirectByteBuffer  |     malloc'd
 [    address   ] -+-> [   data    ]
                   |
```           
> 其中 DirectByteBuffer 自身是一个Java对象，在Java堆中；而这个对象中有个long类型字段address，记录着一块调用 malloc() 申请到的native memory。

> DirectByteBuffer 自身是（Java）堆内的，它背后真正承载数据的buffer是在（Java）堆外——native memory中的。这是 malloc() 分配出来的内存，是用户态的。        

2.FileChannel 的read(ByteBuffer dst)函数,write(ByteBuffer src)函数中，如果传入的参数是HeapBuffer类型,则会临时申请一块DirectBuffer,进行数据拷贝，而不是直接进行数据传输，这是出于什么原因？

题主看的是OpenJDK的 sun.nio.ch.IOUtil.write(FileDescriptor fd, ByteBuffer src, long position, NativeDispatcher nd) 的实现对不对：
```
    static int write(FileDescriptor fd, ByteBuffer src, long position,NativeDispatcher nd)throws IOException{
        if (src instanceof DirectBuffer)
            return writeFromNativeBuffer(fd, src, position, nd);

        // Substitute a native buffer
        int pos = src.position();
        int lim = src.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        // 申请一块临时的对外内存，并对数据进行拷贝
        ByteBuffer bb = Util.getTemporaryDirectBuffer(rem);
        try {
            bb.put(src);
            bb.flip();
            // Do not update src until we see how many bytes were written
            src.position(pos);

            int n = writeFromNativeBuffer(fd, bb, position, nd);
            if (n > 0) {
                // now update src
                src.position(pos + n);
            }
            return n;
        } finally {
            Util.offerFirstTemporaryDirectBuffer(bb);
        }
    }
```

这里其实是在迁就OpenJDK里的HotSpot VM的一点实现细节。

HotSpot VM里的GC除了CMS之外都是要移动对象的，是所谓“compacting GC”。

如果要把一个Java里的 byte[] 对象的引用传给native代码，让native代码直接访问数组的内容的话，就必须要保证native代码在访问的时候这个 byte[] 对象不能被移动，也就是要被“pin”（钉）住。

可惜HotSpot VM出于一些取舍而决定不实现单个对象层面的object pinning，要pin的话就得暂时禁用GC——也就等于把整个Java堆都给pin住。HotSpot VM对JNI的Critical系API就是这样实现的。这用起来就不那么顺手。

所以 Oracle/Sun JDK / OpenJDK 的这个地方就用了点绕弯的做法。它假设把 HeapByteBuffer 背后的 byte[] 里的内容拷贝一次是一个时间开销可以接受的操作，同时假设真正的I/O可能是一个很慢的操作。

于是它就先把 HeapByteBuffer 背后的 byte[] 的内容拷贝到一个 DirectByteBuffer 背后的native memory去，这个拷贝会涉及 sun.misc.Unsafe.copyMemory() 的调用，背后是类似 memcpy() 的实现。这个操作本质上是会在整个拷贝过程中暂时不允许发生GC的，虽然实现方式跟JNI的Critical系API不太一样。（具体来说是 Unsafe.copyMemory() 是HotSpot VM的一个intrinsic方法，中间没有safepoint所以GC无法发生）。

然后数据被拷贝到native memory之后就好办了，就去做真正的I/O，把 DirectByteBuffer 背后的native memory地址传给真正做I/O的函数。这边就不需要再去访问Java对象去读写要做I/O的数据了。
      
### 13Netty处理器重要概念
 
 1.Netty处理器分为入站处理器和出站处理器。
 
 2.入站处理器的顶层是ChannelInboundHandler,出站处理器的顶层是ChannelOutboundHandler.
 
 3.数据处理时常用的各种编码器本质上是处理器。
 
 4.编解码器：无论我们向网络中写入的数据是int，char,String、而二进制等类型，数据在网络传递时，其都是以字节流的形式呈现的；将数据由原本的形式转换为字节流的操作称为编码（encoed）,将数据由字节转换为它原本的格式或是其他格式的操作称为解码（decode）.
 
 5.编码:本质上是一种出站处理器，编码一定是一种ChannelOutboundHandler,从程序到网络。
 
 6.解码:本质上是一种入站处理器，编码一定是一种ChannelInboundHandler,从网络到程序。
 
 7.在Netty中，编码器通常以XXXEncoder命名；解码器通常以XXXDecoder命名。

### 14关于Netty编解码的重要结论
 
 1.无论是编码器还是解码器，其所接收的消息类型必须要与处理的参数类型一致，否则改编码器或者解码器并不会被执行。
 
 2.在解码器进行数据解码时，一定要记得判断缓冲（ByteBuf）中的数据是否足够，否则将会产生一些问题。
 
 code结尾的处理器是编解码器通用
 
 LineBasedFrameDecoder基于行的解码器
 
 FixedLengthFrameDecoder基于长度的解码器
 
 DelimiterBasedFrameDecoder基于分隔符得解码器
 
 LengthFiledBaseFrameDecoder基于长度字段阵的解码器
 

