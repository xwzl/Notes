## GooleProtocol

## rmi: remote method invocation
    跨机器访问jar:A把对象，字段，方法序列化成字节码，以网络传输的方式传递给B,B在以反序列化的方式把对象，字段，方法还原，
                在调用其中的方法,把结果反序列化给客户端。
    client: stub 客户端自动生成代码
    server: skeleton
    限制: 只针对于java
    序列化与反序列化: 编码与解码
 
 ## rpc: Remote Procedure Call 远程过程调用
    优势: 很多RPC框架都是跨语言的。
    1. 定义一个接口说明文件：描述了对象、结构体、成员及接口方法等一系列信息。
    2. 通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件。
    3. 在客户端与服务端分别引入RPC编译器所生成的文件，即可像调用本地文件一样调用远程文件。
    
    性能: 
    1.编解码的效率更高相对传统http
    2.RPC基于socket，WebService基于http
 ## https://developers.google.com/protocol-buffers/docs/javatutorial  
 ``` java
   
   syntax = "proto2";
   
   package tutorial;                                  // 必须定义 默认包名
   
   option java_package = "com.example.tutorial";      //可已定义，定义了生成这个包名针对java
   option java_outer_classname = "AddressBookProtos"; //类名 
   
   message Person {
     required string name = 1;                       // 1-15 唯一标记，二进制编码使用的标记，
     required int32 id = 2;                          // 16以上的可选的标记
     optional string email = 3;
   
     enum PhoneType {
       MOBILE = 0;
       HOME = 1;
       WORK = 2;
     }
     //required: 表示字段的值，必须制定。非常小心，如果不想发送required
     //optional: 可选的值，未指定是默认值，有默认值。引用类型，默认空类型。
     //repeated: 重复值得顺序保存在
     message PhoneNumber {
       required string number = 1;
       optional PhoneType type = 2 [default = HOME];
     }
   
     repeated PhoneNumber phones = 4;
   }
   
   message AddressBook {
     repeated Person people = 1;                   //重复的标记，可选的
   }
```
 bool, int32, float, double, and string