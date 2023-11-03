package com.example.restapi.controller;

import com.example.restapi.model.Customer;
import com.example.restapi.model.Reservation;
import com.example.restapi.model.Table;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
/*
@GetMapping()
public ResponseEntity<Reservation> createReservation()
{
    Customer customer = new Customer(1,"Jasmin","Sauken");
    Table table = new Table(1,"free");
    Reservation reservation = new Reservation(table,customer,now());
    return ResponseEntity.status(HttpStatus.CREATED).header("Reserved by", customer.getName()+" "+ customer.getSurname())
            .body(reservation);
}



@PostMapping()
@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Reservation> showReservation(){
      // Customer customer = new Customer(1,"Jasmin","Sauken");
        Table table = new Table(1,"free");
        Reservation reservation = new Reservation(table,customer,now());
        return ResponseEntity.ok(reservation);

    }
*/
}
