package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    ArrayList<CustomerDTO> getAllCustomers();
    CustomerDTO getById(int id);
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(int id, CustomerDTO updatedCustomerDTO);
     void deleteCustomer(int id);
      Page<CustomerDTO> getAllCustomers(int page, int size);
      List<CustomerDTO> getAllCustomersWithMultiColumnSorting();
    List<CustomerDTO> searchCustomersByName(String keyword);
}
