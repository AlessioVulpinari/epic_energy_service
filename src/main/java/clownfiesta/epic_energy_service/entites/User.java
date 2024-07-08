package clownfiesta.epic_energy_service.entites;

import clownfiesta.epic_energy_service.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utente")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nikname;
    private String email;
    private String password;
    private String nome_utente;
    private String cognome_utente;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String nikname, String email, String password, String nome_utente, String cognome_utente, String avatar, Role role) {
        this.nikname = nikname;
        this.email = email;
        this.password = password;
        this.nome_utente = nome_utente;
        this.cognome_utente = cognome_utente;
        this.avatar = avatar;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
