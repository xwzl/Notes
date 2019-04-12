package com.java.thread.group;

/**
 * @author xuweizhi
 * @date 2019/01/04 12:08
 */
public class ThreadGroups {


    public static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("ThreadName = " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程关联线程组：1级关联
     * 所谓1级关联就是父对象中有子对象，但并不创建孙对象。这种情况在开发中很常见，比如创建一些线程时，为了有效对这些线程进行阻止管理，
     * 通常情况下是创建一个线程组，然后再将部分线程归属到该组中，以此来对零散的线程对象进行有效的管理.
     * <p>
     * 线程关联线程组：多级关联
     * 所谓的多级关联就是父对象中有子对象，子对象中再创建子对象买也就出现了子孙的效果了。但是这种写法在开发中不太常见，因为线程树如果
     * 涉及得复杂反而不利于线程对象的管理，不过JDK确实提供了多级关联的线程树结构。
     * 多级关联的代码就不写了，简单看一下怎么使用关机关联，查看下JDK API的ThreadGroup构造方法：
     * <p>
     * {@link ThreadGroup#ThreadGroup(String)}
     * <p>
     * {@link ThreadGroup#ThreadGroup(ThreadGroup, String)}
     * <p>
     * 注意一下第二个，假如要使用多级关联一般就是用第二个构造函数。第一个参数表示新线程组的父线程组，第二个参数表示新线程组的名称，有了
     * 父线程组和新线程组的名称，自然可以构造出一个新的线程组来了。
     * <p>
     * 当然用第一个构造方法也是可以的，下一部分就会提到。
     * <p>
     * 另外注意一点，线程必须启动后才能归到指定线程组中。
     */
    public static void main(String[] args) {
        MyThread mt0 = new MyThread();
        MyThread mt1 = new MyThread();
        ThreadGroup tg = new ThreadGroup("新建线程组1");
        Thread t0 = new Thread(tg, mt0);
        Thread t1 = new Thread(tg, mt1);
        t0.start();
        t1.start();
        System.out.println("活动的线程数为：" + tg.activeCount());
        System.out.println("线程组的名称为：" + tg.getName());
    }

}

class Simple {
    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("group1");
        ThreadGroup threadGroup2 = new ThreadGroup("group2");
        Thread thread1 = new Thread(threadGroup1, "group1's member");
        Thread thread2 = new Thread(threadGroup2, "group2's member");
        //这样可以复制group里面的thread信息
        Thread[] threads = new Thread[threadGroup1.activeCount()];
        threadGroup1.enumerate(threads);


    }
}
