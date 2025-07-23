package com.example.demo.dto.requestDTO;

import com.example.demo.model.Role;
import com.example.demo.model.Theme;

import java.util.Date;

public class AdminUserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String avatar;
    private Role role;
    private Theme theme;
    private String lang;
    private Date createdAt;

    public AdminUserResponseDTO() {}

    public AdminUserResponseDTO(String id, String username, String email, String avatar,
        Role role, Theme theme, String lang, Date createdAt) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.avatar = avatar;
      this.role = role;
      this.theme = theme;
      this.lang = lang;
      this.createdAt = createdAt;
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
}
