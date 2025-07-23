package com.example.demo.model;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "style")
public class Style {
  @Id
  private ObjectId id;
  private String code;
  private Map<String, Translation> translations;
  
  public Style() {

  }

  public Style(ObjectId id, String code, Map<String, Translation> translations) {
    this.id = id;
    this.code = code;
    this.translations = translations;
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

  public Map<String, Translation> getTranslations() {
    return translations;
  }

  public void setTranslations(Map<String, Translation> translations) {
    this.translations = translations;
}
}