package com.cancun.lasthotel.reservation.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/api/reservation")
public class ReservationControler {

    @GetMapping("/availability")
    public List<Date> getAvailability(){
        return null;

    }

    @GetMapping("/{id}")
    public List<Date> getReservation(Integer id){
        return null;

    }

    @PostMapping
    public void createReservation(@RequestBody List<Date> reservation){

    }

    @PutMapping("/{id}")
    public void updateReservation(@RequestBody List<Date> reservation){

    }
}
