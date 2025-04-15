package com.ship.track.GenInvoice.Model;

public class Cargo {
    public String type; // e.g., "Standard", "Express", "Fragile", "Hazardous"
    public double weight; // in kg
    public String description;

    public Cargo(String type, double weight, String description) {
        this.type = type;
        this.weight = weight;
        this.description = description;
    }

    // Calculate base price based on cargo type and weight
    public double calculateBasePrice() {
        double baseRate = 0.0;

// Set base rate according to cargo type
        switch(type) {
            case "Standard":
                baseRate = 5.0; // $5 per kg for standard
                break;
            case "Express":
                baseRate = 8.0; // $8 per kg for express
                break;
            case "Fragile":
                baseRate = 10.0; // $10 per kg for fragile
                break;
            case "Hazardous":
                baseRate = 15.0; // $15 per kg for hazardous
                break;
            default:
                baseRate = 5.0; // Default rate
        }

// Calculate price based on weight and apply volume discount
        double price = baseRate * weight;

// Apply volume discount for heavier packages
        if (weight > 50) {
            price *= 0.9; // 10% discount for packages over 50kg
        } else if (weight > 20) {
            price *= 0.95; // 5% discount for packages over 20kg
        }

        return price;
    }

    @Override
    public String toString() {
        return type + " - " + weight + "kg (" + description + ")";
    }
}