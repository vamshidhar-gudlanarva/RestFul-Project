package org.example.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.example.spring6restmvc.model.Beer;
import org.example.spring6restmvc.model.BeerStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

       private  Map<UUID, Beer> beerMap;

        public BeerServiceImpl() {
            this.beerMap = new HashMap<>();


            Beer beer1 = Beer.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .beerName("Galaxy cat")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("122")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(120)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer2 = Beer.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .beerName("Crank")
                    .beerStyle(BeerStyle.IPA)
                    .upc("145")
                    .price(new BigDecimal("11.00"))
                    .quantityOnHand(90)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            Beer beer3 = Beer.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("156")
                    .price(new BigDecimal("12.45"))
                    .quantityOnHand(87)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            beerMap.put(beer1.getId(), beer1);
            beerMap.put(beer2.getId(), beer2);
            beerMap.put(beer3.getId(), beer3);
        }

        @Override
        public List<Beer> listBeers() {
            return new ArrayList<>(beerMap.values());
        }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer Id - in service. Id:" + id.toString());
        return beerMap.get(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
            Beer savedBeer = Beer.builder()
                    .id(UUID.randomUUID())
                    .updatedDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .quantityOnHand(beer.getQuantityOnHand())
                    .price(beer.getPrice())
                    .upc(beer.getUpc())
                    .beerStyle(beer.getBeerStyle())
                    .beerName(beer.getBeerName())
                    .version(beer.getVersion())
                    .build();
           beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }
}

