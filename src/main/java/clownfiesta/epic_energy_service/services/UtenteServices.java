package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.User;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteServices {
    @Autowired
    UtenteRepo utenteRepo;

    public User getUtente(long id) {
        Optional<User> optionalUtent = utenteRepo.findById(id);

        if (optionalUtent.isPresent()) {
            return optionalUtent.get();
        } else {
            throw new NotFoundException("Utente con questo id non trovato");
        }
    }

    public User findByEmail(String email) {
        return utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

}
