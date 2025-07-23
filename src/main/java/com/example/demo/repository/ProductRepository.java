package com.example.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Product;

public interface ProductRepository extends MongoRepository<Product,ObjectId> {
  List<Product> findByCategoryId(ObjectId categoryId);
  List<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
