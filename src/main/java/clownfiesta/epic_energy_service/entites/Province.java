package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initialDistrict;
    private String nameDistrict;
    private String regionDistrict;

    public Province(String initial_district, String name_district, String region_district) {
        this.initialDistrict = initial_district;
        this.nameDistrict = name_district;
        this.regionDistrict = region_district;
    }
}
