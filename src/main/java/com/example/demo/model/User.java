package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
  @Id
  private ObjectId id;
  private String username;
  private String email;
  private String password;
  private Date createdAt = new Date();
  private String avatar = "https://cdn-icons-png.flaticon.com/128/847/847969.png";
  private Role role = Role.USER;
  private Theme theme = Theme.LIGHT;
  private String lang = "en";
  private List<ObjectId> wishlistProductIds = new ArrayList<>();
  private List<ObjectId> cartProductIds = new ArrayList<>();
  private Double cartPrice = 0.0;
  private List<ObjectId> recentlyViewedProductIds = new ArrayList<>();

  public User() {
  }
  
  public User(ObjectId id, String username, String email, String password, Date createdAt, String avatar, Role role, Theme theme, String lang, List<ObjectId> wishlistProductIds,
      List<ObjectId> cartProductIds, Double cartPrice, List<ObjectId> recentlyViewedProductIds) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
    this.avatar = avatar;
    this.role = role;
    this.theme = theme;
    this.lang = lang;
    this.wishlistProductIds = wishlistProductIds;
    this.cartProductIds = cartProductIds;
    this.cartPrice = cartPrice;
    this.recentlyViewedProductIds = recentlyViewedProductIds;
  }
  
  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Theme getTheme() {
    return theme;
  }

  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public List<ObjectId> getCartProductIds() {
    return cartProductIds;
}

public void setCartProductIds(List<ObjectId> cartProductIds) {
    this.cartProductIds = cartProductIds;
}

public List<ObjectId> getWishlistProductIds() {
    return wishlistProductIds;
}

public void setWishlistProductIds(List<ObjectId> wishlistProductIds) {
    this.wishlistProductIds = wishlistProductIds;
}

  public Double getCartPrice() {
    return cartPrice;
  }

  public void setCartPrice(Double cartPrice) {
    this.cartPrice = cartPrice;
  }

  public List<ObjectId> getRecentlyViewedProductIds() {
    return recentlyViewedProductIds;
  }
  
  public void setRecentlyViewedProductIds(List<ObjectId> recentlyViewedProductIds) {
    this.recentlyViewedProductIds = recentlyViewedProductIds;
  }
}