package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UtenteRequiredDTO(
        @NotEmpty(message = "Inserire un titolo eento")
        String nickname,
        @Email(message = "inserisci una mail")
        String email,
        @NotEmpty(message = "Inserire una descrizione evento")
        String password,
        @NotEmpty(message = "Inserire posti massimi evento")
        String nome_utente,
        @NotEmpty(message = "Inserire una data per l'evento")
        LocalDate cognome_utente
) {
}