package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.User;
import clownfiesta.epic_energy_service.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    @Autowired
    private UtenteServices utenteServices;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String generateToken(UtenteLoginDto uld) {
        User utente = utenteServices.findByEmail(uld.email());

        if (bcrypt.matches(uld.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
