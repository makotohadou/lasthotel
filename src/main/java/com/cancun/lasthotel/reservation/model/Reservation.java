package com.cancun.lasthotel.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne()
    private Customer customer;

    @Column
    private LocalDate reservationDate;

    @Column
    private String reservationCode;


}
