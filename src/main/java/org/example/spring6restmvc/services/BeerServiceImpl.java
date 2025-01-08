package org.example.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import org.example.spring6restmvc.model.BeerDTO;
import org.example.spring6restmvc.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

       private  Map<UUID, BeerDTO> beerMap;

        public BeerServiceImpl() {
            this.beerMap = new HashMap<>();


            BeerDTO beerDTO1 = BeerDTO.builder()
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

            BeerDTO beerDTO2 = BeerDTO.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .beerName("Crank")
                    .beerStyle(BeerStyle.IPA)
                    .upc("145")
                    .price(new BigDecimal("11.00"))
                    .quantityOnHand(90)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            BeerDTO beerDTO3 = BeerDTO.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("156")
                    .price(new BigDecimal("12.45"))
                    .quantityOnHand(87)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            beerMap.put(beerDTO1.getId(), beerDTO1);
            beerMap.put(beerDTO2.getId(), beerDTO2);
            beerMap.put(beerDTO3.getId(), beerDTO3);
        }

        @Override
        public List<BeerDTO> listBeers() {
            return new ArrayList<>(beerMap.values());
        }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.debug("Get Beer Id - in service. Id:" + id.toString());
        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTo) {
            BeerDTO savedBeerDTO = BeerDTO.builder()
                    .id(UUID.randomUUID())
                    .updatedDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .quantityOnHand(beerDTo.getQuantityOnHand())
                    .price(beerDTo.getPrice())
                    .upc(beerDTo.getUpc())
                    .beerStyle(beerDTo.getBeerStyle())
                    .beerName(beerDTo.getBeerName())
                    .version(beerDTo.getVersion())
                    .build();
           beerMap.put(savedBeerDTO.getId(), savedBeerDTO);
        return savedBeerDTO;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTo) {
        BeerDTO existing = beerMap.get(beerId);
        existing.setBeerName(beerDTo.getBeerName());
        existing.setUpc(beerDTo.getUpc());
        existing.setPrice(beerDTo.getPrice());
        existing.setQuantityOnHand(beerDTo.getQuantityOnHand());

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        beerMap.remove(beerId);
        return true;
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beerDTo) {
        BeerDTO existing = beerMap.get(beerId);

        if (StringUtils.hasText(beerDTo.getBeerName())){
            existing.setBeerName(beerDTo.getBeerName());
        }

        if (beerDTo.getBeerStyle() != null) {
            existing.setBeerStyle(beerDTo.getBeerStyle());
        }

        if (beerDTo.getPrice() != null) {
            existing.setPrice(beerDTo.getPrice());
        }

        if (beerDTo.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beerDTo.getQuantityOnHand());
        }

        if (StringUtils.hasText(beerDTo.getUpc())) {
            existing.setUpc(beerDTo.getUpc());
        }
    }
}

