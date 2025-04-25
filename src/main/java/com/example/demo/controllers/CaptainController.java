package com.example.demo.controllers;

import com.example.demo.models.Captain;
import com.example.demo.services.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {
    private final CaptainService captainService;
    @Autowired
    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    @PostMapping("/addCaptain")
    public Captain addCaptain(@RequestBody Captain captain)
    {
        if (captain.getName() == null || captain.getName().isEmpty())
            throw new IllegalArgumentException("Captain name cannot be empty");
        else if (captain.getAvgRatingScore() == 0) {
            throw new IllegalArgumentException("Captain average rating score cannot be empty");

        } else if (captain.getLicenseNumber() == null || captain.getLicenseNumber().isEmpty()) {
            throw new IllegalArgumentException("Captain license number cannot be empty");
        }
        return captainService.addCaptain(captain);
    }

    @GetMapping("/allCaptains")
    public List<Captain> getAllCaptains()
    {
        return captainService.getAllCaptains();
    }

    @GetMapping("/{id}")
    public Captain getCaptainById(@PathVariable Long id)
    {
        if (id == null)
            throw new IllegalArgumentException("Captain id cannot be null");
        Captain captain = captainService.getCaptainById(id);
        if (captain == null)
            throw new IllegalArgumentException("Captain with id " + id + " not found");
        return captain;
    }

    @GetMapping("/filterByRating")
    public List<Captain> getCaptainsByRating(@RequestParam Double ratingThreshold)
    {
        if (ratingThreshold == null || ratingThreshold < 0||ratingThreshold > 5)
            throw new IllegalArgumentException("Rating threshold must be between 0 and 5");
        return captainService.getCaptainsByRating(ratingThreshold);
    }

    @GetMapping("/filterByLicenseNumber")
    public Captain getCaptainByLicenseNumber(@RequestParam String licenseNumber)
    {
        if (licenseNumber == null || licenseNumber.isEmpty())
            throw new IllegalArgumentException("License number cannot be empty");
        Captain captain = captainService.getCaptainByLicenseNumber(licenseNumber);
        if (captain == null)
            ResponseEntity.notFound().build();
        return captain;
    }


}

