package com.codeclan.example.pirateServiceM1;

import com.codeclan.example.pirateServiceM1.models.Pirate;
import com.codeclan.example.pirateServiceM1.models.Raid;
import com.codeclan.example.pirateServiceM1.models.Ship;
import com.codeclan.example.pirateServiceM1.repositories.PirateRepository;
import com.codeclan.example.pirateServiceM1.repositories.RaidRepository;
import com.codeclan.example.pirateServiceM1.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PirateServiceM1ApplicationTests {

	@Autowired
	PirateRepository pirateRepository;


	@Autowired
	ShipRepository shipRepository;


	@Autowired
	RaidRepository raidRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createPirate(){
		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship);
		Pirate jack = new Pirate("Jack", "Sparrow", 32, ship);
		pirateRepository.save(jack);
	}

	@Test
	public void addPiratesAndRaids(){
		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship);

		Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
		pirateRepository.save(pirate1);

		Raid raid1 = new Raid("Tortuga", 100);
		raidRepository.save(raid1);

		raid1.addPirate(pirate1);
		raidRepository.save(raid1);
	}

	@Test
	public void canFindPiratesOver30(){
		List<Pirate> found = pirateRepository.findPiratesByAgeGreaterThan(30);
		assertEquals(9, found.size());
	}

	@Test
	public void canFindRaidsByLocation(){
		List<Raid> found = raidRepository.findRaidsByLocation("Havana");
		assertEquals("Havana", found.get(0).getLocation());
	}

	@Test
	public void canFindPiratesByRaidId(){
		List<Pirate> foundPirates = pirateRepository.findByRaidsId(1L);
		assertEquals(1, foundPirates.size());
		assertEquals("Jack", foundPirates.get(0).getFirstName());
	}

	@Test
	public void canFindShipsThatHavePirateWithGivenFirstName(){
		List<Ship> foundShips = shipRepository.findByPiratesFirstName("Jack");
		assertEquals(1, foundShips.size());
		assertEquals("The Black Pearl", foundShips.get(0).getName());
	}

	@Test
	public void canFindRaidsForShip(){
		List<Raid> foundRaids = raidRepository.findRaidsByPiratesShipId(2L);
		assertEquals(2, foundRaids.size());
	}

	@Test
	public void canFindAllRaidsForGivenShip(){
		Optional<Ship> foundShip = shipRepository.findById(2L);
		List<Raid> foundRaids = raidRepository.findByPiratesShip(foundShip);
		assertEquals(2, foundRaids.size());
	}
}
