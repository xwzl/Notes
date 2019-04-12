package com.java.thread.security;

/**
 * @author xuweizhi
 * @date 2018/12/25 15:20
 */
public class SynchronizedTest extends Thread {

    private MyObject myObject;

    public SynchronizedTest(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.methodA();
    }

    static class SynchronizedTest2 extends Thread {

        private MyObject myObject;

        public SynchronizedTest2(MyObject myObject) {
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            myObject.methodA();
        }


    }

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        //MyObject myObject1 = new MyObject();
        SynchronizedTest synchronizedTest = new SynchronizedTest(myObject);
        SynchronizedTest2 synchronizedTest2 = new SynchronizedTest2(myObject);
        synchronizedTest.setName("A");
        synchronizedTest2.setName("B");
        synchronizedTest.start();
        synchronizedTest2.start();
    }
}

