package com.nplatform.constant;

public enum ErrorCode {
  // 20000
  OK(20000, "OK"),
  NOT_LOGIN(20001, "Not login"),
  BUSINESS_ERROR(40000, "Business Error"),
  INTERNAL_ERROR(50000, "Internal Error"),

  // 30000: rule verification error
  RULE_PARSE_ERROR(30003, "Rule Parsing Error"),
  //
  COMMON_VALIDATION_FAIL(30004, "Validation fail"),
  //
  PARAM_MISS(30005, "Required Parameter is missing"),
  //
  MONTH_QUOTA_FAIL(30006,"month quota fail"),
  //
  DATE_QUOTA_FAIL(30007,"date quota fail"),

  // 40000
  INVALID_PARAMETER(40001, "Invalid Parameter"),
  //
  INVALID_OPERATION(40002, "Invalid Operation"),
  //
  FORBIDDEN(40003, "Auth failed"),
  //
  NOT_FOUND(40004, "Data not found"),
  //
  TIME_OUT(40006, "Request time out"),
  //
  CLIENT_EXP(40007, "client invoke fail"),
  //
  DATA_EXISTED(40008, "Data existed"),

  // 50000
  TERMINATE_ACTIVITI(50002, "Internal Error, activiti workflow should be terminated"),

  CRC_PROXY_ERROR(50003, "CRC Proxy Error")
  ;
  private int code;
  private String desc;

  ErrorCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
