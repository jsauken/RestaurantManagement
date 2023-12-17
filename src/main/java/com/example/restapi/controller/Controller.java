package com.example.restapi.controller;
import com.example.restapi.DTO.ReservationDTO;
import com.example.restapi.DTO.Translation;
import com.example.restapi.jwt.JwtAuthentication;
import com.example.restapi.service.AuthService;
import com.example.restapi.service.ReservationService;
import com.example.restapi.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.WebRequest;
import java.util.Locale;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class Controller {

    private final AuthService authService;
    private final ReservationService reservationService;
    private final TranslationService translationService;


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("customer/")
    public ResponseEntity<String> helloUser(WebRequest request) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        String greeting = getMessage("helloUser", request);
        return ResponseEntity.ok(greeting + " " + authInfo.getName() + "!");
    }

    @GetMapping("customer/reservation")
    public ResponseEntity<String> userReservation(WebRequest request) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        String userEmail = String.valueOf(authInfo.getPrincipal());

        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerEmail(userEmail);
        if (reservations != null && !reservations.isEmpty()) {
            String reservationsMessage = getMessage("userReservation", request);
            StringBuilder reservationDetails = new StringBuilder();
            reservationDetails.append(reservationsMessage).append(" ").append(userEmail).append(": \n");

            for (ReservationDTO reservation : reservations) {

                reservationDetails.append(getMessage("reservationId", request))
                        .append(" ")
                        .append(reservation.getId())
                        .append("\n ")
                        .append(getMessage("reservationTime", request))
                        .append(": ")
                        .append(reservation.getReservationTime())
                        .append("\n");
            }
            return ResponseEntity.ok(reservationDetails.toString());
        } else {
            String noReservationsMessage = getMessage("noReservations", request);
            return ResponseEntity.ok(noReservationsMessage + " " + userEmail);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<String> helloAdmin(WebRequest request) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        String greeting = getMessage("helloAdmin", request);
        return ResponseEntity.ok(greeting + " " + authInfo.getName() + "!");
    }

    private String getMessage(String key, WebRequest request) {
        Locale locale = request.getLocale();
        String translation = translationService.getTranslationByKeyAndLocale(key, locale);
        if (translation != null) {
            return translation;
        } else {
            return "Translation not found for key: " + key;
        }
    }

}
