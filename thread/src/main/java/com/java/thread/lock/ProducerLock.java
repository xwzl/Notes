package com.java.thread.lock;

import com.java.thread.until.PoolUntil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuweizhi
 * @date 2019/01/02 17:11
 */
@Slf4j
public class ProducerLock {

    static class Service {

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        private Boolean flag = false;

        private void set() {
            try {
                lock.lock();
                while (flag) {
                    //log.info("等待消费者消费" + System.currentTimeMillis());
                    condition.await();
                }
                log.info("产出消息");
                this.flag = true;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void get() {
            try {
                lock.lock();
                while (!flag) {
                    //log.info("等待消息产出");
                    condition.await();
                }
                log.info("消费消息");
                //设置为被消费
                this.flag = false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        //new Thread(() -> {
        //    for (int i = 0; i < Integer.MAX_VALUE; i++) {
        //        service.set();
        //    }
        //}).start();
        //new Thread(() -> {
        //    for (int i = 0; i < Integer.MAX_VALUE; i++) {
        //        service.get();
        //    }
        //}).start();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                service.set();
            }
        });
        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                service.get();
            }
        });
    }
}
