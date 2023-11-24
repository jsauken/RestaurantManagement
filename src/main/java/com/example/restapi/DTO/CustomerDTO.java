package com.example.restapi.DTO;

import com.example.restapi.model.Role;
import lombok.Data;

@Data
public class CustomerDTO {
    private int customerId;
    private String name;
    private String surname;
    private long phoneNumber;
    private String email;
    private String password;
    private String roles;
}
