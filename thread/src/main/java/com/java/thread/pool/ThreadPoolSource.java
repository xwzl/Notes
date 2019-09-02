package com.java.thread.pool;

/**
 * corePoolSize :线程池的核心池大小，在创建线程池之后，线程池默认没有任何线程。
 *
 * 当有任务过来的时候才会去创建创建线程执行任务。换个说法，线程池创建之后，线程池中的线程数为0，当任务过来就会创建一个线程去执行，直到线程数达
 * 到corePoolSize 之后，就会被到达的任务放在队列中。（注意是到达的任务）。换句更精炼的话：corePoolSize 表示允许线程池中允许同时运行的最
 * 大线程数。
 *
 * 如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。
 *
 * maximumPoolSize :线程池允许的最大线程数，他表示最大能创建多少个线程。maximumPoolSize肯定是大于等于corePoolSize。
 * keepAliveTime :表示线程没有任务时最多保持多久然后停止。默认情况下，只有线程池中线程数大于corePoolSize 时，keepAliveTime才会起作用。
 * 换句话说，当线程池中的线程数大于corePoolSize，并且一个线程空闲时间达到了keepAliveTime，那么就是shutdown。
 *
 * Unit:keepAliveTime 的单位。
 *
 * 任务缓存队列
 *
 * 在前面我们多次提到了任务缓存队列，即workQueue，它用来存放等待执行的任务。
 *
 * workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
 *
 * 1）有界任务队列ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
 *
 * 2）无界任务队列LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
 *
 * 3）队列synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
 *
 * 拒绝策略
 * AbortPolicy:丢弃任务并抛出RejectedExecutionException
 *
 * CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
 *
 * DiscardOldestPolicy：丢弃队列中最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
 *
 * DiscardPolicy：丢弃任务，不做任何处理。
 *
 * 线程池的任务处理策略：
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 *
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败
 * （一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 *
 * 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为
 * 核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
 *
 * 线程池的关闭
 *
 * ThreadPoolExecutor提供了两个方法，用于线程池的关闭，分别是shutdown()和shutdownNow()，其中：
 *
 * shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
 *
 * shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
 * @author xuweizhi
 * @date 2019/01/02 13:56
 */
public class ThreadPoolSource {

}
