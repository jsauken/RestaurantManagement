package com.example.restapi.controller;

import com.example.restapi.model.RestrauntTable;
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
    private List<RestrauntTable> restrauntTables = new ArrayList<>();

    @GetMapping("/{tableNum}")
    public ResponseEntity<RestrauntTable> getTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > restrauntTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        RestrauntTable restrauntTable = restrauntTables.get(tableNum - 1);
        return ResponseEntity.ok(restrauntTable);
    }

    @GetMapping()
    public ResponseEntity<List<RestrauntTable>> getAllTables() {
        return ResponseEntity.ok(restrauntTables);
    }

    @PostMapping()
    public ResponseEntity<RestrauntTable> createTable(@RequestBody RestrauntTable restrauntTable) {
        restrauntTables.add(restrauntTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(restrauntTable);
    }

    @PutMapping("/{tableNum}")
    public ResponseEntity<RestrauntTable> updateTable(@PathVariable("tableNum") int tableNum, @RequestBody RestrauntTable updatedRestrauntTable) {
        if (tableNum <= 0 || tableNum > restrauntTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        restrauntTables.set(tableNum - 1, updatedRestrauntTable);
        return ResponseEntity.ok(updatedRestrauntTable);
    }

    @DeleteMapping("/{tableNum}")
    public ResponseEntity<Void> deleteTable(@PathVariable("tableNum") int tableNum) {
        if (tableNum <= 0 || tableNum > restrauntTables.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        restrauntTables.remove(tableNum - 1);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/{tableNum}/assign-waiter")
    public ResponseEntity<RestrauntTable> assignWaiterToTable(
            @PathVariable("tableNum") int tableNum,
            @RequestBody TableAssignment tableAssignment) {
        if (tableNum <= 0 || tableNum > restrauntTables.size() || tableAssignment.getWaiterId() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        RestrauntTable restrauntTable = restrauntTables.get(tableNum - 1);
        int waiterId = tableAssignment.getWaiterId();

        if (waiterId <= waiters.size()) {
            Waiter waiter = waiters.get(waiterId - 1);
            tableAssignments.add(tableAssignment);
            return ResponseEntity.ok(restrauntTable);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
