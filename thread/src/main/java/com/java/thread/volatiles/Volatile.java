package com.java.thread.volatiles;

import com.java.thread.template.TemplatePattern;

/**
 *
 * 分析volatile的应用场景
 * volatile关键字的作用是强制从公共堆栈中获取变量的值，而不是从线程私有数据栈中获取变量的值。
 * volatile关键字的作用是使变量在多线程间可见。
 *
 * Java线程设计技巧或者模式，参考{@link TemplatePattern}
 *
 * 测试实例{@link }
 * @author xuweizhi
 * @date 2018/12/27 9:24
 */
public class Volatile {

  public static void main(String[] args){
      //TemplatePattern templatePattern = new TemplatePattern();
  }
}

class PrintString {

    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printStringMethod() {
        try {
            while (isContinuePrint) {
                System.out.println(Thread.currentThread());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It is able to be stopped;
     */
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        new Thread(printString::printStringMethod, "线程1").start();
        //printString.printStringMethod();
        System.out.println("我要停止这个线程:" + Thread.currentThread());
        printString.setContinuePrint(false);
        //notStop();
    }

    /**
     * 发现根本停不下来
     */
    private static void notStop() {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("我要停止这个线程:" + Thread.currentThread());
        printString.setContinuePrint(false);
    }

}
