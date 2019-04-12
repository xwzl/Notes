package com.java.netty.google.grpc;


import com.java.netty.google.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author xuweizhi
 * @date 2018/12/13 15:39
 */
public class GrpcClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();

        // 同步，阻塞式调用;卡着了就会一直卡着
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        // 异步的请求方式
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);

        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("王五").build());
        System.out.println(response.getRealname());

        System.out.println("对象与对象传递方式----------------------------------------");

        //返回一个流，其实就是一个迭代器
        Iterator<StudentResponse> age = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

        //age.forEachRemaining(System.out::println);

        age.forEachRemaining(response1 -> {
            System.out.println("Age:" + response1.getAge() + " Name:" + response1.getName() + " City:" + response1.getCity());
        });


        System.out.println("请求数据是流，返回对象是一确定的对象--------------------------");
        StreamObserver<StudentResponseList> observer = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println(studentResponse.getName() + "\n" + studentResponse.getCity() + "\n" + studentResponse.getAge());
                    System.out.println("*******");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
            }
        };

        // 客户端以流的方式请求的数据，一定是异步的
        StreamObserver<StudentRequest> streamObserver = stub.getStudentsWrapperByAges(observer);

        streamObserver.onNext(StudentRequest.newBuilder().setAge(12).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(14).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(13).build());
        streamObserver.onNext(StudentRequest.newBuilder().setAge(16).build());
        streamObserver.onCompleted();


        System.out.println("请求方式 流对流的形式-------------------------");

        StreamObserver<StreamRequest> observer1 = stub.chat(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("完成发送");
            }
        });

        for (int i = 0; i <10 ; i++) {
            observer1.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 异步，线程需要阻塞
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
