package com.example.restapi.DTO;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.DTO.RestaurantTableDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private long id;
    private RestaurantTableDTO tableReserved;
    private CustomerDTO customer;
    private WaiterDTO waiter;
    private int numberOfGuests;
    private LocalDateTime reservationTime;

}
