package com.java.thread.pool;

import java.util.concurrent.*;

/**
 * @author xuweizhi
 * @date 2019/01/17 9:53
 */
public class WorkQueue {

    public static void main(String[] args) {
        System.out.println("\n\n =======SynchronousQueue====== \n\n");
        Executor executors = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new RejectHandler());
       executors.execute(() -> System.out.println("这是一个好消息"));

    }
}

class RejectHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("It is rejected");
    }
}
