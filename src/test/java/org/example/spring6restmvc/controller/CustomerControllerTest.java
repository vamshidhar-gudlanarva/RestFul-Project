package org.example.spring6restmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring6restmvc.model.Customer;
import org.example.spring6restmvc.services.CustomerService;
import org.example.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CustomerService customerService;
    CustomerServiceImpl customerServiceImpl;

    

    @Test
    void testUpdateCustomer() throws Exception {

        Customer customer = customerServiceImpl.getCustomers().get(0);

        mockMvc.perform(put(CustomerController.CUSTOMER_PATH+ "/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService).updateCustomerById(uuidArgumentCaptor.capture(), any(Customer.class));
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();

    }

    @Test
    void testCreateNewCustomer() throws Exception {
        Customer customer = customerServiceImpl.getCustomers().get(0);
        customer.setVersion(null);
        customer.setId(null);

        given(customerService.saveNewCustomer(any(Customer.class))).willReturn(customerServiceImpl.getCustomers().get(1));

        mockMvc.perform(post(CustomerController.CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void listAllCustomer() throws Exception {
        given(customerService.getCustomers()).willReturn(customerServiceImpl.getCustomers());
        mockMvc.perform(get(CustomerController.CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }


    @Test

    void getCustomerById() throws Exception {

        Customer testcustomer = customerServiceImpl.getCustomers().get(0);
        given(customerService.getCustomerById(testcustomer.getId())).willReturn(testcustomer);


        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID,  testcustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testcustomer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is(testcustomer.getCustomerName())));


    }
}
