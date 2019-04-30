package geym.conc.ch5.disruptor;

import com.java.thread.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
	@Override
	public void onEvent(PCData event) throws Exception {
		System.out.println(Thread.currentThread().getId() + ":Event: --"
				+ event.get() * event.get() + "--");
	}
}
