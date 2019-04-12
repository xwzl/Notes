package com.java.thread.synchronize;

/**
 * 同步方法容易造成死锁，用同步块来解决问题
 * String 有常量池特性，因此采用new Object标致锁对象
 *
 * @author xuweizhi
 * @date 2018/12/26 16:08
 */
public class LoopDeathLock {

    private Object obj1 = new Object();

    private Object obj2 = new Object();

    void a() {
        synchronized (obj1) {
            System.out.println("begin");
            while (true) {
            }
        }
    }

    void b(){
        synchronized (obj2){
            System.out.println("begin1");
            System.out.println("end1");
        }
    }

    public static void main(String[] args){
        LoopDeathLock loopDeathLock = new LoopDeathLock();
        new Thread(loopDeathLock::a).start();
        new Thread(loopDeathLock::b).start();
    }

}
