package com.example.restapi.repository;

import com.example.restapi.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, Integer> {
}
