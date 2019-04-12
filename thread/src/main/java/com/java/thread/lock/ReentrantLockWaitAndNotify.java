package com.java.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关键字synchronized与wait()和notify()/notifyAll()方法结合实现等待/通知模式，类ReEntrantLock也可以实现同样的功能，
 * 但需要借助于Condition对象。Condition类是在jdk5中出现的技术，使用它拥有更好的灵活性，比如可以实现多路通知功能，也就是在
 * 一个lock对象里面可以创建多个Condition(即对象监视器)实例，线程对象可以注册在指定的Condition中，从而可以有选择性地进行线
 * 程通知，再调度线程上更加灵活。
 * <p>
 * 在使用notify()和notifyAll()方法进行通知时，被通知的线程却是有JVM随机选择的。但使用ReEntrantLock结合Condition类是可
 * 以实现前面介绍过的"选择性通知"，这个功能是非常重要的，而且在Condition类中是默认提供的。
 * <p>
 * 而Synchronized将相当于整个Lock对象中只有一个单一的Condition对象，所有的线程都注册在它一个对象的身上。线程开始notify时，
 * 需要通知所有等待的WAITING线程，没有选择权，会出现相当大的效率问题。
 * <p>
 * 必须配套使用，否则会报错
 * lock.lock();
 * condition.await();
 *
 * @author xuweizhi
 * @date 2019/01/02 16:15
 */
@Slf4j
public class ReentrantLockWaitAndNotify {

    static class Service {

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public void await() {
            try {
                lock.lock();
                log.info("await时间：" + System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("锁被释放了");
            }
        }

        public void signal() {
            try {
                lock.lock();
                log.info("singal时间为" + System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        Service service = new Service();
        Thread thread = new Thread(service::await);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Thread(service::signal);
        thread1.start();
    }

}


