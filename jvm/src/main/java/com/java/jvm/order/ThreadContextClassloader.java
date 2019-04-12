package com.java.jvm.order;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author xuweizhi
 * @date 2019/02/26 22:20
 * <p>
 * 当前类加载器（Current classloader）
 * <p>
 * 每个类都会使用自己的类加载器（即加载自身的类加载器）去加载其他类（指的是所以来的类）
 * 如果ClassX引用了ClassY,那么ClassX的类加载器就会去加载ClassY(前提是ClassY未被加载)
 * <p>
 * 线程上下文类加载器（context classloader）
 * <p>
 * 线程上下文类加载器是从JDK1.2开始引入的，类Thread的getContextClassloader()和setContextClassloader
 * (Classloader classloader)方法分别获取和设置上下文类加载器。
 * <p>
 * 如果没有通过setContextClassloader(Classloader c1)进行设值的话，线程将继承其父线程的上下文类加载器，
 * Java应用运行时的初始线程的上下文类加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类与资源。
 * <p>
 * 线程上下文类加载器的重要性
 * <p>
 * SPI(Service Provider Interface)
 * 接口(跟加载器加载)由JDK确定，其他厂商实现它，因为双亲委托模型的出现，顶层的接口会尝试加载实现类，classpath
 * 路径下接口的实现类根类加载器无法加载，即虚拟机无法访问由厂商实现的类Class对象，因此线程下文类加载器的出现极其
 * 重要。
 * <p>
 * 父Class可以使用当前线程Thread.currentThread().getClassLoader()所指定的classloader加载的类，
 * 这就改变了父Classloader不能使用子Classloader或是其他没有直接父子关系的ClassLoader加载的类的情况，
 * 即改变了双亲委托模型。
 * <p>
 * 线程上下文类加载器就是当前线程的Current Classloader
 * <p>
 * 在双亲委托模型下，类加载器是由上至下的，即下层的类加载器委托上层进行加载。但是对于SPI来说，有些接口是Java核心库所提供的，
 * 而Java核心库是由启动类加载器来加载的，而这些接口的实现却来自于不同的jar包（厂商提供），Java的启动类加载器是不会加载其他
 * 来源的Jar包，这样传统的双亲委托模型就无法满足SPI的要求。而通过给当前线程设置上下文类加载器，就可以由设置的上下文类加载器
 * 来实现对接口实现的类加载。
 */
public class ThreadContextClassloader {

    static class MyThread implements Runnable {

        Thread thread;

        public MyThread() {
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            ClassLoader classLoader = this.thread.getContextClassLoader();
            this.thread.setContextClassLoader(classLoader);
            System.out.println("This thread context classloader" + classLoader.getClass());
            System.out.println("This thread parent context classloader" + classLoader.getParent().getClass());
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //System.out.println(Thread.currentThread().getContextClassLoader());
        //System.out.println(Thread.class.getClassLoader());

    }

}

/**
 * 线程上下文类加载器的一般使用模式（获取-设置-还原），框架中大量使用
 * ClassLoader classloader = Thread.currentThread().getContextClassloader();
 * <p>
 * try{
 * Thread.currentThread().setContextClassloader(targetClassloader);
 * myMethod();
 * }finally{
 * Thread.currentThread().setContextClassloader(classloader);
 * }
 * <p>
 * myMethod里面调用了Thread.currentThread().getContextClassloader(),获取当前线程的上下文类加载器做某些事情
 * <p>
 * 如果一个类由类加载器A加载，那么这个类的依赖类也是相同的类加载器加载的（如果该依赖类之前没有加载过的或）
 * <p>
 * ContextClassloader的作用就是为了破坏Java的加载委托机制
 * <p>
 * 当高层提供了统一的接口让低层实现，同时又要在高层加载（或实例化）低层的类时，就必须通过线程上下文类加载器帮助高层的ClassLoader
 * 找到并加载该类。
 */
class SpiImpl {

    /**
     * 查看ServiceLoader源码可知，SPI服务的提供者提供Jar必须有META-INF/service这个目录，且必须包含具体实现类的类路径
     * 如MySql的jar，包内包含META-INF/service.java.sql.Driver文件，
     * com.mysql.jdbc.Driver
     * com.mysql.fabric.jdbc.FabricMySQLDriver
     * <p>
     * //定义服务的接口
     * Example Suppose we have a service type com.controller.CodecSet which is intended to represent sets of encoder/decoder
     * pairs for some protocol. In this case it is an abstract class with two abstract methods:
     * public abstract Encoder getEncoder(String encodingName);
     * public abstract Decoder getDecoder(String encodingName);
     * Each method returns an appropriate object or null if the provider does not support the given encoding. Typical providers
     * support more than one encoding.
     * If com.controller.impl.StandardCodecs is an implementation of the CodecSet service then its jar file also contains a file
     * named
     * META-INF/services/com.controller.CodecSet//第三方Jar包service/目录下文件命名规则，让其上下文类加载器知道服务的接口，即JDK所提供的接口
     * This file contains the single line:
     * com.controller.impl.StandardCodecs    # Standard codecs //第三方具体实现类的二进制限定名
     * The CodecSet class creates and saves a single service instance at initialization:
     * private static ServiceLoader<CodecSet> codecSetLoader
     * = ServiceLoader.load(CodecSet.class);
     * To locate an encoder for a given encoding name it defines a static factory method which iterates through the known and a
     * vailable providers, returning only when it has located a suitable encoder or has run out of providers.
     * public static Encoder getEncoder(String encodingName) {
     * for (CodecSet cp : codecSetLoader) {
     * Encoder enc = cp.getEncoder(encodingName);
     * if (enc != null)
     * return enc;
     * }
     * return null;
     * }
     */
    public static void main(String[] args) {

        /**
         * 引起ServiceLoader由根类加载器加载，双亲委托机制的存在它加载的类会被分类加载器尝试加载，然而第三方软件商提供的接口和类位
         * 于ClassPath路径下，根类加载器无法加载接口，导致服务不可见。
         *
         * 打破双亲委托机制，假如不获取上下文类加载器，ServiceLoader中的接口将委托给根类加载，根类加载器可以加载接口，
         * 而无法加载第三方软件商提供的实现类，导致第三方实现的类对根类加载器不可见。
         *
         * 上下文类加载器默认是系统类加载器，所以接口和类都是系统类加载器加载的
         *
         */
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver: " + driver.getClass() + ",loader: " + driver.getClass().getClassLoader());
        }

        System.out.println("当前线程上下文类加载器: " + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader类加载器: " + ServiceLoader.class.getClassLoader());
    }

}

/**
 * SPI服务，JDBC的具体实现
 */
class JdbcImpl {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "username", "password");
    }

}

class PrintNineMNineTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i < j) {
                    break;
                }
                if (i == 4 || i == 3) {
                    if (j == 2) {
                        System.out.print(j + "*" + i + "=" + (i * j) + "  ");
                        continue;
                    }
                    System.out.print(j + "*" + i + "=" + (i * j) + " ");
                } else {
                    System.out.print(j + "*" + i + "=" + (i * j) + " ");
                }

            }
            System.out.println(" ");
        }
    }
}

class PrintTriangle {

    public static void main(String[] args) {
        int a = 6;
        for (int i = 1; i <= a; i++) {
            for (int j = a - i; j >= 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < (i * 2 - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}

class A {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

}