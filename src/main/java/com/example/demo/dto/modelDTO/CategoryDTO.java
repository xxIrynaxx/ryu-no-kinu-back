package com.example.demo.dto.modelDTO;

import com.example.demo.model.Category;
import com.example.demo.model.Translation;

public class CategoryDTO {
  private String id;
  private String name;
  private String description;
  private String country;
  private StyleDTO style;
  private TypeDTO type;

  public CategoryDTO() {
  }
  
  public CategoryDTO(Category category, StyleDTO style,
      TypeDTO type, String lang) {
    this.id = category.getId().toHexString();
    this.country = category.getCountry();
    this.style = style;
    this.type = type;

    Translation translation = category.getTranslations().getOrDefault(lang, category.getTranslations().get("ua"));
    if (translation != null) {
      this.name = translation.getName();
      this.description = translation.getDescription();
    }
  }

  public CategoryDTO(Category category, String lang) {
    this.id = category.getId().toHexString();
    Translation translation = category.getTranslations().getOrDefault(lang, category.getTranslations().get("ua"));
    if (translation != null) {
      this.name = translation.getName();
      this.description = translation.getDescription();
    }
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

  public StyleDTO getStyle() {
    return style;
  }

  public void setStyle(StyleDTO style) {
    this.style = style;
  }

  public TypeDTO getType() {
    return type;
  }

  public void setType(TypeDTO type) {
    this.type = type;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
