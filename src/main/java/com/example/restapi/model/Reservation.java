package com.example.restapi.model;

public class Reservation {

    long id;
    RestrauntTable restrauntTable;
    Customer customer;
    String reservationTime;



    public RestrauntTable getTable() {
        return restrauntTable;
    }

    public void setTable(RestrauntTable restrauntTable) {
        this.restrauntTable = restrauntTable;
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
