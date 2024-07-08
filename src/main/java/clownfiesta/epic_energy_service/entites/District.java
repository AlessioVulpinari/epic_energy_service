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
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initial_district;
    private String name_district;
    private String region_district;

    public District(String initial_district, String name_district, String region_district) {
        this.initial_district = initial_district;
        this.name_district = name_district;
        this.region_district = region_district;
    }
}
