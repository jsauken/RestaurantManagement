package com.example.restapi.service;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.DTO.ReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    Page<ReservationDTO> getAllReservationsPaged(int page, int size);
    ReservationDTO getById(long id);
    Page<ReservationDTO> searchReservationsByCustomerName(String keyword, int page, int size);
    List<ReservationDTO> searchReservationsByCustomerEmail(String customerEmail);
    Page<ReservationDTO> getAllReservationsWithMultiColumnSorting(int page, int size);

    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO updateReservation(long id, ReservationDTO updatedReservationDTO);
    void deleteReservation(long id);
}
