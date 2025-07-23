package com.example.demo.dto.requestDTO;

import java.util.List;

import com.example.demo.dto.modelDTO.DeliveryDTO;

public class OrderRequestDTO {
  private List<String> productIds;
  private String paymentMethod;
  private DeliveryDTO delivery;

  public List<String> getProductIds() {
    return productIds;
  }

  public void setProductIds(List<String> productIds) {
    this.productIds = productIds;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public DeliveryDTO getDelivery() {
    return delivery;
  }

  public void setDelivery(DeliveryDTO delivery) {
    this.delivery = delivery;
  }
}
