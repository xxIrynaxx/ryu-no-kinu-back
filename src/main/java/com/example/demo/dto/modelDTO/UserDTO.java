package com.example.demo.dto.modelDTO;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Role;
import com.example.demo.model.Theme;
import com.example.demo.model.User;

public class UserDTO {
  private String id;
  private String username;
  private String email;
  private Date createdAt;
  private String avatar;
  private Role role;
  private Theme theme;
  private String lang;

  private List<ProductDTO> wishlistProduct;
  private List<ProductDTO> cartProduct;
  private Double cartPrice;
  private List<ProductDTO> recentlyViewedProduct;

  public UserDTO(User user) {
    this.id = user.getId().toHexString();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.avatar = user.getAvatar();
    this.createdAt = user.getCreatedAt();
    this.role = user.getRole();
    this.theme = user.getTheme();
    this.lang = user.getLang();

    this.wishlistProduct = null;
    this.cartProduct = null;
    this.cartPrice = user.getCartPrice();
    this.recentlyViewedProduct = null;
  }


  public UserDTO(User user, List<ProductDTO> wishlistProduct, List<ProductDTO> cartProduct,
                 Double cartPrice, List<ProductDTO> recentlyViewedProduct) {
    this(user);
    this.wishlistProduct = wishlistProduct;
    this.cartProduct = cartProduct;
    this.cartPrice = cartPrice;
    this.recentlyViewedProduct = recentlyViewedProduct;
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

  public List<ProductDTO> getCartProduct() {
    return cartProduct;
}

public void setCartProduct(List<ProductDTO> cartProduct) {
    this.cartProduct = cartProduct;
}

public List<ProductDTO> getWishlistProduct() {
    return wishlistProduct;
}

public void setWishlistProduct(List<ProductDTO> wishlistProduct) {
    this.wishlistProduct = wishlistProduct;
}

  public Double getCartPrice() {
    return cartPrice;
  }

  public void setCartPrice(Double cartPrice) {
    this.cartPrice = cartPrice;
  }

  public List<ProductDTO> getRecentlyViewedProduct() {
    return recentlyViewedProduct;
  }
  
  public void setRecentlyViewedProductIds(List<ProductDTO> recentlyViewedProduct) {
    this.recentlyViewedProduct = recentlyViewedProduct;
  }
}
