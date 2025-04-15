package com.ship.track.GenInvoice.Model;
public class Customer {
    String customerID;
    public String name;
    String email;

    public Customer(String customerID, String name, String email) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (ID: " + customerID + ")";
    }
}
