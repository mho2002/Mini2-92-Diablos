package com.example.demo.repositories;

import com.example.demo.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    // 🔹 Find ratings by entity ID and entity type (e.g. captain, customer, trip)
    List<Rating> findByEntityIdAndEntityType(long entityId, String entityType);

    // 🔹 Find ratings with a score greater than a specific value
    List<Rating> findByScoreGreaterThan(double score);
}
