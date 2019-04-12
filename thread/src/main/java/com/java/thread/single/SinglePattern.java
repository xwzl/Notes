package com.java.thread.single;

import com.java.thread.until.PoolUntil;

import java.io.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;

/**
 * 静态内部类创建单例模式
 *
 * @author xuweizhi
 * @date 2019/01/03 14:23
 */
public class SinglePattern {

    private static class MyOuterClass {
        private static SinglePattern singlePattern = new SinglePattern();
    }

    private SinglePattern() {
    }

    public static SinglePattern getInstance() {
        return MyOuterClass.singlePattern;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> System.out.println(SinglePattern.getInstance().hashCode()));
        }
    }

}

class SingleOne {

    /**
     * 什么是立即加载？立即加载就是使用类的时候已经将对象创建完毕，常见的实现办法就是直接new实例化。而立即加载从中文的语境来看，
     * 有着急，急迫的含义，所以称为恶汉模式
     * 立即加载方式 恶汉模式
     */
    private static SingleOne singleOne = new SingleOne();

    private SingleOne() {

    }

    /**
     * 此代码版本立即加载
     * 此版本代码的缺点是不能有其他实例变量
     * 因为getInstance()方法没有同步
     * 所有可能出现非线程安全。
     */
    public static SingleOne getInstance() {
        return singleOne;
    }

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            threadPoolExecutor.execute(() -> System.out.println(SingleOne.getInstance().hashCode()));
        }
    }

}

class SingleTwo {

    /**
     * 延迟加载就是在调用get()方法实例才被创建，常见的实现颁发就是在get()方法中进行new实例化。而延迟加载从中文的语境来看，是缓慢，不急迫的含义，也成为懒汉模式。
     * 多线程中高并发的情况下会创建多个实例
     */
    private static SingleTwo singleTwo;

    private SingleTwo() {

    }

    public static SingleTwo getInstance() {
        if (singleTwo != null) {
        } else {
            singleTwo = new SingleTwo();
        }
        return singleTwo;
    }


    public static void main(String[] args) {
        //ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        //for (int i = 0; i < 100; i++) {
        //    executor.execute(() -> System.out.println(SingleTwo.getInstance().hashCode()));
        //}
        Thread[] threads = new Thread[100];
        //Arrays.asList(threads).parallelStream().map(thread -> new Thread(() -> System.out.println(SingleTwo.getInstance().hashCode()))).forEach(Thread::start);
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(SingleTwo::run);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
    }


    private static void run() {
        System.out.println(SingleTwo.getInstance().hashCode());
    }
}

class SingleThree {

    /**
     * 延迟加载就是在调用get()方法实例才被创建，常见的实现颁发就是在get()方法中进行new实例化。而延迟加载从中文的语境来看，是缓慢，不急迫的含义，也成为懒汉模式。
     * 多线程中高并发的情况下会创建多个实例
     */
    private static SingleThree three;

    private SingleThree() {

    }

    /**
     * 加入synchronized解决单例模式出现多实例的解决方案 但是执行效率太低
     * 尝试同步代码块
     */
    public static synchronized SingleThree getInstance() {
        try {
            if (three != null) {
            } else {
                //模拟创建对象之前的一些准备工作
                Thread.sleep(5000);
                three = new SingleThree();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return three;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> System.out.println(SingleThree.getInstance().hashCode()));
        }
        Thread[] threads = new Thread[100];
        //Arrays.asList(threads).parallelStream().map(thread -> new Thread(() -> System.out.println(SingleThree.getInstance().hashCode()))).forEach(Thread::start);
        //for (int i = 0; i < 100; i++) {
        //    threads[i] = new Thread(SingleTwo::run);
        //}
        //for (int i = 0; i < 100; i++) {
        //    threads[i].start();
        //}
    }


}

class SingleFour {

    /**
     * 延迟加载就是在调用get()方法实例才被创建，常见的实现颁发就是在get()方法中进行new实例化。而延迟加载从中文的语境来看，是缓慢，不急迫的含义，也成为懒汉模式。
     * 多线程中高并发的情况下会创建多个实例
     */
    private static SingleFour singleFour;

    private SingleFour() {
    }

    /**
     * 加入synchronized解决单例模式出现多实例的解决方案 但是执行效率太低
     * 尝试同步代码块
     */
    public static SingleFour getInstance() {
        try {
            //此种写法等同于
            //public static SingleFour getInstance() {
            //的写法，效率一样很低
            synchronized (SingleFour.class) {
                if (singleFour != null) {
                } else {
                    //模拟创建对象之前的一些准备工作
                    Thread.sleep(5000);
                    singleFour = new SingleFour();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return singleFour;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> System.out.println(SingleFour.getInstance().hashCode()));
        }
        System.out.println((System.currentTimeMillis() - l));
    }


}


class SingleFive {

    private static SingleFive singleFive;

    private SingleFive() {

    }

    public static SingleFive getInstance() {
        try {
            if (singleFive != null) {
            } else {
                //模拟创建对象之前的一些准备工作
                Thread.sleep(5000);
                //只针对某些重要的代码块进行同步
                //使用 synchronized (SingleFive.class) 虽然部分代码块被上锁，但是还是有非线程安全问题
                synchronized (SingleFive.class) {
                    if (singleFive == null) {
                        singleFive = new SingleFive();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return singleFive;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> System.out.println(SingleFive.getInstance().hashCode()));
        }
        System.out.println((System.currentTimeMillis() - l));
    }

}

/**
 * 静态内置类可以达到线程安全问题，但如果遇到序列化对象时，使用默认的方式运行得到的结果还是多例的
 */
class SingleSix implements Serializable {

    private static final long serialVersionUID = 62030356653978163L;

    private static class Handler {
        private static final SingleSix SINGLE_SIX = new SingleSix();
    }

    private SingleSix() {
    }

    public static SingleSix getInstance() {
        return Handler.SINGLE_SIX;
    }

    /**
     * 这么神奇吗？
     */
    protected Object readResolve() {
        System.out.println("调用了这个方法");
        return Handler.SINGLE_SIX;
    }

    /**
     * 打印不是同一个对象
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SingleSix singleSix = SingleSix.getInstance();
        FileOutputStream fosRef = new FileOutputStream(new File("HotKey.txt"));
        ObjectOutputStream oosRef = new ObjectOutputStream(fosRef);
        oosRef.writeObject(singleSix);
        System.out.println(singleSix.hashCode());
        oosRef.close();
        fosRef.close();
        FileInputStream fisRef = new FileInputStream(new File("HotKey.txt"));
        ObjectInputStream iosRef = new ObjectInputStream(fisRef);
        SingleSix six = (SingleSix) iosRef.readObject();
        System.out.println(six.hashCode());
        iosRef.close();
        fisRef.close();
    }

}

/**
 * 使用静态代码块实现单例模式十分简单，不加编写
 * 使用枚举实现静态代码块同上
 */
