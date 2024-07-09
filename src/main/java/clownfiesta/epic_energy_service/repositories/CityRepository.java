package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
