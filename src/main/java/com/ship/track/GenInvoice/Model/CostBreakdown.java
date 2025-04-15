package com.ship.track.GenInvoice.Model;

public class CostBreakdown {
    double baseShippingCost;
    double insuranceCost;
    double handlingFee;
    double tax;
    public double totalCost;
    double taxRate;

    public CostBreakdown(Shipment shipment, double taxRate) {
        this.taxRate = taxRate;

// Calculate base shipping cost from cargo
        this.baseShippingCost = shipment.cargo.calculateBasePrice();

// Calculate insurance (higher for fragile/hazardous items)
        if (shipment.cargo.type.equals("Fragile") || shipment.cargo.type.equals("Hazardous")) {
            this.insuranceCost = baseShippingCost * 0.15; // 15% of base cost
        } else {
            this.insuranceCost = baseShippingCost * 0.05; // 5% of base cost
        }

// Calculate handling fee
        if (shipment.cargo.type.equals("Express")) {
            this.handlingFee = 25.0; // Fixed $25 express handling
        } else if (shipment.cargo.type.equals("Hazardous")) {
            this.handlingFee = 50.0; // Fixed $50 hazardous handling
        } else {
            this.handlingFee = 10.0; // Standard handling fee
        }

// Calculate subtotal
        double subtotal = baseShippingCost + insuranceCost + handlingFee;

// Calculate tax
        this.tax = subtotal * taxRate;

// Calculate total
        this.totalCost = subtotal + tax;
    }
}
