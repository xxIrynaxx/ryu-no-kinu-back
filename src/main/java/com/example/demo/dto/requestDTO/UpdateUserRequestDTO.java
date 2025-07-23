package com.example.demo.dto.requestDTO;

public class UpdateUserRequestDTO {
  private String username;
  private String avatar;
  private String password;

  public UpdateUserRequestDTO(){}

  public UpdateUserRequestDTO(String username, String avatar, String password) {
    this.username = username;
    this.avatar = avatar;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
