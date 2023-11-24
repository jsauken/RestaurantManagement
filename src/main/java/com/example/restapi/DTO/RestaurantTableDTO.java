package com.example.restapi.DTO;

import lombok.Data;

@Data
public class RestaurantTableDTO {
    private int tableId;
    int tableNum;
    int seatNum;
    String status;
}
