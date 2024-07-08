package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    boolean existsByVatNumber(String vat_number);
    boolean existsByBusinessNameAndClientType(String businessName, ClientType clientType);
}
