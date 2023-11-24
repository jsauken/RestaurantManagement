package com.example.restapi.service.impl;

import com.example.restapi.DTO.RestaurantTableDTO;
import com.example.restapi.model.RestaurantTable;
import com.example.restapi.repository.RestaurantTableRepo;
import com.example.restapi.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {
    @Autowired
    private RestaurantTableRepo restaurantTableRepo;
    @Override
    public ArrayList<RestaurantTableDTO> getAllTables(){
        return null;
    }
    @Override
    public RestaurantTableDTO getById(int id){
        RestaurantTable restaurantTable = restaurantTableRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Table not found with ID: " + id));
        return convertToTableDTO(restaurantTable);
    }
    public RestaurantTableDTO convertToTableDTO(RestaurantTable restaurantTable) {
        RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO();
        restaurantTableDTO.setTableNum(restaurantTable.getTableNum());
        restaurantTableDTO.setStatus(restaurantTable.getStatus());
        return restaurantTableDTO;
    }

    public RestaurantTable convertToTable(RestaurantTableDTO restaurantTableDTO) {
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableNum(restaurantTableDTO.getTableNum());
        restaurantTable.setStatus(restaurantTableDTO.getStatus());
        return restaurantTable;
    }

    @Override
    public RestaurantTableDTO createTable(RestaurantTableDTO restaurantTableDTO){
        RestaurantTable restaurantTable = convertToTable(restaurantTableDTO);
        restaurantTable = restaurantTableRepo.save(restaurantTable);
        return restaurantTableDTO;
    }
    @Override
    public RestaurantTableDTO updateTable(int id, RestaurantTableDTO updatedRestaurantTableDTO){
        return null;
    }
    @Override
    public void deleteTable(int id){
    }

}
