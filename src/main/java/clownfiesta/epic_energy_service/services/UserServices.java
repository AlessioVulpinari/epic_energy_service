package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.User;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.payloads.UserRequiredDTO;
import clownfiesta.epic_energy_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserRequiredDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(user ->
        {throw new BadRequestException("Esiste già un utente con questa email: " + body.email());});

        this.userRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());});

        User newUser = new User(body.username(), body.email(),  passwordEncoder.encode(body.password()),  body.name(), body.surname());
        return userRepository.save(newUser);
    }

    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("Utente con questo id non trovato");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

}
