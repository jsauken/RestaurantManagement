package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;

import java.util.ArrayList;

public interface CustomerService {
    ArrayList<CustomerDTO> getAllCustomers();
    CustomerDTO getById(int id);
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(int id, CustomerDTO updatedCustomerDTO);
     void deleteCustomer(int id);
}
