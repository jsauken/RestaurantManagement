package com.example.restapi.service.impl;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.Exceptions.ResourceNotFoundException;
import com.example.restapi.model.Customer;
import com.example.restapi.repository.CustomerRepo;
import com.example.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

 /**   public ArrayList<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDTO customerDTO = convertToCustomerDTO(customer);
            customerDTOs.add(customerDTO);
        }

        return customerDTOs;
    }
  **/

    @Override
    public CustomerDTO getById(int id){
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + id));
        return convertToCustomerDTO(customer);
    }
    public static CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setRoles(customer.getRoles());
        return customerDTO;
    }

    public static Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        //customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setRoles(customerDTO.getRoles());
        return customer;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        Customer customer = convertToCustomer(customerDTO);

        customer = customerRepo.save(customer);

        customerDTO.setCustomerId(customer.getCustomerId());

        return customerDTO; // Return the updated customerDTO
    }
    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", (long) id));


        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setSurname(updatedCustomerDTO.getSurname());
        existingCustomer.setModifiedAt(LocalDateTime.now());
        existingCustomer = customerRepo.save(existingCustomer);
        return convertToCustomerDTO(existingCustomer);
    }
    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", (long) id));
        customerRepo.delete(customer);
    }
    @Override
    public void deleteAllCustomers() {
        customerRepo.deleteAll();
    }
//pagination
    @Override
    public Page<CustomerDTO> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerRepo.findAll(pageable);

        return customers.map(CustomerServiceImpl::convertToCustomerDTO);
    }
    @Override
    public boolean checkPassword(CustomerDTO customerDTO, String password) {
        return customerDTO.getPassword().equals(password);

    }


    @Override
    public Optional<CustomerDTO> getByEmail(String email) {
        Optional<Customer> customerOptional = customerRepo.findByEmail(email);
        return customerOptional.map(CustomerServiceImpl::convertToCustomerDTO);
    }

    //sort

    @Override
    public List<CustomerDTO> getAllCustomersWithMultiColumnSorting() {
        Sort sort = Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.asc("surname")
        );

        List<Customer> customers = customerRepo.findAll(sort);

        return customers.stream()
                .map(CustomerServiceImpl::convertToCustomerDTO)
                .collect(Collectors.toList());
    }
 //search
    @Override
    public List<CustomerDTO> searchCustomersByName(String keyword) {
        List<Customer> customers = customerRepo.findByNameContaining(keyword);

        return customers.stream().map(CustomerServiceImpl::convertToCustomerDTO).collect(Collectors.toList());
    }


}
