package com.java.lambda.chain;

/**
 * @author xuweizhi
 * @date 2018/12/17 13:28
 */
public interface Chain {

    public void say();

    default Chain says() {
        return () -> System.out.println("这是一个默认方法实现");
    }

     abstract  static class  mmmmmm implements  Chain{

        public final Chain chain;

        protected mmmmmm(Chain chain) {
            this.chain = chain;
        }

        @Override
        public void say() {

        }
    }

}
