package org.example.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring6restmvc.model.CustomerDTO;
import org.example.spring6restmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    @Autowired
     private CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId,
                                            @RequestBody CustomerDTO customerDTO){

        customerService.patchCustomerById(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId){

    if(!customerService.deleteById(customerId)){
        throw new NotFoundException();
    }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {
       if(customerService.updateCustomerById(customerId, customerDTO).isEmpty()){
           throw new NotFoundException();
       }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

     @GetMapping(CUSTOMER_PATH)
     public List<CustomerDTO> listAllCustomers() {
         return customerService.getCustomers();
     }

     @GetMapping(value = CUSTOMER_PATH_ID)
     public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) {
         return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
     }

     @PostMapping(CUSTOMER_PATH)
     public ResponseEntity handleCustomer(@RequestBody CustomerDTO customerDTO) {
         CustomerDTO savedCustomerDTO = customerService.saveNewCustomer(customerDTO);
         HttpHeaders responseHeaders = new HttpHeaders();
         responseHeaders.add("Location", CUSTOMER_PATH + "/" + savedCustomerDTO.getId().toString());
         return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
     }



}
