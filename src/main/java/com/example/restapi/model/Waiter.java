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
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}