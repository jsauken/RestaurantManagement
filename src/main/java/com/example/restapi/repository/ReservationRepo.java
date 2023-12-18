package com.example.restapi.repository;

import com.example.restapi.model.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restapi.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomerNameContaining(String keyword, Pageable pageable);
    List<Reservation> findAll(Sort sort);
    List<Reservation> findByCustomerEmail(String customerEmail);

}
