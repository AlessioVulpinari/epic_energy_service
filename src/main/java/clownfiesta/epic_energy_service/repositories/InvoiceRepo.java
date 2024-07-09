package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    boolean existsByNumberInvoice(long number_invoice);

    Optional<Invoice> findByNumberInvoice(long number_invoice);
}
