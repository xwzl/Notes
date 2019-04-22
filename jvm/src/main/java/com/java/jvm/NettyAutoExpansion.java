package com.java.jvm;

/**
 * @author Administrator
 * @description Netty 自动扩容
 */
public class NettyAutoExpansion {

    public static void main(String[] args){
        for (int i = 1; i >0; i<<=1) {
            System.out.println(i);
        }

    }

}
