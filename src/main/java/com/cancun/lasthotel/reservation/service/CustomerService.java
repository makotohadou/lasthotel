package com.cancun.lasthotel.reservation.service;

import com.cancun.lasthotel.reservation.model.Customer;
import com.cancun.lasthotel.reservation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public boolean saveCustomer(String customerName){
        if (existsCustomer(customerName)){
            return false;
        }
        Customer customer = new Customer();
        customer.setName(customerName);
        repository.save(customer);
        return true;
    }

    private boolean existsCustomer(String customerName){
        return repository
                .findByName(customerName)
                .isPresent();
    }


}
