package com.java.thread.producer;

/**
 * 一生产一消费
 * 一个生产者一个消费者进行数据的交互，在控制台中打印的日志get和Set交替交替运行。
 * <p>
 * 但是如果在此实验的基础上，设计出多个生产者和多个消费者，那么在运行的过程中极有可能出现假死的现象。
 * 也就是所有的线程成waiting状态
 *
 * @author xuweizhi
 * @date 2018/12/29 13:08
 */
public class ProducerOneToOne {

    public static void main(String[] args) {
        String lock = "线程锁";
        ConsumerOne consumerOne = new ConsumerOne(lock);
        ProducerOne producerOne = new ProducerOne(lock);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                producerOne.setValue();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                consumerOne.getValue();
            }
        }).start();
    }

}

class ProducerOne {

    private String lock;

    public ProducerOne(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!DataCenter.value.equals("")) {
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set的值是：" + value);
                System.out.println("");
                DataCenter.value = value;
                lock.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ConsumerOne {

    private String lock;

    public ConsumerOne(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (DataCenter.value.equals("")) {
                    //System.out.println("Consumer先启动");
                    lock.wait();
                }
                System.out.println("get的值是：" + DataCenter.value);
                DataCenter.value = "";
                lock.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}