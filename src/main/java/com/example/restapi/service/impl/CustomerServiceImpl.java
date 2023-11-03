package com.example.restapi.service.impl;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.repository.CustomerRepo;
import com.example.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        return null;
    }
    @Override
    public CustomerDTO createCustomer(CustomerDTO customer){
        return null;
    }

}
