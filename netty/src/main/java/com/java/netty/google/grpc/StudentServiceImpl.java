package com.java.netty.google.grpc;

import com.java.netty.google.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * @author xuweizhi
 * @date 2018/12/13 15:26
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     * 重写返回方法
     *
     * @param request          客户端传递过的阐述
     * @param responseObserver 返回给客户端的信息
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受到客户端的信息:" + request.getUsername());
        //返回操作
        responseObserver.onNext(MyResponse.newBuilder().setRealname("非常地带").build());
        responseObserver.onCompleted();
    }

    /**
     * 请求是一个对象 服务端是一个流对象
     *
     * @param request          请求参数
     * @param responseObserver 返回就是一个数组
     */
    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("接受到客户端的信息:" + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setAge(12).setName("张三").setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(12).setName("李四").setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(12).setName("王五").setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(12).setName("赵六").setCity("成都").build());

        responseObserver.onCompleted();

    }

    /**
     * 请求是一个流，返回一个确定的对象
     *
     * @param responseObserver 请求参数
     * @return 返回值
     */
    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse response = StudentResponse.newBuilder().setName("1").setAge(20).setCity("四川").build();
                StudentResponse response1 = StudentResponse.newBuilder().setName("2").setAge(23).setCity("四川").build();
                StudentResponseList list = StudentResponseList.newBuilder().
                        addStudentResponse(response).addStudentResponse(response1).build();
                responseObserver.onNext(list);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> chat(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {

            @Override
            public void onNext(StreamRequest value) {
                System.out.println("客户端请求数据:"+value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo("服务端返回的数据:"+ UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}
