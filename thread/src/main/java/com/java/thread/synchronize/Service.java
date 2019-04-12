package com.java.thread.synchronize;

/**
 * 根据前面对synchronized(this)同步代码块可知，synchronized(非this对象)格式的作用只用一种：synchronized(非this对象)同步代码块。
 * 同步针对的是实例变量
 *
 * @author xuweizhi
 * @date 2018/12/26 12:39
 */
public class Service {

    private String username;

    private String password;

    private String any = new String();

    public void setUsernameAndPassword(String username, String password) {
        try {
            synchronized (any) {
                System.out.println("线程名称：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步代码块");
                System.out.println(any);
                this.username = username;
                Thread.sleep(3000);
                this.password = password;
                System.out.println("线程名称：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步代码块");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setUsernameAndPasswords(String username, String password) {
        try {
            String lock = new String();
            synchronized (lock) {
                System.out.println("线程名称q：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步代码块");
                System.out.println(lock);
                this.username = username;
                Thread.sleep(3000);
                this.password = password;
                System.out.println("线程名称q：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步代码块");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 任意对象都可以成为锁
     * 锁非this对象有一定的有点:如果一个类中有很多个synchronized方法，这时虽然能实现同步，但是会受到阻塞，所以影响运行效率。但如果使用同步代码块
     * 锁非this对象，则是异步的不与其它锁this方法争抢this锁，则可大大提高运行效率。
     */
    public static void main(String[] args) {
        noThis();
        //Service service = new Service();
        //new Thread(() -> service.setUsernameAndPasswords("a", "aa"), "A").start();
        //new Thread(() -> service.setUsernameAndPasswords("b", "aa"), "B").start();
    }

    /**
     * 非this对象线程锁
     */
    private static void noThis() {
        Service service = new Service();
        //service.any = new String("对象锁1");
        new Thread(() -> service.setUsernameAndPassword("a", "aa"), "A").start();
        //try {
        //    Thread.sleep(100);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //service.any = new String("对象锁2");
        new Thread(() -> service.setUsernameAndPassword("b", "aa"), "B").start();
    }

}
