package com.example.restapi.service.impl;

import com.example.restapi.DTO.WaiterDTO;
import com.example.restapi.Exceptions.ResourceNotFoundException;
import com.example.restapi.model.Restaurant;
import com.example.restapi.model.Waiter;
import com.example.restapi.repository.WaiterRepo;
import com.example.restapi.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WaiterServiceImpl implements WaiterService {

    private final WaiterRepo waiterRepo;

    @Autowired
    public WaiterServiceImpl(WaiterRepo waiterRepo) {
        this.waiterRepo = waiterRepo;
    }

    @Override
    public WaiterDTO getById(int id) {
        Waiter waiter = waiterRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Waiter not found with ID: " + id));
        return convertToWaiterDTO(waiter);
    }

    @Override
    public WaiterDTO createWaiter(WaiterDTO waiterDTO) {
        Waiter waiter = convertToWaiter(waiterDTO);
        waiter = waiterRepo.save(waiter);
        waiterDTO.setWaiterId(waiter.getWaiterId());
        waiterDTO.setName(waiter.getName());
        waiterDTO.setRestaurantId(waiter.getRestaurant().getRestaurantId());
        return waiterDTO;
    }

    @Override
    public WaiterDTO updateWaiter(int id, WaiterDTO updatedWaiterDTO) {
        Waiter existingWaiter = waiterRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waiter", "ID", (long) id));

        existingWaiter.setName(updatedWaiterDTO.getName());

        existingWaiter = waiterRepo.save(existingWaiter);
        return convertToWaiterDTO(existingWaiter);
    }

    @Override
    public void deleteWaiter(int id) {
        Waiter waiter = waiterRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waiter", "ID", (long) id));
        waiterRepo.delete(waiter);
    }


    public static WaiterDTO convertToWaiterDTO(Waiter waiter) {
        WaiterDTO waiterDTO = new WaiterDTO();
        waiterDTO.setWaiterId(waiter.getWaiterId());
        waiterDTO.setName(waiter.getName());
        waiterDTO.setRestaurantId(waiter.getRestaurant().getRestaurantId());
        return waiterDTO;
    }
    public static Waiter convertToWaiter(WaiterDTO waiterDTO) {
        Waiter waiter = new Waiter();
        waiter.setWaiterId(waiterDTO.getWaiterId());
        waiter.setName(waiterDTO.getName());
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(waiterDTO.getRestaurantId());
        waiter.setRestaurant(restaurant);
        return waiter;
    }


}
