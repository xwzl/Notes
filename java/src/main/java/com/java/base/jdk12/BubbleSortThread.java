package com.java.base.jdk12;

/**
 * 每次提交任务时，如果线程数还没达到coreSize就创建新线程并绑定该任务。 所以第coreSize次提交任务后线程总数
 * 必达到coreSize，不会重用之前的空闲线程。
 * <p>
 * 线程数达到coreSize后，新增的任务就放到工作队列里，而线程池里的线程则努力的使用take()从工作队列里拉活来干。
 * 如果队列是个有界队列，又如果线程池里的线程不能及时将任务取走，工作队列可能会满掉，插入任务就会失败，此时线程
 * 池就会紧急的再创建新的临时线程来补救。
 * <p>
 * 临时线程使用poll(keepAliveTime，timeUnit)来从工作队列拉活，如果时候到了仍然两手空空没拉到活，表明它太
 * 闲了，就会被解雇掉。
 * <p>
 * 如果core线程数＋临时线程数 >maxSize，则不能再创建新的临时线程了，转头执行RejectExecutionHanlder。默
 * 认的AbortPolicy抛RejectedExecutionException异常，其他选择包括静默放弃当前任务(Discard)，放弃工作
 * 队列里最老的任务(DisacardOldest)，或由主线程来直接执行(CallerRuns)，或你自己发挥想象力写的一个。
 * <p>
 * JDK1.5
 *
 * @author Administrator
 */
public class BubbleSortThread extends Thread {
    private int[] numbers;

    public BubbleSortThread(int[] numbers) {
        setName("Simple Thread");
        setUncaughtExceptionHandler(
                new SimpleThreadExceptionHandler());
        this.numbers = numbers;
    }

    public static void main(String[] args) {
        int[] i = {12, 21, 212};
        new BubbleSortThread(i).start();
    }

    @Override
    public void run() {
        int index = numbers.length;
        boolean finished = false;
        while (!finished) {
            index--;
            finished = true;
            for (int i = 0; i < index; i++) {
                // Create error condition
                if (numbers[i + 1] < 0) {
                    throw new IllegalArgumentException(
                            "Cannot pass negative numbers into this thread!");
                }
                if (numbers[i] > numbers[i + 1]) {
                    // swap
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    finished = false;
                }
            }
        }
    }
}

class SimpleThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.printf("%s: %s at line %d of %s%n",
                t.getName(),
                e.toString(),
                e.getStackTrace()[0].getLineNumber(),
                e.getStackTrace()[0].getFileName());
    }
}