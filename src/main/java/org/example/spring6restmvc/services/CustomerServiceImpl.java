package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .id(UUID.randomUUID())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existingCustomer = customerMap.get(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setVersion(customer.getVersion());
        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @Override
    public void deleteById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
    }
}
