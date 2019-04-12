package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2019/01/03 16:17
 */
public class ThreadState extends Thread {

    public ThreadState() {
        System.out.println("构造方法中的运行状态" + Thread.currentThread().getState());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Run中的运行状态" + Thread.currentThread().getState());
        waits();

        //notifyS();
    }

    private void notifyS() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            System.out.println("notify前的运行状态" + Thread.currentThread().getState());
            this.notifyAll();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notify后的运行状态" + Thread.currentThread().getState());
        }
    }

    private void waits() {
        synchronized (this) {
            try {
                System.out.println("wait前的运行状态" + Thread.currentThread().getState());
                this.wait();
                System.out.println("wait后的运行状态" + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadState state = new ThreadState();
        System.out.println("main1中的运行状态" + state.getState());
        ThreadState.sleep(1000);
        state.start();
        ThreadState.sleep(1000);
        Thread thread = new Thread(state::notifyS);
        thread.start();
        Thread.sleep(100);
        System.out.println("main2中的运行状态" + thread.getState());
        ThreadState.sleep(4000);
        System.out.println("main3中的运行状态" + state.getState());
    }
}
