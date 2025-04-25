package com.example.demo.services;

import com.example.demo.models.Captain;
import com.example.demo.models.Rating;
import com.example.demo.repositories.CaptainRepository;
import com.example.demo.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptainService {
    // Dependency Injection
    private final CaptainRepository captainRepository;
    private final RatingRepository ratingRepository;
    public CaptainService(CaptainRepository captainRepository, RatingRepository ratingRepository) {
        this.captainRepository = captainRepository;
        this.ratingRepository = ratingRepository;
    }

    public Captain addCaptain(Captain captain)
    {
        return captainRepository.save(captain);
    }

    public List<Captain> getAllCaptains()
    {
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id)
    {
        if (captainRepository.findById(id).isPresent())
            return captainRepository.findById(id).get();
        else return null;
    }

    public List<Captain> getCaptainsByRating(Double ratingThreshold)
    {

        return captainRepository.findCaptainsWithRatingAbove(ratingThreshold);
    }

    public Captain getCaptainByLicenseNumber(String licenseNumber)
    {
        return captainRepository.findByLicenseNumber(licenseNumber);
    }
}
