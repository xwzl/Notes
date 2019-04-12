package com.java.thread.producer;

/**
 * 从打印的新来看，成假死状态的进程中所有的线程的状态都是Waiting状态。为什么会出现这种情况呢？在带吗中已经用了wait/notify
 * <p>
 * 在代码中已经实现了wait/notify进行了通信，但不能保证notify唤醒的是异类，也许是同类，更或者是自身再次抢到CPU的执行权；
 * <p>
 * 假如是生产者唤醒生产者，就会是两条或者多条生产者处理休眠情况，最坏的情况就是生产证全部等待，发出一条信息给消费者
 * 消费者再次唤醒其他线程，如果这次过程中抢到锁的是消费者，那么消费者线程释放等待生产者，生产者拿到锁之后继续唤醒线程，
 * 如果下一次拿到锁的是生产者，那么就会直接等待，线程被锁死了。
 *
 * 解决方案 notify换成notifyall
 *
 * @author xuweizhi
 * @date 2018/12/29 13:42
 */
public class ProducerManyToMany {

    /*
     生产者  生产者线程1 RUNNABLE了
     生产者  生产者线程1 WAITING了*
     生产者  生产者线程2 WAITING了*
     消费者  消费者线程2RUNNABLE了
     消费者  消费者线程2 WAITING了*
     消费者  消费者线程1 WAITING了*
     生产者  生产者线程1 RUNNABLE了
     生产者  生产者线程1 WAITING了*
     生产者  生产者线程2 WAITING了*
     main RUNNABLE
     Monitor Ctrl-Break RUNNABLE
     消费者线程1 WAITING
     生产者线程1 WAITING
     消费者线程2 WAITING
     生产者线程2 WAITING
     */
    public static void main(String[] args) {
        String lock = "";
        ConsumerTwo consumer = new ConsumerTwo(lock);
        ProducerTwo producer = new ProducerTwo(lock);
        Thread[] threadP = new Thread[1];
        Thread[] threadC = new Thread[1];
        for (int i = 0; i < 1; i++) {
            threadP[i] = new Thread(() -> {
                while (true) {
                    consumer.getValue();
                }
            }, "  消费者线程" + (i + 1));
            threadC[i] = new Thread(() -> {
                while (true) {
                    producer.setValue();
                }
            }, "  生产者线程" + (i + 1));
            threadP[i].start();
            threadC[i].start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread[] array = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getName() + " " + array[i].getState());
        }
    }
}

class ProducerTwo {

    private String lock;

    public ProducerTwo(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!DataCenter.value.equals("")) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " WAITING了*");
                    lock.wait();
                }
                System.out.println("生产者" + Thread.currentThread().getName() + " RUNNABLE了");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                DataCenter.value = value;
                //synchronize.notify();
                lock.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ConsumerTwo {

    private String lock;

    public ConsumerTwo(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (DataCenter.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " WAITING了*");
                    lock.wait();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + "RUNNABLE了");
                DataCenter.value = "";
                //synchronize.notify();
                lock.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}