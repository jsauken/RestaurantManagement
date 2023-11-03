package com.example.restapi.model;

public class Customer {
    int customerId;
    String name;
    String surname;

    public Customer(int customerId, String name, String surname) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
