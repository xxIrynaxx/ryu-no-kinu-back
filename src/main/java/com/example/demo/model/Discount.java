package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discount")
public class Discount {
  @Id
  private ObjectId id;
  private String title;
  private double percentage;
  private Date startDate;
  private Date endDate;
  private List<ObjectId> items;

  public Discount() {
  }
  
  public Discount(ObjectId id, String title, double percentage, Date startDate, Date endDate, List<ObjectId> items) {
    this.id = id;
    this.title = title;
    this.percentage = percentage;
    this.startDate = startDate;
    this.endDate = endDate;
    this.items = items;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getPercentage() {
    return percentage;
  }

  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public List<ObjectId> getItems() {
    return items;
  }

  public void setItems(List<ObjectId> items) {
    this.items = items;
  }
}
