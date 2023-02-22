package com.cancun.lasthotel.reservation.rest;

import com.cancun.lasthotel.reservation.model.json.in.ReservationInput;
import com.cancun.lasthotel.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/reservation")
public class ReservationControler {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/availability")
    public List<Date> getAvailability() {
        return reservationService.getAvailability();

    }

    @GetMapping("/{code}")
    public List<Date> getReservation(String code) {
        return null;

    }

    @PostMapping
    public ResponseEntity<Object> createReservation(@RequestBody ReservationInput reservation) {
        if (reservationService.createReservation(reservation)){
            return ResponseEntity
                    .ok()
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("You cannot make this reservation");
        }


    }

    @PutMapping("/{id}")
    public void updateReservation(@RequestBody List<Date> reservation) {

    }
}
