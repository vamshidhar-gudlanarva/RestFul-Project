package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.Beer;
import org.example.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy cat")
                .beerStyle(BeerStyle.GOSE)
                .upc("122")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(120)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
