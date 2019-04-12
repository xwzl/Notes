
#Netty

## 一、 Netty是什么(Netty.io)

>&nbsp;&nbsp;Netty是 一个异步事件驱动的网络应用程序框架，用于快速开发可维护的高性能协议服务器和客户端。传统的http请求是同步的。
<br>&nbsp;&nbsp;快速简便”并不意味着最终的应用程序会受到可维护性或性能问题的影响。Netty经过精心设计，具有丰富的协议，如FTP，SMTP，
<br>HTTP以及各种二进制和基于文本的传统协议。因此，Netty成功地找到了一种在不妥协的情况下实现易于开发，性能，稳定性和灵活性的方法。

- 异步：通过监听器的回调进一步判断是否发送成功，异步相对于同步来说复杂性倍增。
- 事件驱动：Netty是同时实现协议，当发送请求时由事件来调用回调函数，达成长连接的效果。
- 网络框架：大量协议的实现。

## 二、 Netty学习路线
    
    1. Netty介绍。
    2. Netty架构实现。
    3. Netty模块分析。
    4. Netty HTTP Tunnel。
    5. Netty与Socket的实现。
    6. Netty压缩与解压缩。
    7. Netty对RPC的支援。
    
 ### 1.RPC框架 
 
    在客户端生成stub文件
    在服务端生成skeleton文件
    Apache Thrift RPC框架和库
 
 ### 2.WebSocket 长连接
     
    Http:无状态请求，再请求时间断开内，则无需重连接，断开则需要重连接。
    Http基于请求相应的方式，客户端请求，服务端响应。
    
    Websocket依附于http,当http发送请求，会进性upgrade，如果支持则http升级为websocket，客户端与服务端等价，即都可发送请求。 
    
    http实现数据实时响应，则会采用轮询的方式向服务器发起请求，然而每次都要携带Header,数据请求量增大。
    
    Websocket则可以只发送body,心跳机制判断连接是否断开.
    
### 3.NIO模型在Netty中的实现
   
    Channel 序列化的讲解    
### 4.Netty参考书 
    
    Netty In Action
    
    
    
  
       
       
      
      