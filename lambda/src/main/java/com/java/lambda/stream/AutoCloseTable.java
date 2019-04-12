package com.java.lambda.stream;

/**
 * @version 1.0.0
 * @date 2018-12-02 21:30 星期日 Java8
 **/
public class AutoCloseTable implements AutoCloseable {


    @Override
    public void close() throws Exception {
        System.out.println("关闭这个流");
    }

    public void doSomeThing(){
        System.out.println("做事情");
    }

    public static void main(String[] args) throws Exception {

        try(AutoCloseTable autoCloseTable = new AutoCloseTable()){
            autoCloseTable.doSomeThing();
        }

    }

}
