package com.ship.track.GenInvoice.Service;
import com.ship.track.GenInvoice.Model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipmentManagementSystem {
    private List<Customer> customers;
    private List<Shipment> shipments;
    private Employee currentEmployee;

    // Email configuration
    private String smtpHost = "smtp.gmail.com";
    private String smtpPort = "587";
    private String senderEmail = "crystalswaby14@gmail.com"; // Replace with your email
    private String senderPassword = "your_app_password"; // Replace with your app password

    public ShipmentManagementSystem() {
// Initialize lists
        customers = new ArrayList<>();
        shipments = new ArrayList<>();

// Add sample data
        initSampleData();
    }

    private void initSampleData() {
// Add sample customers
        Customer c1 = new Customer("C001", "Crystal Bent", "crystalbent1313@gmail.com");
        Customer c2 = new Customer("C002", "John Smith", "johnsmith@example.com");
        Customer c3 = new Customer("C003", "Alex Johnson", "alex@example.com");
        Customer c4 = new Customer("C004", "Maria Garcia", "maria@example.com");

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);

// Current date
        Date now = new Date();

// Create different cargo types
        Cargo standardCargo = new Cargo("Standard", 15.5, "Books and documents");
        Cargo expressCargo = new Cargo("Express", 8.2, "Urgent medical supplies");
        Cargo fragileCargo = new Cargo("Fragile", 12.0, "Glassware");
        Cargo hazardousCargo = new Cargo("Hazardous", 25.0, "Industrial chemicals");
        Cargo heavyCargo = new Cargo("Standard", 75.0, "Manufacturing equipment");

// Add sample shipments with different statuses and cargo
        shipments.add(new Shipment("S001", c1, now, "Delivered", standardCargo));
        shipments.add(new Shipment("S002", c2, now, "In Transit", expressCargo));
        shipments.add(new Shipment("S003", c3, now, "Delivered", fragileCargo));
        shipments.add(new Shipment("S004", c4, now, "Processing", hazardousCargo));
        shipments.add(new Shipment("S005", c1, now, "Delivered", heavyCargo));

// Set current employee
        currentEmployee = new Employee("EMP001", "John Smith", "Accounts Clerk");
    }

    public Invoice generateInvoice(Shipment shipment) {
        if (!shipment.status.equals("Delivered")) {
            throw new IllegalArgumentException("Cannot generate invoice for shipments that are not delivered");
        }

        String invoiceID = "INV-" + shipment.shipmentID;
        double taxRate = 0.15; // 15% tax rate

        CostBreakdown breakdown = new CostBreakdown(shipment, taxRate);
        Date dateGenerated = new Date();

        return new Invoice(invoiceID, shipment, currentEmployee, breakdown, dateGenerated);
    }

    // Method to send invoice by email
    public boolean sendInvoice(Invoice invoice) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");

// Create email subject
        String subject = "Invoice #" + invoice.invoiceID + " for Shipment " + invoice.shipment.shipmentID;

// Create email body
        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(invoice.shipment.customer.name).append(",\n\n");
        body.append("Please find attached your invoice for shipment #").append(invoice.shipment.shipmentID).append(".\n\n");
        body.append("Invoice Details:\n");
        body.append("Invoice ID: ").append(invoice.invoiceID).append("\n");
        body.append("Date: ").append(sdf.format(invoice.dateGenerated)).append("\n\n");

        body.append("Shipment Details:\n");
        body.append("Cargo Type: ").append(invoice.shipment.cargo.type).append("\n");
        body.append("Weight: ").append(invoice.shipment.cargo.weight).append(" kg\n");
        body.append("Description: ").append(invoice.shipment.cargo.description).append("\n\n");

        body.append("Total Amount Due: $").append(String.format("%.2f", invoice.costBreakdown.totalCost)).append("\n\n");

        body.append("Payment Terms: Due within 30 days of invoice date\n");
        body.append("Payment Method: Bank transfer or check\n\n");

        body.append("Thank you for your business.\n\n");
        body.append("Regards,\n");
        body.append(invoice.generatedBy.name).append("\n");
        body.append("Your Shipping Company");

// Send the email with invoice attached
        return invoice.sendInvoiceByEmail(smtpHost, smtpPort, senderEmail, senderPassword, subject, body.toString());
    }

    public List<Shipment> getShipmentsByStatus(String status) {
        List<Shipment> filteredShipments = new ArrayList<>();
        for (Shipment shipment : shipments) {
            if (shipment.status.equals(status)) {
                filteredShipments.add(shipment);
            }
        }
        return filteredShipments;
    }

    public List<Shipment> getAllShipments() {
        return new ArrayList<>(shipments);
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    // Update email settings
    public void updateEmailSettings(String host, String port, String email, String password) {
        this.smtpHost = host;
        this.smtpPort = port;
        this.senderEmail = email;
        this.senderPassword = password;
    }
}
