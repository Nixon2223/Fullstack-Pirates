package com.codeclan.example.pirateServiceM1.repositories;

import com.codeclan.example.pirateServiceM1.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PirateRepository extends JpaRepository<Pirate, Long> {

    List<Pirate> findPiratesByAgeGreaterThan(int age);

    List<Pirate> findByRaidsId(Long id);

}
