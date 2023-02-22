package com.cancun.lasthotel.reservation.model.json.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationOutput {
    private String customer;

    private String reservationCode;

    private List<Date> reservationDates;

}
