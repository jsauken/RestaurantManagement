package com.example.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "waiter")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int waiterId;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "assignedWaiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RestaurantTable> assignedTables;
    @Column(name = "role", nullable = false)
    private Role roles = Role.ADMIN;
}