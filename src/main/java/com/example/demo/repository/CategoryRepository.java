package com.example.demo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Category;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
  boolean existsBySlug(String slug);
    Optional<Category> findBySlug(String slug);
}
