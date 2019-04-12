package com.java.thread.synchronize;

/**
 * 在使用同步代码块需要注意的是，当一个线程访问object的一个同步代码块的时候，其他线程对同一个object中所有其他synchronized同步的访问将被阻塞，
 * 这说明synchronized使用的对象监视器是一个
 * 同步代码块拥有的是相同的锁对象。
 *
 * @author xuweizhi
 * @date 2018/12/26 11:38
 */
public class ObjectListen {

    public void methodA() {
        synchronized (this) {
            System.out.println("A begin time = " + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A begin time = " + System.currentTimeMillis());
        }
    }

    public void methodB() {
        synchronized (this) {
            System.out.println("B begin time = " + System.currentTimeMillis());
            System.out.println("B begin time = " + System.currentTimeMillis());
        }
    }

    private void methodC(){
        synchronized (this){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程啊"+System.currentTimeMillis());
        }
    }

    public static void main(String[] args){
        //lockOneObject();
        lockTwoObject();
    }

    /**
     * 同步代码块的锁对象是同一个
     */
    public static void lockOneObject() {
        ObjectListen listen = new ObjectListen();
        new Thread(listen::methodA, "A").start();
        new Thread(listen::methodB, "B").start();
        System.out.println("共用一个锁对象");
    }

    public static void lockTwoObject(){
        ObjectListen listen = new ObjectListen();
        ObjectListen listen1 = new ObjectListen();
        new Thread(listen::methodC, "A").start();
        new Thread(listen1::methodC, "B").start();
        System.out.println("用一个锁对象");
    }

}
