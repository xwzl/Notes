package com.java.thread.synchronize;

/**
 * @author xuweizhi
 * @date 2018/12/24 17:43
 */
public class MyLockThread extends Thread {

    private LockStop lockStop;

    public MyLockThread(LockStop lockStop) {
        super();
        this.lockStop = lockStop;
    }

    @Override
    public void run() {
        super.run();
        lockStop.printString("b", "bb");
    }

    public static void main(String[] args) {
        try {
            LockStop lockStop = new LockStop();
            MyLockThread lockThread = new MyLockThread(lockStop);
            lockThread.start();
            //Thread.sleep(2);
            lockThread.stop();
            System.out.println(lockStop.getUsername()+":"+lockStop.getPassword());
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
