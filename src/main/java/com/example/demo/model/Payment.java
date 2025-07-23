package com.example.demo.model;

public class Payment {
  private String method;
  private String status;
  private String transactionId;

  public Payment() {
  }
  
  public Payment(String method, String status, String transactionId) {
    this.method = method;
    this.status = status;
    this.transactionId = transactionId;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
}
