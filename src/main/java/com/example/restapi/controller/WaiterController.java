package com.example.restapi.controller;

import com.example.restapi.DTO.WaiterDTO;
import com.example.restapi.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/waiters")
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @GetMapping("/{id}")
    public ResponseEntity<WaiterDTO> getWaiterById(@PathVariable int id) {
        WaiterDTO waiter = waiterService.getById(id);
        return ResponseEntity.ok(waiter);
    }

    @PostMapping
    public ResponseEntity<WaiterDTO> createWaiter(@RequestBody WaiterDTO waiterDTO) {
        WaiterDTO createdWaiter = waiterService.createWaiter(waiterDTO);
        return ResponseEntity.ok(createdWaiter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaiterDTO> updateWaiter(@PathVariable int id, @RequestBody WaiterDTO updatedWaiterDTO) {
        WaiterDTO updatedWaiter = waiterService.updateWaiter(id, updatedWaiterDTO);
        return ResponseEntity.ok(updatedWaiter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWaiter(@PathVariable int id) {
        waiterService.deleteWaiter(id);
        return ResponseEntity.ok().build();
    }

}
