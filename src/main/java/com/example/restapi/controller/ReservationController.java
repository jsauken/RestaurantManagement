package com.example.restapi.controller;
import com.example.restapi.DTO.ReservationDTO;
import com.example.restapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable long id) {
        ReservationDTO reservation = reservationService.getById(id);
        return ResponseEntity.ok(reservation);
    }


    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable long id, @RequestBody ReservationDTO updatedReservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(id, updatedReservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsWithMultiColumnSorting() {
        List<ReservationDTO> reservations = reservationService.getAllReservationsWithMultiColumnSorting();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReservationDTO>> searchReservations(
            @RequestParam(name = "keyword") String keyword) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerName(keyword);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ReservationDTO>> getAllReservationsPaged(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReservationDTO> reservations = reservationService.getAllReservationsPaged(page, size);
        return ResponseEntity.ok(reservations);
    }
}
