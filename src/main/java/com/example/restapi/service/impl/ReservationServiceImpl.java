package com.example.restapi.service.impl;

import com.example.restapi.DTO.CustomerDTO;
import com.example.restapi.DTO.ReservationDTO;
import com.example.restapi.DTO.RestaurantTableDTO;
import com.example.restapi.DTO.WaiterDTO;
import com.example.restapi.Exceptions.ResourceNotFoundException;

import com.example.restapi.model.Customer;
import com.example.restapi.model.Reservation;

import com.example.restapi.model.Restaurant;
import com.example.restapi.model.Waiter;
import com.example.restapi.repository.ReservationRepo;
import com.example.restapi.service.CustomerService;
import com.example.restapi.service.ReservationService;
import com.example.restapi.service.RestaurantTableService;
import com.example.restapi.service.WaiterService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import static com.example.restapi.service.impl.CustomerServiceImpl.convertToCustomer;
import static com.example.restapi.service.impl.CustomerServiceImpl.convertToCustomerDTO;
import static com.example.restapi.service.impl.RestaurantTableServiceImpl.convertToTable;
import static com.example.restapi.service.impl.RestaurantTableServiceImpl.convertToTableDTO;
import static com.example.restapi.service.impl.WaiterServiceImpl.convertToWaiter;
import static com.example.restapi.service.impl.WaiterServiceImpl.convertToWaiterDTO;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private RestaurantTableService restaurantTableService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WaiterService waiterService;
    @PersistenceContext
    private EntityManager entityManager;

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
        return reservations.stream()
                .map(this::convertToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getById(long id) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "ID",  id));
        return convertToReservationDTO(reservation);
    }

    private ReservationDTO convertToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setTableReserved(convertToTableDTO(reservation.getTableReserved()));
        reservationDTO.setCustomer(convertToCustomerDTO(reservation.getCustomer()));
        reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
        reservationDTO.setReservationTime(reservation.getReservationTime());
        reservationDTO.setWaiter(convertToWaiterDTO(reservation.getAssignedWaiter()));
        return reservationDTO;
    }

    private Reservation convertToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setTableReserved(convertToTable(reservationDTO.getTableReserved()));
        reservation.setCustomer(convertToCustomer(reservationDTO.getCustomer()));
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setReservationTime(reservationDTO.getReservationTime());
        reservation.setAssignedWaiter(convertToWaiter(reservationDTO.getWaiter()));
        return reservation;
    }

    @Override
    @Transactional
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        int tableId = reservationDTO.getTableReserved().getTableId();
        RestaurantTableDTO table = restaurantTableService.getById(tableId);

        if (table != null && table.getStatus().equals("available")) {
            Customer customer = convertToCustomer(customerService.getById(reservationDTO.getCustomer().getCustomerId()));
            Waiter waiter = convertToWaiter(waiterService.getById(reservationDTO.getWaiter().getWaiterId()));

            if (customer != null && waiter != null) {
                Reservation reservation = convertToReservation(reservationDTO);
                reservation.setCustomer(customer);
                reservation.setAssignedWaiter(waiter);


                table.setStatus("booked");
                restaurantTableService.updateTable(table.getTableId(), table);

                Waiter mergedWaiter = entityManager.merge(waiter);
                reservation.setAssignedWaiter(mergedWaiter);

                reservationRepo.save(reservation);
                return convertToReservationDTO(reservation);
            } else {
                throw new ResourceNotFoundException("Customer or Waiter", "ID",
                        (long) ((customer == null) ? reservationDTO.getCustomer().getCustomerId() :
                                reservationDTO.getWaiter().getWaiterId()));
            }
        } else {
            throw new ResourceNotFoundException("Table", "ID", (long) tableId);
        }
    }


    @Override
    public List<ReservationDTO> searchReservationsByCustomerEmail(String customerEmail) {

        List<Reservation> reservationsByCustomer = reservationRepo.findByCustomerEmail(customerEmail);

        return reservationsByCustomer.stream()
                .map(this::convertToReservationDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ReservationDTO updateReservation(long id, ReservationDTO updatedReservationDTO) {
        Reservation existingReservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "ID",  id));

        existingReservation.setTableReserved(convertToTable(updatedReservationDTO.getTableReserved()));
        existingReservation.setCustomer(convertToCustomer(updatedReservationDTO.getCustomer()));
        existingReservation.setNumberOfGuests(updatedReservationDTO.getNumberOfGuests());
        existingReservation.setReservationTime(updatedReservationDTO.getReservationTime());
        existingReservation.setModifiedAt(LocalDateTime.now());
        existingReservation = reservationRepo.save(existingReservation);
        return convertToReservationDTO(existingReservation);
    }

    @Override
    public void deleteReservation(long id) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "ID",  id));
        reservationRepo.delete(reservation);
    }

    @Override
    public Page<ReservationDTO> getAllReservationsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservations = reservationRepo.findAll(pageable);

        return reservations.map(this::convertToReservationDTO);
    }
    @Override
    public Page<ReservationDTO> getAllReservationsWithMultiColumnSorting(int page, int size) {
        Sort sort = Sort.by(
                Sort.Order.asc("reservationTime"),
                Sort.Order.asc("numberOfGuests")
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Reservation> reservations = reservationRepo.findAll(pageable);
        return reservations.map(this::convertToReservationDTO);
    }

    @Override
    public Page<ReservationDTO> searchReservationsByCustomerName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservations = (Page<Reservation>) reservationRepo.findByCustomerNameContaining(keyword,pageable);
        return reservations.map(this::convertToReservationDTO);
    }
}




