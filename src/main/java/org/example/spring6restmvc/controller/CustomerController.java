package org.example.spring6restmvc.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.spring6restmvc.model.Customer;
import org.example.spring6restmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
     private CustomerService customerService;

     @RequestMapping(method = RequestMethod.GET)
     public List<Customer> getCustomers() {
         return customerService.getCustomers();
     }

     @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
     public Customer getCustomerById(@PathVariable("customerId") UUID id) {
         return customerService.getCustomerById(id);
     }

}
