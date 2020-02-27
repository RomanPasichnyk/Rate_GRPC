package com.rate;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: rate_streaming.proto")
public final class RateStreamingServiceGrpc {

  private RateStreamingServiceGrpc() {}

  public static final String SERVICE_NAME = "com.rate.RateStreamingService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.rate.RateRequest,
      com.rate.RateResponse> METHOD_OBSERVE =
      io.grpc.MethodDescriptor.<com.rate.RateRequest, com.rate.RateResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "com.rate.RateStreamingService", "observe"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.rate.RateRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.rate.RateResponse.getDefaultInstance()))
          .setSchemaDescriptor(new RateStreamingServiceMethodDescriptorSupplier("observe"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RateStreamingServiceStub newStub(io.grpc.Channel channel) {
    return new RateStreamingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RateStreamingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RateStreamingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RateStreamingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RateStreamingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RateStreamingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.rate.RateRequest> observe(
        io.grpc.stub.StreamObserver<com.rate.RateResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_OBSERVE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_OBSERVE,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.rate.RateRequest,
                com.rate.RateResponse>(
                  this, METHODID_OBSERVE)))
          .build();
    }
  }

  /**
   */
  public static final class RateStreamingServiceStub extends io.grpc.stub.AbstractStub<RateStreamingServiceStub> {
    private RateStreamingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RateStreamingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateStreamingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RateStreamingServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.rate.RateRequest> observe(
        io.grpc.stub.StreamObserver<com.rate.RateResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_OBSERVE, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RateStreamingServiceBlockingStub extends io.grpc.stub.AbstractStub<RateStreamingServiceBlockingStub> {
    private RateStreamingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RateStreamingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateStreamingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RateStreamingServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class RateStreamingServiceFutureStub extends io.grpc.stub.AbstractStub<RateStreamingServiceFutureStub> {
    private RateStreamingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RateStreamingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateStreamingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RateStreamingServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_OBSERVE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RateStreamingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RateStreamingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OBSERVE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.observe(
              (io.grpc.stub.StreamObserver<com.rate.RateResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RateStreamingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RateStreamingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.rate.RateStreaming.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RateStreamingService");
    }
  }

  private static final class RateStreamingServiceFileDescriptorSupplier
      extends RateStreamingServiceBaseDescriptorSupplier {
    RateStreamingServiceFileDescriptorSupplier() {}
  }

  private static final class RateStreamingServiceMethodDescriptorSupplier
      extends RateStreamingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RateStreamingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RateStreamingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RateStreamingServiceFileDescriptorSupplier())
              .addMethod(METHOD_OBSERVE)
              .build();
        }
      }
    }
    return result;
  }
}
