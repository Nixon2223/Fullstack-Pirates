package com.codeclan.example.pirateServiceM1.repositories;

import com.codeclan.example.pirateServiceM1.models.Raid;
import com.codeclan.example.pirateServiceM1.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaidRepository extends JpaRepository<Raid, Long> {

    List<Raid> findRaidsByLocation(String name);

    List<Raid> findRaidsByPiratesShipId(Long id);

    List<Raid> findByPiratesShip(Optional<Ship> ship);
}