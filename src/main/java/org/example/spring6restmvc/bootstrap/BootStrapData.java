package org.example.spring6restmvc.bootstrap;

import lombok.RequiredArgsConstructor;
import org.example.spring6restmvc.entities.Beer;
import org.example.spring6restmvc.entities.Customer;
import org.example.spring6restmvc.model.BeerDTO;
import org.example.spring6restmvc.model.BeerStyle;
import org.example.spring6restmvc.model.CustomerDTO;
import org.example.spring6restmvc.repositories.BeerRepository;
import org.example.spring6restmvc.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {

        loadBeerData();
        loadCustomerData();
    }

    private void loadBeerData() {
        if (beerRepository.count() == 0) {
            Beer beer1 = Beer.builder()
                    .beerName("Galaxy cat")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("122")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(120)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Crank")
                    .beerStyle(BeerStyle.IPA)
                    .upc("145")
                    .price(new BigDecimal("11.00"))
                    .quantityOnHand(90)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            Beer beer3 = Beer.builder()
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.GOSE)
                    .upc("156")
                    .price(new BigDecimal("12.45"))
                    .quantityOnHand(87)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now()).build();

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3);
        }
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {

            Customer customer1 = Customer.builder()
                    .customerName("vamshidhar")
                    .version(2)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate((LocalDateTime.now())).build();

            Customer customer2 = Customer.builder()
                    .customerName("venu")
                    .version(2)
                    .lastModifiedDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now()).build();

            Customer customer3 = Customer.builder()
                    .customerName("hemanth")
                    .version(2)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now()).build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }
    }
}
