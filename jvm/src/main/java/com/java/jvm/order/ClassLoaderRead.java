package com.java.jvm.order;


/**
 *
 *
 *  类加载器的双亲委托模型的好处：
 *
 *  1.可以确保Java核心库的类型安全：所有Java应用都至少会引用java.lang.object类，也就是说明在运行期，java.lang.Object这个类
 *    会被加载到Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的java.lang.
 *    Object类，而这些类之间还是不兼容的，相互不可见的（正是命名空间在发挥着作用）。
 *    借助于双亲委托机制，Java核心类库中的类的加载工作都是由启动类加载器来统一完成，从而确保了Java应用所使用的都是同一个版本的Java
 *    核心类库，他们之间是相互兼容的。
 *  2.可以确保Java核心类库所提供的类不会被自定义的类所替代
 *  3.不同的类加载器可以为相同名称（binary name）的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要不同的类加载器加载
 *    他们即可。不同类加载所加载的类之间是不兼容的，这就相当于Java虚拟机内部创建一个又一个相互隔离的Java类空间，这类技术在很多框架中
 *    都得到了实际应用。
 * @author xuweizhi
 * @date 2019/02/25 11:31
 *
 */
public class ClassLoaderRead {

    /**
     *
     * 在运行期，一个Java类是由该类的完全限定名（binary name，二进制名）和用于加载该类的定义类加载器（defining loader）所共同决定的。
     * 如果同样名字（即相同的完全限定名）的类是由两个不同的加载器所加载，那么这些类是不同的，即便.class文件的字节码完全一样，并且从相同的
     * 位置加载如此。
     *
     * 在Oracle的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则运行会出错，提示如下错误信息：
     *
     * Error occurred during initialization of VM
     * java/lang/NoClassDefFoundError: java/lang/Object
     */
    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        /**
         *
         * 内建于JVM中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类，
         * 当JVM启动时，一块特殊的机器码会运行，它会加载扩展类加载器与系统类加载器，这块特
         * 殊的机器码叫做启动类加载器（Bootstrap）。
         *
         * 启动类加载器并不是Java类，而其他的加载器则是Java类，启动类加载器是特定于平台的
         * 机器指令，他负责开启整个加载过程。
         *
         * 所有类加载器（除了启动类加载器）都被实现为Java类，不过，总归有一个组件来加载第一
         * 个Java类加载器，从而让整个加载过程能够顺利进行下去，加载第一个纯Java类加载器就是
         * 启动类加载器的职责。
         *
         * 启动类加载还会负责提供Java正常运行所需要的基本组件，这包括java.util和java.lang
         * 包中类等等。
         *
         */
        System.out.println(ClassLoader.class.getClassLoader());

        //扩展类加载器与系统类也是有启动类加载器所加载的,JDK1.8使用
        //System.out.println(Launcher.class.getClassLoader());

        System.out.println("----------------------------");

        // 默认的系统类加载器未定义
        // D:\root\JavaPlus\jvm\out\production\classes>java -Djava.system.class.loader=com.java.jvm.order.MyClassLoader
        // com.java.jvm.order.ClassLoaderRead 制定后系统类加载器是MyClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(ClassLoaderRead.class.getClassLoader());

        System.out.println(MyClassLoader.class.getClassLoader());

        System.out.println(ClassLoader.getSystemClassLoader());

    }
}
