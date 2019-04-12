package com.java.thread.base;

/**
 * @author xuweizhi
 * @date 2018/12/24 17:25
 */
public class MyThreadStop extends Thread {

    private int i = 0;

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * stop方法相当的暴力，直接终止服务
     * 因为强制让线程停止则有可能使一些清理工作得不到完成，另外一个情况就是对锁对象的解锁，导致数据得不到同步的处理，出现数据不一致的问题。
     * 使用stop方法释放锁将会给数据造成不一致性的结果。如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误，一定要特别注意。
     * @param args
     */
    public static void main(String[] args){
           MyThreadStop stop = new MyThreadStop();
           stop.start();
        try {
            Thread.sleep(8000);
            stop.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
