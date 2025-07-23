package com.example.demo.repository;

import com.example.demo.model.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends MongoRepository<Discount, ObjectId> {
    List<Discount> findByItemsContainsAndStartDateBeforeAndEndDateAfter(ObjectId itemId, Date now1, Date now2);
}
