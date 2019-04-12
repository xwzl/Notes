package com.java.thread.volatiles;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 据说用Eclipse开发环境运行，会出现线程终止的情况发生
 * 但是我没有出现线程终止的情况
 * 在服务环境中，运行jvm时加入参数server，就会出现无限死循环
 * 是什么样的原因造成JVM设置为-server就出现是循环呢？在动RunThread.java线城时，变量 private boolean isRunning=true;
 * 存在于公共堆栈以及线程有得私有堆栈中。在JVM被设置为-server模式时为了线程运行的效率，线程一直在私有堆栈中取得isRunning的值
 * true。而代码thread.setRunning(false);虽然被执行，更新的却是公共堆栈中的isRunning变量值false,所以一直就是死循环的状态。
 * <p>
 * 这个问题其实就是私有堆栈中的值和公共堆栈中的值不同步造成的。解决这样的就在于使用volatile关键字了，它的主要作用就是当线程访问
 * isRunning这个变量时，强制从公共堆栈中获取数据。
 * </p>
 * 使用volatile关键字增加了实例变量在多个线程之间的可见性。但是volatile关键字最致命的缺点是不支持原子性:
 *   1.关键字volatile是线程同步的轻量级实现，所以volatile性能肯定比synchronized要好，并且volatile执行修饰变量，而synchronized
 * 可以修饰方法。以及代码块。随着JDK新版本的发布，synchronized关键字在执行效率上得到很大提升，在开发中使用synchronized的比率还是
 * 比较大的。
 *   2.多线程访问volatile不会发生阻塞，而synchronized会发生阻塞。
 *   3.volatile保证了数据的可见性，但不保证原子性。而synchronized可以保证原子性，也可以间接保证可见性。因为它会将私有内存和公共内存中
 * 的数据做同步。
 *   4.再次重申一次，关键字volatile是解决变量在多个线程之间的可见性。而synchronized关键字解决的是多个线程之间访问资源的同步性。
 * 线程安全包含原子性和可见性两个方面，Java的同步机制都是围绕这两个方面来确定线程安全的。
 *
 * 接下来验证volatile验证非原子的特性 {@link VolatileNoAtomic}
 *
 * @author xuweizhi
 * @date 2018/12/27 9:41
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
public class RunThread extends Thread {

    /**
     * 从线程公共堆栈中获取数据
     */
    private volatile boolean isRunning = true;

    private RunThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        log.info("进入了{}", this.getClass());
        while (isRunning == true) {
            // 线程睡眠会使线程终止循环，个人见解就是当while循环中，在多次判定inRunning的值true情况系,jvm为了优化执行效率，在私有栈获取值，
            // 不在从公共站获取值，所以线程不会出现终止的情况
            //try {
            //    Thread.sleep(100);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        }
        System.out.println("线程停止了");
    }


    public static void main(String[] args) {
        RunThread thread = new RunThread("线程A");
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setRunning(false);
        log.info("已经赋值为false");
    }
}