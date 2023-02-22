package com.cancun.lasthotel.reservation.repository;

import com.cancun.lasthotel.reservation.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByReservationDateBetween(Date from, Date to);

    Optional<Reservation> findByReservationDate(Date date);
}
