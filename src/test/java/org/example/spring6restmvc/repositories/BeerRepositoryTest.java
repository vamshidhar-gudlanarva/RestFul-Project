package org.example.spring6restmvc.repositories;

import org.example.spring6restmvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {

        Beer saveBeer = beerRepository.save(Beer.builder()
                .beerName("movk")
                .build());

       assertThat(saveBeer).isNotNull();
       assertThat(saveBeer.getId()).isNotNull();

    }
}