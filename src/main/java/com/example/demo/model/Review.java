package com.example.demo.model;

import java.util.Date;

public class Review {
  private UserInfo user;
  private int rating;
  private String comment;
  private Date createdAt;

  public Review() {
  }

  public Review(UserInfo user, int rating, String comment, Date createdAt) {
    this.user = user;
    this.rating = rating;
    this.comment = comment;
    this.createdAt = createdAt;
  }

  public UserInfo getUser() {
    return user;
  }

  public void setUser(UserInfo user) {
    this.user = user;
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