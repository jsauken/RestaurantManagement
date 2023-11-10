package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.DTO.TableDTO;

import java.util.ArrayList;

public interface TableService {
    ArrayList<TableDTO> getAllTables();
    TableDTO getById(int id);
    TableDTO createTable(TableDTO table);
    TableDTO updateTable(int id, TableDTO updatedTableDTO);
    void deleteTable(int id);
}
