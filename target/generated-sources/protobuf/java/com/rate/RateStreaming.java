// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rate_streaming.proto

package com.rate;

public final class RateStreaming {
  private RateStreaming() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_rate_RateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_rate_RateRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_rate_Bank_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_rate_Bank_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_rate_RateResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_rate_RateResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_rate_Rate_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_rate_Rate_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024rate_streaming.proto\022\010com.rate\"+\n\013Rate" +
      "Request\022\034\n\004bank\030\001 \001(\0132\016.com.rate.Bank\"\024\n" +
      "\004Bank\022\014\n\004name\030\001 \001(\t\"2\n\014RateResponse\022\"\n\nc" +
      "urrencies\030\001 \003(\0132\016.com.rate.Rate\";\n\004Rate\022" +
      "$\n\010currency\030\001 \001(\0162\022.com.rate.Currency\022\r\n" +
      "\005value\030\002 \001(\002*%\n\010Currency\022\007\n\003EUR\020\000\022\007\n\003USD" +
      "\020\001\022\007\n\003RUB\020\0022T\n\024RateStreamingService\022<\n\007o" +
      "bserve\022\025.com.rate.RateRequest\032\026.com.rate" +
      ".RateResponse(\0010\001B\014\n\010com.rateP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_rate_RateRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_rate_RateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_rate_RateRequest_descriptor,
        new java.lang.String[] { "Bank", });
    internal_static_com_rate_Bank_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_rate_Bank_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_rate_Bank_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_com_rate_RateResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_rate_RateResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_rate_RateResponse_descriptor,
        new java.lang.String[] { "Currencies", });
    internal_static_com_rate_Rate_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_rate_Rate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_rate_Rate_descriptor,
        new java.lang.String[] { "Currency", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
