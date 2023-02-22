package com.cancun.lasthotel.reservation.service;

import com.cancun.lasthotel.reservation.misc.DateUtil;
import com.cancun.lasthotel.reservation.model.Reservation;
import com.cancun.lasthotel.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public List<Date> getAvailability() {
        List<Date> reservations = repository
                .findByReservationDateBetween(DateUtil.getTomorrow(), DateUtil.getThirtyDaysFromNow())
                .stream()
                .map(Reservation::getReservationDate)
                .collect(Collectors.toList());

        return DateUtil.getNextThirtyDays()
                .stream()
                .filter(Predicate.not(reservations::contains))
                .collect(Collectors.toList());

    }
}
