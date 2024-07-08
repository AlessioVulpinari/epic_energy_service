package clownfiesta.epic_energy_service.Repositories;

import clownfiesta.epic_energy_service.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<User, Long> {
    boolean
}
