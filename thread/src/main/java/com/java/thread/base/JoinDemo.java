package com.java.thread.base;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author xuweizhi
 * @since 2019-08-15
 */
@Slf4j
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        //Thread[] threads = new Thread[10];
        //for (int i = 0; i < 10; i++) {
        //    threads[i] = new JoinThread("线程" + i);
        //    threads[i].start();
        //}
        //for (int i = 0; i < 10; i++) {
        //    threads[i].join();
        //}
        JoinThread joinThread = new JoinThread("线程1");
        joinThread.setDaemon(true);
        joinThread.start();
        //joinThread.join();
        Thread.sleep(100);
        log.info("等待线程1被执行完毕！");
    }

}


@Slf4j
class JoinThread extends Thread {

    public JoinThread(@NotNull String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            log.info(i + "");
        }
    }
}
