package com.nplatform.bean;

import com.nplatform.constant.ErrorCode;

public class ApiResult {
  private int code;
  private Object data;
  private String message;

  //if there is no default constructor, deserialization is failed.
  public ApiResult() {
  }

  public ApiResult(ErrorCode errorCode, String message, Object data) {
    this.code = errorCode.getCode();
    this.message = message;
    this.data = data;
  }

  public ApiResult(ErrorCode errorCode, String message) {
    this.code = errorCode.getCode();
    this.message = message;
  }

  public ApiResult(ErrorCode errorCode, Object data) {
    this(errorCode, errorCode.getDesc(), data);
  }

  public int getCode() {
    return code;
  }

  public ApiResult setCode(int code) {
    this.code = code;
    return this;
  }

  public Object getData() {
    return data;
  }

  public ApiResult setData(Object data) {
    this.data = data;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ApiResult setMessage(String message) {
    this.message = message;
    return this;
  }

  public static ApiResult success() {
    return success(null);
  }

  public static ApiResult success(Object data) {
    return new ApiResult(ErrorCode.OK, data);
  }

  public static ApiResult success(String message, Object data) {
    return new ApiResult(ErrorCode.OK, message, data);
  }

  public static ApiResult error(ErrorCode errorCode) {
    return new ApiResult(errorCode, errorCode.getDesc(), null);
  }

  public static ApiResult error(ErrorCode errorCode, String message, Object data) {
    return new ApiResult(errorCode, message, data);
  }

  public static ApiResult error(ErrorCode errorCode, String message) {
    return new ApiResult(errorCode, message);
  }

}
