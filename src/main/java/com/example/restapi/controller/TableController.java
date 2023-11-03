package com.example.restapi.controller;

import com.example.restapi.model.Table;
import com.example.restapi.model.TableAssignment;
import com.example.restapi.model.Waiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/table")
public class TableController {
    private List<Waiter> waiters = new ArrayList<>();

    private List<TableAssignment> tableAssignments = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

    @GetMapping("/{tableNum}")
    public ResponseEntity<Table> getTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > tables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Table table = tables.get(tableNum - 1);
        return ResponseEntity.ok(table);
    }

    @GetMapping()
    public ResponseEntity<List<Table>> getAllTables() {
        return ResponseEntity.ok(tables);
    }

    @PostMapping()
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        tables.add(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(table);
    }

    @PutMapping("/{tableNum}")
    public ResponseEntity<Table> updateTable(@PathVariable("tableNum") int tableNum, @RequestBody Table updatedTable) {
        if (tableNum <= 0 || tableNum > tables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        tables.set(tableNum - 1, updatedTable);
        return ResponseEntity.ok(updatedTable);
    }

    @DeleteMapping("/{tableNum}")
    public ResponseEntity<Void> deleteTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > tables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tables.remove(tableNum - 1);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/{tableNum}/assign-waiter")
    public ResponseEntity<Table> assignWaiterToTable(
            @PathVariable("tableNum") int tableNum,
            @RequestBody TableAssignment tableAssignment) {
        if (tableNum <= 0 || tableNum > tables.size() || tableAssignment.getWaiterId() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Table table = tables.get(tableNum - 1);
        int waiterId = tableAssignment.getWaiterId();

        if (waiterId <= waiters.size()) {
            Waiter waiter = waiters.get(waiterId - 1);
            tableAssignments.add(tableAssignment);
            return ResponseEntity.ok(table);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
