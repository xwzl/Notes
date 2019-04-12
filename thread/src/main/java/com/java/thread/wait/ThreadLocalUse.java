package com.java.thread.wait;

/**
 * 为单独线程绑定共享数值
 *
 * @author xuweizhi
 * @date 2019/01/02 10:47
 */
public class ThreadLocalUse {

    private static ThreadLocal t1 = new ThreadLocal();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("从未放过值");
            t1.set("我的值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

}

/**
 * 验证线程隔离性
 */
class ThreadIsolation {

    public static ThreadLocal t1 = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (ThreadIsolation.t1.get() == null) {
                    ThreadIsolation.t1.set("ThreadA" + (i + 1));
                }
                System.out.println("ThreadA Get Value" + ThreadIsolation.t1.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (ThreadIsolation.t1.get() == null) {
                    ThreadIsolation.t1.set("ThreadB" + (i + 1));
                }
                System.out.println("ThreadB Get Value" + ThreadIsolation.t1.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            if (ThreadIsolation.t1.get() == null) {
                ThreadIsolation.t1.set("Main" + (i + 1));
            }
            System.out.println("Main Get Value" + ThreadIsolation.t1.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

/**
 * ThreadLocal 对象默认不初始化
 */
class ThreadLocalInit extends ThreadLocal {

    private Object value;

    public ThreadLocalInit(Object value) {
        this.value = value;
    }

    @Override
    protected Object initialValue() {
        return this.value;
    }

    public static void main(String[] args) {
        ThreadLocalInit a = new ThreadLocalInit("A");
        ThreadLocalInit b = new ThreadLocalInit("B");
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(a.get());
            }
        }).start();
        System.out.println(b.get());
    }
}