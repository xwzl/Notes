package com.java.thread.until;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 * @date 2019/04/07 13:08
 */
@Data
public class ThreadPools {

    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private TimeUnit unit;

    private BlockingQueue<Runnable> workQueue;

    private ThreadFactory threadFactory;

}
