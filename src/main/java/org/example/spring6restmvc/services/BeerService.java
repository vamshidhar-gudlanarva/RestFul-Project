package org.example.spring6restmvc.services;

import org.example.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTo);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTo);

    Boolean deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beerDTo);
}
