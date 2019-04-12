package com.java.thread.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/12/27 13:52
 */
public class ComminutionList {

    private List<String> list = new ArrayList<>();

    public void add() {
        list.add("(^v^)");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ComminutionList list = new ComminutionList();
        ThreadA threadA = new ThreadA(list);
        threadA.setName("A");
        threadA.start();
        try {
            Thread.sleep(3999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadB threadB = new ThreadB(list);
        threadB.setName("B");
        threadB.start();
    }
}

class ThreadA extends Thread {

    private ComminutionList comminutionList;

    public ThreadA(ComminutionList comminutionList) {
        this.comminutionList = comminutionList;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 10; i++) {
                comminutionList.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadB extends Thread {

    private ComminutionList comminutionList;

    public ThreadB(ComminutionList comminutionList) {
        this.comminutionList = comminutionList;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                //获取不了数据
                //System.out.println(comminutionList.size());
                if (comminutionList.size() == 5) {
                    throw new InterruptedException();
                }
                //Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}