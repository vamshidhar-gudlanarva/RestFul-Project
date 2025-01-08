package org.example.spring6restmvc.repositories;

import jakarta.validation.ConstraintViolationException;
import org.example.spring6restmvc.entities.Beer;
import org.example.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, ()->{
            Beer saveBeer = beerRepository.save(Beer.builder()
                    .beerName("movk 464646449449884saeeavdavjhdajefjavfajhvevedjavjvdjahvdjavjdvajvfjhavjvjsvfvsfhcjsvdjsdvjhvsjdj")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("56556")
                    .price(new BigDecimal("15.89"))
                    .build());
            beerRepository.flush();
        });





    }

    @Test
    void testSaveBeer() {

        Beer saveBeer = beerRepository.save(Beer.builder()
                .beerName("movk")
                        .beerStyle(BeerStyle.GOSE)
                        .upc("56556")
                        .price(new BigDecimal("15.89"))
                .build());
        beerRepository.flush();

       assertThat(saveBeer).isNotNull();
       assertThat(saveBeer.getId()).isNotNull();

    }
}