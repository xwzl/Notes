package com.java.base.jvm;


/**
 * 线程上下文类加载器分析
 * https://blog.csdn.net/yangcheng33/article/details/52631940
 * Java类加载器机制
 * https://blog.csdn.net/yangcheng33/article/details/52464898
 * Java 提供了很多服务提供者接口（Service Provider Interface，SPI），允许第
 * 三方为这些接口提供实现。常见的 SPI 有 JDBC、JCE、JNDI、JAXP 和 JBI 等。
 * <p>
 * 这些 SPI 的接口由 Java 核心库来提供，而这些 SPI 的实现代码则是作为 Java 应用
 * 所依赖的 jar 包被包含进类路径（CLASSPATH）里。SPI接口中的代码经常需要加载具体
 * 的实现类。那么问题来了，SPI的接口是Java核心库的一部分，是由**启动类加载器(Bootstrap
 * Classloader)来加载的；SPI的实现类是由系统类加载器(System ClassLoader)**来
 * 加载的。引导类加载器是无法找到 SPI 的实现类的，因为依照双亲委派模型，BootstrapClassloader
 * 无法委派AppClassLoader来加载类。
 * <p>
 * 而线程上下文类加载器破坏了“双亲委派模型”，可以在执行线程中抛弃双亲委派加载链模式，
 * 使程序可以逆向使用类加载器。
 * <p>
 * <h2>SPI机制简介</h2>
 * SPI的全名为Service Provider Interface，主要是应用于厂商自定义组件或插件中。
 * 在java.util.ServiceLoader的文档里有比较详细的介绍。简单的总结下java SPI机
 * 制的思想：我们系统里抽象的各个模块，往往有很多不同的实现方案，比如日志模块、xml解
 * 析模块、jdbc模块等方案。面向的对象的设计里，我们一般推荐模块之间基于接口编程，模
 * 块之间不对实现类进行硬编码。一旦代码里涉及具体的实现类，就违反了可拔插的原则，如果
 * 需要替换一种实现，就需要修改代码。为了实现在模块装配的时候能不在程序里动态指明，这
 * 就需要一种服务发现机制。 Java SPI就是提供这样的一个机制：为某个接口寻找服务实现
 * 的机制。有点类似IOC的思想，就是将装配的控制权移到程序之外，在模块化设计中这个机制
 * 尤其重要。
 *
 * <h2>SPI具体约定</h2>
 * <p>
 * Java SPI的具体约定为：当服务的提供者提供了服务接口的一种实现之后，在jar包的META-INF
 * /services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现
 * 类。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找
 * 到具体的实现类名，并装载实例化，完成模块的注入。基于这样一个约定就能很好的找到服务接口的
 * 实现类，而不需要再代码里制定。jdk提供服务实现查找的一个工具类：java.util.ServiceLoader。
 * <h2>Spring加载问题</h2>
 * Tomcat 加载器的实现清晰易懂，并且采用了官方推荐的“正统”的使用类加载器的方式。这时作者提
 * 一个问题：如果有 10 个 Web 应用程序都用到了spring的话，可以把Spring的jar包放到 common
 * 或 shared 目录下让这些程序共享。Spring 的作用是管理每个web应用程序的bean，getBean时
 * 自然要能访问到应用程序的类，而用户的程序显然是放在 /WebApp/WEB-INF 目录中的（由 WebAppClassLoader
 * 加载），那么在 CommonClassLoader 或 SharedClassLoader 中的 Spring 容器如何去加载
 * 并不在其加载范围的用户程序（/WebApp/WEB-INF/）中的Class呢？
 * <p>
 * 答案呼之欲出：spring根本不会去管自己被放在哪里，它统统使用TCCL来加载类，而TCCL默认设置为
 * 了WebAppClassLoader，也就是说哪个WebApp应用调用了spring，spring就去取该应用自己的
 * WebAppClassLoader来加载bean，简直完美~
 * <p>
 * 通过上面的两个案例分析，我们可以总结出线程上下文类加载器的适用场景：
 * <ul>
 * <li>当高层提供了统一接口让低层去实现，同时又要是在高层加载（或实例化）低层的类时，必须通过线程
 * 上下文类加载器来帮助高层的ClassLoader找到并加载该类。</li>
 * <li>当使用本类托管类加载，然而加载本类的ClassLoader未知时，为了隔离不同的调用者，可以取调用者
 * 各自的线程上下文类加载器代为托管。</li>
 * </ul>
 *
 * @author xuweizhi
 * @date 2019/03/21 22:45
 */
public class ThreadContextClassloader {

    /**
     * 我对线程上下文类加载器的理解:
     * Java提供了SPI服务，有第三方厂家自信实现，由于SPI的接口是被启动类加载器加载，
     * 无法加载第三方提供的实现类，因为第三方类库不在jre/lib目录下。提供了一个线程
     * 类加载器加载第三方实现类，实现了对SPI的服务。
     * 扩展类加载器 1.8有效
     */
    public static void main(String[] args) {

        //AESKeyGenerator keyGenerator = new AESKeyGenerator();
        //System.out.println(keyGenerator.getClass().getClassLoader());
        System.out.println(ThreadContextClassloader.class.getClassLoader());

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        //进入方法内部
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
