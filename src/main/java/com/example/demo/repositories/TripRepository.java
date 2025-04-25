package com.example.demo.repositories;

import com.example.demo.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    // 🔹 Retrieve trips within a specified date range
    @Query("SELECT t FROM Trip t WHERE t.tripDate BETWEEN :startDate AND :endDate")
    List<Trip> findTripsWithinDateRange(@Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);

    // 🔹 Filter trips by captain ID
    @Query("SELECT t FROM Trip t WHERE t.captain.id = :captainId")
    List<Trip> findTripsByCaptainId(@Param("captainId") long captainId);
}