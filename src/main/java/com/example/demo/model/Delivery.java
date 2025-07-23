package com.example.demo.model;

public class Delivery {
  private String firstName;
  private String lastName;
  private String address;
  private String method;
  private String status;
  private String trackingNumber;

  public Delivery() {
  }
  
  public Delivery(String firstName, String lastName, String address, String method, String status,
      String trackingNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.method = method;
    this.status = status;
    this.trackingNumber = trackingNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }
}
