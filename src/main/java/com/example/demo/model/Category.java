package com.example.demo.model;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {
  @Id
  private ObjectId id;
  private String slug;
  private ObjectId styleId;
  private ObjectId typeId;
  private String country;
  private Map<String, Translation> translations;
  
  public Category() {
  }
  
  public Category(ObjectId id, String slug, ObjectId styleId, ObjectId typeId, String country,
      Map<String, Translation> translations) {
    this.id = id;
    this.slug = slug;
    this.styleId = styleId;
    this.typeId = typeId;
    this.country = country;
    this.translations = translations;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public ObjectId getStyleId() {
    return styleId;
  }

  public void setStyleId(ObjectId styleId) {
    this.styleId = styleId;
  }

  public ObjectId getTypeId() {
    return typeId;
  }

  public void setTypeId(ObjectId typeId) {
    this.typeId = typeId;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Map<String, Translation> getTranslations() {
    return translations;
  }

  public void setTranslations(Map<String, Translation> translations) {
    this.translations = translations;
  }
}
