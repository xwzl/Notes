package com.java.lambda.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author xuweizhi
 * @date 2018/11/30 16:24
 */
public class Bank {

    private double[] accounts;

    private Lock bankLock;

    private Condition sufficientFunds;

    /**
     * Condition newCondition( )
     * 返回一个与该锁相关的条件对象
     * <p>
     * void await( )
     * 将该线程放到条件的等待集中。
     * <p>
     * void signalA11( )
     * 解除该条件的等待集中的所有线程的阻塞状态。
     * <p>
     * void signal ( )
     * 从该条件的等待集中随机地选择一个线程， 解除其阻塞状
     */
    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            this.accounts[0] = initialBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    /**
     *
     *     换句话说，
     *     public synchronized void method(){
     *         method body
     *     }
     *
     *     等价于
     *     public void method(){
     *         this.intrinsidock.1ock();
     *         try
     *         {
     *             method body
     *         }
     *         finally { this.intrinsicLock.unlockO;
     *         }
     *      }
     *
     * @param from 模拟转账的人
     * @param to 模拟接收人
     * @param amount 模拟转账金额
     */
    public void transfer(int from, int to, double amount){
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                try {
                    sufficientFunds.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.println(String.format("%10.2f from %d to %d ", amount, from, to));
            accounts[to] += amount;
            System.out.println(String.format("Total Balance: %10.2f%n", getTotalBalance()));
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * void notifyAll()
     * 解除那些在该对象上调用 wait 方法的线程的阻塞状态。该方法只能在同步方法或同步
     * 部调用。如果当前线程不是对象锁的持有者，该方法拋出一个 IllegalMonitorStateException
     * 异常。
     * void notify()
     * 随机选择一个在该对象上调用 wait 方法的线程， 解除其阻塞状态。该方法只能在一
     * 个同步方法或同步块中调用。如果当前线程不是对象锁的持有者， 该方法抛出一个
     * IllegalMonitorStateException
     * void wait( )
     * 导致线程进人等待状态直到它被通知。该方法只能在一个同步方法中调用。如果当前
     * 线程不是对象锁的持有者，该方法拋出一个 IllegalMonitorStateException 异常。
     * void wait(long mi11is)
     * void wait(long mi 11Is, int nanos )
     * 导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在一个同步
     * 法中调用。如果当前线程不是对象锁的持有者该方法拋出一个 IllegalMonitorStateException
     */
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }

    public static void main(String[] args) {
        Bank bank = new Bank(4, 1000);
        Thread thread1 = new Thread(() -> bank.transfer(0, 1, 500));
        Thread thread2 = new Thread(() -> bank.transfer(0, 1, 600));
        Thread thread3 = new Thread(() -> bank.transfer(2, 1, 500));
        Thread thread4 = new Thread(() -> bank.transfer(0, 1, 500));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
