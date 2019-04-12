package com.java.thread.synchronize;

/**
 * 关键字synchronized还可以应用在static静态方法上，如果这样写，那是对当前*.java文件对应的class文件进行持锁，
 * 类加锁
 *
 * @author xuweizhi
 * @date 2018/12/26 14:54
 */
public class StaticLockCode {

    public static void printA() {
        try {
            synchronized (StaticLockCode.class) {
                System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入方法printA");
                Thread.sleep(6000);
                System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开方法printA");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printB() {
        try {
            synchronized (StaticLockCode.class) {
                System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入方法printB");
                System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开方法printB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void printC() {
        System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入方法printC");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开方法printC");
    }

    /**
     * 不同步的原因是因为持有两个不同锁,static持有class类锁，可以对所有实例加锁
     * staticLock只是实例锁，不对static方法是两个锁
     * staticLockCode
     */
    public static void main(String[] args) {
        StaticLock staticLock = new StaticLock();
        StaticLock staticLock1 = new StaticLock();
        new Thread(() -> staticLock.printA(), "A").start();
        new Thread(() -> staticLock.printB(), "B").start();
    }

}
