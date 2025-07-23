package com.example.demo.model;

public class Variant {
  private String size;
  private String color;
  private int stock;

  public Variant() {
  }
  
  public Variant(String size, String color, int stock) {
    this.size = size;
    this.color = color;
    this.stock = stock;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
