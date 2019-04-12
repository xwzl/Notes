package com.java.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * 解决类 {@link }
 *
 * @author xuweizhi
 * @date 2018/12/28 21:13
 */
public class Change {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Adds adds = new Adds(lock);
        Subtracts subtracts = new Subtracts(lock);
        //list集合中有一个数值，却进行了两次减，所以会出错。
        new Thread(subtracts::subtract, "Subtract11").start();
        new Thread(subtracts::subtract, "Subtract22").start();
        Thread.sleep(1000);
        new Thread(adds::add, "Adda").start();
    }

}

class Adds {

    private String lock;

    public Adds(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObjectss.list.add("anything");
            lock.notifyAll();
        }
    }

}

class Subtracts {

    private String lock;

    public Subtracts(String lock) {
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                while (ValueObjectss.list.size() == 0) {
                    System.out.println("begin" + Thread.currentThread());
                    lock.wait();
                    System.out.println("end" + Thread.currentThread());
                }
                ValueObjectss.list.remove(0);
                System.out.println("List size" + ValueObjectss.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ValueObjectss {
    public static List<Object> list = new ArrayList<>();
}
