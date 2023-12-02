package com.example.restapi.repository;


import com.example.restapi.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepo extends JpaRepository<Waiter, Integer> {

}
