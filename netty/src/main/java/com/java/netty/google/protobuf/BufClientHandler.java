package com.java.netty.google.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author xuweizhi
 * @date 2018/12/10 15:11
 */
public class BufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Animal> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Animal msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);

        MyDataInfo.Animal animal = null;
        if (randomInt < 1) {
            animal = MyDataInfo.Animal.newBuilder().setDataType(MyDataInfo.Animal.DataType.StudentType).
                    setStudent(MyDataInfo.Student.newBuilder().
                            setAge(12).
                            setName("李四1").
                            setAddress("是一个人").build()).
                    build();
        } else if (randomInt < 2) {
            animal = MyDataInfo.Animal.newBuilder().setDataType(MyDataInfo.Animal.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().
                            setAge(12).
                            setName("李四2").
                            setNice("是一只狗").build()).
                    build();
        } else {
            animal = MyDataInfo.Animal.newBuilder().setDataType(MyDataInfo.Animal.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().
                            setAge(12).
                            setName("李四3").
                            setNice("是一只猫").build()).
                    build();
        }
        //MyDataInfo.Student student = MyDataInfo.Student.newBuilder().setAge(12).setName("李四").setAddress("四川省成都市").build();
        //System.out.println(student);
        ctx.channel().writeAndFlush(animal);
    }
}
