package clownfiesta.epic_energy_service.repositories;

import clownfiesta.epic_energy_service.entites.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
}
