package com.cancun.lasthotel.reservation.repository;

import com.cancun.lasthotel.reservation.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByReservationDateBetween(LocalDate from, LocalDate to);

    Optional<Reservation> findByReservationDate(LocalDate date);

    List<Reservation> findByCustomerName(String customerName);

    List<Reservation> findByReservationCode(String code);


}
