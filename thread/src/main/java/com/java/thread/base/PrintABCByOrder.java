package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2019/01/03 16:54
 */
public class PrintABCByOrder extends Thread {

    private Object lock;

    private String chars;

    private int index;

    private int number;

    private static int totalCount = 1;

    public PrintABCByOrder(Object lock, String chars, int index) {
        this.lock = lock;
        this.chars = chars;
        this.index = index;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                if (totalCount % 3 == index) {
                    System.out.println(totalCount + Thread.currentThread().getName() + " " + chars);
                    totalCount++;
                    number++;
                    lock.notifyAll();
                    if (number == 3) {
                        break;
                    }
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        PrintABCByOrder order1 = new PrintABCByOrder(lock, "A", 1);
        PrintABCByOrder order2 = new PrintABCByOrder(lock, "B", 2);
        PrintABCByOrder order3 = new PrintABCByOrder(lock, "C", 0);
        order1.start();
        order2.start();
        order3.start();

    }

}
