package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Address;
import clownfiesta.epic_energy_service.enums.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    boolean existsByStreetAndLocality(String street, String locality);

    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId")
    List<Address> filterByCustomer(@Param("customerId") long customerId);

    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId AND a.locationTypes = :locationType")
    List<Address> filterByCustomerAndExistByLocationType(@Param("customerId") long customerId, @Param("locationType") LocationType locationType);
}
