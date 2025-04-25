package com.example.demo.controllers;

import com.example.demo.models.Rating;
import com.example.demo.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;
    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating)
    {
//        if (ratingService.isRatingHasValidData(rating))
//            throw new IllegalArgumentException(
//                    "Rating data is not valid"
//            );
        return ratingService.addRating(rating);
    }

    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating updatedRating)
    {
//        if(ratingService.isRatingHasValidData(updatedRating))
//            throw new IllegalArgumentException("Rating data is not valid");
//        else
        if (id.isEmpty() ) {
            throw new IllegalArgumentException("Rating id cannot be empty");
        }
        return ratingService.updateRating(id, updatedRating);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id)
    {
        if (id.isEmpty())
            return ("Rating id cannot be empty");
        ratingService.deleteRating(id);
        return ("Rating deleted successfully");
    }

    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String
            entityType)
    {
        if (entityType.isEmpty())
            throw new IllegalArgumentException("Entity type cannot be empty");
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore)
    {
        if (minScore < 1 || minScore > 5)
            throw new IllegalArgumentException("Min score must be between 1 and 5");
        return ratingService.findRatingsAboveScore(minScore);
    }


}

