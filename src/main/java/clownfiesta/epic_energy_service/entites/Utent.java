package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utente implements UserDetails {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nikname;
    private String email;
    private String password;
    private String nome_utente;
    private String cognome_utente;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo;

    public Utente(String nikname, String email, String password, String nome_utente, String cognome_utente, String avatar, RuoloUtente ruolo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome_utente = nome_utente;
        this.cognome_utente = cognome_utente;
        this.avatar = avatar;
        this.ruolo = ruolo;
    }
