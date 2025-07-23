package com.example.demo.dto.modelDTO;

import com.example.demo.model.Role;
import com.example.demo.model.Theme;

import java.util.Date;
import java.util.List;

public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String avatar;
    private Role role;
    private Theme theme;
    private String lang;
    private Date createdAt;
    private List<String> wishlistProductIds;
    private List<String> cartProductIds;
    private Double cartPrice;
    private List<String> recentlyViewedProductIds;

    public UserResponseDTO() {}

    public UserResponseDTO(String id, String username, String email, String avatar, Role role, Theme theme, String lang,
                           Date createdAt, List<String> wishlistProductIds, List<String> cartProductIds, Double cartPrice, List<String> recentlyViewedProductIds) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
        this.theme = theme;
        this.lang = lang;
        this.createdAt = createdAt;
        this.wishlistProductIds = wishlistProductIds;
        this.cartProductIds = cartProductIds;
        this.cartPrice = cartPrice;
        this.recentlyViewedProductIds = recentlyViewedProductIds;
    }

    public String getId() {
      return id;
    }
  
    public void setId(String id) {
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
  
    public List<String> getCartProductIds() {
      return cartProductIds;
  }
  
  public void setCartProductIds(List<String> cartProductIds) {
      this.cartProductIds = cartProductIds;
  }
  
  public List<String> getWishlistProductIds() {
      return wishlistProductIds;
  }
  
  public void setWishlistProductIds(List<String> wishlistProductIds) {
      this.wishlistProductIds = wishlistProductIds;
  }
  
    public Double getCartPrice() {
      return cartPrice;
    }
  
    public void setCartPrice(Double cartPrice) {
      this.cartPrice = cartPrice;
    }
  
    public List<String> getRecentlyViewedProductIds() {
      return recentlyViewedProductIds;
    }
    
    public void setRecentlyViewedProductIds(List<String> recentlyViewedProductIds) {
      this.recentlyViewedProductIds = recentlyViewedProductIds;
    }
  }
