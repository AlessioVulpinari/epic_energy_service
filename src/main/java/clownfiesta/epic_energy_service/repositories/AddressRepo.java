package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    boolean existsByStreetAndLocality(String street, String locality);
}
