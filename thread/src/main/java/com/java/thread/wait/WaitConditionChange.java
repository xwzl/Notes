package com.java.thread.wait;


import java.util.ArrayList;
import java.util.List;

/**
 * 解决类 {@link }
 * @author xuweizhi
 * @date 2018/12/28 21:13
 */
public class WaitConditionChange {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        //list集合中有一个数值，却进行了两次减，所以会出错。
        new Thread(subtract::subtract, "Subtract1").start();
        new Thread(subtract::subtract, "Subtract2").start();
        Thread.sleep(1000);
        new Thread(add::add, "Add").start();
    }

}

class Add {

    private String lock;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObjects.list.add("anything");
            lock.notifyAll();
        }
    }

}

class Subtract {

    private String lock;

    public Subtract(String lock) {
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                if (ValueObjects.list.size() == 0) {
                    System.out.println("begin" + Thread.currentThread());
                    lock.wait();
                    System.out.println("end" + Thread.currentThread());
                }
                ValueObjects.list.remove(0);
                System.out.println("List size" + ValueObjects.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ValueObjects {
    public static List<Object> list = new ArrayList<>();
}
