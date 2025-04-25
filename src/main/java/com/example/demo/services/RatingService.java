package com.example.demo.services;

import com.example.demo.models.Rating;
import com.example.demo.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating)
    {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(String id, Rating updatedRating)
    {
        Rating existingRating = new Rating();
        if (ratingRepository.findById(id).isPresent())
            existingRating = ratingRepository.findById(id).get();
        else
            return null;
        existingRating.setId(id);
        existingRating.setRatingDate(updatedRating.getRatingDate());
        existingRating.setComment(updatedRating.getComment());
        existingRating.setEntityId(updatedRating.getEntityId());
        existingRating.setScore(updatedRating.getScore());
        existingRating.setEntityType(updatedRating.getEntityType());
        return ratingRepository.save(existingRating);
    }

    public void deleteRating(String id)
    {
        if (ratingRepository.findById(id).isPresent())
            ratingRepository.deleteById(id);
    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType)
    {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore)
    {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }

    public boolean isRatingHasValidData (Rating rating)
    {
        if (rating.getRatingDate() == null|| rating.getRatingDate().isAfter(LocalDateTime.now()))
            return true;
        else if (rating.getEntityType() == null || rating.getEntityType().isEmpty()) {
            return true;
        } else if (rating.getComment() == null || rating.getComment().isEmpty()) {
            return true;
        } else return rating.getScore() < 1 || rating.getScore() > 5 || rating.getScore() == null;
    }

}

