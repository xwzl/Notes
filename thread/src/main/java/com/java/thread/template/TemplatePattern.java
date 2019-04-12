package com.java.thread.template;


/**
 * Thread类中模板语法的运用
 * @author xuweizhi
 * @date 2018/12/26 23:18
 */
public class TemplatePattern {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("xxxxxxxxxxxxxxxxxx"));
        thread.start();

        Thread thread1 = new Thread(() -> System.out.println("aaaaaas"));
        thread1.start();
    }

}

/**
 * 模板语法实例
 * 必须实现Runnable接口，且必须重写run方法
 */
interface Runnable {


    void run();

}

class RepeatCalledException extends Exception {

    private int state;

    public RepeatCalledException() {
    }

    public RepeatCalledException(String message, int state) {
        super(message);
        this.state = state;
    }

    public int getState() {
        return state;
    }

    private static class CustomExceptionTest {

        private int getDivisionValue(int x, int y) throws RepeatCalledException {
            if (y == 0) {
                throw new RepeatCalledException("计算异常", 1);
            }
            return x / y;
        }

    }

    public static void main(String[] args) {
        CustomExceptionTest test = new CustomExceptionTest();
        try {
            test.getDivisionValue(1, 0);
        } catch (RepeatCalledException e) {
            e.printStackTrace();
            System.out.println(e.state);
        }
    }
}

class Thread implements Runnable {

    private Runnable runnable;

    private int index = 0;

    public Thread(Runnable runnable) {
        this.runnable = runnable;
    }

    public synchronized void start() {
        if (index != 0) {
            throw new RuntimeException("该方法不能被调用两次", new ArrayIndexOutOfBoundsException());
        }
        System.out.println("模板语法前固定代码块");
        runnable.run();
        System.out.println("模板语法后固定代码块");
    }

    @Override
    public void run() {
        throw new RuntimeException("逻辑多变，请使用者自主实现");
    }

}
