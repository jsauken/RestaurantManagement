package com.example.restapi.service;

import com.example.restapi.DTO.RestaurantTableDTO;

import java.util.ArrayList;

public interface RestaurantTableService {
    ArrayList<RestaurantTableDTO> getAllTables();
    RestaurantTableDTO getById(int id);
    RestaurantTableDTO createTable(RestaurantTableDTO table);
    RestaurantTableDTO updateTable(int id, RestaurantTableDTO updatedRestaurantTableDTO);
    void deleteTable(int id);
}
