package org.example.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring6restmvc.model.BeerDTO;
import org.example.spring6restmvc.services.BeerService;
import org.example.spring6restmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {



    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeerService beerService;
    BeerServiceImpl beerServiceImpl;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void testDeleteBeer() throws Exception {
        BeerDTO beerDTo = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(delete(BeerController.BEER_PATH + "/" + beerDTo.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(beerService).deleteById(uuidArgumentCaptor.capture());

       assertThat(beerDTo.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDTO beerDTo = beerServiceImpl.listBeers().get(0);
        mockMvc.perform(put(BeerController.BEER_PATH + "/" + beerDTo.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDTo)))
                        .andExpect(status().isNoContent());

        verify(beerService).updateBeerById(any(UUID.class), any(BeerDTO.class));
    }

    @Test
    void testCreateNewBeer() throws Exception {
        BeerDTO beerDTo = beerServiceImpl.listBeers().get(0);
        //System.out.println(objectMapper.writeValueAsString(beer));
        beerDTo.setVersion(null);
        beerDTo.setId(null);

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post(BeerController.BEER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTo)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

   @Test
   void testListBeers() throws Exception {
       given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

       mockMvc.perform(get(BeerController.BEER_PATH)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(3)));
   }

    @Test
    void getBeerByIdNotFound() throws Exception {

        given(beerService.getBeerById(any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get(BeerController.BEER_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }

    @Test
    void getBeerById() throws Exception {

        BeerDTO testBeerDTO = beerServiceImpl.listBeers().get(0);

        given(beerService.getBeerById(testBeerDTO.getId())).willReturn(testBeerDTO);

        mockMvc.perform(get(BeerController.BEER_PATH_ID, testBeerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeerDTO.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeerDTO.getBeerName())));

    }
}