package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("vamshidhar")
                .version(2)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate((LocalDateTime.now())).build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("venu")
                .version(2)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now()).build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("hemanth")
                .version(2)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();

        customerMap = new HashMap<>();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }
}