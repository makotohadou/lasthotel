package com.cancun.lasthotel.reservation.rest;

import com.cancun.lasthotel.reservation.model.json.in.ReservationInput;
import com.cancun.lasthotel.reservation.model.json.in.ReservationInputUpdate;
import com.cancun.lasthotel.reservation.model.json.out.AvailabilityOutput;
import com.cancun.lasthotel.reservation.model.json.out.ReservationOutput;
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
    public AvailabilityOutput getAvailability() {
        return reservationService.getAvailability();
    }

    @GetMapping("/{code}")
    public ResponseEntity<ReservationOutput> getReservation(@PathVariable String code) {
        ReservationOutput reservationOutput = reservationService.getReservationByCode(code);
        if (reservationOutput != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reservationOutput);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createReservation(@RequestBody ReservationInput reservation) {
        if (reservationService.createReservation(reservation)) {
            return ResponseEntity
                    .ok()
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("You cannot make this reservation");
        }
    }

    @DeleteMapping("/{code}")
    public void deleteReservation(@PathVariable String code) {
        reservationService.deleteReservation(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<String>  updateReservation(@RequestBody ReservationInputUpdate reservation, @PathVariable String code) {
        try {
            reservationService.updateReservation(code, reservation);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (RuntimeException exception){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(exception.getMessage());
        }
    }
}
