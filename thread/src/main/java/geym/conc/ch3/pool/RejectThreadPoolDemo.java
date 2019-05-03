package geym.conc.ch3.pool;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {
	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyTask task = new MyTask();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 6,
				0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r,
												  ThreadPoolExecutor executor) {
						System.out.println(/*r.toString()+*/" is discard");
					}
				});
		ExecutorService es = threadPoolExecutor;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			es.submit(task);
			//System.out.println(threadPoolExecutor.getPoolSize()+":"+threadPoolExecutor.getQueue().size()+threadPoolExecutor.getMaximumPoolSize());
			Thread.sleep(10);
		}
	}
}
