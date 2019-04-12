package com.java.thread.synchronize;

/**
 * 当多线程同时执行synchronized(x){} 出现同步效果
 *
 * @author xuweizhi
 * @date 2018/12/26 13:56
 */
public class BlockLock {

    public void method(MyObject myObject, long sleepTime) {
        synchronized (myObject) {
            try {
                System.out.println(Thread.currentThread().getName() + " methodA_getLock time=" + System.currentTimeMillis());
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " methodA releaseLock time=" + System.currentTimeMillis());
                System.out.println("耗时1" + (CommonUtil.startTime - System.currentTimeMillis()));
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程锁对象
     */
    static class MyObject {

        synchronized private void mineMethod() {
            System.out.println(Thread.currentThread().getName() + " methodA_getLock time=" + System.currentTimeMillis());
            System.out.println("------------------------------------------------------");
            System.out.println(Thread.currentThread().getName() + " methodA releaseLock time=" + System.currentTimeMillis());
            System.out.println("耗时" + (CommonUtil.startTime - System.currentTimeMillis()));
            System.out.println("");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void say() {
            System.out.println("没有同步锁，所有我是异步方法");
            System.out.println("耗时" + (CommonUtil.startTime - System.currentTimeMillis()));
            System.out.println("");
        }

        private void mineMethodA() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " This is a synchronized's code!" + " Run time:" + System.currentTimeMillis());
                System.out.println("耗时" + (CommonUtil.startTime - System.currentTimeMillis()));
                System.out.println("");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("mineMethodA 等待结束" + (CommonUtil.startTime - System.currentTimeMillis()));
                System.out.println("");
            }
        }
    }

    static class CommonUtil {

        private static long startTime;

        private static long startTime1;

        private static long currentTime;
    }

    /**
     * 由打印结果可知
     * 假如是同一个对象锁，同步代码执行顺序优先级
     * synchronized(this)>synchronized>synchronized(x)
     * next class{@link StaticLock}
     */
    public static void main(String[] args) {
        CommonUtil.startTime = System.currentTimeMillis();
        BlockLock blockLock = new BlockLock();
        MyObject myObject = new MyObject();
        MyObject myObject1 = new MyObject();
        //1.当多个线程同时执行synchronized(x){}同步代码块时呈同步现象

        new Thread(() -> blockLock.method(myObject, 1000), "blockLock对象锁A").start();
        new Thread(() -> blockLock.method(myObject1, 1000), "第二个blockLock对象锁C").start();
        new Thread(() -> blockLock.method(myObject, 1000), "blockLock对象锁B").start();
        new Thread(() -> blockLock.method(myObject1, 1000), "第二个blockLock对象锁C").start();
        new Thread(() -> blockLock.method(myObject, 1000), "blockLock对象锁B").start();
        new Thread(() -> blockLock.method(myObject, 1000), "blockLock对象锁B").start();
        //使用不同的对象监听，则同步方法会出现异步现象
        new Thread(() -> blockLock.method(myObject1, 1000), "第二个blockLock对象锁C").start();
        new Thread(myObject::mineMethod, "myObject对象锁").start();
        //2.当其他线程执行x对象中synchronized同步方法是呈同步效果。
        new Thread(myObject::mineMethod, "myObject对象锁").start();
        new Thread(myObject::say, "myObject无所方法").start();
        new Thread(myObject::mineMethod, "myObject对象锁").start();
        new Thread(myObject::say, "myObject无所方法").start();
        new Thread(myObject::mineMethodA, "myObject同步代码块").start();
        new Thread(myObject::say, "myObject无所方法").start();
        //3.当其他线程执行x对象方法里面的synchronized(this)代码块时也呈现同步效果。
        new Thread(myObject::mineMethodA, "myObject同步代码块").start();
        new Thread(myObject::mineMethodA, "myObject同步代码块").start();

    }

}
