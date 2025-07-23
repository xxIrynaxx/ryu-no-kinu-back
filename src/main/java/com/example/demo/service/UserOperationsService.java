package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;


import com.example.demo.dto.modelDTO.ProductDTO;
import com.example.demo.dto.modelDTO.UserDTO;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserOperationsService {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public UserOperationsService(UserRepository userRepository, ProductRepository productRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  private User getUserByIdOrThrow(ObjectId userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
       
  public UserDTO addToWishlist(ObjectId userId, ObjectId productId) {
    User user = getUserByIdOrThrow(userId);

    if (!user.getWishlistProductIds().contains(productId)) {
      user.getWishlistProductIds().add(productId);
      userRepository.save(user);
    }

    return new UserDTO(user);
  }

  public UserDTO removeFromWishlist(ObjectId userId, ObjectId productId) {
    User user = getUserByIdOrThrow(userId);
    user.getWishlistProductIds().remove(productId);
    userRepository.save(user);
    return new UserDTO(user);
  }

  public UserDTO addToCart(ObjectId userId, ObjectId productId) {
    User user = getUserByIdOrThrow(userId);

    if (!user.getCartProductIds().contains(productId)) {
      user.getCartProductIds().add(productId);
      userRepository.save(user);
    }

    return new UserDTO(user);
  }

  public UserDTO removeFromCart(ObjectId userId, ObjectId productId) {
    User user = getUserByIdOrThrow(userId);
    user.getCartProductIds().remove(productId);
    userRepository.save(user);
    return new UserDTO(user);
  }

  public UserDTO addToRecentlyViewed(ObjectId userId, ObjectId productId) {
    User user = getUserByIdOrThrow(userId);

    if (!user.getRecentlyViewedProductIds().contains(productId)) {
      user.getRecentlyViewedProductIds().add(productId);
      userRepository.save(user);
    }

    return new UserDTO(user);
  }

  public UserDTO getUserWithProducts(ObjectId userId, String lang) {
    User user = getUserByIdOrThrow(userId);
    
    List<ProductDTO> wishlistProducts = getProductsByIds(user.getWishlistProductIds(), lang);
    List<ProductDTO> cartProducts = getProductsByIds(user.getCartProductIds(), lang);
    List<ProductDTO> recentlyViewedProducts = getProductsByIds(user.getRecentlyViewedProductIds(), lang);
    
    return new UserDTO(user, wishlistProducts, cartProducts, user.getCartPrice(), recentlyViewedProducts);
  }

  private List<ProductDTO> getProductsByIds(List<ObjectId> productIds, String lang) {
    return productIds.stream()
        .map(productId -> {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

            return new ProductDTO(product, lang);
        })
        .collect(Collectors.toList());
  }
}