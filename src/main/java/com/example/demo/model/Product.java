package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product{
  @Id
  private ObjectId id;
  private String code;
  private ObjectId categoryId;
  private Double price;
  
  private boolean available;
  private Map<String, Translation> translation;
  private List<Variant> variants;
  private List<String> photo;
  private List<Review> reviews;
  private Date createdAt = new Date();

  public Product() {

  }

  public Product(ObjectId id, String code, ObjectId categoryId, Double price, boolean available, Map<String, Translation> translation, List<Variant> variants, List<String> photo, List<Review> reviews) {
      this.id = id;
      this.code = code;
      this.categoryId = categoryId;
      this.price = price;
      this.available = available;
      this.translation = translation;
      this.variants = variants;
      this.photo = photo;
      this.reviews = reviews;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ObjectId getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(ObjectId categoryId) {
    this.categoryId = categoryId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public Map<String, Translation> getTranslations() {
    return translation;
  }

  public void setTranslations(Map<String, Translation> translation) {
    this.translation = translation;
  }

  public List<Variant> getVariants() {
    return variants;
  }

  public void setVariants(List<Variant> variants) {
    this.variants = variants;
  }
  
  public List<String> getPhotos() {
    return photo;
   }
  
  public void setPhotos(List<String> photo) {
    this.photo = photo;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
