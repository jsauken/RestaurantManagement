package com.example.restapi.repository;


import com.example.restapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameContaining(String keyword);
}
