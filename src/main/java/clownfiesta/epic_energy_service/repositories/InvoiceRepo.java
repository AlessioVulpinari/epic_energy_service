package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.entites.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    boolean existsByNumberInvoice(long number_invoice);

    Optional<Invoice> findByNumberInvoice(long number_invoice);

    List<Invoice> findByCustomer(Customer customer);

    @Query("SELECT i FROM Invoice i WHERE i.customer.businessName = :customername")
    Page<Invoice> filterByCustomer(@Param("customername") String customerName, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceState.statusName = :status")
    Page<Invoice> filterByStatus(@Param("status") String status, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate = :date")
    Page<Invoice> filterByDate(@Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE YEAR(i.invoiceDate) = :year")
    Page<Invoice> filterByYear(@Param("year") int year, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE i.importInvoice <= :max AND i.importInvoice >= :min")
    Page<Invoice> filterByImport(@Param("max") double max, @Param("min") double min, Pageable pageable);
}
