package com.java.jvm.GC;

/**
 * @author xuweizhi
 * @date 2019/03/10 15:09
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> {
                dontStop();
            });
            thread.start();
        }
    }

    /**
     * -Xss2M
     */
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
