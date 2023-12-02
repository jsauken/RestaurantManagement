package com.example.restapi.service;

import com.example.restapi.DTO.WaiterDTO;

public interface WaiterService {
    WaiterDTO getById(int id);
    WaiterDTO createWaiter(WaiterDTO waiterDTO);
    WaiterDTO updateWaiter(int id, WaiterDTO updatedWaiterDTO);
    void deleteWaiter(int id);
    // Other methods as needed
}
