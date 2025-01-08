package org.example.spring6restmvc.repositories;

import org.example.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
