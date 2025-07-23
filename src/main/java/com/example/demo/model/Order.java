package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "orders")
public class Order {
  @Id
  private ObjectId id;
  private User user;
  private List<Product> items;
  private Double totalPrice;
  private OrderStatus status = OrderStatus.PENDING;
  private Date createdAt;
  private Payment payment;
  private Delivery delivery;
  private Date deliveryDate;

  public Order() {

  }

  public Order(ObjectId id, User user, List<Product> items, Double totalPrice, OrderStatus status, Date createdAt,
      Payment payment, Delivery delivery, Date deliveryDate) {
    this.id = id;
    this.user = user;
    this.items = items;
    this.totalPrice = totalPrice;
    this.status = status;
    this.createdAt = createdAt;
    this.payment = payment;
    this.delivery = delivery;
    this.deliveryDate = deliveryDate;
  }
  
  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Product> getItems() {
    return items;
  }

  public void setItems(List<Product> items) {
    this.items = items;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }
}