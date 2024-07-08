package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    boolean existsByVat_number(long vat_number);
}
