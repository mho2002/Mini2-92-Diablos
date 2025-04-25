package com.example.demo.services;

import com.example.demo.models.Trip;
import com.example.demo.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip)
    {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips()
    {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id)
    {
        if (tripRepository.findById(id).isPresent())
            return tripRepository.findById(id).get();
        return null;
    }

    public Trip updateTrip(Long id, Trip trip)
    {
        Trip existingTrip = new Trip();
        if (tripRepository.findById(id).isPresent())
            existingTrip = tripRepository.findById(id).get();
        else
            return null;
        existingTrip.setId(id);
        existingTrip.setTripCost(trip.getTripCost());
        existingTrip.setTripDate(trip.getTripDate());
        existingTrip.setCaptain(trip.getCaptain());
        existingTrip.setCustomer(trip.getCustomer());
        existingTrip.setDestination(trip.getDestination());
        existingTrip.setOrigin(trip.getOrigin());
        existingTrip.setPayment(trip.getPayment());
        return tripRepository.save(existingTrip);
    }

    public void deleteTrip(Long id)
    {
        if (tripRepository.findById(id).isPresent())
            tripRepository.deleteById(id);
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate)
    {
        return tripRepository.findTripsWithinDateRange(startDate, endDate);
    }

    public List<Trip> findTripsByCaptainId(Long captainId)
    {
        return tripRepository.findTripsByCaptainId(captainId);
    }

    public boolean validateTripData(Trip trip)
    {
        if (trip.getTripDate() == null )
            return false;
        else if (trip.getCaptain() == null || trip.getCustomer() == null || trip.getDestination() == null || trip.getOrigin() == null) {
            return false;
        } else if (trip.getDestination().isEmpty() || trip.getOrigin().isEmpty()) {
            return false;
        } else return !(trip.getTripCost() <= 0.0);
    }


}