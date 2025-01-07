package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerById(UUID id);
    List<Customer> getCustomers();

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);
}
