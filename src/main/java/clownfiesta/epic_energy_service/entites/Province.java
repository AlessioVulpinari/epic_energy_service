package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia", nullable = false)
    private Long id;

    @Column(name = "iniziali_provincia", nullable = false)
    private String initialDistrict;

    @Column(name = "nome_provincia", nullable = false)
    private String nameDistrict;

    @Column(name = "nome_regione", nullable = false)
    private String regionDistrict;

    public Province(String initial_district, String name_district, String region_district) {
        this.initialDistrict = initial_district;
        this.nameDistrict = name_district;
        this.regionDistrict = region_district;
    }
}
