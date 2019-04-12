package com.java.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author xuweizhi
 * @date 2018/12/17 15:05
 */
public class GetAcquire {

    public static void main(String[] args) {
        numberType();
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        // 与buffer同用一份数据
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            slice.put((byte) (slice.get(i) * 2));
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }

    }

    private static void numberType() {
        ByteBuffer buffer = ByteBuffer.allocate(100);

        buffer.putInt(12);
        buffer.putChar('你');
        buffer.putDouble(10.5D);
        buffer.putFloat(12.1f);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getFloat());
    }
}
