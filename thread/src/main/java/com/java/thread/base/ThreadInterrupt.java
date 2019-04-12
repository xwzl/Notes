package com.java.thread.base;

/**
 * {@link Thread#interrupt()}           //中断线程
 * {@link Thread#isInterrupted()}       //判断是否被中断
 * {@link Thread#interrupted()}         //判断是否被中断,并且清楚当前中断状态
 *
 * @author xuweizhi
 * @date 2018/12/24 16:19
 */
public class ThreadInterrupt extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (interrupted()) {
                    System.out.println("已经是停止状态，我要退出线程了");
                    break;
                    //throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Thread.sleep方法由于中断而抛出异常，此时，它会清除中断标记，如果不加处理，那么在下一次循环时，
            //就无法捕捉到这个中断，故在异常处理中，再次设置中断标记位。
            Thread.currentThread().interrupt();
        }
        System.out.println("如果磁县成");
    }


    /**
     * 使用interrupt方法中断线程，但是interrupt方法不像for-break的使用效果那么明显，马上就停止循环。调用interrupt方法仅仅
     * 是在当前线程中打了一个停止标记，并不是真的停止线程。根据结果来看，好像并没有停止for循环之后的代码，break只是中断了for的
     * 代码，for之后的代码并没有终止，采用抛出异常的方式来中断线程。
     * <p>
     * {@link Thread#interrupt()}          //中断线程
     * {@link Thread#isInterrupted()} ()}  //判断线程是否被中断
     * {@link Thread#interrupted()} ()}    //判断线程是否被中断，并清除当前中断状态
     * <p>
     * 用来中断当前线程代码逻辑的循环
     */
    public static void main(String[] args) {
        try {
            ThreadInterrupt thread = new ThreadInterrupt();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (Exception e) {
            System.out.println("main");
            e.printStackTrace();
        }
        System.out.println("end");
    }

}
