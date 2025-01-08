package org.example.spring6restmvc.mappers;

import org.example.spring6restmvc.entities.Customer;
import org.example.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoCustomer(CustomerDTO CustomerDTO);
    CustomerDTO customertoCustomer(Customer customer);
}
