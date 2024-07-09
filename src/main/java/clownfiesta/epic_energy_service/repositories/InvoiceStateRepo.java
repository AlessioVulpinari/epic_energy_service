package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.InvoiceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceStateRepo extends JpaRepository<InvoiceState, Long> {
    boolean existsByStatusName(String status_name);

    Optional<InvoiceState> findByStatusName(String status_name);


}
