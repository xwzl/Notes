package com.java.base.thread;

/**
 * 一般来说，在多线程环境下，如果某个对象是非线程安全的，那么访问对象时必须采用synchronized进行线程同步。然而对于一些Object类，线程同步会降低并发行，影响系统性能，因此没有采用线程同步机制。同时，通过代码来解决线程安全比较困难。因此JDK提供了java.lang.ThreadLocal，为解决Object类多线程并发问题提供了一个新的思路。
 * <p>
 * ThreadLocal不是一个Thread，而是Thread的局部变量。当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * <p>
 * 这正好可以理解，为什么这Spring中Bean都可以声明为singleton作用域，就是因为Spring对于一些Bean（比如RequestContextHolder)采用来ThreadLocal处理非线程安全状态，这样它们就拥有了线程安全的状态，因此可以在多线程中共享了。
 * <p>
 * 总结一下:
 * <p>
 * 一般解决多线程的安全性的两种方法
 * <ul>
 * <li> 1.加Synchronized，保持并发同步</li>
 * <li>2.使用ThreadLocal 本地线程，每个线程一个变量副本</li>
 * </ul>
 * <p>
 * 两种线程安全方案的差异:
 * 同步机制是以“时间换空间”，ThreadLocal是以“空间换时间”。
 * 同步机制提供了一份变量，不同线程排队访问。ThreadLocal为每一个线程提供一份变量，自己访问自己的。
 * ThreadLocal的缺点很明显，就是占用内存比较大，但速度很快，所以在内存比较充足并且对并发执行效率
 * 要求高的情况下，使用ThreadLocal是一个好的选择。
 *
 * @author xuweizhi
 * @date 2019/03/24 17:15
 */
public class ThreadLocal {

}
