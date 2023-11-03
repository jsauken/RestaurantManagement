package com.example.restapi.model;

public class Reservation {
    Table table;
    Customer customer;
    String reservationTime;

    public Reservation(Table table, Customer customer, String reservationTime) {
        this.table = table;
        this.customer = customer;
        this.reservationTime = reservationTime;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }
}
