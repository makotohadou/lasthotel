package com.cancun.lasthotel.reservation.rest;

import com.cancun.lasthotel.reservation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/customer")
public class CustomerControler {

    @Autowired
    private CustomerService service;

    @PostMapping("/new")
    public ResponseEntity<String> saveCustomer(@RequestBody String customerName) {
        if (service.saveCustomer(customerName)){
            return ResponseEntity
                    .ok()
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Customer with this name already exists");
        }
    }

    @GetMapping("/reservations/{customerName}")
    public List<Date> getReservationByUser(String customerName) {
        return null;

    }
}