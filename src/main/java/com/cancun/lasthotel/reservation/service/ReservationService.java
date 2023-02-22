package com.cancun.lasthotel.reservation.service;

import com.cancun.lasthotel.reservation.misc.DateUtil;
import com.cancun.lasthotel.reservation.model.Customer;
import com.cancun.lasthotel.reservation.model.Reservation;
import com.cancun.lasthotel.reservation.model.json.in.ReservationInput;
import com.cancun.lasthotel.reservation.model.json.in.ReservationInputUpdate;
import com.cancun.lasthotel.reservation.model.json.out.AvailabilityOutput;
import com.cancun.lasthotel.reservation.model.json.out.ReservationOutput;
import com.cancun.lasthotel.reservation.repository.CustomerRepository;
import com.cancun.lasthotel.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    public AvailabilityOutput getAvailability() {
        List<LocalDate> reservations = repository
                .findByReservationDateBetween(DateUtil.getTomorrow(), DateUtil.getThirtyDaysFromNow())
                .stream()
                .map(Reservation::getReservationDate)
                .collect(Collectors.toList());

        return new AvailabilityOutput(DateUtil.getNextThirtyDays()
                .stream()
                .filter(Predicate.not(reservations::contains))
                .collect(Collectors.toList()));
    }

    public boolean createReservation(ReservationInput reservationInput) {
        Optional<Customer> customer = customerRepository.findByName(reservationInput.getCustomer());
        List<LocalDate> reservationDates = DateUtil.getDaysBetweenDates(reservationInput.getStartDate(),
                reservationInput.getEndDate());

        if (customer.isEmpty()) {
            return false;
        }
        if (!canReserve(reservationDates,customer.get())){
            return false;
        }
        String reservationCode = UUID.randomUUID().toString();
        reservationDates
                .forEach(date -> {
                    Reservation reservation = new Reservation();
                    reservation.setReservationDate(date);
                    reservation.setCustomer(customer.get());
                    reservation.setReservationCode(reservationCode);
                    repository.save(reservation);
                });

        return true;
    }

    //TODO not allow reservations when another reservation of the customer would cause a continuous stay for more than
    //3 days
    private boolean canReserve(LocalDate date, Customer customer) {
        return repository.findByReservationDate(date).isEmpty();
    }

    private boolean canReserve(List<LocalDate> dates, Customer customer){
        if (dates.size() > 3){
            return false;
        }
        return dates.stream().allMatch(date -> canReserve(date,customer));
    }

    public List<ReservationOutput> findReservationsByCustomer(String customerName){
        List<ReservationOutput> reservationOutputs = new ArrayList<>();
        repository.findByCustomerName(customerName)
                .stream()
                .collect(Collectors.groupingBy(Reservation::getReservationCode))
                .forEach((code, reservations) -> {
                    ReservationOutput output = new ReservationOutput();
                    output.setCustomer(customerName);
                    output.setReservationCode(code);
                    output.setReservationDates(reservations
                            .stream()
                            .map(Reservation::getReservationDate)
                            .collect(Collectors.toList()));
                    reservationOutputs.add(output);
                });
        return reservationOutputs;
    }

    public ReservationOutput getReservationByCode(String code){
        List<Reservation> reservations = repository.findByReservationCode(code);
        if (reservations.isEmpty()){
            return null;
        }
        ReservationOutput reservationOutput = new ReservationOutput();
        reservationOutput.setReservationCode(code);
        reservationOutput.setCustomer(reservations
                .stream()
                .findAny()
                .map(Reservation::getCustomer)
                .map(Customer::getName)
                .orElse("Name"));
        reservationOutput.setReservationDates(reservations
                .stream()
                .map(Reservation::getReservationDate)
                .collect(Collectors.toList()));

        return reservationOutput;
    }

    public void deleteReservation(String code){
        repository.findByReservationCode(code)
                .forEach(reservation -> repository.delete(reservation));
    }

    @Transactional()
    public void updateReservation(String code, ReservationInputUpdate reservationInputUpdate){
        String customerName = repository.findByReservationCode(code)
                .stream()
                .findAny()
                .map(Reservation::getCustomer)
                .map(Customer::getName)
                .orElseThrow(()->new RuntimeException("Could not find Customer name"));
        deleteReservation(code);
        if (!createReservation(new ReservationInput(reservationInputUpdate,customerName))){
            throw new RuntimeException("Could not update reservation");
        }
    }
}
