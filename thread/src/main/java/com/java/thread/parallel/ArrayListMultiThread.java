package com.java.thread.parallel;

import com.java.thread.until.PoolUntil;
import com.java.thread.until.ThreadPools;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 并发下的ArrayList
 *
 * @author xuweizhi
 * @date 2019/04/07 13:06
 */
public class ArrayListMultiThread {

    private static ArrayList<Integer> arrayList = new ArrayList<>();

    private static synchronized void addColumn() {
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor(new ThreadPools());
        executor.execute(ArrayListMultiThread::addColumn);
        executor.execute(ArrayListMultiThread::addColumn);

        Thread.sleep(30000);
        System.out.println(arrayList.size());
    }

}
