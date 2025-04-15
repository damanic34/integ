package com.ship.track.trackPackage.Controller;

import com.ship.track.trackPackage.Model.Package;
import com.ship.track.trackPackage.Model.PackageTracker;
import com.ship.track.trackPackage.Service.PackageService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Allows requests from any frontend
public class PackageController {
    private final PackageTracker packageTracker;
    private final PackageService packageService;

    public PackageController(PackageTracker packageTracker, PackageService packageService) {
        this.packageTracker = packageTracker;
        this.packageService = packageService;
    }

    // Get all packages (fixing map issue)
    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    // Add a package (fixing parameters issue)
    @PostMapping
    public String addPackage(
            @RequestParam String trackingNumber,
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String status,
            @RequestParam double weight,
            @RequestParam String cargoType) {

        LocalDateTime estimatedDeliveryDate = LocalDateTime.now().plusDays(3); // Example: Default 3-day delivery

        packageTracker.addPackage(trackingNumber,
                origin,
                destination,
                status,
                weight,
                estimatedDeliveryDate,
                cargoType);
        return "Package added successfully!";
    }

    @GetMapping("/{trackingNumber}")
    public Package getPackage(@PathVariable String trackingNumber) {
        return packageService.getPackageDetails(trackingNumber);
    }

    @PutMapping("/{trackingNumber}/status")
    public String updatePackageStatus(@PathVariable String trackingNumber, @RequestParam String status) {
        packageTracker.updateStatus(trackingNumber, status);
        return "Package status update attempted. Check logs for details.";
    }

    @DeleteMapping("/{trackingNumber}")
    public String deletePackage(@PathVariable String trackingNumber) {
        packageService.deletePackage(trackingNumber);
        return "Package deleted successfully!";
    }

    @GetMapping("/status/{status}")
    public List<Package> getPackagesByStatus(@PathVariable String status) {
        return packageService.getPackagesByStatus(status);
    }


}

