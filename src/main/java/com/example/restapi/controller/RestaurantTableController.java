package com.example.restapi.controller;

import com.example.restapi.model.RestaurantTable;
import com.example.restapi.model.Waiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/table")
@PreAuthorize("hasAuthority('ADMIN')")
public class RestaurantTableController {

    private List<RestaurantTable> restaurantTables = new ArrayList<>();

    @GetMapping("/{tableNum}")
    public ResponseEntity<RestaurantTable> getTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > restaurantTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        RestaurantTable restaurantTable = restaurantTables.get(tableNum);
        return ResponseEntity.ok(restaurantTable);
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        return ResponseEntity.ok(restaurantTables);
    }

    @PostMapping()
    public ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable restaurantTable) {
        restaurantTables.add(restaurantTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTable);
    }

    @PutMapping("/{tableNum}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable("tableNum") int tableNum, @RequestBody RestaurantTable updatedRestaurantTable) {
        if (tableNum <= 0 || tableNum > restaurantTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        restaurantTables.set(tableNum - 1, updatedRestaurantTable);
        return ResponseEntity.ok(updatedRestaurantTable);
    }

    @DeleteMapping("/{tableNum}")
    public ResponseEntity<Void> deleteTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > restaurantTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        restaurantTables.remove(tableNum - 1);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
