package com.example.restapi.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(name = "customer_name", nullable = false)
    private String name;
    @Column(name = "customer_surname", nullable = false)
    private String surname;
    @Column(name = "customer_phone")
    private long phoneNumber;
    @Column(name = "customer_email")
    private String email;
    @Column(name = "customer_password")
    private String password;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "modifiedAt")
    private Date modifiedAt;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @Column(name = "roles", nullable = false)
    private String roles = Role.USER.getAuthority();


}
