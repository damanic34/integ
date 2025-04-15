package com.ship.track.trackPackage.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Package {
    @Id
    private String trackingNumber;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime lastUpdated;
    private LocalDateTime estimatedDeliveryDate;
    private double weight;
    private String cargoType;

    public Package(String trackingNumber, String origin, String destination, String status,
                   double weight, LocalDateTime estimatedDeliveryDate, String cargoType) {
        this.trackingNumber = trackingNumber;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.weight = weight;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.cargoType = cargoType;
        this.lastUpdated = LocalDateTime.now();
    }

    public Package() {
    }

    // Getters and Setters
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public double getWeight() {
        return weight;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getDetails() {
        return "Tracking Number: " + trackingNumber + "\n" +
                "Origin: " + origin + "\n" +
                "Destination: " + destination + "\n" +
                "Status: " + status + "\n" +
                "Weight: " + weight + " kg\n" +
                "Estimated Delivery Date: " + estimatedDeliveryDate + "\n" +
                "Last Updated: " + lastUpdated + "\n" +
                "Cargo Type: " + cargoType;
    }
}
