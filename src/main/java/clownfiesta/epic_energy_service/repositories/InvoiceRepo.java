package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    boolean existsByNumber_invoice(long number_invoice);
}
