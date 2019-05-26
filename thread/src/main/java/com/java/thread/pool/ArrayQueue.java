package com.java.thread.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author xuweizhi
 * @since 2019/05/26 10:31
 */
@Slf4j
public class ArrayQueue {

    private static int count = 1;

    public static void main(String[] args) {
        arrayBlockingQueue();
        //linkedBlockingDeque();
        //synchronousQueue();
    }

    private static void arrayBlockingQueue() {
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        queueDemo(arrayBlockingQueue);
    }

    /**
     * 超过核心线程后，由于新任务被直接提交到队列中，因此不会触发最大线程池数量
     */
    private static void linkedBlockingDeque() {
        LinkedBlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();
        queueDemo(blockingDeque);
    }

    /**
     * SynchronousQueue:同步队列，直接提交任务，没有队列缓存；拒绝策略与最大线程池有关系；
     */
    private static void synchronousQueue() {
        SynchronousQueue<Runnable> workQueue = new SynchronousQueue<>();
        queueDemo(workQueue);
    }


    private static void queueDemo(BlockingQueue<Runnable> workQueue) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, workQueue,
                r -> {
                    String time = LocalDateTime.now().toString();
                    return new Thread(() -> {
                        log.info("开始" + time);
                        r.run();
                    }, "time" + time);
                }, (r, executor1) -> log.info("被拒绝" + count++));

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> log.info(LocalDateTime.now().toString()));
        }
    }


}
