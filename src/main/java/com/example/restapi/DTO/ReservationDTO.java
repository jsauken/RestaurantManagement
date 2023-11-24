package com.example.restapi.DTO;

import com.example.restapi.model.Customer;
import com.example.restapi.model.RestaurantTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private long id;
    private RestaurantTable tableReserved;
    private Customer customer;
    private int numberOfGuests;
    private LocalDateTime reservationTime;

}
