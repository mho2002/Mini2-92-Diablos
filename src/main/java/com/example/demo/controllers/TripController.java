package com.example.demo.controllers;

import com.example.demo.models.Captain;
import com.example.demo.models.Trip;
import com.example.demo.services.TripService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;
    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip)
    {
//        if (!tripService.validateTripData(trip))
//            throw new IllegalArgumentException("Invalid trip data");
        return tripService.addTrip(trip);
    }

    @GetMapping("/allTrips")
    public List<Trip> getAllTrips()
    {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id)
    {
        if (id == null)
            throw new IllegalArgumentException("Invalid trip id");
        Trip trip = tripService.getTripById(id);
        if (trip == null)
            throw new IllegalArgumentException("Trip not found");
        return trip;
    }

    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip)
    {
        if (id == null)
            throw new IllegalArgumentException("Invalid trip id");
//        else if (!tripService.validateTripData(trip)) {
//            throw new IllegalArgumentException("Invalid trip data");
//        }
        return tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id)
    {
        tripService.deleteTrip(id);
        return "Trip deleted";
    }

    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(@RequestParam LocalDateTime startDate, @RequestParam
    LocalDateTime endDate)
    {
        if (startDate == null || endDate == null)
            throw new IllegalArgumentException("Invalid date range");
        else if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        } else if (startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("Start date cannot be equal to end date");
        } else if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        return tripService.findTripsWithinDateRange(startDate, endDate);
    }

    @GetMapping("/findByCaptainId")
    public List<Trip> findTripsByCaptainId(@RequestParam Long captainId)
    {
        if (captainId == null)
            throw new IllegalArgumentException("Invalid captain id");
        List<Trip> trips = tripService.findTripsByCaptainId(captainId);

        if (trips.isEmpty())
            ResponseEntity.ok("No trips found for captain id " + captainId);
        return trips;
    }



}