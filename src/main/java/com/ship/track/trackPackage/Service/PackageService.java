package com.ship.track.trackPackage.Service;

import com.ship.track.trackPackage.Model.Package;
import com.ship.track.trackPackage.Repository.PackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void addPackage(Package pkg) {
        if (packageRepository.existsById(pkg.getTrackingNumber())) {
            throw new IllegalArgumentException("Tracking number already exists!");
        }
        packageRepository.save(pkg);
    }

    public void addPackage(String trackingNumber, String origin, String destination, String status,
                           double weight, LocalDateTime estimatedDeliveryDate, String cargoType) {
        if (packageRepository.existsById(trackingNumber)) {
            throw new IllegalArgumentException("Tracking number already exists!");
        }

        Package newPackage = new Package(trackingNumber, origin, destination, status, weight, estimatedDeliveryDate, cargoType);
        packageRepository.save(newPackage);
    }

    public void updatePackageStatus(String trackingNumber, String newStatus) {
        Package pkg = packageRepository.findById(trackingNumber)
                .orElseThrow(() -> new IllegalArgumentException("Package not found: " + trackingNumber));
        pkg.setStatus(newStatus);
        packageRepository.save(pkg);
    }

    public Package getPackageDetails(String trackingNumber) {
        return packageRepository.findById(trackingNumber)
                .orElseThrow(() -> new IllegalArgumentException("Package not found: " + trackingNumber));
    }

    public List<Package> getPackagesByStatus(String status) {
        return packageRepository.findAll()
                .stream()
                .filter(pkg -> pkg.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public void deletePackage(String trackingNumber) {
        if (!packageRepository.existsById(trackingNumber)) {
            throw new IllegalArgumentException("Package not found: " + trackingNumber);
        }
        packageRepository.deleteById(trackingNumber);
    }

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }
}
