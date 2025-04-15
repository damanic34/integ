package com.ship.track.trackPackage.Model;
import org.springframework.stereotype.Component;
import com.ship.track.trackPackage.Service.PackageService;

import java.time.LocalDateTime;

@Component
public class PackageTracker {
    private final PackageService packageService;

    public PackageTracker(PackageService packageService) {
        this.packageService = packageService;
    }

    public void addPackage(String trackingNumber, String origin, String destination, String status,
                           double weight, LocalDateTime estimatedDeliveryDate, String cargoType) {
        packageService.addPackage(trackingNumber, origin, destination, status, weight, estimatedDeliveryDate, cargoType);
        System.out.println("Package added successfully!");
    }

    public void updateStatus(String trackingNumber, String newStatus) {
        try {
            packageService.updatePackageStatus(trackingNumber, newStatus);
            System.out.println("Package status updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewPackageDetails(String trackingNumber) {
        try {
            String details = String.valueOf(packageService.getPackageDetails(trackingNumber));
            System.out.println(details);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void refreshStatuses() {
        System.out.println("Statuses refreshed successfully!");
    }

}
