package com.java.netty.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author xuweizhi
 * @date 2018/12/10 14:39
 */
public class DataInfoTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder().setAge(12).setAddress("北京").setName("Nice").build();

        System.out.println(student);
        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2);
        System.out.println(student2.getAddress());

    }
}
