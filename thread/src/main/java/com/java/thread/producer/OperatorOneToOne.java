package com.java.thread.producer;

import java.util.ArrayList;
import java.util.List;

/**
 * while与if多线程中区别参考 WhileAndIf
 *
 * @author xuweizhi
 * @date 2018/12/29 14:40
 */
public class OperatorOneToOne {

    public static void main(String[] args) {
        //onePToManyC();
        //manyPToManyC();
        MyStack stack = new MyStack();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
    }

    private static void manyPToManyC() {
        MyStack stack = new MyStack();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
    }

    private static void onePToManyC() {
        MyStack stack = new MyStack();
        new Thread(() -> {
            while (true) {
                stack.push();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                String pop = stack.pop();
                System.out.println("pop=" + pop);
            }
        }).start();
    }

}

class MyStack {

    private List<String> list = new ArrayList<>();

    public synchronized void push() {
        try {
            while (list.size() == 1) {
                //if (list.size() == 1) {
                this.wait();
            }
            list.add("AnyThing" + Math.random());
            this.notifyAll();
            //this.notify();解决线程假死问题
            System.out.println("Push" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String pop() {
        String returnValue = "";
        try {
            //if 与 while 在多线程中的差别 参考WhileAndIf.txts
            while (list.size() != 1) {
                //if (list.size() != 1) {
                System.out.println("pop操作的：" + Thread.currentThread().getName() + " 线程是wait状态");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            //this.notify(); 解决线程假死问题
            this.notifyAll();
            System.out.println("pop=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

