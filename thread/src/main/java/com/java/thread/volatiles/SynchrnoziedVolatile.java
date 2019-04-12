package com.java.thread.volatiles;

/**
 * synchronized可以使多个线程访问同一个资源具有同步性，而且它还具有将线程工作内存中的私有变量与公共内存中的变量同步的功能。
 *
 * @author xuweizhi
 * @date 2018/12/27 13:12
 */
public class SynchrnoziedVolatile {

    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    private void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    private void printMethod() {
        try {
            while (isContinuePrint) {
                //synchronized (this) {
                    System.out.println(Thread.currentThread());
                //}
            }
            System.out.println("停下来了");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        this.isContinuePrint = false;
    }

    public static void main(String[] args) {
        SynchrnoziedVolatile printString = new SynchrnoziedVolatile();
        new Thread(printString::printMethod, "AA").start();
        System.out.println("我要停止这个线程:" + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(printString::stop, "BB").start();
    }


}
