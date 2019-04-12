package com.java.thread.security;

/**
 * 关键字synchronized拥有锁可重入的功能，也就是在使用synchronized的时候，当一个线程获取锁对象后，再次请求此对象锁是是可以再次获得该对象的
 * 所得
 * 可重入锁的概念：自己可以再次获取自己的内部锁，比如有一条线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候
 * 还是可以获取的，如不可锁重入的话，就会造成死锁。
 * 同步不具有继承性
 * 关键字同步是由弊端的，加入某个线程调用方法加载时间过长，其他线程必须等待很久的时间。
 * @author xuweizhi
 * @date 2018/12/25 17:44
 */
public class ReloadLockParent {

    int i = 10;

    synchronized public void parentPrint() {

        try {
            i--;
            System.out.println("parent print i = " + i);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 子类不继承同步锁的特性
     * 子类可以有重入锁的特性
     */
    static class ReloadLockSub extends ReloadLockParent {

        @Override
        public void parentPrint() {
            System.out.println("AAA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void subPrint() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("sub print i = " + i);
                    Thread.sleep(100);
                    parentPrint();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
      ReloadLockSub sub = new ReloadLockSub();
      //Thread thread = new Thread(sub::subPrint);
      //thread.start();
      Thread thread1 = new Thread(sub::parentPrint);
      Thread thread2 = new Thread(sub::parentPrint);
      Thread thread3 = new Thread(sub::parentPrint);
      Thread thread4 = new Thread(sub::parentPrint);
      Thread thread5 = new Thread(sub::parentPrint);
      Thread thread6 = new Thread(sub::parentPrint);
      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
      thread5.start();
      thread6.start();
    }
}
