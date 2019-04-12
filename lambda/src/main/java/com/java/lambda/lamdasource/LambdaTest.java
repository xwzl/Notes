package com.java.lambda.lamdasource;


public class LambdaTest {

    Runnable r1 = () -> System.out.println(this);

    //匿名内部类 磁盘会生成对应的class文件
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) {

        LambdaTest lambdaTest = new LambdaTest();
        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();

    }
}
