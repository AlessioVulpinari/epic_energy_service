package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ruolo")
@NoArgsConstructor
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruolo", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String name;

//    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.EAGER)
//    List<User> users;

    public UserRole(String name) {
        this.name = name;
    }
}
