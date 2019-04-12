package com.java.netty.google;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * gradle generateProto 生成文件，在build目录下generated中
 * GRPC四种调用方式
 * 1. 客户端发起一个请求，服务端响应
 * 2. 客户端发起单个数据请求，服务端相应一个流响应
 * 3. 与2相反
 * 4. 客户端发起流，服务端响应流
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.java.netty.proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.java.netty.google.MyRequest,
      com.java.netty.google.MyResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.java.netty.google.MyRequest.class,
      responseType = com.java.netty.google.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.java.netty.google.MyRequest,
      com.java.netty.google.MyResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.java.netty.google.MyRequest, com.java.netty.google.MyResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod = 
              io.grpc.MethodDescriptor.<com.java.netty.google.MyRequest, com.java.netty.google.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.java.netty.proto.StudentService", "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.MyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUsername"))
                  .build();
          }
        }
     }
     return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest,
      com.java.netty.google.StudentResponse> getGetStudentsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAge",
      requestType = com.java.netty.google.StudentRequest.class,
      responseType = com.java.netty.google.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest,
      com.java.netty.google.StudentResponse> getGetStudentsByAgeMethod() {
    io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest, com.java.netty.google.StudentResponse> getGetStudentsByAgeMethod;
    if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgeMethod = getGetStudentsByAgeMethod = 
              io.grpc.MethodDescriptor.<com.java.netty.google.StudentRequest, com.java.netty.google.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.java.netty.proto.StudentService", "GetStudentsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StudentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAge"))
                  .build();
          }
        }
     }
     return getGetStudentsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest,
      com.java.netty.google.StudentResponseList> getGetStudentsWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsWrapperByAges",
      requestType = com.java.netty.google.StudentRequest.class,
      responseType = com.java.netty.google.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest,
      com.java.netty.google.StudentResponseList> getGetStudentsWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.java.netty.google.StudentRequest, com.java.netty.google.StudentResponseList> getGetStudentsWrapperByAgesMethod;
    if ((getGetStudentsWrapperByAgesMethod = StudentServiceGrpc.getGetStudentsWrapperByAgesMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsWrapperByAgesMethod = StudentServiceGrpc.getGetStudentsWrapperByAgesMethod) == null) {
          StudentServiceGrpc.getGetStudentsWrapperByAgesMethod = getGetStudentsWrapperByAgesMethod = 
              io.grpc.MethodDescriptor.<com.java.netty.google.StudentRequest, com.java.netty.google.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.java.netty.proto.StudentService", "GetStudentsWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StudentResponseList.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsWrapperByAges"))
                  .build();
          }
        }
     }
     return getGetStudentsWrapperByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.java.netty.google.StreamRequest,
      com.java.netty.google.StreamResponse> getChatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Chat",
      requestType = com.java.netty.google.StreamRequest.class,
      responseType = com.java.netty.google.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.java.netty.google.StreamRequest,
      com.java.netty.google.StreamResponse> getChatMethod() {
    io.grpc.MethodDescriptor<com.java.netty.google.StreamRequest, com.java.netty.google.StreamResponse> getChatMethod;
    if ((getChatMethod = StudentServiceGrpc.getChatMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getChatMethod = StudentServiceGrpc.getChatMethod) == null) {
          StudentServiceGrpc.getChatMethod = getChatMethod = 
              io.grpc.MethodDescriptor.<com.java.netty.google.StreamRequest, com.java.netty.google.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.java.netty.proto.StudentService", "Chat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.java.netty.google.StreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("Chat"))
                  .build();
          }
        }
     }
     return getChatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   * gradle generateProto 生成文件，在build目录下generated中
   * GRPC四种调用方式
   * 1. 客户端发起一个请求，服务端响应
   * 2. 客户端发起单个数据请求，服务端相应一个流响应
   * 3. 与2相反
   * 4. 客户端发起流，服务端响应流
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 对象 =&gt; 对象
     * </pre>
     */
    public void getRealNameByUsername(com.java.netty.google.MyRequest request,
        io.grpc.stub.StreamObserver<com.java.netty.google.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     * <pre>
     *stream定义流 参数和响应必须是message类型的 对象 =&gt; 流
     * </pre>
     */
    public void getStudentsByAge(com.java.netty.google.StudentRequest request,
        io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentsByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流 =&gt; 对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.java.netty.google.StudentRequest> getStudentsWrapperByAges(
        io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentsWrapperByAgesMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流 =&gt; 流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.java.netty.google.StreamRequest> chat(
        io.grpc.stub.StreamObserver<com.java.netty.google.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getChatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.java.netty.google.MyRequest,
                com.java.netty.google.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetStudentsByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.java.netty.google.StudentRequest,
                com.java.netty.google.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            getGetStudentsWrapperByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.java.netty.google.StudentRequest,
                com.java.netty.google.StudentResponseList>(
                  this, METHODID_GET_STUDENTS_WRAPPER_BY_AGES)))
          .addMethod(
            getChatMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.java.netty.google.StreamRequest,
                com.java.netty.google.StreamResponse>(
                  this, METHODID_CHAT)))
          .build();
    }
  }

  /**
   * <pre>
   * gradle generateProto 生成文件，在build目录下generated中
   * GRPC四种调用方式
   * 1. 客户端发起一个请求，服务端响应
   * 2. 客户端发起单个数据请求，服务端相应一个流响应
   * 3. 与2相反
   * 4. 客户端发起流，服务端响应流
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 对象 =&gt; 对象
     * </pre>
     */
    public void getRealNameByUsername(com.java.netty.google.MyRequest request,
        io.grpc.stub.StreamObserver<com.java.netty.google.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *stream定义流 参数和响应必须是message类型的 对象 =&gt; 流
     * </pre>
     */
    public void getStudentsByAge(com.java.netty.google.StudentRequest request,
        io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 流 =&gt; 对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.java.netty.google.StudentRequest> getStudentsWrapperByAges(
        io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudentsWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 流 =&gt; 流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.java.netty.google.StreamRequest> chat(
        io.grpc.stub.StreamObserver<com.java.netty.google.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getChatMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * gradle generateProto 生成文件，在build目录下generated中
   * GRPC四种调用方式
   * 1. 客户端发起一个请求，服务端响应
   * 2. 客户端发起单个数据请求，服务端相应一个流响应
   * 3. 与2相反
   * 4. 客户端发起流，服务端响应流
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 对象 =&gt; 对象
     * </pre>
     */
    public com.java.netty.google.MyResponse getRealNameByUsername(com.java.netty.google.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *stream定义流 参数和响应必须是message类型的 对象 =&gt; 流
     * </pre>
     */
    public java.util.Iterator<com.java.netty.google.StudentResponse> getStudentsByAge(
        com.java.netty.google.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * gradle generateProto 生成文件，在build目录下generated中
   * GRPC四种调用方式
   * 1. 客户端发起一个请求，服务端响应
   * 2. 客户端发起单个数据请求，服务端相应一个流响应
   * 3. 与2相反
   * 4. 客户端发起流，服务端响应流
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 对象 =&gt; 对象
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.java.netty.google.MyResponse> getRealNameByUsername(
        com.java.netty.google.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENTS_WRAPPER_BY_AGES = 2;
  private static final int METHODID_CHAT = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.java.netty.google.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.java.netty.google.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((com.java.netty.google.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENTS_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentsWrapperByAges(
              (io.grpc.stub.StreamObserver<com.java.netty.google.StudentResponseList>) responseObserver);
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<com.java.netty.google.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.java.netty.google.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetStudentsByAgeMethod())
              .addMethod(getGetStudentsWrapperByAgesMethod())
              .addMethod(getChatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
