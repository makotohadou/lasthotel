package com.cancun.lasthotel.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private Date reservationDate;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String reservationCode;


}
