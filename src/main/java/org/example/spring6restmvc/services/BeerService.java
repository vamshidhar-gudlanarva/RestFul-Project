package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    BeerDTO getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTo);

    void updateBeerById(UUID beerId, BeerDTO beerDTo);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beerDTo);
}
