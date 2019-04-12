package com.java.thread.wait;

import lombok.extern.slf4j.Slf4j;

/**
 * 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程往往将早于子线程结束之前结束。
 * 这是，如果主线程想等待子线程执行完成之后在结束，比如子线程处理一个数据，主线程要取得这个数据中的值，用join方法。
 * 方法join的作用是等待线程对象销毁。
 *
 * @author xuweizhi
 * @date 2019/01/02 9:49
 */
@Slf4j
public class JoinDetails extends Thread {


    /**
     * join方法的作用是使所属的线程对象x正常执行run()方法中的任务，而是当前线程z进行无线期的阻塞，等待线程x销毁后再执行之后的代码。
     * 方法join具有线程排队运行的作用，有些类似同步的效果。
     * join与synchronized的区别是，join在内部使用wait方法进行等待，而synchronized关键字的作用是监视对象器原理作为同步。
     * join方法具有释放锁的特点
     * <p>
     * join(long)等待的是当前线程锁对象等待的时间，加入三个线程拥有的是同一个线程锁，每个线程等待的时间，将会进行累加。
     * 如果超时，则会执行其他线程锁对象的线程。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int mathValue = (int) (Math.random() * 10000);
            System.out.println(mathValue);
            try {
                Thread.sleep(mathValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //其他线程必须等待当前线程中的run方法被执行完成后，再返回。
        thread.join();
        System.out.println("a");
        log.info("主方法先于子线程执行");
    }

}

@Slf4j
class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                log.info("Time Start" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("Time End" + System.currentTimeMillis());
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                log.info("Time Start" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                log.info("Time End" + System.currentTimeMillis());
            }

        });
        thread.start();
        thread1.start();
        thread1.join(500);
        log.info("main end");
    }
}
