package com.java.thread.base;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 暂停线程意味着此线程还可以恢复运行，在java多线程中，可以用suspend()方法暂停线程，使用resume()恢复线程的执行。
 *
 * @author xuweizhi
 * @date 2018/12/24 17:50
 */
public class MyThreadSuspend extends Thread {

    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
        }
    }

    // 如果使用不当，极易造成公共的同步对象的独占，使得其他线程无法访问公共对象。
    // 数据不同步的问题，因为线程短暂停止，会导致数据不同步的问题。
    public static void main(String[] args) {
        try {
            MyThreadSuspend suspend = new MyThreadSuspend();
            suspend.start();
            Thread.sleep(5000);

            //A段 线程等待
            suspend.suspend();
            System.out.println("A="+System.currentTimeMillis()+" i="+suspend.getI());
            Thread.sleep(5000);
            System.out.println("A="+System.currentTimeMillis()+" i="+suspend.getI());

            suspend.resume();
            Thread.sleep(5000);

            //B段 线程唤醒
            suspend.suspend();
            System.out.println("B="+System.currentTimeMillis()+" i="+suspend.getI());
            Thread.sleep(5000);
            System.out.println("B="+System.currentTimeMillis()+" i="+suspend.getI());
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
                System.out.println(threadEntry.getKey());
                Stream.of(threadEntry.getValue()).forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}