package com.java.thread.synchronize;

/**
 * 多线程死锁问题
 *
 * @author xuweizhi
 * @date 2018/12/26 16:16
 */
public class DealThread implements Runnable {

    private String username;

    /**
     * (1).所谓“局部内部类”就是在对象的方法成员内部定义的类。而方法中的类，访问同一个方法中的局部变量，却必须要加上一个final。
     * (2).原因是编译程序实现上的困难：内部类对象的生命周期会超过局部变量的生命期。局部变量的生命期：当该方法被调用时，该方法中
     * 的局部变量在栈中被创建，当方法调用结束时，退栈，这些局部变量全部死亡。而内部类对象生命期，与其它类一样，当创建一个局
     * 部内部类对象后，只有当没有其它人再引用它时，它才能死亡。所以完全可能一个方法已调用结束（局部变量已死亡），但该局部类
     * 的对象仍然活着。即：局部类的对象生命期会超过局部变量。
     * (3).局部内部类的对象访问同一个方法中的局部变量，那么这就要求只要局部内部类对象还活着，那么栈中的那些它要访问的局部变量就
     * 不能“死亡”（否则：它都死了，还访问个什么呢？）。这就是说：局部变量的生命期至少等于或大于局部内部类对象的生命期。
     * (4).解决方法：局部内部类的对象可以访问同一个方法中被定义为final的局部变量。定义为final后，编译程序的实现方法：将所有的
     * 局部内部类对象要访问的final型局部变量，都拷贝成为该内部类对象中的一个数据成员。这样，即使栈中局部变量（含final）已
     * 死亡，但由于它是final,其值永不变，因而局部内部类对象在变量死亡后，照样可以访问final型局部变量。
     * (5).归纳总结：局部内部类对象中包含有要访问的final型局部变量的一个拷贝，成为它的数据成员。因此，正是在这个意义上，final
     * 型局部变量的生命期，超过其方法的一次调用。严格来说，方法调用结束，所有的局部变量（含final）全死亡了。但：局部内部类
     * 对象中有final型局部变量的拷贝。
     */
    private final Object lock1 = new Object();

    private final Object lock2 = new Object();

    public DealThread(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (this.username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock1锁等待完毕，尝试进入lock2同步代码块");
                synchronized (lock2) {
                    System.out.println("按lock1->lock2代码顺序执行了");
                }
            }
        }
        if (this.username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock2锁等待完毕，尝试进入lock1同步代码块");
                synchronized (lock1) {
                    System.out.println("按lock2->lock1代码顺序执行了");
                }
            }
        }
    }

    /**
     *  1.lock1拿到锁，等待方法继续执行
     *  2.lock2拿到锁，等待方法继续执行
     *  3.lock1方法等待完毕，进入lock2方法，但是没办法获取lock2的，因为lock2的锁在（2）中被锁定。
     *  4.lock1方法等待lock2方法执行完毕，lock2方法等待时间完毕，继续往下执行，却发现拿不了lock1的锁，wtf?
     *  5.尝试获取lock1的锁，lock1又在等待lock2，无限循环，造成死锁。
     *
     *  只要互相等待对方释放锁，就有可能出现死锁的现象。
     *  获取死锁监控，百度一下。
     *
     *  了解一下内部类
     *  {@link PublicClass}
     */
    public static void main(String[] args) {
        DealThread dealThread = new DealThread("a");
        new Thread(dealThread).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dealThread.username = "b";
        //出现死锁
        new Thread(dealThread).start();
    }
}
