package com.cancun.lasthotel.reservation.model.json.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReservationInput {

    private String customer;

    private Date startDate;

    private Date endDate;


}
