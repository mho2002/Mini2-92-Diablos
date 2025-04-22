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

    }

    public List<Captain> getAllCaptains()
    {

    }

    public Captain getCaptainById(Long id)
    {
    }

    public List<Captain> getCaptainsByRating(Double ratingThreshold)
    {
    }

    public Captain getCaptainByLicenseNumber(String licenseNumber)
    {

    }
}
