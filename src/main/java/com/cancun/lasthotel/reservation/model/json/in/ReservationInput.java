package com.cancun.lasthotel.reservation.model.json.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationInput {

    private String customer;

    private List<Date> reservationDates;



}
