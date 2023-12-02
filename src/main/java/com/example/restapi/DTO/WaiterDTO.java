package com.example.restapi.DTO;

import lombok.Data;

@Data
public class WaiterDTO {
    private int waiterId;
    private String name;
    private int restaurantId;
}
