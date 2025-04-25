package com.example.demo.repositories;

import com.example.demo.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // 🔹 Find payment by trip ID
    @Query("SELECT p FROM Payment p WHERE p.trip.id = :tripId")
    List<Payment> findByTripId(@Param("tripId") long tripId);

    // 🔹 Find payments with an amount above a certain threshold
    @Query("SELECT p FROM Payment p WHERE p.amount > :threshold")
    List<Payment> findByAmountGreaterThan(@Param("threshold") double threshold);
}
