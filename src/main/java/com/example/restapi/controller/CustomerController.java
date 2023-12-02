package com.example.restapi.controller;

import com.example.restapi.DTO.CustomerDTO;

import com.example.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO updatedCustomerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, updatedCustomerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<CustomerDTO> customers = customerService.getAllCustomers(page, size);

        return ResponseEntity.ok(customers);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllCustomers() {
        // Implement the logic to delete all customers here
        customerService.deleteAllCustomers();
        return ResponseEntity.ok().build();
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<CustomerDTO>> getAllCustomersWithMultiColumnSorting() {
        List<CustomerDTO> customers = customerService.getAllCustomersWithMultiColumnSorting();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(
            @RequestParam(name = "keyword") String keyword) {

        List<CustomerDTO> customers = customerService.searchCustomersByName(keyword);
        return ResponseEntity.ok(customers);
    }
}