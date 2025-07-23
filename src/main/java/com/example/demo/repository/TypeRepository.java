package com.example.demo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Type;

public interface TypeRepository extends MongoRepository<Type, ObjectId> {
  Optional<Type> findByCode(String code);
  Optional<Type> findById(ObjectId id);
}
