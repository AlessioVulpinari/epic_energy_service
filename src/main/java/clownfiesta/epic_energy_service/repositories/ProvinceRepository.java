package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    boolean existsByNameDistrict(String nameDistrict);

    Optional<Province> findByNameDistrict(String nameDistrict);
}
