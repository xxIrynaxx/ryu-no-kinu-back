package com.example.demo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Style;

public interface StyleRepository extends MongoRepository<Style, ObjectId> {
  Optional<Style> findByCode(String code);
  Optional<Style> findById(ObjectId id);
}
