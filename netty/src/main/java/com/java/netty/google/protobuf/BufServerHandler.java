package com.java.netty.google.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuweizhi
 * @date 2018/12/10 15:06
 */
public class BufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Animal> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Animal msg) throws Exception {
        MyDataInfo.Animal.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.Animal.DataType.StudentType) {
            MyDataInfo.Student student = msg.getStudent();
            System.out.println(student.getAge());
            System.out.println(student.getName());
            System.out.println(student.getAddress());
        } else if (dataType == MyDataInfo.Animal.DataType.DogType) {
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());
            System.out.println(dog.getNice());
        } else {
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getAge());
            System.out.println(cat.getName());
            System.out.println(cat.getNice());

        }
    }

}
