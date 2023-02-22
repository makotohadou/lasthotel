package com.cancun.lasthotel.reservation.rest;

import com.cancun.lasthotel.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public List<Date> getReservation(Integer id) {
        return null;

    }

    @PostMapping
    public void createReservation(@RequestBody List<Date> reservation) {

    }

    @PutMapping("/{id}")
    public void updateReservation(@RequestBody List<Date> reservation) {

    }
}
