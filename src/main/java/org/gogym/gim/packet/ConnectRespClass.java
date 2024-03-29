// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConnectResp.proto
package org.gogym.gim.packet;
public final class ConnectRespClass {
  private ConnectRespClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConnectRespOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConnectResp)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *参数类型 参数名称=字段编码值
     * </pre>
     *
     * <code>optional string senderId = 1;</code>
     */
    java.lang.String getSenderId();
    /**
     * <pre>
     *参数类型 参数名称=字段编码值
     * </pre>
     *
     * <code>optional string senderId = 1;</code>
     */
    com.google.protobuf.ByteString
        getSenderIdBytes();

    /**
     * <pre>
     *业务结果
     * </pre>
     *
     * <code>optional int32 result = 2;</code>
     */
    int getResult();

    /**
     * <pre>
     *结果描述信息
     * </pre>
     *
     * <code>optional string msg = 3;</code>
     */
    java.lang.String getMsg();
    /**
     * <pre>
     *结果描述信息
     * </pre>
     *
     * <code>optional string msg = 3;</code>
     */
    com.google.protobuf.ByteString
        getMsgBytes();
  }
  /**
   * <pre>
   *message是固定的。UserInfo是类名，可以随意指定，符合规范即可
   * </pre>
   *
   * Protobuf type {@code ConnectResp}
   */
  public  static final class ConnectResp extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConnectResp)
      ConnectRespOrBuilder {
    // Use ConnectResp.newBuilder() to construct.
    private ConnectResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConnectResp() {
      senderId_ = "";
      result_ = 0;
      msg_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private ConnectResp(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              senderId_ = s;
              break;
            }
            case 16: {

              result_ = input.readInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              msg_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ConnectRespClass.internal_static_ConnectResp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ConnectRespClass.internal_static_ConnectResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ConnectRespClass.ConnectResp.class, ConnectRespClass.ConnectResp.Builder.class);
    }

    public static final int SENDERID_FIELD_NUMBER = 1;
    private volatile java.lang.Object senderId_;
    /**
     * <pre>
     *参数类型 参数名称=字段编码值
     * </pre>
     *
     * <code>optional string senderId = 1;</code>
     */
    public java.lang.String getSenderId() {
      java.lang.Object ref = senderId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        senderId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *参数类型 参数名称=字段编码值
     * </pre>
     *
     * <code>optional string senderId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSenderIdBytes() {
      java.lang.Object ref = senderId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        senderId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int RESULT_FIELD_NUMBER = 2;
    private int result_;
    /**
     * <pre>
     *业务结果
     * </pre>
     *
     * <code>optional int32 result = 2;</code>
     */
    public int getResult() {
      return result_;
    }

    public static final int MSG_FIELD_NUMBER = 3;
    private volatile java.lang.Object msg_;
    /**
     * <pre>
     *结果描述信息
     * </pre>
     *
     * <code>optional string msg = 3;</code>
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *结果描述信息
     * </pre>
     *
     * <code>optional string msg = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getSenderIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, senderId_);
      }
      if (result_ != 0) {
        output.writeInt32(2, result_);
      }
      if (!getMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, msg_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getSenderIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, senderId_);
      }
      if (result_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, result_);
      }
      if (!getMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, msg_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof ConnectRespClass.ConnectResp)) {
        return super.equals(obj);
      }
      ConnectRespClass.ConnectResp other = (ConnectRespClass.ConnectResp) obj;

      boolean result = true;
      result = result && getSenderId()
          .equals(other.getSenderId());
      result = result && (getResult()
          == other.getResult());
      result = result && getMsg()
          .equals(other.getMsg());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + SENDERID_FIELD_NUMBER;
      hash = (53 * hash) + getSenderId().hashCode();
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResult();
      hash = (37 * hash) + MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ConnectRespClass.ConnectResp parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ConnectRespClass.ConnectResp parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ConnectRespClass.ConnectResp parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ConnectRespClass.ConnectResp parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ConnectRespClass.ConnectResp parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ConnectRespClass.ConnectResp parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ConnectRespClass.ConnectResp parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ConnectRespClass.ConnectResp parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ConnectRespClass.ConnectResp parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ConnectRespClass.ConnectResp parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(ConnectRespClass.ConnectResp prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     *message是固定的。UserInfo是类名，可以随意指定，符合规范即可
     * </pre>
     *
     * Protobuf type {@code ConnectResp}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConnectResp)
        ConnectRespClass.ConnectRespOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ConnectRespClass.internal_static_ConnectResp_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ConnectRespClass.internal_static_ConnectResp_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ConnectRespClass.ConnectResp.class, ConnectRespClass.ConnectResp.Builder.class);
      }

      // Construct using ConnectRespClass.ConnectResp.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        senderId_ = "";

        result_ = 0;

        msg_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ConnectRespClass.internal_static_ConnectResp_descriptor;
      }

      public ConnectRespClass.ConnectResp getDefaultInstanceForType() {
        return ConnectRespClass.ConnectResp.getDefaultInstance();
      }

      public ConnectRespClass.ConnectResp build() {
        ConnectRespClass.ConnectResp result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public ConnectRespClass.ConnectResp buildPartial() {
        ConnectRespClass.ConnectResp result = new ConnectRespClass.ConnectResp(this);
        result.senderId_ = senderId_;
        result.result_ = result_;
        result.msg_ = msg_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ConnectRespClass.ConnectResp) {
          return mergeFrom((ConnectRespClass.ConnectResp)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ConnectRespClass.ConnectResp other) {
        if (other == ConnectRespClass.ConnectResp.getDefaultInstance()) return this;
        if (!other.getSenderId().isEmpty()) {
          senderId_ = other.senderId_;
          onChanged();
        }
        if (other.getResult() != 0) {
          setResult(other.getResult());
        }
        if (!other.getMsg().isEmpty()) {
          msg_ = other.msg_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ConnectRespClass.ConnectResp parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ConnectRespClass.ConnectResp) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object senderId_ = "";
      /**
       * <pre>
       *参数类型 参数名称=字段编码值
       * </pre>
       *
       * <code>optional string senderId = 1;</code>
       */
      public java.lang.String getSenderId() {
        java.lang.Object ref = senderId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          senderId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *参数类型 参数名称=字段编码值
       * </pre>
       *
       * <code>optional string senderId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getSenderIdBytes() {
        java.lang.Object ref = senderId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          senderId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *参数类型 参数名称=字段编码值
       * </pre>
       *
       * <code>optional string senderId = 1;</code>
       */
      public Builder setSenderId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        senderId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *参数类型 参数名称=字段编码值
       * </pre>
       *
       * <code>optional string senderId = 1;</code>
       */
      public Builder clearSenderId() {
        
        senderId_ = getDefaultInstance().getSenderId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *参数类型 参数名称=字段编码值
       * </pre>
       *
       * <code>optional string senderId = 1;</code>
       */
      public Builder setSenderIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        senderId_ = value;
        onChanged();
        return this;
      }

      private int result_ ;
      /**
       * <pre>
       *业务结果
       * </pre>
       *
       * <code>optional int32 result = 2;</code>
       */
      public int getResult() {
        return result_;
      }
      /**
       * <pre>
       *业务结果
       * </pre>
       *
       * <code>optional int32 result = 2;</code>
       */
      public Builder setResult(int value) {
        
        result_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *业务结果
       * </pre>
       *
       * <code>optional int32 result = 2;</code>
       */
      public Builder clearResult() {
        
        result_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object msg_ = "";
      /**
       * <pre>
       *结果描述信息
       * </pre>
       *
       * <code>optional string msg = 3;</code>
       */
      public java.lang.String getMsg() {
        java.lang.Object ref = msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *结果描述信息
       * </pre>
       *
       * <code>optional string msg = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *结果描述信息
       * </pre>
       *
       * <code>optional string msg = 3;</code>
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *结果描述信息
       * </pre>
       *
       * <code>optional string msg = 3;</code>
       */
      public Builder clearMsg() {
        
        msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *结果描述信息
       * </pre>
       *
       * <code>optional string msg = 3;</code>
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msg_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:ConnectResp)
    }

    // @@protoc_insertion_point(class_scope:ConnectResp)
    private static final ConnectRespClass.ConnectResp DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ConnectRespClass.ConnectResp();
    }

    public static ConnectRespClass.ConnectResp getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConnectResp>
        PARSER = new com.google.protobuf.AbstractParser<ConnectResp>() {
      public ConnectResp parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ConnectResp(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConnectResp> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConnectResp> getParserForType() {
      return PARSER;
    }

    public ConnectRespClass.ConnectResp getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConnectResp_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConnectResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021ConnectResp.proto\"<\n\013ConnectResp\022\020\n\010se" +
      "nderId\030\001 \001(\t\022\016\n\006result\030\002 \001(\005\022\013\n\003msg\030\003 \001(" +
      "\tB\022B\020ConnectRespClassb\006proto3"
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
    internal_static_ConnectResp_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConnectResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConnectResp_descriptor,
        new java.lang.String[] { "SenderId", "Result", "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
