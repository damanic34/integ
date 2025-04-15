package com.ship.track.ViewHistory.Service;
import com.ship.track.trackPackage.Model.Package;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class GenerateHistory {
    //injected from database
    private final List<Package> shipmentData = new ArrayList<>();

    public List<Package> getAllShipments() {
        return shipmentData;
    }
}

