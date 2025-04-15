package com.ship.track.ViewHistory.Controller;
import com.ship.track.ViewHistory.Service.GenerateHistory;
import com.ship.track.trackPackage.Model.Package;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // For testing from HTML file
public class ShipmentHistController {

    private final GenerateHistory generateHistory;

    public ShipmentHistController(GenerateHistory generateHistory) {
        this.generateHistory = generateHistory;
    }

    @GetMapping
    public List<Package> getShipments(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "false") boolean isAdmin
    ) {
        List<Package> packages = generateHistory.getAllShipments();

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        return packages.stream()
                .filter(p -> {
                    // Convert LocalDateTime to LocalDate for comparison
                    LocalDate deliveryDate = p.getEstimatedDeliveryDate().toLocalDate();

                    // Apply 6-month retention rule for non-admins
                    if (!isAdmin && deliveryDate.isBefore(sixMonthsAgo)) {
                        return false;
                    }

                    boolean matchStatus = (status == null || status.equalsIgnoreCase("All") ||
                            p.getStatus().equalsIgnoreCase(status));

                    boolean matchStart = (startDate == null || !deliveryDate.isBefore(startDate));
                    boolean matchEnd = (endDate == null || !deliveryDate.isAfter(endDate));

                    return matchStatus && matchStart && matchEnd;
                })
                .collect(Collectors.toList());
    }
}



