package com.example.restapi.repository;


import com.example.restapi.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameContaining(String keyword);
    List<Customer> findAll(Sort sort);
    Optional<Customer> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer")
    void deleteAllCustomers();
}
