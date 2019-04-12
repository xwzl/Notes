package com.java.thread.pool;

import java.util.concurrent.*;

/**
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 * @author xuweizhi
 * @date 2019/01/02 13:44
 */
public class Pool1 {

    /**
     * 线程池的正确使用
     * <p>
     * 以下阿里编码规范里面说的一段话：
     * <p>
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
     * 1）newFixedThreadPool和newSingleThreadExecutor:
     *   主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
     * 2）newCachedThreadPool和newScheduledThreadPool:
     *   主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
     * <p>
     * 手动创建线程池有几个注意点
     * 1.任务独立。如何任务依赖于其他任务，那么可能产生死锁。例如某个任务等待另一个任务的返回值或执行结果，那么除非线程池足够大，否则将发生线程饥饿死锁。
     * 2.合理配置阻塞时间过长的任务。如果任务阻塞时间过长，那么即使不出现死锁，线程池的性能也会变得很糟糕。在Java并发包里可阻塞方法都同时定义了限时方式
     * 和不限时方式。例如
     * Thread.join,BlockingQueue.put,CountDownLatch.await等，如果任务超时，则标识任务失败，然后中止任务或者将任务放回队列以便随后执行，这样，
     * 无论任务的最终结果是否成功，这种办法都能够保证任务总能继续执行下去。
     * 3.设置合理的线程池大小。只需要避免过大或者过小的情况即可，上文的公式线程池大小=NCPU *UCPU(1+W/C)。
     * 4.选择合适的阻塞队列。newFixedThreadPool和newSingleThreadExecutor都使用了无界的阻塞队列，无界阻塞队列会有消耗很大的内存，如果使用了有界阻
     * 塞队列，它会规避内存占用过大的问题，但是当任务填满有界阻塞队列，新的任务该怎么办？在使用有界队列是，需要选择合适的拒绝策略，队列的大小和线程池的大
     * 小必须一起调节。对于非常大的或者无界的线程池，可以使用SynchronousQueue来避免任务排队，以直接将任务从生产者提交到工作者线程。
     */
    public static void main(String[] args) {
        //pool1();
        //pool2();
        //pool3();
        //pool4();
        pool5();
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
     */
    private static void pool5() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 定期执行示例代码如下：
     */
    private static void pool4() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }


    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
     */
    private static void pool3() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.MILLISECONDS);
    }


    /**
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
     * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
     * 创建一个指定工作线程数的线程池，其中参数 corePoolSize 和 maximumPoolSize 相等，阻塞队列基于LinkedBlockingQueue
     * 它是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是在线程池空闲时，即线程池中没有可
     * 运行任务时，它也不会释放工作线程，还会占用一定的系统资源
     */
    private static void pool2() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    /**
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     * 创建一个可缓存工作线程的线程池，默认存活时间60秒，线程池的线程数可达到Integer.MAX_VALUE，即2147483647，内部使用SynchronousQueue作为阻塞队列；
     * 在没有任务执行时，当线程的空闲时间超过keepAliveTime，则工作线程将会终止，当提交新任务时，如果没有空闲线程，则创建新线程执行任务，会导致一定的系统开销
     */
    private static void pool1() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> {
                System.out.println(index);
            });
        }
    }
}

class ThreadFactoryDemo {
    public static class MyTask1 implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thrad ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask1 task = new MyTask1();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("创建线程" + t);
                return t;
            }
        });
        for (int i = 0; i <= 4; i++) {
            es.submit(task);
        }
    }
}
