package com.java.thread.volatiles;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuweizhi
 * @date 2018/12/27 13:03
 */
@Slf4j
public class AtomicLongTest {

    private AtomicLong atomicLong = new AtomicLong();


    private void print() {
        for (int i = 0; i < 10000; i++) {
            log.info(atomicLong.incrementAndGet() + "");
        }
    }

    private synchronized void addNum() {
        log.info(Thread.currentThread() + "加100后的值" + atomicLong.addAndGet(100));
        //这个是原子的，但是方法之间的调用不是同步的,导致打印顺序出错
        atomicLong.addAndGet(1);
    }

    /**
     * 没有使用同步关键字，得到的结果却是正确的
     *
     * @param args
     */
    public static void main(String[] args) {
        //AtomicLongTest atomicLongTest = new AtomicLongTest();
        //new Thread(atomicLongTest::print, "AA").start();
        //new Thread(atomicLongTest::print, "BB").start();
        //new Thread(atomicLongTest::print, "CC").start();
        //new Thread(atomicLongTest::print, "DD").start();
        //new Thread(atomicLongTest::print, "EE").start();

        AtomicLongTest atomicLongTest1 = new AtomicLongTest();
        new Thread(atomicLongTest1::addNum, "AA").start();
        new Thread(atomicLongTest1::addNum, "BB").start();
        new Thread(atomicLongTest1::addNum, "CC").start();
        new Thread(atomicLongTest1::addNum, "DD").start();
        new Thread(atomicLongTest1::addNum, "EE").start();
    }

}
