package com.example.demo.dto.requestDTO;

import java.util.Date;
import java.util.List;

import com.example.demo.dto.modelDTO.DeliveryDTO;
import com.example.demo.dto.modelDTO.PaymentDTO;
import com.example.demo.dto.modelDTO.ProductDTO;

public class OrderResponseDTO {
  private String id;
  private List<ProductDTO> items;
  private Double totalPrice;
  private String status;
  private Date createdAt;
  private PaymentDTO payment;
  private DeliveryDTO delivery;
  private Date deliveryDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<ProductDTO> getItems() {
    return items;
  }

  public void setItems(List<ProductDTO> items) {
    this.items = items;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public PaymentDTO getPayment() {
    return payment;
  }

  public void setPayment(PaymentDTO payment) {
    this.payment = payment;
  }

  public DeliveryDTO getDelivery() {
    return delivery;
  }

  public void setDelivery(DeliveryDTO delivery) {
    this.delivery = delivery;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }
}
