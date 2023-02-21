package com.cancun.lasthotel.reservation.repository;


import com.cancun.lasthotel.reservation.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public Optional<Customer> findByName(String name);
}
