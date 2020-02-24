package com.example.domain.customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findOne(Integer customerId);

    Customer save(Customer customer);

    void delete(Integer customerId);
}
