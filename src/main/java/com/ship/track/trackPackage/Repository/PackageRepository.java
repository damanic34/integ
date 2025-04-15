package com.ship.track.trackPackage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ship.track.trackPackage.Model.Package;
import org.springframework.stereotype.Repository;


@Repository
public interface PackageRepository extends JpaRepository<Package, String> {
    // JpaRepository gives us basic CRUD operations automatically
}
