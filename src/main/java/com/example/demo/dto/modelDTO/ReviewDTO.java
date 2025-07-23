package com.example.demo.dto.modelDTO;

import java.util.Date;

import com.example.demo.model.Review;
import com.example.demo.model.UserInfo;

public class ReviewDTO {
  private String userId;
  private String username;
  private String avatarUrl;
  private int rating;
  private String comment;
  private Date createdAt;

  public ReviewDTO() {
  }

  public ReviewDTO(Review review) {
    UserInfo user = review.getUser();
    this.userId = user.getId().toHexString();
    this.username = user.getUsername();
    this.avatarUrl = user.getAvatarUrl();
    this.rating = review.getRating();
    this.comment = review.getComment();
    this.createdAt = review.getCreatedAt();
  }

  public String getUserId() {
    return userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getAvatarUrl() {
    return avatarUrl;
}

public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
}

public int getRating() {
    return rating;
}

public void setRating(int rating) {
    this.rating = rating;
}

public String getComment() {
    return comment;
}

public void setComment(String comment) {
    this.comment = comment;
}

public Date getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
}
}
