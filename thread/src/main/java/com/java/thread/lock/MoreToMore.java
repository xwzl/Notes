package com.java.thread.lock;

import com.java.thread.until.PoolUntil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuweizhi
 * @date 2019/01/02 17:38
 */
@Slf4j
public class MoreToMore {

    static class Service {

        private ReentrantLock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        private Boolean hasValue = false;

        public void set() {
            try {
                lock.lock();
                while (hasValue) {
                    log.info("当前线程保持锁定的个数,调用lock方法的次数"+lock.getHoldCount());
                    log.info("有可能**连续，等待获取lock锁对象的线程数"+lock.getQueueLength()+"wait被唤醒的线程数"+lock.getWaitQueueLength(condition));
                    condition.await();
                }
                log.info("打印*");
                hasValue = true;
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void get() {
            try {
                lock.lock();
                while (!hasValue) {
                    log.info("当前线程保持锁定的个数,调用lock方法的次数"+lock.getHoldCount());
                    log.info("有可能☆☆连续"+lock.getQueueLength()+"wait"+lock.getWaitQueueLength(condition));
                    condition.await();
                }
                log.info("打印☆");
                //设置为被消费
                this.hasValue = false;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        Service service = new Service();
        ThreadPoolExecutor executor = PoolUntil.getThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    service.set();
                }
            });
            executor.execute(() -> {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    service.get();
                }
            });
        }

        //for (int i = 0; i < 10; i++) {
        //    //executor.execute(service::set);
        //    //executor.execute(service::get);
        //    new Thread(()->{
        //        for (int j = 0; j < Integer.MAX_VALUE; j++) {
        //            service.set();
        //        }
        //    }).start();
        //    new Thread(()->{
        //        for (int j = 0; j < Integer.MAX_VALUE; j++) {
        //            service.get();
        //        }
        //    }).start();
        //}
    }

}
