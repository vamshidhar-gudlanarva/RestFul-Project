package org.example.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring6restmvc.model.BeerDTO;
import org.example.spring6restmvc.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final BeerService beerService;


    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTo){

        beerService.patchBeerById(beerId, beerDTo);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId){

        if(! beerService.deleteById(beerId)){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDTO beerDTo) {

       if( beerService.updateBeerById(beerId, beerDTo).isEmpty()){
           throw new NotFoundException();
       }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody BeerDTO beerDTo) {
        BeerDTO savedBeerDTO = beerService.saveNewBeer(beerDTo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",BEER_PATH + "/" + savedBeerDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = BEER_PATH)
     public List<BeerDTO> listBeers()
     {
         return beerService.listBeers();
     }



     @GetMapping(value = BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id- in controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }
}
