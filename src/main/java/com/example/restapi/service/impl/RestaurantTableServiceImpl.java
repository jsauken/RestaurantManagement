package com.example.restapi.service.impl;

import com.example.restapi.DTO.RestaurantTableDTO;
import com.example.restapi.model.RestaurantTable;
import com.example.restapi.repository.RestaurantTableRepo;
import com.example.restapi.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {
    @Autowired
    private RestaurantTableRepo restaurantTableRepo;
    @Override
    public ArrayList<RestaurantTableDTO> getAllTables() {
        List<RestaurantTable> tables = restaurantTableRepo.findAll();
        return tables.stream()
                .map(RestaurantTableServiceImpl::convertToTableDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public RestaurantTableDTO getById(int id){
        RestaurantTable restaurantTable = restaurantTableRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Table not found with ID: " + id));
        return convertToTableDTO(restaurantTable);
    }
    public static RestaurantTableDTO convertToTableDTO(RestaurantTable restaurantTable) {
        RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO();
        restaurantTableDTO.setTableId(restaurantTable.getTableId());
        restaurantTableDTO.setSeatNum(restaurantTable.getSeatNum());
        restaurantTableDTO.setTableNum(restaurantTable.getTableNum());
        restaurantTableDTO.setStatus(restaurantTable.getStatus());
        return restaurantTableDTO;
    }

    public static RestaurantTable convertToTable(RestaurantTableDTO restaurantTableDTO) {
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableId(restaurantTableDTO.getTableId());
        restaurantTable.setTableNum(restaurantTableDTO.getTableNum());
        restaurantTable.setSeatNum(restaurantTableDTO.getSeatNum());
        restaurantTable.setStatus(restaurantTableDTO.getStatus());
        return restaurantTable;
    }

    @Override
    public RestaurantTableDTO createTable(RestaurantTableDTO restaurantTableDTO){
        RestaurantTable restaurantTable = convertToTable(restaurantTableDTO);
         restaurantTableRepo.save(restaurantTable);
        return restaurantTableDTO;
    }
    @Override
    public RestaurantTableDTO updateTable(int id, RestaurantTableDTO updatedRestaurantTableDTO) {
        RestaurantTable existingTable = restaurantTableRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with ID: " + id));

        existingTable.setTableNum(updatedRestaurantTableDTO.getTableNum());
        existingTable.setSeatNum(updatedRestaurantTableDTO.getSeatNum());
        existingTable.setStatus(updatedRestaurantTableDTO.getStatus());

        restaurantTableRepo.save(existingTable);

        return convertToTableDTO(existingTable);
    }

    @Override
    public void deleteTable(int id) {
        restaurantTableRepo.deleteById(id);
    }


}
