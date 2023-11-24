package com.example.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "restaurant_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;
    @Column(name = "table_number", nullable = false)
    int tableNum;
    @Column(name = "seat_number", nullable = false)
    int seatNum; //number of seats
    @Column(name = "status", nullable = false)
    String status;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "modifiedAt")
    private Date modifiedAt;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "assigned_waiter_id")
    private Waiter assignedWaiter;

}
