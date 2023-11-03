package com.example.restapi.controller;

import com.example.restapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int customerId){
        ArrayList<Customer> customers=new ArrayList<>();
        Customer customer1 = new Customer(1,"Jasmin","Sauken");
        Customer customer2 = new Customer(2,"Aidana","Megembaeva");
        Customer customer3 = new Customer(3,"Aidana","Pazylkhan");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        return ResponseEntity.ok(customers.get(customerId-1));
    }

}
