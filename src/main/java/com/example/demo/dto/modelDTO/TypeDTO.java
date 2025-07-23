package com.example.demo.dto.modelDTO;

import com.example.demo.model.Translation;
import com.example.demo.model.Type;
import com.example.demo.util.TranslationUtil;

public class TypeDTO {
  private String id;
  private String name;
  private String description;

  public TypeDTO() {

  }
  
  public TypeDTO(Type type, String lang) {
    this.id = type.getId().toHexString();
    Translation translation = TranslationUtil.getTranslationOrDefault(type.getTranslations(), lang, "ua");
    this.name = translation.getName();
    this.description = translation.getDescription();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
