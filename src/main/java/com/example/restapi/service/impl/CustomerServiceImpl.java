package com.example.restapi.service.impl;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.Exceptions.ResourceNotFoundException;
import com.example.restapi.model.Customer;
import com.example.restapi.repository.CustomerRepo;
import com.example.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public ArrayList<CustomerDTO> getAllCustomers(){
    return null;
    }
    @Override
    public CustomerDTO getById(int id){
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + id));
        return convertToCustomerDTO(customer);
    }
    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());

        return customerDTO;
    }

    private Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());

        return customer;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        Customer customer = convertToCustomer(customerDTO);
        customer = customerRepo.save(customer);
        return customerDTO;
    }
    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", (long) id));


        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setSurname(updatedCustomerDTO.getSurname());

        existingCustomer = customerRepo.save(existingCustomer);
        return convertToCustomerDTO(existingCustomer);
    }
    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", (long) id));
        customerRepo.delete(customer);
    }


}
