package com.example.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable tableReserved;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;  //one Reservation have one Customer, and one Customer can have multiple Reservations
    @Column(name = "number_of_guests", nullable = false)
    private int numberOfGuests;
    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "assigned_waiter_id")
    private Waiter assignedWaiter; //same as Customer

}
