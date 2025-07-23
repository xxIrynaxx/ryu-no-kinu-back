package com.example.demo.dto.modelDTO;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.model.Translation;
import com.example.demo.model.Variant;

public class ProductDTO {
  private String id;
  private CategoryDTO category;
  private Double price;
  private Double discountPrice;
  private boolean available;
  private String name;
  private String description;
  private List<Variant> variants;
  private List<String> photo;
  private List<Review> reviews;
  private Date createdAt;
  private StyleDTO style;
  private TypeDTO type;

  public ProductDTO() {
  }
  
  public ProductDTO(Product product, CategoryDTO categoryDTO, StyleDTO styleDTO, TypeDTO typeDTO, String lang) {
    this.id = product.getId().toHexString();
    this.category = categoryDTO;
    this.price = product.getPrice();
    this.available = product.isAvailable();
    this.variants = product.getVariants();
    this.photo = product.getPhotos();
    this.reviews = product.getReviews();
    this.createdAt = product.getCreatedAt();

    Translation translation = product.getTranslations().getOrDefault(lang, product.getTranslations().get("ua"));
    if (translation != null) {
      this.name = translation.getName();
      this.description = translation.getDescription();
    }

    this.style = styleDTO;
    this.type = typeDTO;
  }

  public ProductDTO(Product product, String lang) {
    this.id = product.getId().toHexString();
    this.price = product.getPrice();
    this.available = product.isAvailable();
    this.variants = product.getVariants();
    this.photo = product.getPhotos();
    this.reviews = product.getReviews();
    this.createdAt = product.getCreatedAt();

    Translation translation = product.getTranslations().getOrDefault(lang, product.getTranslations().get("ua"));
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

  public CategoryDTO getCategory() {
    return category;
  }

  public void setCategory(CategoryDTO category) {
    this.category = category;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(Double discountPrice) {
    this.discountPrice = discountPrice;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
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

  public List<Variant> getVariants() {
    return variants;
  }

  public void setVariants(List<Variant> variants) {
    this.variants = variants;
  }

  public List<String> getPhoto() {
    return photo;
  }

  public void setPhoto(List<String> photo) {
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
}
