package com.java.thread.producer;

/**
 * @author xuweizhi
 * @date 2019/01/02 9:30
 */
public class TransportPrint {

    private volatile boolean flag = false;

    private synchronized void printA() {
        while (flag == true) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("输出");
        this.flag = true;
        this.notifyAll();
    }

    private synchronized void printB() {
        while (flag == false) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("输入");
        this.flag = false;
        this.notifyAll();
    }

    public static void main(String[] args) {
        TransportPrint transportPrint = new TransportPrint();
        for (int i = 0; i < 100; i++) {
            new Thread(transportPrint::printA).start();
            new Thread(transportPrint::printB).start();
        }
    }

}
