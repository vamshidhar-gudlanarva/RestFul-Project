package org.example.spring6restmvc.mappers;

import org.example.spring6restmvc.entities.Beer;
import org.example.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDTO(Beer beer);

}
