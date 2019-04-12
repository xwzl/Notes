package com.java.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author xuweizhi
 * @date 2018/12/17 15:23
 */
public class OnlyReadBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        readOnlyBuffer.position(0);
    }
}
