package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.enums.ClientType;
import clownfiesta.epic_energy_service.enums.LocationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByVatNumber(String vat_number);

    boolean existsByBusinessNameAndClientType(String businessName, ClientType clientType);

    @Query("SELECT c FROM Customer c ORDER BY businessName")
    Page<Customer> orderByBusinessName(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY annualTurnover")
    Page<Customer> orderByAnnualTurnover(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY insertionDate")
    Page<Customer> orderByInsertionDate(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY dateLastContact")
    Page<Customer> orderByDateLastContact(Pageable pageable);

    @Query("SELECT c FROM Customer c JOIN Address a ON c.id = a.customer.id WHERE a.locationTypes = :locationType ORDER BY a.cityResidence.district.nameDistrict")
    Page<Customer> orderByProvinceName(@Param("locationType") LocationType locationType, Pageable pageable);

    //////////////////////////////FILTRI777777777777777777777777777777

    @Query("SELECT c FROM Customer c WHERE c.annualTurnover = :turnover")
    Page<Customer> filterByTurnover(@Param("turnover") long turnover, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.insertionDate = :date")
    Page<Customer> filterByInsertionDater(@Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.dateLastContact = :date")
    Page<Customer> filterByLastContactDate(@Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE lower(c.businessName) LIKE lower(concat('%',:name,'%'))")
    Page<Customer> filterByName(@Param("name") String name, Pageable pageable);
}



