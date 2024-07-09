package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stati_fattura")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InvoiceState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stato_fattura")
    private Long id;

    @Column(name = "nome_stato_fattura")
    private String statusName;

    public InvoiceState(String status_name) {
        this.statusName = status_name;
    }
}
