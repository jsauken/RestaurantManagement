package com.example.restapi.service.impl;

import com.example.restapi.DTO.TableDTO;
import com.example.restapi.model.RestrauntTable;
import com.example.restapi.repository.TableRepo;
import com.example.restapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableRepo tableRepo;
    @Override
    public ArrayList<TableDTO> getAllTables(){
        return null;
    }
    @Override
    public TableDTO getById(int id){
        RestrauntTable restrauntTable = tableRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Table not found with ID: " + id));
        return convertToTableDTO(restrauntTable);
    }
    private TableDTO convertToTableDTO(RestrauntTable restrauntTable) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setTableNum(restrauntTable.getTableNum());
        tableDTO.setStatus(restrauntTable.getStatus());
        return tableDTO;
    }

    private RestrauntTable convertToTable(TableDTO tableDTO) {
        RestrauntTable restrauntTable = new RestrauntTable();
        restrauntTable.setTableNum(tableDTO.getTableNum());
        restrauntTable.setStatus(tableDTO.getStatus());
        return restrauntTable;
    }

    @Override
    public TableDTO createTable(TableDTO tableDTO){
        RestrauntTable restrauntTable = convertToTable(tableDTO);
        restrauntTable = tableRepo.save(restrauntTable);
        return tableDTO;
    }
    @Override
    public TableDTO updateTable(int id, TableDTO updatedTableDTO){
        return null;
    }
    @Override
    public void deleteTable(int id){
    }

}
