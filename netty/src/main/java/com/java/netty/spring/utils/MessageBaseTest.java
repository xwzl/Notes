package com.java.netty.spring.utils;

import com.java.netty.spring.protocol.protobuf.MessageBase;

import java.util.UUID;

/**
 * @author xuweizhi
 */
public class MessageBaseTest {

    public static void main(String[] args) {
        MessageBase.Message message = MessageBase.Message.newBuilder()
                .setRequestId(UUID.randomUUID().toString())
                .setContent("hello world").build();
        System.out.println("message: "+message.toString());
    }
}
