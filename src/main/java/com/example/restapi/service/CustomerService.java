package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDTO getById(int id);
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(int id, CustomerDTO updatedCustomerDTO);
     void deleteCustomer(int id);
     void deleteAllCustomers();
      Page<CustomerDTO> getAllCustomers(int page, int size);
      List<CustomerDTO> getAllCustomersWithMultiColumnSorting();
    List<CustomerDTO> searchCustomersByName(String keyword);
    Optional<CustomerDTO> getByEmail(String email);
    boolean checkPassword(CustomerDTO customerDTO, String password);

}
