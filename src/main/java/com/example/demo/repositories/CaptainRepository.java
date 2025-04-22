package com.example.demo.repositories;

import com.example.demo.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaptainRepository extends JpaRepository<Captain, Long> {

    @Query("SELECT c FROM Captain c WHERE c.avgRatingScore > :threshold")
    List<Captain> findCaptainsWithRatingAbove(@Param("threshold") double threshold);

    // Query to find a captain by license number
    Captain findByLicenseNumber(String licenseNumber);
}