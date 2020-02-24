package com.example.application.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.customer.Customer;
import com.example.domain.customer.CustomerRepository;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
