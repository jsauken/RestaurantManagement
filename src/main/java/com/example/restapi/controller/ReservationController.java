package com.example.restapi.controller;
import com.example.restapi.DTO.ReservationDTO;
import com.example.restapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@PreAuthorize("hasAuthority('ADMIN')")
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
        ResponseEntity<ReservationDTO> response;
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        if (createdReservation != null) {
            response = ResponseEntity.ok(createdReservation);
        } else {
            response = ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ReservationDTO());
        }

        return response;
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
    public ResponseEntity<Page<ReservationDTO>> getAllReservationsWithMultiColumnSorting(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReservationDTO> reservations  = reservationService.getAllReservationsWithMultiColumnSorting(page,size);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ReservationDTO>> searchReservations(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReservationDTO> reservations = reservationService.searchReservationsByCustomerName(keyword,page,size);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping()
    public ResponseEntity<Page<ReservationDTO>> getAllReservationsPaged(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReservationDTO> reservations = reservationService.getAllReservationsPaged(page, size);
        return ResponseEntity.ok(reservations);
    }
}
