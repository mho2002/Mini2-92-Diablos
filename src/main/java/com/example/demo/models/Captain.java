package com.example.demo.models;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "captains")
public class Captain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment strategy for 'long' id
    @Column(updatable = false, nullable = false)
    private long id;

    private String name;
    private String licenseNumber;
    private double avgRatingScore;

    @OneToMany(mappedBy = "captain", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Trip> trips;


    public Captain(String name, String licenseNumber, double avgRatingScore) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avgRatingScore = avgRatingScore;
    }
    public Captain() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getAvgRatingScore() {
        return avgRatingScore;
    }

    public void setAvgRatingScore(double avgRatingScore) {
        this.avgRatingScore = avgRatingScore;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}