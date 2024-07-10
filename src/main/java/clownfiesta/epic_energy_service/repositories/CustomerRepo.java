package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.enums.ClientType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByVatNumber(String vat_number);

    @Query("SELECT c FROM Customer c ORDER BY businessName")
    Page<Customer> orderByBusinessName(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY annualTurnover")
    List<Customer> orderByAnnualTurnover();

    @Query("SELECT c FROM Customer c ORDER BY insertionDate")
    List<Customer> orderByInsertionDate();

    @Query("SELECT c FROM Customer c ORDER BY dateLastContact")
    List<Customer> orderByDateLastContact();

    boolean existsByBusinessNameAndClientType(String businessName, ClientType clientType);


}



