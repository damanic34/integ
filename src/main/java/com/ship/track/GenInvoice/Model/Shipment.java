package com.ship.track.GenInvoice.Model;

import java.util.Date;

public class Shipment {
    public String shipmentID;
    public String status;
    public Customer customer;
    Date date;
    public Cargo cargo;

    public Shipment(String shipmentID, Customer customer, Date date, String status, Cargo cargo) {
        this.shipmentID = shipmentID;
        this.customer = customer;
        this.date = date;
        this.status = status;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Shipment " + shipmentID + " - " + customer.name + " - " + cargo.type + " (" + cargo.weight + "kg) - " + status;
    }
}
