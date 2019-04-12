package com.java.thread.wait;

/**
 * @author xuweizhi
 * @date 2018/12/28 20:46
 */
public class AvoidNotifyEarly {

    public static void main(String[] args) {
        //earlyNotify();
        Punctual punctual = new Punctual(new Object());
        new Thread(punctual::notifyBoy).start();
        new Thread(punctual::waitGirlNotify).start();
    }

    private static void earlyNotify() {
        Early early = new Early(new Object());
        //有可能出现Girl is death不会打印的情况，因为notifyBoy方法先执行
        new Thread(early::waitGirlNotify).start();
        new Thread(early::notifyBoy).start();
    }

}

class Punctual {

    private Object lock;

    private boolean flag = true;

    public Punctual(Object object) {
        this.lock = object;
    }

    public void waitGirlNotify() {
        synchronized (lock) {
            try {
                //如果通知线程先启动，wait线程永远不会启动。
                while (flag) {
                    System.out.println("Boy wait for girl to  eat meals in BeiJingKaoYa restaurant ");
                    lock.wait();
                    System.out.println("Girl is active!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyBoy() {
        try {
            Thread.sleep((long) Math.random());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            System.out.println("进入饭店！");
            lock.notify();
            this.flag = false;
            System.out.println("和男孩一起吃晚餐！");
        }
    }

}

class Early {

    private Object lock;


    public Early(Object object) {
        this.lock = object;
    }

    public void waitGirlNotify() {
        synchronized (lock) {
            try {
                System.out.println("Boy wait for girl to  eat meals in BeiJingKaoYa restaurant ");
                lock.wait();
                System.out.println("Girl is death!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyBoy() {
        synchronized (lock) {
            System.out.println("进入饭店！");
            lock.notify();
            System.out.println("和男孩一起吃晚餐！");
        }
    }

}
