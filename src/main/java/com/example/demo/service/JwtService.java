package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
  
  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(User user) {
    return Jwts.builder()
      .setSubject(user.getId().toHexString())
      .claim("email", user.getEmail())
      .claim("role", user.getRole().name())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }

  public String extractUserId(String token) {
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userId = extractUserId(token);
    return userId.equals(userDetails.getUsername());
  }
}
